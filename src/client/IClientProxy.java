package client;

public interface IClientProxy {
	public String getHost();
	
	public void setHost(String host);
	
	public int getPort();
	
	public void setPort(int port);
	
	public int getObjectId();
	
	public void setObjectId(int objectId);
}
