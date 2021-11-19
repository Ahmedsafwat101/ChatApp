import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

    public static void main(String[] args) throws Exception {
        Socket clientSocket;
        PrintWriter out;
        BufferedReader in;

        Client1.setUpClientServer("127.0.0.1", 8888);
        //clientSocket = new Socket("127.0.0.1", 8888);
        Client1.initializeWriter();
       // out = new PrintWriter(clientSocket.getOutputStream());
        //Reading from KeyBoard
        in = new BufferedReader(new InputStreamReader(System.in));
        String sendMessage;
        while (true) {

            sendMessage = in.readLine();  // keyboard reading
            Client1.sendMessage(sendMessage);
           // out.println(sendMessage);       // sending to server
            //out.flush();                    // flush the
        }

    }

    void callOnRandomPortsNumber(){
        int[]ports =  new int[]{8833,8877,8899,8877,8833,8866};
    }
}


