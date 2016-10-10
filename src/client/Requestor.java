package client;

import java.io.IOException;
import java.net.UnknownHostException;
import java.rmi.MarshalException;

import message.Message;

import server.Invoker;
import utils.Config;
import utils.Marshaller;


public class Requestor {
	private ClientRequestHandlerTCP clientHandler;
	
	public Termination invoke(Invocation inv) throws IOException, ClassNotFoundException{
		clientHandler = new ClientRequestHandlerTCP(inv.getPortNumber());
		Marshaller marshaller = new Marshaller();
		Termination termination = new Termination();
		
		byte[] msgMarshalled;
		byte[] msgToBeUnmarshalled;
		Message msgUnmarshalled = new Message();
		
		Message msgToBeMarshalled = new Message(inv.getParameter());
		
		msgMarshalled = marshaller.marshall(msgToBeMarshalled);
		
		clientHandler.send(msgMarshalled);
		
		msgToBeUnmarshalled = clientHandler.receive();
		
		msgUnmarshalled = marshaller.unmarshall(msgToBeUnmarshalled);
		
		termination.setResult(msgUnmarshalled.getMessage());
		
		return termination;
	}
}
