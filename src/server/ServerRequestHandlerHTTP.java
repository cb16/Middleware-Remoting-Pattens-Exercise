package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Base64;

import common.IHandler;


public class ServerRequestHandlerHTTP implements IHandler {
	private int port;
	private ServerSocket welcomeSocket = null;
	private Socket connectionSocket = null;
	
	private int sentMessageSize;
	private int receivedMessageSize;
	private DataOutputStream outToClient = null;
	private DataInputStream inFromClient = null;
	
	public ServerRequestHandlerHTTP(int port) {
		this.port = port;
	}
	
	public byte[] receive() throws IOException {
		welcomeSocket = new ServerSocket(this.port);
		connectionSocket = welcomeSocket.accept();
		
		inFromClient = new DataInputStream(connectionSocket.getInputStream());
		outToClient = new DataOutputStream(connectionSocket.getOutputStream());
		
		String a = inFromClient.readLine();
		String b = inFromClient.readLine();
		String c = inFromClient.readLine();
		String d = inFromClient.readLine();
		
		byte[] msg = Base64.getDecoder().decode(d.getBytes());
		
		
		System.out.println("----------- RECEIVED ---------------");
		System.out.println(a);
		System.out.println(b);
		System.out.println(c);
		System.out.println(d);
		System.out.println("-----------------------------------");
		
		return msg;
	}
	
	public void send(byte[] msg) throws IOException {
		
		StringBuilder response = new StringBuilder();
		response.append("HTTP/1.0 200 OK\n");
		response.append("Content-Length:" + msg.length + "\n");
		response.append("\n");

		String b64 = Base64.getEncoder().encodeToString(msg);
		
		response.append(b64);
		response.append("\n");
		
//		System.out.println("----------- SENDING ---------------");
//		System.out.println(response.toString());
//		System.out.println("-----------------------------------");
		
		byte[] toSend = response.toString().getBytes();
		
		sentMessageSize = toSend.length;
		outToClient.write(toSend, 0, sentMessageSize);
		
		connectionSocket.close();
		welcomeSocket.close();
		outToClient.close();
		inFromClient.close();
	}
}
