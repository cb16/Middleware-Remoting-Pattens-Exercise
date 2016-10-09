package utils;

import java.net.UnknownHostException;

import client.ClientRequestHandlerTCP;

public class Requestor {
	private ClientRequestHandlerTCP clientHandler = new ClientRequestHandlerTCP(Config.port);
	
	public Terminator invoke(Invoker inv) throws UnknownHostException{
		return 2;
	}
}
