package server;

import java.io.IOException;
import java.util.Scanner;

import utils.Marshaller;

import common.Config;
import common.IHandler;

public class Server {
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		IHandler serverRequestHandler;
		String handlerType;
		
		Scanner in = new Scanner(System.in);
		
		
		
		Marshaller marshaller = new Marshaller();
		
		while(true) {
			System.out.println("Choose request handler type (TCP, UDP, HTTP)\n(default TCP)");
			handlerType = in.nextLine();
			
			switch (handlerType) {
				case "UDP":
					serverRequestHandler = new ServerRequestHandlerUDP(Config.port);
					break;
				case "HTTP":
					serverRequestHandler = new ServerRequestHandlerHTTP(Config.port);
					break;
				default:
					serverRequestHandler = new ServerRequestHandlerTCP(Config.port);
			}
		
		
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
		
		
	}
	
	public static String toUpper(String a) {
		return a.toUpperCase();
	}
}
