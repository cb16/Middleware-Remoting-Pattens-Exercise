package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Base64;

import common.IHandler;

public class ClientRequestHandlerHTTP implements IHandler {
	private String host;
	private int port;
	private int sentMessageSize;
	private int receivedMessageSize;
	
	private Socket clientSocket = null;
	private DataOutputStream outToServer = null;
	private DataInputStream inFromServer = null;
	
	public ClientRequestHandlerHTTP(int port) {
		this.host = "localhost";
		this.port = port;
	}
	
	public void send(byte[] msg) throws UnknownHostException, IOException {
		clientSocket = new Socket(this.host, this.port);
		outToServer = new DataOutputStream(clientSocket.getOutputStream());
		inFromServer = new DataInputStream(clientSocket.getInputStream());
		
		StringBuilder headers = new StringBuilder();
		headers.append("POST / HTTP/1.0\n");
		headers.append("Content-Length:" + msg.length + "\n");
		headers.append("\n");
		
		String b64 = Base64.getEncoder().encodeToString(msg);
		
		headers.append(b64);
		headers.append("\n");
		
		byte[] toSend = headers.toString().getBytes();
		
//		System.out.println("----------- SENDING ---------------");
//		System.out.println(headers.toString());
//		System.out.println("-----------------------------------");
		
		sentMessageSize = toSend.length;
		outToServer.write(toSend, 0, sentMessageSize);
		outToServer.flush();
	}
	
	public byte[] receive() throws IOException {

		String a = inFromServer.readLine();
		String b = inFromServer.readLine();
		String c = inFromServer.readLine();
		String d = inFromServer.readLine();
		
		System.out.println("----------- RECEIVED ---------------");
		System.out.println(a);
		System.out.println(b);
		System.out.println(c);
		System.out.println(d);
		System.out.println("-----------------------------------");
		
		byte[] msg = Base64.getDecoder().decode(d.getBytes());
		
		clientSocket.close();
		outToServer.close();
		inFromServer.close();
		
		return msg;
	}

}
