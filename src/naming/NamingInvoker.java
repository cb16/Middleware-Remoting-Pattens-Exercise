package naming;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

import client.IClientProxy;
import server.ServerRequestHandlerTCP;
import utils.Marshaller;

public class NamingInvoker {
	private static HashMap<String, IClientProxy> repository = new HashMap<String, IClientProxy>();
	private static String address = "localhost";
	
	public static void bind(String serviceName, IClientProxy proxy) {
		repository.put(serviceName, proxy);
	}
	
	public void invoke(int portNamingServer) throws IOException, Throwable {
		while (true) {

			ServerSocket socket = new ServerSocket(portNamingServer);
			Socket connectionSocket = socket.accept();
			
			ObjectInputStream inFromClient = new ObjectInputStream(connectionSocket.getInputStream());
			ObjectOutputStream outToClient = new ObjectOutputStream(connectionSocket.getOutputStream());
			
			NamingMessage msgFromClient = null;
			NamingMessage msgToClient = null;
					
			msgFromClient = (NamingMessage) inFromClient.readObject();
			
			if(msgFromClient.type == 1) {
				msgToClient = lookup(msgFromClient);
			} else if(msgFromClient.type == 2){
				bind(msgFromClient.message, msgFromClient.proxy);
				msgToClient = new NamingMessage(4);
			}
			
			outToClient.writeObject(msgToClient);
			
			inFromClient.close();
			outToClient.close();
			
			connectionSocket.close();
			socket.close();
		}
	}
	
	public NamingMessage lookup(NamingMessage msgFromClient){
		NamingMessage msgToClient;
		
		if(repository.containsKey(msgFromClient.message))
			msgToClient = new NamingMessage(3, repository.get(msgFromClient.message));
		else
			msgToClient = new NamingMessage(-1);
		
		return msgToClient;
	}
}
