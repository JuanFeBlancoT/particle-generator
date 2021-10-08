package co.domi.clientparticlegenerator;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Communication extends Thread{

    private BufferedWriter brw;
    private MainActivity mainA;

    public Communication(MainActivity mainA){
        this.mainA = mainA;
    }

    @Override
    public void run() {
        startServer();
    }

    public void startServer() {

        try {
            Socket conectionSocket = new Socket("10.0.2.2", 8000);

            OutputStream outputS= conectionSocket.getOutputStream();
            OutputStreamWriter outputSW = new OutputStreamWriter(outputS);
            brw = new BufferedWriter(outputSW);


        }catch(IOException e) {

        }
    }

    public void sendMessage(String message){
        new Thread(()->{
            try {
                brw.write(message);
                brw.flush();
            }catch(IOException e){

            }

        }).start();
    }
}
