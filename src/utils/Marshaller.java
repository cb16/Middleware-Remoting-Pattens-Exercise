package utils;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import naming.NamingMessage;
import message.Message;


public class Marshaller {
	public byte[] marshall(Message a) throws IOException {
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
		ObjectOutputStream objectStream = new ObjectOutputStream(byteStream);
		
		objectStream.writeObject(a);
		return byteStream.toByteArray();
	}
	
	public Message unmarshall(byte[] bytes) throws IOException, ClassNotFoundException {
		ByteArrayInputStream byteStream = new ByteArrayInputStream(bytes);
		ObjectInputStream objectStream = new ObjectInputStream(byteStream);
		
		return (Message) objectStream.readObject();
	}
	
	public byte[] marshallMessage(NamingMessage a) throws IOException {
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
		ObjectOutputStream objectStream = new ObjectOutputStream(byteStream);
		
		objectStream.writeObject(a);
		return byteStream.toByteArray();
	}

	public NamingMessage unmarshallMessage(byte[] bytes) throws IOException, ClassNotFoundException {
		
		ByteArrayInputStream byteStream = new ByteArrayInputStream(bytes);
		ObjectInputStream objectStream = new ObjectInputStream(byteStream);
		
		return (NamingMessage) objectStream.readObject();
	}
}
