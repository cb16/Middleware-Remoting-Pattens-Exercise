import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientRequestHandlerTCP implements IHandler {
	private String host;
	private int port;
	private int sentMessageSize;
	private int receivedMessageSize;
	
	private Socket clientSocket = null;
	private DataOutputStream outToServer = null;
	private DataInputStream inFromServer = null;
	
	public ClientRequestHandlerTCP(int port) {
		this.host = "localhost";
		this.port = port; 
	}
	
	public void send(byte[] msg) throws UnknownHostException, IOException {
		clientSocket = new Socket(this.host, this.port);
		outToServer = new DataOutputStream(clientSocket.getOutputStream());
		inFromServer = new DataInputStream(clientSocket.getInputStream());
		
		sentMessageSize = msg.length;
		outToServer.writeInt(sentMessageSize);
		outToServer.write(msg, 0, sentMessageSize);
		outToServer.flush();
	}
	
	public byte[] receive() throws IOException {
		byte[] msg = null;
		
		receivedMessageSize = inFromServer.readInt();
		msg = new byte[receivedMessageSize];
		inFromServer.read(msg, 0, receivedMessageSize);
		
		clientSocket.close();
		outToServer.close();
		inFromServer.close();
		
		return msg;
	}

}
