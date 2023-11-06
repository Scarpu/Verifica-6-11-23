package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class MioThread extends Thread{

    private Socket s;
    private List<MioThread> threads;
    public MioThread(Socket s, List<MioThread> threads){
        this.s = s;
        this.threads = threads;
    }
    @Override
    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            DataOutputStream out = new DataOutputStream(s.getOutputStream());
            String temp = "";
            ArrayList<String> Lista = new ArrayList<String>();
            String stringaricevuta = new String();
            do{
                stringaricevuta=in.readLine();
                System.out.println("["+currentThread().getName() + "] Client scrive : " + stringaricevuta);
                if(stringaricevuta.equals("Q")){
                    out.writeBytes("Q" + "\n");
                    System.out.println("["+currentThread().getName() + "] Client chiude la connessione...");
                }
                else if(stringaricevuta.equals("@")){
                    temp=Lista.toString();
                    out.writeBytes(stringaricevuta + "\n");
                    out.writeBytes(temp + "\n") ;
                    System.out.println("["+currentThread().getName() + "] Client guarda la lista...");
                }
                else {
                    out.writeBytes(stringaricevuta + "\n");
                    Lista.add(stringaricevuta);
                    System.out.println("Scrivo Stringa...");     
                }                
            }while (stringaricevuta!="Q");
            s.close();
            threads.remove(this);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            threads.remove(this);
        }
    }
}
    

