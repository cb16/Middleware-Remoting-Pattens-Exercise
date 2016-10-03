package utils;
import java.io.IOException;
import java.net.UnknownHostException;


public interface IHandler {
	public byte[] receive() throws IOException;
	public void send(byte[] msg) throws UnknownHostException, IOException ;
}
