import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServerHelper {

    static ServerSocket serverSocket;
    static Socket clientSocket;
    static PrintWriter out;
    static BufferedReader in;
    static Scanner sc = new Scanner(System.in);

    public static void setUpServerSocket(int portNumber) throws IOException {
        serverSocket = new ServerSocket(portNumber);
    }

    public static void acceptConnection() throws IOException {
        clientSocket = serverSocket.accept();
    }

    public static void initializeServerWriter() throws IOException {
        out = new PrintWriter(clientSocket.getOutputStream());
    }

    public static void initializeServerReader() throws IOException {
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    public static void sendMessage(String msg){
        out.println(msg);       // sending to server
        out.flush();
    }

    public static void tearDownConnection() throws IOException {
        serverSocket.close();
    }

}
