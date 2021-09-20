package pers.mtx.init;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.util.JSONPObject;
import pers.mtx.connect.DataSourceImpl;
import pers.mtx.connect.PoolConnection;
import pers.mtx.format.JsonUtil;
import pers.mtx.init.entity.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class DataStructure {
    private ObjectMapper mapper;

    private ArrayList<String> defaultSchemaNameList;
    private PoolConnection poolConnection;

    private Connection connect;


    {
        System.out.println("aaaaa");
        poolConnection = DataSourceImpl.getConnection();
        connect =poolConnection.getConnect();
        mapper = new ObjectMapper();
        defaultSchemaNameList = new ArrayList<String>(){{
            add("mysql");
            add("information_schema");
            add("performance_schema");
            add("sys");
        }};
    }

    public void getDataStructure() throws Exception {
        System.out.println("adaaa");
        //获取数据库名称列表
        PreparedStatement statement = connect.prepareStatement("select TABLE_SCHEMA as db,TABLE_NAME as tb,COLUMN_NAME as col,DATA_TYPE as data from information_schema.COLUMNS where TABLE_SCHEMA in (select SCHEMA_NAME from information_schema.SCHEMATA where SCHEMA_NAME != 'mysql' and SCHEMA_NAME !='information_schema' and SCHEMA_NAME != 'performance_schema' and SCHEMA_NAME != 'sys')");
        ResultSet resultSet = statement.executeQuery();
        Root root = Root.getRoot();
        AtomicInteger rank = new AtomicInteger();
        while (resultSet.next()){
            String db = resultSet.getString("db");
            String tb = resultSet.getString("tb");
            String col = resultSet.getString("col");
            String data = resultSet.getString("data");
            ArrayList<Db> list = root.getDataStructure();
            INode dbObject = getDbObject(list, db);
            if (dbObject!=null){
                INode tbObject = getDbObject(((Db) dbObject).getTables(), tb);
                if (tbObject!=null){
                    Col col1 = new Col(col, data, rank.getAndIncrement());
                    Collections.addAll(Root.getNodeArrayList(),col1);
                    ((Tb) tbObject).getCols().add(col1);
                }else {
                    Tb tb1 = new Tb(tb,rank.getAndIncrement());
                    Col col1 = new Col(col, data, rank.getAndIncrement());
                    Collections.addAll(Root.getNodeArrayList(),tb1,col1);
                    tb1.getCols().add(col1);
                    ((Db) dbObject).getTables().add(tb1);
                }
            }else {
                Db db1 = new Db(db,rank.getAndIncrement());
                Tb tb1 = new Tb(tb,rank.getAndIncrement());
                Col col1 = new Col(col, data, rank.getAndIncrement());
                tb1.getCols().add(col1);
                db1.getTables().add(tb1);
                root.getDataStructure().add(db1);
                Collections.addAll(Root.getNodeArrayList(),db1,tb1,col1);
            }
        }
        System.out.println("dwada");
        System.out.println(mapper.writeValueAsString(root));
        System.out.println();

        BufferedWriter out = new BufferedWriter(new FileWriter("./DataStructure.json"));
        out.write(JsonUtil.formatJson(mapper.writeValueAsString(root)));
        out.close();
        System.out.println("文件创建成功！");
        poolConnection.releaseConnect();
    }

    /**
     * 判断是否存在该INode，存在则返回已有的，不存在返回null对象
     * @param list INode列表
     * @param mark
     * @return
     */
    public INode getDbObject(ArrayList<? extends INode> list, String mark) throws Exception {
        if (list==null) return null;
        return list.stream().filter(e -> e.mgetMark().equals(mark)).findAny().orElse(null);
    }



}
