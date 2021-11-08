package demo;

import java.io.IOException;

public class TestHelper {
    public static boolean openPortWithSpecificIPAndPort(String ip, int port) throws IOException {
        Server.setUpServerSocket(8833);
        Server.acceptConnection();
        Server.initializeWriter();
        Server.initializeClientReader();

        Client.setUpClientServer(ip, port);
        Client.initializeWriter();
        Client.initializeClientReader();

        return true;
    }

    public static boolean  clientSendMessage(String msg) throws IOException {
        Client.sendMessage(msg);
        return msg.equals(Server.in.readLine());
    }

    public static boolean serverReplyOnMessage(String msg) throws IOException {
        Server.sendMessage(msg);
        return msg.equals(Client.in.readLine());
    }

    public static boolean addNewClient(String ip, int port) throws IOException {
        Client.tearDownConnection();
        Client.setUpClientServer(ip, port);
        return true;
    }

}
