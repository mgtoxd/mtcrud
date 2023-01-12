package pers.mtx.service;

import lombok.Data;
import pers.mtx.connect.DataSourceImpl;
import pers.mtx.init.entity.Root;
import pers.mtx.util.IdWorker;
import pers.mtx.util.LogUtil;
import pers.mtx.util.ResultSetConvertUtil;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;


/**
 * AT 事务模式逻辑实现类
 */
public class AtServiceImpl {


    private final ConcurrentHashMap<Long, List<Map<String, String>>> beforeImage;   // key为事务id,value为 before image
    private final ConcurrentHashMap<String, Object> rowLock;    // 行锁，k为 数据库代码-表代码-rowid，v为空

    private final ConcurrentHashMap<Long, RowNum> rowData;  // 事务id对应事务操作的库，表，行

    private final ConcurrentHashMap<Long, List<Map<String, String>>> afterImage;    // key为事务id,value为 after image

    private static AtServiceImpl instance = new AtServiceImpl();

    public static AtServiceImpl getInstance() {
        if (!Objects.nonNull(instance)) {
            instance = new AtServiceImpl();
        }
        return instance;
    }

    private final Object SPACE;

    private final ConcurrentHashMap<String, Object> conflictLocation; // 冲突位置

    private final ReentrantLock lock;

    private final ConcurrentHashMap<String, Condition> conflictMap; // 冲突位置对应Condition解锁阻塞线程


    private AtServiceImpl() {
        SPACE = new Object();
        rowLock = new ConcurrentHashMap<>();
        afterImage = new ConcurrentHashMap<>();
        beforeImage = new ConcurrentHashMap<>();
        rowData = new ConcurrentHashMap<>();
        conflictLocation = new ConcurrentHashMap<>();
        conflictMap = new ConcurrentHashMap<>();
        lock = new ReentrantLock();
    }


    /**
     * 添加before image
     */
    public Long beforeImagePut(List<Map<String, String>> bfImageData, String DbName, String TbName) {
        long id = IdWorker.idWorker.nextId();
        RowNum rowNum = new RowNum(Integer.valueOf(DbName), Integer.valueOf(TbName));
        // 检查行锁
        for (int i = 0; i < bfImageData.size(); i++) {
            Map<String, String> row = bfImageData.get(i);
            // 判断行是否被锁
            if (rowLock.containsKey(DbName + "-" + TbName + "-" + row.get("_rowid"))) {
                // 拿不到行锁,设置行冲突位置
                conflictLocation.put(DbName + "-" + TbName + "-" + row.get("_rowid"), SPACE);
                // 如果自身第一个资源是冲突，就释放资源
                if (conflictLocation.containsKey(DbName + "-" + TbName + "-" + row.get("_rowid"))) {
                    // 释放资源
                    for (int j = 0; j < i; j++) {
                        rowLock.remove(DbName + "-" + TbName + "-" + bfImageData.get(j).get("_rowid"));
                    }
                    break;
                }
                // 第一个资源不冲突就添加Condition或者拿到Condition然后阻塞等待唤醒
                Condition condition = lock.newCondition();
                Condition alreadyCond = conflictMap.putIfAbsent(DbName + "-" + TbName + "-" + row.get("_rowid"), condition);
                if (Objects.isNull(alreadyCond)) {
                    try {
                        condition.await();
                        rowLock.put(DbName + "-" + TbName + "-" + row.get("_rowid"), SPACE);
                        rowNum.addRowId(row.get("_rowid"));
                        conflictLocation.remove(DbName + "-" + TbName + "-" + row.get("_rowid"));
                    } catch (InterruptedException e) {
                        LogUtil.getExceptionInfo(e);
                    }
                } else {
                    try {
                        alreadyCond.await();
                        rowLock.put(DbName + "-" + TbName + "-" + row.get("_rowid"), SPACE);
                        rowNum.addRowId(row.get("_rowid"));
                        conflictLocation.remove(DbName + "-" + TbName + "-" + row.get("_rowid"));
                    } catch (InterruptedException e) {
                        LogUtil.getExceptionInfo(e);
                    }
                }
            } else {
                rowLock.put(DbName + "-" + TbName + "-" + row.get("_rowid"), SPACE);
                rowNum.addRowId(row.get("_rowid"));
            }
        }
        // 行都被本事务锁住了
        beforeImage.put(id, bfImageData);
        rowData.put(id, rowNum);
        return id;
    }

    /**
     * after image 设置
     */
    public void afterImagePut(Long id, List<Map<String, String>> afImageData) {
        try {
            afterImage.put(id, afImageData);
        } catch (Exception e) {
            LogUtil.getExceptionInfo(e);
        }

    }

    /**
     * 确认事务，释放事务的锁，删除image
     */
    public boolean confirm(Long id) {
        try {
            beforeImage.remove(id);
            afterImage.remove(id);
            RowNum rowNum = rowData.get(id);
            rowNum.rowId.forEach(e -> {
                rowLock.remove(rowNum.getDataBase().toString() + "-" + rowNum.getTable().toString() + "-" + e);
                Condition condition = conflictMap.get(rowNum.getDataBase().toString() + "-" + rowNum.getTable().toString() + "-" + e);
                if (condition != null) {
                    condition.signal();
                }

            });

            return true;
        } catch (Exception e) {
            LogUtil.getExceptionInfo(e);
            return false;
        }
    }

