package pers.mtx.util.setting;


import lombok.Data;

@Data
public class RocketConfig {
    private String mqEndPoints;
    private String tag;
    private String topic;
    private String consumerGroup;
}
