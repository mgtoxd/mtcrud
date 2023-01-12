package pers.mtx.init.entity;

import lombok.Data;

import java.util.ArrayList;

/**
 * 所有数据库结构类的父类
 */
@Data
public abstract class INode {
    /**
     * 获取节点名称
     * @return 节点名
     */
    public abstract String mgetMark();

    public int rank;
}