    /**
     * 事务回滚，若当前与before image不符，需要转人工
     */
    public boolean rollback(Long id) {
        List<Map<String, String>> afterData = afterImage.get(id);
        List<Map<String, String>> beforeData = beforeImage.get(id);
        RowNum rowInfo = rowData.get(id);

        // 构造查询当前数据的语句
        StringBuilder builder = new StringBuilder("select *,_rowid from ");
        String nowDataSql = setSDISql(afterData, rowInfo, builder, beforeData);
        try (Statement statement = DataSourceImpl.getConnection().getConnect().createStatement()) {
            // 获取当前数据
            ResultSet resultSet = statement.executeQuery(nowDataSql);
            List<Map<String, String>> maps = ResultSetConvertUtil.convertListAndValueStr(resultSet);
            // 与 after data 排序后比较
            maps.sort(Comparator.comparingLong(res -> Long.parseLong(res.get("_rowid"))));
            afterData.sort(Comparator.comparingLong(res -> Long.parseLong(res.get("_rowid"))));
            // 行数不对直接false
            if (maps.size() == afterData.size()) {
                // 判断是否一致
                for (int i = 0; i < maps.size(); i++) {
                    boolean equals = maps.get(i).equals(afterData.get(i));
                    if (!equals) {
                        return false;
                    }
                }
                // 一致后，判断是否无效语句
                if (beforeData.size() == 0 && afterData.size() == 0) {
                    return true;
                }

                // 一致且有效，判断是否Insert
                if (beforeData.size() == 0) {
                    StringBuilder delBuilder = new StringBuilder("delete from ");
                    String delDataSql = setSDISql(afterData, rowInfo, delBuilder, beforeData);
                    try (Statement delstatement = DataSourceImpl.getConnection().getConnect().createStatement()) {
                        delstatement.executeUpdate(delDataSql);
                    } catch (Exception e) {
                        LogUtil.getExceptionInfo(e);
                    }
                    return true;
                }
                // 一致且有效，判断是否update
                if (beforeData.size() == afterData.size()) {
                    beforeData.forEach(e -> {
                        StringBuilder all = new StringBuilder("update ");
                        all.append(Root.getNodeName(rowInfo.dataBase))
                                .append(" . ")
                                .append(Root.getNodeName(rowInfo.table))
                                .append(" set ");
                        e.forEach((k, v) -> {
                            if (!k.equals("_rowid")) {
                                all.append(k).append("=").append(v).append(",");
                            }
                        });
                        all.delete(all.length() - 1, all.length());
                        all.append(" where ").append("_rowid=").append(e.get("_rowid"));
                        try (Statement upstatement = DataSourceImpl.getConnection().getConnect().createStatement()) {
                            upstatement.executeUpdate(all.toString());
                        } catch (Exception ee) {
                            LogUtil.getExceptionInfo(ee);
                        }
                    });
                    return true;
                }
                // 一致且有效，判断是否delete
                if (afterData.size() == 0) {
                    beforeData.forEach(e -> {
                        StringBuilder values = new StringBuilder();
                        StringBuilder colList = new StringBuilder();
                        StringBuilder all = new StringBuilder();
                        all.append("insert into ")
                                .append(Root.getNodeName(rowInfo.dataBase))
                                .append(".")
                                .append(Root.getNodeName(rowInfo.table));

                        e.forEach((key, value) -> {
                            if (!key.equals("_rowid")) {
                                colList.append(key).append(",");
                                values.append("'")
                                        .append(value)
                                        .append("',");
                            }
                        });
                        colList.delete(colList.length() - 1, colList.length());
                        values.delete(values.length() - 1, values.length());

                        all.append(" (").append(colList).append(")").append(" values ").append(" (")
                                .append(values).append(");");
                        try (Statement delstatement = DataSourceImpl.getConnection().getConnect().createStatement()) {
                            delstatement.executeUpdate(all.toString());
                        } catch (Exception ee) {
                            LogUtil.getExceptionInfo(ee);
                        }
                    });
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            LogUtil.getExceptionInfo(e);
        }
        rowInfo.rowId.forEach(e -> rowLock.remove(rowInfo.getDataBase().toString() + "-" + rowInfo.getTable().toString() + "-" + e));
        return false;
    }

    /**
     * 添加delete，insert和update语句当前数据检索sql
     */
    private static String setSDISql(List<Map<String, String>> afterData, RowNum rowInfo, StringBuilder builder, List<Map<String, String>> beforeData) {
        builder.append(Root.getNodeName(rowInfo.dataBase))
                .append(".")
                .append(Root.getNodeName(rowInfo.table)).append(" where ");
        if (afterData.size() == 0) {
            // 为delete
            beforeData.stream().map(e -> e.get("_rowid")).forEach(e -> {
                builder.append("_rowid = ");
                builder.append(e);
                builder.append("or");
            });
        } else {
            afterData.stream().map(e -> e.get("_rowid")).forEach(e -> {
                builder.append("_rowid = ");
                builder.append(e);
                builder.append("or");
            });
        }
        builder.delete(builder.length() - 2, builder.length());
        return builder.toString();


    }


    /**
     * 事务对应的锁
     */
    @Data
    private static class RowNum {
        private Integer dataBase;
        private Integer table;
        private List<Object> rowId;

        public RowNum(Integer dataBase, Integer table) {
            this.dataBase = dataBase;
            this.table = table;
            this.rowId = new ArrayList<>();
        }

        public void addRowId(Object rowId) {
            this.rowId.add(rowId);
        }
    }

}
