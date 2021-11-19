import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.security.PublicKey;
import java.util.Scanner;

public class Client1 {

    static Socket clientSocket;
    static PrintWriter out;
    static BufferedReader in;
    static Scanner sc = new Scanner(System.in);
    static int number = 0;



    public static void setUpClientServer(String host, int portNumber) throws IOException {
        clientSocket = new Socket(host, portNumber);
    }


    public static void initializeWriter() throws IOException {
        out = new PrintWriter(clientSocket.getOutputStream());
    }

    public static void initializeClientReader() throws IOException {
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    public static void sendMessage(String msg) {
        out.println(msg);       // sending to server
        out.flush();
    }

    public static void tearDownConnection() throws IOException {
        clientSocket.close();
        in.close();
        out.close();
    }
}
