package utils;

import java.io.IOException;

import message.Message;
import server.ServerRequestHandlerTCP;
import client.Client;

public class Invoker {
	public void invoke(Client client) throws IOException, Throwable{
		ServerRequestHandlerTCP handler = new ServerRequestHandlerTCP(Config.port);
		byte[] msgToBeUnmarshalled = null;
		byte[] msgMarshalled = null;
		Message msgUnmarshalled = new Message();
		Marshaller marshaller = new Marshaller();
		Terminator ter = new Terminator();
		
		while(true){
			
		}
	}
}
