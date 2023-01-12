package pers.mtx.util.setting;

import lombok.Data;

@Data
public class Server {
    private int port;
    private int grpc_port;
}
