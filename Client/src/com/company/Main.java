package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Main {

    public static void main(String[] args) {
        //My Client
        Socket clientSocket;
        PrintWriter out;
        BufferedReader in;

        try{

            clientSocket = new Socket("127.0.0.1",8833);
            out = new PrintWriter(clientSocket.getOutputStream());
            //Reading from KeyBoard
            in = new BufferedReader(new InputStreamReader(System.in));
            String  sendMessage;
            while(true)
            {

                sendMessage = in.readLine();  // keyboard reading
                out.println(sendMessage);       // sending to server
                out.flush();                    // flush the
            }

        }catch (IOException e){
            System.out.println("Server isn't running !");
        }
    }
}
