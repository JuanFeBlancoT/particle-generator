package view;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Communication extends Thread{

	private BufferedReader bfr;
	private BufferedWriter brw;
	private MainServer mainServer;
	
	public Communication(MainServer mns) {
		mainServer = mns;
	}
	
	@Override
	public void run() {
		startServer();
	}
	
	public void startServer() {

			try {
				ServerSocket ss = new ServerSocket(8000);
				Socket conectionSocket = ss.accept();
				System.out.println("WOWOWOW");
				
				InputStream inputS = conectionSocket.getInputStream();
				InputStreamReader inputSR = new InputStreamReader(inputS);
				bfr = new BufferedReader(inputSR);
				
				OutputStream outputS= conectionSocket.getOutputStream();
				OutputStreamWriter outputSW = new OutputStreamWriter(outputS);
				brw = new BufferedWriter(outputSW);
				
				readMessages();
			}catch(IOException e) {
				
			}
	}

	private void readMessages() {
		while(true) {
			
			try {
				String message = bfr.readLine();
				System.out.println(message);
				//mainServer.notifyMessage(message);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}

		
}
