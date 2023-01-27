package pers.mtx.util;

import org.yaml.snakeyaml.Yaml;
import pers.mtx.util.setting.Setting;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;


/**
 * 加载YML
 */
public class YmlUtil {
    protected YmlUtil() {
    }

    private static Setting setting;
    private static HashMap sql;

    static {
        try {
            setting = new Yaml().loadAs(new FileInputStream(System.getProperty("user.dir")+"/setting.yaml"),Setting.class);
            if (setting.getCustomSql()){
                sql = new Yaml().loadAs(new FileInputStream(System.getProperty("user.dir")+"/sql.yaml"),HashMap.class);
                System.out.println("扫描到自定义sql"+sql.size()+"条");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Setting getSetting(){
        return setting;
    }

    public static HashMap<String ,String > getSql() {
        if (setting.getCustomSql()){
            return sql;
        }
        return null;
    }
}
