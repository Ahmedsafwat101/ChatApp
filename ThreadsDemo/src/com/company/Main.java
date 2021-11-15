package com.company;

public class Main {

    public static void main(String[] args) {

        if(args.length>0)
            System.out.println(args[0]);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    MultithreadedSocketServer.main(new String[]{args[0]});
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        Thread thread2= new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    TCPClientThread.main(new String[]{args[0]});
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        Thread thread3= new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    TCPClientThread.main(new String[]{"8844"});
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

        thread.start();
        thread3.start();
    }
}
