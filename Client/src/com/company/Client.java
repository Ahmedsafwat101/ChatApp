package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Client {

    //My Client
    static Socket clientSocket;
    static PrintWriter out;
    static BufferedReader in;
    static BufferedReader fromServer;

    public static void main(String[] args) {
        connect();
    }

    public static void connect(){
        try{

            clientSocket = createSocket("127.0.0.1",8833);
            out = new PrintWriter(clientSocket.getOutputStream());
            //Reading from KeyBoard
            in = new BufferedReader(new InputStreamReader(System.in));
            fromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            String  sendMessage;
            while(true)
            {

                //Send to the server
                sendMessage = in.readLine();  // keyboard reading
                out.println(sendMessage);       // sending to server
                out.flush();


                // receive from server
                System.out.println(fromServer.readLine());

            }

        }catch (IOException e){
            System.out.println("Server isn't running !");
        }
    }

    public static Socket createSocket(String host,int port) throws IOException {
        return new Socket(host,port);
    }




}
