package com.company;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Server {
    public static void main(String[] args) throws Exception {
        //My Server
        ServerSocket serverSocket;
        Socket clientSocket;
        BufferedReader in;
        Set<Integer> ports =  new HashSet<>();


        serverSocket = new ServerSocket(8888);
        clientSocket = serverSocket.accept();
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));




        String msgReceived;

        while (true) {
            if ((msgReceived = in.readLine()) != null) {

                String arr[] = new  String[3];
                arr = msgReceived.split(" ");
                if (msgReceived.equals("**")) { //terminate
                    System.out.println("Client closed the channel ");
                    break;
                }
                else if((arr[0].trim()).equals("con")){
                    System.out.println("Client is connected ");
                    int port = Integer.parseInt(arr[1].trim());
                    if(!ports.contains(port)) {
                        System.out.println("herer0");

                        //make server
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    System.out.println("herer1");

                                    MultithreadedSocketServer.main(new String[]{""+port});
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }).start();
                    }
                    ports.add(port);
                    //make client
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                System.out.println("herer2");
                                TCPClientThread.main(new String[]{""+port});
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                }
                System.out.println("Client Says : " + msgReceived);
            }

        }
        //Close the Socket
        clientSocket.close();
        serverSocket.close();
    }

}