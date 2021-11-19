import java.io.IOException;
import java.util.HashSet;

public class TestHelper {
    public static boolean openPortWithSpecificIPAndPort(String ip, int port) throws IOException {
        ServerHelper.setUpServerSocket(port);
        ClientHelper.setUpClientServer(ip, port);
        ServerHelper.acceptConnection();
        ClientHelper.initializeWriter();
        ServerHelper.initializeServerReader();
        ServerHelper.initializeServerWriter();
        ClientHelper.initializeClientReader();

        return true;
    }

    public static boolean  clientSendMessage(String msg) throws IOException {
        ClientHelper.sendMessage(msg);
        return msg.equals(ServerHelper.in.readLine());
    }

    public static boolean serverReplyOnMessage(String msg) throws IOException {
        ServerHelper.sendMessage(msg);
        return msg.equals(ClientHelper.in.readLine());
    }

    public static boolean closeConnection() throws IOException {
        ClientHelper.tearDownConnection();
        ServerHelper.tearDownConnection();
        return true;
    }
    public static boolean addNewClient(String ip, int port) throws IOException {
        ClientHelper.tearDownConnection();
        ClientHelper.setUpClientServer(ip, port);
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
