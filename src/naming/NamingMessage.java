package naming;

import java.io.Serializable;

import client.IClientProxy;

public class NamingMessage implements Serializable{
	int type;
	String message;
	int port;
	IClientProxy proxy;
	
	public NamingMessage() {}
	
	public NamingMessage(int type, String m) {
		this.type = type;
		this.message = m;
	}
	
	public NamingMessage(int type, String m, int port) {
		this.type = type;
		this.message = m;
		this.port = port;
	}
	
	public NamingMessage(int type, IClientProxy proxy) {
		this.type = type;
		this.proxy = proxy;
	}
	
	public NamingMessage(int type, String m, IClientProxy proxy) {
		this.type = type;
		this.message = m;
		this.proxy = proxy;
	}
	
	public NamingMessage(int type) {
		this.type = type;
	}

}
