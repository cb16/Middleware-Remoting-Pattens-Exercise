package client;

import java.io.IOException;

import java.util.Scanner;

import utils.Marshaller;

import common.Config;
import common.IHandler;


public class Client {
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		IHandler clientRequestHandler;
		
		Scanner in = new Scanner(System.in);
		
		String handlerType;
		String a;
		
		Marshaller marshaller = new Marshaller();
		
		while(true) {
			System.out.println("Choose request handler type (TCP, UDP, HTTP)\n(default TCP)");
			handlerType = in.nextLine();
			
			switch (handlerType) {
				case "UDP":
					clientRequestHandler = new ClientRequestHandlerUDP(Config.port);
					break;
				case "HTTP":
					clientRequestHandler = new ClientRequestHandlerHTTP(Config.port);
					break;
				default:
					clientRequestHandler = new ClientRequestHandlerTCP(Config.port);
			}
			
			System.out.println("Digite a mensagem a ser enviada:");
			a = in.nextLine();
			
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

}
