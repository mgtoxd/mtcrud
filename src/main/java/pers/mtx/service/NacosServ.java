package pers.mtx.service;

import com.alibaba.nacos.api.naming.NamingFactory;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import pers.mtx.util.LogUtil;
import pers.mtx.util.YmlUtil;
import pers.mtx.util.setting.NacosConfig;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.*;

public class NacosServ {


    public static void InitNacos(){
        try {
            NacosConfig nacosConfig = YmlUtil.getSetting().getNacos();
            String serverAddr = nacosConfig.getNacosIp() + ":" + nacosConfig.getNacosPort();
            Properties properties = new Properties();
            properties.setProperty("serverAddr", serverAddr);
            properties.setProperty("namespace", nacosConfig.getNameSpace());

            List<String> ipAddrs = getIpAddr(nacosConfig.getNetworkCardName());
            for (String addr :
                    ipAddrs) {
                Instance instance = new Instance();
                instance.setIp(addr);//IP
                instance.setPort(YmlUtil.getSetting().getServer().getGrpc_port());//端口
                instance.setEnabled(true);//true: 上线 false: 下线
                instance.setHealthy(true);//健康状态
                instance.setWeight(1.0);//权重
                NamingService naming = NamingFactory.createNamingService(properties);
                naming.registerInstance(nacosConfig.getServName()+"_grpc", instance);
            }


        }catch (Exception e){
            LogUtil.getExceptionInfo(e);
        }
    }

    public static List<String> getIpAddr(String netName) {
        List<String> list = new LinkedList<>();
        try {
            Enumeration enumeration = NetworkInterface.getNetworkInterfaces();
            while (enumeration.hasMoreElements()) {
                NetworkInterface network = (NetworkInterface) enumeration.nextElement();

                if (network.getDisplayName().equals(netName)){
                    Enumeration addresses = network.getInetAddresses();
                    while (addresses.hasMoreElements()) {
                        InetAddress address = (InetAddress) addresses.nextElement();
                        if (address != null && (address instanceof Inet4Address)) {
                            list.add(address.getHostAddress());
                        }
                    }
                }
            }

        }catch (Exception e){
            LogUtil.getExceptionInfo(e);
        }
        return list;
    }
}

