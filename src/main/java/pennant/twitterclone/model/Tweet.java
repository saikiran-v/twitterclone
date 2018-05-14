package pennant.twitterclone.model;

import java.util.Date;

/**
 * @author saikiran.v
 *
 */
public class Tweet {
	private int tweetId;
	private String user;
	private String message;
	private Date created;

	public int getTweetId() {
		return tweetId;
	}

	public void setTweetId(int tweetId) {
		this.tweetId = tweetId;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date javaDate) {
		this.created = javaDate;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return user + "|| " + message + " || Date and Time :" + getCreated();
	}

}
