package pers.mtx.init.entity;

import com.sun.istack.internal.Nullable;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Root {
    private static ArrayList<Db> DataStructure=new ArrayList<Db>();
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
        for (int i = 0; i < nodeArrayList.size(); i++) {
            if (rank==nodeArrayList.get(i).rank) return nodeArrayList.get(i).mgetMark();
        }
        return null;
    }

    public static String getColType(Integer rank){
        for (int i = 0; i < nodeArrayList.size(); i++) {
            if (rank==nodeArrayList.get(i).rank) return ((Col)nodeArrayList.get(i)).getDataType();
        }
        return null;
    }

}
