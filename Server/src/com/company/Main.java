package com.company;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class Main {

    public static void main(String[] args) {
	  //My Server
        ServerSocket  serverSocket;
        Socket clientSocket;
        BufferedReader in;
        OutputStream output;
        PrintWriter out;

        try {

            serverSocket = new ServerSocket(8833);
            clientSocket = serverSocket.accept();
            in =  new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new PrintWriter(clientSocket.getOutputStream());


            String msgReceived;

            while (true){
                System.out.println("in");
                if((msgReceived = in.readLine()) != null){
                    if(msgReceived.equals("**")) { //terminate
                        System.out.println("Client closed the channel ");
                        break;
                    }

                   else if(msgReceived.contains("SEND")){
                        System.out.println("here");

                        int start_index = msgReceived.indexOf("SEND");
                        String msg =msgReceived.substring(start_index+4).trim();
                        System.out.println("Client Says: " + msg);
                        System.out.println("here in");

                    }
                   else if(msgReceived.equals("WHO")){
                       //Send IP to the client
                        System.out.println("Ds");
                        out.println(clientSocket.getInetAddress());       // sending to server
                        out.flush();
                        System.out.println("DATA SENT");

                    }
                }

            }
            //Close the Socket
            clientSocket.close();
            serverSocket.close();

        }catch (IOException e){
            System.out.println("Client is disconnected !");
        }



    }
}
