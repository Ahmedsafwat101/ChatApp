package test;

import com.company.Client;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


import static org.junit.Assert.*;


class ClientTest {

    private PrintWriter outClient;
    private BufferedReader fromServer;
    private Socket clientSocket;
    private ServerSocket serverSocket;


    private BufferedReader inServer;
    private OutputStream outputServer;
    private PrintWriter outServer;
    private Socket serverClientSocket;


    @Test
    public void testClientSocketGetsCreated() throws IOException {

        serverSocket = Server.createServerSocket(8833);
        clientSocket = Client.createSocket("127.0.0.1", 8833);
        serverSocket.close();
        clientSocket.close();
        assertNotNull(clientSocket);

    }


    @Test
    public void testSendCommandToServer() throws IOException {


        //Server
        serverSocket = Server.createServerSocket(8833);
        //Client
        clientSocket = Client.createSocket("127.0.0.1", 8833);

        outClient = new PrintWriter(clientSocket.getOutputStream());


        outClient.println("SEND Hello");       // sending to server
        outClient.flush();

        //Server
        serverClientSocket = serverSocket.accept();
        inServer = new BufferedReader(new InputStreamReader(serverClientSocket.getInputStream()));
        String msg = inServer.readLine();
        int start_index = msg.indexOf("SEND");
        msg = msg.substring(start_index + 4).trim();

        serverSocket.close();

        Assertions.assertEquals("Hello", msg);


    }


    @Test
    public void testWhoCommandToServer() throws IOException {


        //Server
        serverSocket = Server.createServerSocket(8833);
        //Client
        clientSocket = Client.createSocket("127.0.0.1", 8833);

        outClient = new PrintWriter(clientSocket.getOutputStream());

        outClient.println("WHO");       // sending to server
        outClient.flush();

        //Server
        serverClientSocket = serverSocket.accept();


        outputServer = serverClientSocket.getOutputStream();
        outServer = new PrintWriter(outputServer, true);
        outServer.println(serverClientSocket.getInetAddress());       // sending to Client
        outServer.flush();


        //Client
        fromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        String ret = fromServer.readLine();


        serverSocket.close();


        Assertions.assertEquals("/127.0.0.1", ret);

    }
}