import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;


public class ServerRequestHandlerUDP implements IHandler {
	private int port;
	private DatagramSocket socket;
	private byte[] receivedData;
	private int receivedDataLength = 1024;
	private int sentDataLength;
	private DatagramPacket sentPacket;
	private DatagramPacket receivedPacket;
	private InetAddress IPAdress;

	public ServerRequestHandlerUDP(int port) {
		this.port = port;
	}
	
	@Override
	public byte[] receive() throws IOException {
		receivedData = new byte[receivedDataLength];
		
		socket = new DatagramSocket(this.port);
		receivedPacket = new DatagramPacket(receivedData, receivedDataLength);
		socket.receive(receivedPacket);
		IPAdress = receivedPacket.getAddress();
		port = receivedPacket.getPort();
		
		return receivedPacket.getData();
	}

	@Override
	public void send(byte[] msg) throws UnknownHostException, IOException {
		sentDataLength = msg.length;
		sentPacket = new DatagramPacket(msg, sentDataLength, IPAdress, port);
		socket.send(sentPacket);
		
		socket.close();
	}

}
