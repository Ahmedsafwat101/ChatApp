package com.company;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {
        final Socket clientSocket;
        final PrintWriter out;
        final Scanner sc =  new Scanner(System.in);



        try{
            //Because both of server and client on the same machine the host will be "127.0.0.1"
            clientSocket =  new Socket("127.0.0.1",8833);
            out = new  PrintWriter(clientSocket.getOutputStream());


            //Thread for sending data

            Thread sender = new Thread(new Runnable() {
                String msg;
                @Override
                public void run() {

                    while (true){
                        msg = sc.nextLine();
                        out.println(msg);
                        out.flush();
                    }
                }
            });
            sender.setName("SenderThread");
            sender.start();

        }catch(IOException e){
            e.printStackTrace();
        }
    }


}
