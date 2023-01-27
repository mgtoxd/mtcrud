package pers.mtx.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import pers.mtx.connect.DataSourceImpl;
import pers.mtx.util.JsonUtil;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomServ {

    private static CustomServ ins = new CustomServ();

    public static CustomServ getInstance() {
        return ins;
    }

    private CustomServ() {

    }

    /**
     * 执行自定义sql
     */
    public String executeCustomSql(String originalSql, byte[] content) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            HashMap hashMap = mapper.reader().readValue(content, HashMap.class);
            ArrayList<String> strings = parseCustomSql(originalSql);
            PreparedStatement prepareStatement = DataSourceImpl.getConnection().getConnect().prepareStatement(strings.get(strings.size() - 1));
            for (int i = 0; i < strings.size() - 1; i++) {
                prepareStatement.setString(i+1, hashMap.get(strings.get(i)).toString());
            }
            if (originalSql.toLowerCase().startsWith("select")) {
                // 是查询
                ResultSet resultSet = prepareStatement.executeQuery();
                return JsonUtil.resultSetToJson(resultSet);
            } else {
                int i = prepareStatement.executeUpdate();
                return String.valueOf(i);

            }


        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }


    }

    /**
     * 解析sql语句，替换变量为问号
     *
     * @param originalSql
     * @return 参数名顺序，最后一个为？替换的sql语句
     */
    private ArrayList<String> parseCustomSql(String originalSql) {
        ArrayList<String> list = new ArrayList<>();
        Pattern compile = Pattern.compile("\\{.*?}");
        Matcher matcher = compile.matcher(originalSql);
        while (matcher.find()){
            list.add(originalSql.substring(matcher.start()+1,matcher.end()-1));
        }
        list.add(originalSql.replaceAll("\\{.*?}", "?"));
        return list;
    }


}
