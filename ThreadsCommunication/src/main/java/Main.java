
import java.io.BufferedReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        List<String> input = new ArrayList<>();
        boolean isConnected = false;


        for (String arg : args) {
            System.out.println("Got argument [" + arg + "]");
            input.add(arg);
        }

        //My ServerHelper
        ServerSocket serverSocket;
        Socket clientSocket;
        BufferedReader in;
        Set<Integer> ports = new HashSet<>();



        System.out.printf("here");

        ServerHelper.setUpServerSocket(8888); // new

        //serverSocket = new ServerSocket(8888);

        ServerHelper.acceptConnection(); // new

       // clientSocket = serverSocket.accept();

        ServerHelper.initializeServerReader();  // new
        ServerHelper.initializeServerWriter(); //new

       // in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));


        String msgReceived;

        while (true) {
            if ((msgReceived = ServerHelper.in.readLine()) != null) {

                System.out.printf(msgReceived);
                String arr[] = new String[3];
                arr = msgReceived.split(" ");
                if (msgReceived.equals("**")) { //terminate
                    System.out.println("ClientHelper closed the channel ");
                    break;
                } else if ((arr[0].trim()).equals("con")) {
                    System.out.println("Client is connected " + arr[0] + arr[1]);
                    int port = Integer.parseInt(arr[1].trim());
                    if (!ports.contains(port)) {
                        System.out.println("herer0");

                        //make server
                        new Thread(() -> {
                            try {
                                System.out.println("Initialized new server with port " + port );

                                MultithreadedSocketServer.main(new String[]{"" + port});
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }).start();
                    }
                    ports.add(port);
                    //make client
                    new Thread(() -> {
                        try {
                            System.out.println("Initialized new Client with port " + port );
                            TCPClientThread.main(new String[]{"" + port});
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }).start();
                }
                System.out.println("Client  Says : " + msgReceived);
            }

        }
        //Close the Socket
        ServerHelper.tearDownConnection();
        //clientSocket.close();
        //serverSocket.close();
    }

}

