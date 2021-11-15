package com.company;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultithreadedSocketServer {

    public static void main(String[] args) throws Exception {
       // ServerSocket serverSocket = new ServerSocket(8888);
        ServerSocket serverSocket = new ServerSocket(Integer.parseInt(args[0].trim()));




        int counter = 0;

        //Handel User Input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("TCP Server is started... ");

        String clientMessage = "";

        while (true) {

            //Take input from user
           /*System.out.println("conn host port :");

            clientMessage = br.readLine();
            String[] clientInputArray = new String[3];
            clientInputArray = clientMessage.split(" ");
            System.out.println(clientMessage);


            String[] finalClientInputArrayThread = clientInputArray;

            Thread t1 = new Thread(() -> {
                try {
                    initializeServer(Integer.parseInt(finalClientInputArrayThread[2].trim()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            Thread t2= new Thread(() -> {
                try {
                    TCPClientThread.runTCPClient(finalClientInputArrayThread[1].trim(),Integer.parseInt(finalClientInputArrayThread[2].trim()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });


            t1.start();
            t2.start();*/


            //Server accept client connection
            Socket clientServer = serverSocket.accept();
            counter++;
            System.out.println("Client " + counter + " is connected...");
            //ServerClientThread is a custom class for handling client threads
           ServerClientThread serverClientThread = new ServerClientThread(clientServer, counter);
            serverClientThread.start(); //  to start thread
        }
    }


    public static void initializeServer(int port) throws Exception {
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("TCP Server is started from parent server... ");
      //  TCPClientThread.runTCPClient("127.0.0.1",8833);

        Socket serverClient = serverSocket.accept();  //server accept the client connection request
        BufferedReader br = new BufferedReader(new InputStreamReader(serverClient.getInputStream()));
        OutputStream output = serverClient.getOutputStream();
        PrintWriter writer = new PrintWriter(output, true);
        String clientMessage = "";
        clientMessage = br.readLine();
        System.out.println("TCP Server is started from parent server here... ");

        while (!clientMessage.equals("bye")) {
            System.out.println("TCP Server children in");
            //System.out.println("Client says " + clientMessage);
            clientMessage = br.readLine();
            writer.println("This is a message sent to the server");

        }
        serverSocket.close();
        serverClient.close();
        br.close();
    }
}
