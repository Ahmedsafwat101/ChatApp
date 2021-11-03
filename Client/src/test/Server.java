package test;

import java.io.IOException;
import java.net.ServerSocket;

public class Server {

    public static ServerSocket createServerSocket( int port) throws  IOException {
        return new ServerSocket(port);
    }

}
