package com.company;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

    public static void main(String[] args) throws Exception {
        Socket clientSocket;
        PrintWriter out;
        BufferedReader in;


        clientSocket = new Socket("127.0.0.1", 8888);
        out = new PrintWriter(clientSocket.getOutputStream());
        //Reading from KeyBoard
        in = new BufferedReader(new InputStreamReader(System.in));
        String sendMessage;
        while (true) {

            sendMessage = in.readLine();  // keyboard reading
            out.println(sendMessage);       // sending to server
            out.flush();                    // flush the
        }

    }
}


