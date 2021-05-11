import java.io.Serializable;
import java.time.*;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Post implements Serializable{
	
	private User creator;
	private String content;
	private String postScope; //Scope: (public or friends or group)
	private LocalDateTime timestamp;
	private int numberOfLikes;
	private ArrayList<User> likers = new ArrayList<User>(); //All users who likes the post
	
	
	//Constructor for Post class
	public Post(User creator, String content, String postScope) {
		this.creator = creator;
		this.content = content;
		this.postScope = postScope;
		this.timestamp = LocalDateTime.now();
		this.numberOfLikes = 0; //initially the number of Likes is 0
	}
	
	
	/*method which adds Like to a Post
	Boolean to know if in Gui we will change the value with the Likes so that unnecessary procedures are not done in Gui*/
	public boolean addLike(User theUser) {
		
		if (likers.contains(theUser)) {
			String message = "You have already like this Post!";
			JOptionPane.showMessageDialog(new JFrame(), message, "Message",
			        JOptionPane.INFORMATION_MESSAGE);
			return false; //Like couldn' t added
		}
		else {
			numberOfLikes++;
			likers.add(theUser);
			return true; //New like added
		}	
	}


	
	//*********************************************************************************************************************
	//Getters and Setters
	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPostScope() {
		return postScope;
	}

	public void setPostScope(String postScope) {
		this.postScope = postScope;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public int getNumberOfLikes() {
		return numberOfLikes;
	}

	public void setNumberOfLikes(int numberOfLikes) {
		this.numberOfLikes = numberOfLikes;
	}

	public ArrayList<User> getLikers() {
		return likers;
	}

	public void setLikers(ArrayList<User> likers) {
		this.likers = likers;
	}
		
}
