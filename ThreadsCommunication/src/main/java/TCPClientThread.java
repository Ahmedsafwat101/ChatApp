
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Random;

public class TCPClientThread {
    public static void main(String[] args) throws Exception {


        System.out.println(args[0]);
        Socket socket = new Socket("127.0.0.1", Integer.parseInt(args[0].trim()));

        //Input
        DataInputStream inStream = new DataInputStream(socket.getInputStream());
        //Output
        DataOutputStream outStream = new DataOutputStream(socket.getOutputStream());
        //Handel User Input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String clientMessage = "Send Hello", serverMessage = "";

        while (!clientMessage.equals("bye")) {
            System.out.println("Use command `Send + Any Message` to send a message\n or Reply");

            String currClientMessage = br.readLine();
            if(currClientMessage!=null)
                clientMessage = currClientMessage;

          //  System.out.printf("messge is " + clientMessage);
            String[] messageArray = clientMessage.split(" ");
            if (messageArray[0].equals("Send")) {
                // send to server
                outStream.writeUTF(messageArray[1]);
                outStream.flush();


                serverMessage = inStream.readUTF();
                System.out.println(serverMessage);

            } else if (messageArray[0].equals("Reply")) {
                //Read from server
                serverMessage = inStream.readUTF();
                System.out.println(serverMessage);
            }


        }
        outStream.close();
        socket.close();
    }
}
