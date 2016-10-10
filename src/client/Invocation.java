package client;

public class Invocation {
	private int objectId;
	private String host;
	private int port;
	private String parameter;
	
	public void setObjectId(int id) {
		this.objectId = id;
	}
	
	public void setIpAdress(String h) {
		this.host = h;
	}
	
	public void setPortNumber(int port) {
		this.port = port;
	}
	
	public void setParameter(String a) {
		this.parameter = a;
	}
	
	public int getObjectId() {
		return this.objectId;
	}
	
	public String getIpAdress() {
		return this.host;
	}
	
	public int getPortNumber() {
		return this.port;
	}
	
	public String getParameter() {
		return this.parameter;
	}

}
