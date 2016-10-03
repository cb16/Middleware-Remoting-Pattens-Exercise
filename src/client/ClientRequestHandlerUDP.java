package client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

import common.IHandler;


public class ClientRequestHandlerUDP implements IHandler {
	private int port;
	private DatagramSocket socket;
	private byte[] receivedData;
	private int receivedDataLength;
	private int sentDataLength;
	private DatagramPacket sentPacket;
	private DatagramPacket receivedPacket;
	private InetAddress IPAdress;
	
	public ClientRequestHandlerUDP(int port) {
		this.port = port;
	}
	
	@Override
	public byte[] receive() throws IOException {
		receivedDataLength = 1024;
		receivedData = new byte[receivedDataLength];
		
		receivedPacket = new DatagramPacket(receivedData, receivedDataLength);
		socket.receive(receivedPacket);
		receivedData = receivedPacket.getData();
		
		socket.close();
		
		return receivedData;
	}

	@Override
	public void send(byte[] msg) throws UnknownHostException, IOException {
		socket = new DatagramSocket();
		IPAdress = InetAddress.getByName("localhost");
		sentDataLength = msg.length;
		sentPacket = new DatagramPacket(msg, sentDataLength, IPAdress, port);
		socket.send(sentPacket);
	}

}
