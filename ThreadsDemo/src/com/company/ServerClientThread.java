package com.company;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class ServerClientThread extends Thread {
    Socket clientServer;
    int clientNo;
    int square;

    public ServerClientThread(Socket clientServer, int clientNo) {
        this.clientNo = clientNo;
        this.clientServer = clientServer;
    }

    public void run() {
        try {
            //Input I could use buffer reader, but I choose to go with DataInputStream without converting to use the BufferedReader
            DataInputStream inStream = new DataInputStream(clientServer.getInputStream());
            //Output
            DataOutputStream outStream = new DataOutputStream(clientServer.getOutputStream());

            String clientMessage="", serverMessage="";

            while(!clientMessage.equals("bye")) {
                clientMessage=inStream.readUTF();
                square = Integer.parseInt(clientMessage) * Integer.parseInt(clientMessage);
                serverMessage="From Server to Client-" + clientNo + " Square of " + clientMessage + " is " + square;
                outStream.writeUTF(serverMessage);
                outStream.flush();
            }

            inStream.close();
            outStream.close();
            clientServer.close();

            }catch (Exception e){
            System.out.println(e);
        }finally {
            System.out.println("Client -" + clientNo + " exit!! ");
        }
    }
}
