package pennant.twitterclone.model;

import java.util.Date;
import java.util.Set;

/**
 * @author saikiran.v
 *
 */
public class Person {

	private String userID;
	private String fullName;
	private String email;
	private String password;
	private Date joined;
	private boolean active;
	private Set<Person> following;
	private Set<Person> followers;
	private Set<Tweet> tweets;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getJoined() {
		return joined;
	}

	public void setJoined(Date joined) {
		this.joined = joined;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Set<Person> getFollowing() {
		return following;
	}

	public void setFollowing(Set<Person> following) {
		this.following = following;
	}

	public Set<Person> getFollowers() {
		return followers;
	}

	public void setFollowers(Set<Person> followers) {
		this.followers = followers;
	}

	public Set<Tweet> getTweets() {
		return tweets;
	}

	public void setTweets(Set<Tweet> tweets) {
		this.tweets = tweets;
	}

}
