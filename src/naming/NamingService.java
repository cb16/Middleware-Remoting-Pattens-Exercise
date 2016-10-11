package naming;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;

import utils.Config;

import client.ClientProxy;
import client.IClientProxy;

public class NamingService {
	private String address;
	private int port;
	
	public NamingService(String address, int port) {
		this.address = address;
		this.port = port;
	}
	
	public IClientProxy lookup(String serviceName) throws IOException, ClassNotFoundException {
		Socket clientSocket = new Socket("localhost", 1414);
		
		System.out.println("Connected");
		
		ObjectOutputStream outToServer = new ObjectOutputStream(clientSocket.getOutputStream());
		ObjectInputStream inFromServer = new ObjectInputStream(clientSocket.getInputStream());
		
		NamingMessage msgToServer = new NamingMessage(1, serviceName);
		NamingMessage msgFromServer;
		
		outToServer.writeObject(msgToServer);
		
		msgFromServer = (NamingMessage) inFromServer.readObject();
		
		outToServer.close();
		inFromServer.close();
		
		clientSocket.close();
		
		return msgFromServer.proxy;
	}
	
	public void bind(String serviceName, IClientProxy proxy) throws UnknownHostException, IOException, ClassNotFoundException {
		Socket clientSocket = new Socket("localhost", 1414);
		
		System.out.println("Connected");
		
		ObjectOutputStream outToServer = new ObjectOutputStream(clientSocket.getOutputStream());
		ObjectInputStream inFromServer = new ObjectInputStream(clientSocket.getInputStream());
		
		NamingMessage msgToServer = new NamingMessage(2, serviceName, proxy);
		NamingMessage msgFromServer;
		
		outToServer.writeObject(msgToServer);
		
		msgFromServer = (NamingMessage) inFromServer.readObject();
		
		outToServer.close();
		inFromServer.close();
		
		clientSocket.close();
	}
}
