package server;

import java.io.IOException;

import message.Message;
import utils.Config;
import utils.Marshaller;
import client.Client;
import client.ClientProxy;
import client.Termination;

public class Invoker {
	public void invoke(ClientProxy clientProxy) throws IOException, Throwable{
		ServerRequestHandlerTCP handler = new ServerRequestHandlerTCP(Config.port);
		byte[] msgToBeUnmarshalled = null;
		byte[] msgMarshalled = null;
		Message msgUnmarshalled = new Message();
		Message msgToBeMarshalled = new Message();
		Marshaller marshaller = new Marshaller();
		Termination ter = new Termination();
		
		while(true){
			System.out.println("SERVER - Receiving");
			msgToBeUnmarshalled = handler.receive();
			
			System.out.println("SERVER - Unmarshalling");
			msgUnmarshalled = marshaller.unmarshall(msgToBeUnmarshalled);
			
			ter.setResult(msgUnmarshalled.getMessage().toUpperCase());
			msgToBeMarshalled.setMessage((String)ter.getResult());
			
			System.out.println("SERVER - Marshalling");
			msgMarshalled = marshaller.marshall(msgToBeMarshalled);
			
			System.out.println("SERVER - Sending");
			handler.send(msgMarshalled);
		}
	}
}
