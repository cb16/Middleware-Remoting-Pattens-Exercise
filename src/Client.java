import java.io.IOException;

import java.util.Scanner;


public class Client {
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		IHandler clientRequestHandler;
		
		Scanner in = new Scanner(System.in);
		
		String handlerType = Config.handlerType;
		
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
		
		Marshaller marshaller = new Marshaller();
		
		String a;
		
		while(true) {
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
