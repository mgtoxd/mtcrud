package pers.mtx.util.setting;

@lombok.Data
public class Data {
    private String url;
    private String username;
    private String password;
    private int initSize;
    private int maxSize;
    private int stepSize;
    private int timeout;
}