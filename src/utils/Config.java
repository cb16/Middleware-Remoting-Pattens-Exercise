package utils;

public class Config {
	public static final int port = 8080;
	public static String handlerType = "TCP"; //set as default
	
	public static void setHandlerType(String type) {
		handlerType = type;
	}
}
