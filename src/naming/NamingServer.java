package naming;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

import com.sun.corba.se.spi.activation.Repository;

import utils.Config;

import client.IClientProxy;

public class NamingServer {
	private static HashMap<String, IClientProxy> repository = new HashMap<String, IClientProxy>();
	private static String address = "localhost";
	private static int port = 1414;
	
	public static void bind(String serviceName, IClientProxy proxy) {
		repository.put(serviceName, proxy);
	}
	
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		while(true) {
				
			ServerSocket socket = new ServerSocket(port);
			Socket connectionSocket = socket.accept();
			
			ObjectInputStream inFromClient = new ObjectInputStream(connectionSocket.getInputStream());
			ObjectOutputStream outToClient = new ObjectOutputStream(connectionSocket.getOutputStream());
			
			NamingMessage msgFromClient = null;
			NamingMessage msgToClient = null;
			
			//while(true) {
			msgFromClient = (NamingMessage) inFromClient.readObject();
			if(msgFromClient.type == 1) {
				if(repository.containsKey(msgFromClient.message))
					msgToClient = new NamingMessage(3, repository.get(msgFromClient.message));
				else
					msgToClient = new NamingMessage(-1);
			} else if(msgFromClient.type == 2){
				bind(msgFromClient.message, msgFromClient.proxy);
				msgToClient = new NamingMessage(4);
			}
			
			outToClient.writeObject(msgToClient);
			//}
			
			inFromClient.close();
			outToClient.close();
			
			connectionSocket.close();
			socket.close();
		}
	}
}
