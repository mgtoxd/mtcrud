package pers.mtx.util;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * 日志工具，输出错误
 */
public class LogUtil {
    public static String getExceptionInfo(Exception e) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        e.printStackTrace(new PrintStream(baos));
        System.out.println(baos);
        return baos.toString();
    }
}
