import java.io.IOException;
import java.util.HashSet;

public class TestHelper {
    public static boolean openPortWithSpecificIPAndPort(String ip, int port) throws IOException {
        Server.setUpServerSocket(port);
        Client1.setUpClientServer(ip, port);
        Server.acceptConnection();
        Client1.initializeWriter();
        Server.initializeServerReader();
        Server.initializeServerWriter();
        Client1.initializeClientReader();

        return true;
    }

    public static boolean  clientSendMessage(String msg) throws IOException {
        Client1.sendMessage(msg);
        return msg.equals(Server.in.readLine());
    }

    public static boolean serverReplyOnMessage(String msg) throws IOException {
        Server.sendMessage(msg);
        return msg.equals(Client1.in.readLine());
    }

    public static boolean closeConnection() throws IOException {
        Client1.tearDownConnection();
        Server.tearDownConnection();
        return true;
    }
    public static boolean addNewClient(String ip, int port) throws IOException {
        Client1.tearDownConnection();
        Client1.setUpClientServer(ip, port);
        return true;
    }


    public static boolean setUp(String port) throws Exception {

        HashSet<Integer> ports = new HashSet<>();
        int portNum = Integer.parseInt(port.trim());
        if(!ports.contains(portNum)) {
            new Thread(() -> {
                try {
                    ports.add(portNum);
                    MultithreadedSocketServer.main(new String[]{"" + port});
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }).start();

        }else{

        }
        new Thread(() -> {
            try {
                TCPClientThread.main(new String[]{""+port});
            } catch (Exception e) {
                e.printStackTrace();
            }

        }).start();




        return true;
    }
}
