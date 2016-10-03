package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import utils.IHandler;


public class ServerRequestHandlerTCP implements IHandler {
	private int port;
	private ServerSocket welcomeSocket = null;
	private Socket connectionSocket = null;
	
	private int sentMessageSize;
	private int receivedMessageSize;
	private DataOutputStream outToClient = null;
	private DataInputStream inFromClient = null;
	
	public ServerRequestHandlerTCP(int port) {
		this.port = port;
	}
	
	public byte[] receive() throws IOException {
		welcomeSocket = new ServerSocket(this.port);
		connectionSocket = welcomeSocket.accept();
		
		inFromClient = new DataInputStream(connectionSocket.getInputStream());
		outToClient = new DataOutputStream(connectionSocket.getOutputStream());
		
		receivedMessageSize = inFromClient.readInt();
		byte[] receivedMessage = new byte[receivedMessageSize];
		inFromClient.read(receivedMessage, 0, receivedMessageSize);
		
		return receivedMessage;
	}
	
	public void send(byte[] msg) throws IOException {
		sentMessageSize = msg.length;
		outToClient.writeInt(sentMessageSize);
		outToClient.write(msg, 0, sentMessageSize);
		
		connectionSocket.close();
		welcomeSocket.close();
		outToClient.close();
		inFromClient.close();
	}
}
