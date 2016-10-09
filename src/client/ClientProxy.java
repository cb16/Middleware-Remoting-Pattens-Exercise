package client;

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
