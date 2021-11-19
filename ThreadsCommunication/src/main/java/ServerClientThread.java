import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class ServerClientThread extends Thread {
    Socket clientServer;
    int clientNo;

    public ServerClientThread(Socket clientServer, int clientNo) {
        this.clientNo = clientNo;
        this.clientServer = clientServer;
    }

    public void run() {
        try {
            //Input
            DataInputStream inStream = new DataInputStream(clientServer.getInputStream());
            //Output
            DataOutputStream outStream = new DataOutputStream(clientServer.getOutputStream());

            String clientMessage="", serverMessage="";

            while(!clientMessage.equals("bye")) {
                clientMessage=inStream.readUTF();

                System.out.println("Server: Received Message from "+clientNo+" is : "+ clientMessage);

                // square = Integer.parseInt(clientMessage) * Integer.parseInt(clientMessage);
                serverMessage="From Server to Client-" + clientNo + " is "+ clientMessage ;
                outStream.writeUTF(serverMessage);
                outStream.flush();
            }

            inStream.close();
            outStream.close();
            clientServer.close();

            }catch (Exception e){
            System.out.println(e);
        }finally {
            System.out.println("Client1 -" + clientNo + " exit!! ");
        }
    }
}
