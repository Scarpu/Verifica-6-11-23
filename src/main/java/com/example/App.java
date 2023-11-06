package com.example;


import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class App 
{
    public static void main( String[] args )
    {
        try{
            System.out.println("Server avviato");
            ServerSocket server= new ServerSocket(3000);
            List<MioThread> threads = new ArrayList<MioThread>();

        for (;;) {
            Socket s = server.accept();
            MioThread thread = new MioThread (s, threads);
            threads.add(thread);
            thread.start();
            System.out.println(""+s.getPort()+" si è connesso");
            System.out.println("Totale Client: "+ threads.size());
        }

    }catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Errore durante l'istanza del server!");
        }
    }
}
