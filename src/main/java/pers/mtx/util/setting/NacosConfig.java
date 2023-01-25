package pers.mtx.util.setting;

import lombok.Data;

@Data
public class NacosConfig {

    private String nacosIp;

    private String nacosPort;

    private String servName;

    private String nameSpace;

    private String networkCardName;

}
