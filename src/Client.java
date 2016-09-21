import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;


public class Client {
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		//IHandler clientRequestHandler = new ClientRequestHandlerTCP(Config.port);
		//IHandler clientRequestHandler = new ClientRequestHandlerUDP(Config.port);
		IHandler clientRequestHandler = new ClientRequestHandlerHTTP(Config.port);
		
		Marshaller marshaller = new Marshaller();
		
		String a = "will it work";
		
		System.out.println("CLIENT - Marshalling");
		byte[] msg = marshaller.marshall(a);
		
		System.out.println("CLIENT - Sending");
		clientRequestHandler.send(msg);
		
		System.out.println("CLIENT - Receiving");
		msg = clientRequestHandler.receive();
		
		System.out.println("CLIENT - Unmarshalling");
		a = marshaller.unmarshall(msg);
		
		System.out.println("CLIENT - Result: " + a);
	}

}
