import java.io.IOException;
import java.util.Scanner;

public class Server {
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		IHandler serverRequestHandler;
		String handlerType;
		
		Scanner in = new Scanner(System.in);
		
		System.out.println("Choose request handler type (TCP, UDP, HTTP)\n(default TCP)");
		handlerType = in.nextLine();
		
		Marshaller marshaller = new Marshaller();
		
		switch (handlerType) {
			case "UDP":
				serverRequestHandler = new ServerRequestHandlerUDP(Config.port);
				Config.setHandlerType(handlerType);
				break;
			case "HTTP":
				serverRequestHandler = new ServerRequestHandlerHTTP(Config.port);
				Config.setHandlerType(handlerType);
				break;
			default:
				serverRequestHandler = new ServerRequestHandlerTCP(Config.port);
				Config.setHandlerType(handlerType);
		}
		
		while(true) {
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
