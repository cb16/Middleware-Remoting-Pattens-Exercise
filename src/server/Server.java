package server;

import java.io.IOException;
import java.util.Scanner;

import client.ClientProxy;

import utils.Config;
import utils.IHandler;
import utils.Marshaller;

public class Server {
	public static void main(String[] args) throws Throwable {
		Invoker invoker = new Invoker();
		
		//remove object
		ClientProxy client = new ClientProxy(Config.port);
		
		//naming service?
		
		invoker.invoke(client);
		
		
	}
	
	public static String toUpper(String a) {
		return a.toUpperCase();
	}
}
