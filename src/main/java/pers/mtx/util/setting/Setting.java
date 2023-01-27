package pers.mtx.util.setting;

@lombok.Data
public class Setting {
    private Data data;
    private Server server;
    private RocketConfig rocketmq;

    private NacosConfig nacos;

    private Boolean customSql;
}


