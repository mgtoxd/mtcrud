package pers.mtx.init.entity;

import lombok.Data;

import java.util.ArrayList;

@Data
public abstract class INode {
    public abstract String mgetMark();
    public abstract ArrayList<? extends INode> mgetList();

    public int rank;
}
