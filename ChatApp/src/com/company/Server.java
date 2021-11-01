package com.company;
import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        final ServerSocket serverSocket;
        final Socket clientSocket;
        final BufferedReader in;


        try {
            serverSocket = new ServerSocket(8833);
            clientSocket = serverSocket.accept();
            in =  new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            Thread receive = new Thread(new Runnable() {
                String msg;
                @Override
                public void run() {
                     try {
                         msg = in.readLine();

                         while (msg!=null){
                             System.out.println("Client says "+ msg);
                             msg = in.readLine();
                         }


                     }catch (IOException e){
                       //  e.printStackTrace();
                         System.out.println("Client is disconnected ");
                     }finally {
                         //msg equals null
                         try {
                             clientSocket.close();
                             serverSocket.close();

                         } catch (IOException e) {
                             e.printStackTrace();
                         }
                     }
                }
            });

            receive.start();
        }catch (IOException e){
            e.printStackTrace();
        }


    }
}
