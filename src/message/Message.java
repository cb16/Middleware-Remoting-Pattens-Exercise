package message;

import java.io.Serializable;

public class Message implements Serializable{
	private String message;
	
	public Message() {
		this.message = null;
	}
	
	public Message(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
