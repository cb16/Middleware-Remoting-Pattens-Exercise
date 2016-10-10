package client;

import java.io.IOException;
import java.io.Serializable;


public class ClientProxy implements Serializable{
	private static final long serialVersionUID = 1L;
	protected String host;
	protected int port;
	protected int objId;
	
	public ClientProxy(String host, int port, int objId){
		this.host = host;
		this.port = port;
		this.objId = objId;
	}
	public ClientProxy(final int p){		
	}
	
	public String toUpper(String a) throws ClassNotFoundException, IOException {
		Invocation inv = new Invocation();
		Termination ter = new Termination();
		Requestor requestor = new Requestor();
		
		inv.setObjectId(this.getObjectId());
		inv.setIpAdress(this.getHost());
		inv.setPortNumber(this.getPort());
		inv.setParameter(a);
		
		ter = requestor.invoke(inv);
		
		return (String) ter.getResult();
	}
	
	public String getHost(){
		return host;
	}
	
	public void setHost(String host){
		this.host = host;
	}
	
	public int getPort(){
		return port;
	}
	
	public void setPort(int port){
		this.port = port;
	}
	
	public int getObjectId(){
		return objId;
	}
	
	public void setObjectId(int objectId){
		this.objId = objectId;
	}
}
