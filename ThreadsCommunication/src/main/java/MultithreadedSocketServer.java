import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MultithreadedSocketServer {

    public static void main(String[] args) throws Exception {
        //ServerSocket serverSocket = new ServerSocket(8888);
        ServerSocket serverSocket = new ServerSocket(Integer.parseInt(args[0].trim()));


        int counter = 0;

        //Handel User Input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("TCP ServerHelper is started... ");
        String clientMessage = "";

        while (true) {

            //ServerHelper accept client connection
            Socket clientServer = serverSocket.accept();
            counter++;
            System.out.println("Client " + counter + " is connected...");
            //ServerClientThread is a custom class for handling client threads
            ServerClientThread serverClientThread = new ServerClientThread(clientServer, counter);
            serverClientThread.start(); //  to start thread
        }
    }
}
