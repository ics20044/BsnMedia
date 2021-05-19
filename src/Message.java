import java.time.*;

public class Message {
	
	private String content;
	private LocalDateTime timesent;
	private User sender;
	private User reciever;
	
	
	public Message(String content, User sender, User reciever) {
		this.content = content;
		this.timesent = LocalDateTime.now();
		this.sender = sender;
		this.reciever = reciever;
	}
	
	//Constructor for testing encryption
	public Message(String content) {
		this.content = content;
		this.timesent = LocalDateTime.now();
	}


	public String getContent() {
		return content;
	}


	public LocalDateTime getTimesent() {
		return timesent;
	}


	public User getSender() {
		return sender;
	}

	
	
}
