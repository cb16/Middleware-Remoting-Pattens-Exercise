import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import java.rmi.*;
import java.rmi.registry.LocateRegistry;

public class Server {
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		
		//IHandler serverRequestHandler = new ServerRequestHandlerTCP(8080);
		//IHandler serverRequestHandler = new ServerRequestHandlerUDP(8080);
		IHandler serverRequestHandler = new ServerRequestHandlerHTTP(8080);
		
		Marshaller marshaller = new Marshaller();
		
		System.out.println("SERVER - Receiving");
		byte[] msg = serverRequestHandler.receive();
		
		System.out.println("SERVER - Unmarshalling");
		String a = marshaller.unmarshall(msg);
		
		a = toUpper(a);
		
		System.out.println("SERVER - Marshalling");
		msg = marshaller.marshall(a);
		
		System.out.println("SERVER - Sending");
		serverRequestHandler.send(msg);
	}
	
	public static String toUpper(String a) {
		return a.toUpperCase();
	}
}
