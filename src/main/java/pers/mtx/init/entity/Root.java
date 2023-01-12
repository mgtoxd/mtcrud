package pers.mtx.init.entity;

import lombok.Data;

import java.util.ArrayList;


/**
 * 存储所有数据库结构的根节点
 */
@Data
public class Root {
    private static ArrayList<Db> DataStructure= new ArrayList<>();
    private static ArrayList<INode> nodeArrayList = new ArrayList<>();
    private static Root root = new Root();

    private Root(){}
    public static ArrayList<INode> getNodeArrayList(){
        return nodeArrayList;
    }
    public ArrayList<Db> getDataStructure(){
        return DataStructure;
    }
    public static Root getRoot() {
        return root;
    }
    public static String getNodeName(Integer rank){
        for (INode iNode : nodeArrayList) {
            if (rank == iNode.rank) return iNode.mgetMark();
        }
        return null;
    }

    public static String getColType(Integer rank){
        for (INode iNode : nodeArrayList) {
            if (rank == iNode.rank) return ((Col) iNode).getDataType();
        }
        return null;
    }

}
