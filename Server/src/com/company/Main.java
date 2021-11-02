package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    public static void main(String[] args) {
	  //My Server
        ServerSocket  serverSocket;
        Socket clientSocket;
        BufferedReader in;

        try {

            serverSocket = new ServerSocket(8833);
            clientSocket = serverSocket.accept();
            in =  new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            String msgReceived;

            while (true){
                if((msgReceived = in.readLine()) != null){
                    if(msgReceived.equals("**")) { //terminate
                        System.out.println("Client closed the channel ");
                        break;
                    }
                    System.out.println("Client Says : " + msgReceived);
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
