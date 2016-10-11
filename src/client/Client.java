package client;

import java.io.IOException;

import java.util.Scanner;

import naming.NamingService;

import message.Message;

import utils.Config;
import utils.IHandler;
import utils.Marshaller;


public class Client {
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		Scanner in = new Scanner(System.in);
		String message;
		
		NamingService namingService = new NamingService("localhost", Config.port);
		
		ClientProxy clientProxy = (ClientProxy) namingService.lookup("upper");
		//ClientProxy clientProxy = new ClientProxy("localhost", Config.port, 1);
		
		while(true) {
			System.out.println("Digite a mensagem a ser enviada:");
			message = in.nextLine();
			
			String response = clientProxy.toUpper(message);
			System.out.println("RESPONSE: " + response);
		}
		
	}

}
