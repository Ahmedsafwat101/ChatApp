package com.company;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class TCPClientThread {
    public static void main(String[] args) throws  Exception {

        System.out.println(args[0]);
        Socket socket=new Socket("127.0.0.1",Integer.parseInt(args[0].trim()));
        //Input
        DataInputStream inStream = new DataInputStream(socket.getInputStream());
        //Output
        DataOutputStream outStream = new DataOutputStream(socket.getOutputStream());
        //Handel User Input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String clientMessage = "", serverMessage = "";

        while (!clientMessage.equals("bye")) {

            System.out.println("Enter number :");
            clientMessage=br.readLine();
            // send to server
            outStream.writeUTF(clientMessage);
            outStream.flush();

            //Read from server
            serverMessage=inStream.readUTF();
            System.out.println(serverMessage);
        }
        outStream.close();
        outStream.close();
        socket.close();
    }

    static void runTCPClient(String hos,int port) throws  Exception{

        System.out.println("TCP Client is Called");

        Socket socket=new Socket(hos,port);
        //Input
        DataInputStream inStream = new DataInputStream(socket.getInputStream());
        //Output
        DataOutputStream outStream = new DataOutputStream(socket.getOutputStream());
        //Handel User Input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String clientMessage = "", serverMessage = "";

        while (!clientMessage.equals("bye")) {
            System.out.println("TCP Client is in while");

            System.out.println("Say hi :");
            clientMessage=br.readLine();

            System.out.println(clientMessage);
            // send to server
            outStream.writeUTF(clientMessage);
            outStream.flush();

            System.out.println("lol");
            //Read from server
            serverMessage=inStream.readUTF();
            System.out.println(serverMessage);
        }
        outStream.close();
        outStream.close();
        socket.close();
    }
}
