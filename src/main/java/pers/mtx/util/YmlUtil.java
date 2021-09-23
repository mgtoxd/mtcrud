package pers.mtx.util;

import org.yaml.snakeyaml.Yaml;
import pers.mtx.util.setting.Setting;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class YmlUtil {
    protected YmlUtil() {
    }

    private static Setting setting;

    static {
        try {
            setting = new Yaml().loadAs(new FileInputStream(System.getProperty("user.dir")+"/setting.yaml"),Setting.class);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Setting getSetting(){
        return setting;
    }
}
