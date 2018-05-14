/**
 * 
 */
package pennant.twitterclone.model;

/**
 * @author saikiran.v
 *
 */
public class Counter {
	private int tweetsCount;
	private int followingCount;
	private int followersCount;

	public int getTweetsCount() {
		return tweetsCount;
	}

	public void setTweetsCount(int tweetsCount) {
		this.tweetsCount = tweetsCount;
	}

	public int getFollowingCount() {
		return followingCount;
	}

	public void setFollowingCount(int followingCount) {
		this.followingCount = followingCount;
	}

	public int getFollowersCount() {
		return followersCount;
	}

	public void setFollowersCount(int followersCount) {
		this.followersCount = followersCount;
	}
}
