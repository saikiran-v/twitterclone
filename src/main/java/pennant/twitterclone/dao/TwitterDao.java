package pennant.twitterclone.dao;

import java.util.Set;

import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Repository;

import pennant.twitterclone.model.Counter;
import pennant.twitterclone.model.Person;
import pennant.twitterclone.model.Tweet;

/**
 * @author saikiran.v
 *
 */
@Repository
@EnableAspectJAutoProxy
public interface TwitterDao {
	public abstract boolean loginCheck(Person person);

	public abstract Set<Tweet> findTweets(String userId);

	public abstract boolean registerUser(Person person);

	public abstract void saveTweet(Tweet tweetMessage);

	public abstract Person findPerson(String userId);

	public abstract void follow(Person follow, String follower);

	public Set<String> getAllFollowers(Person person);

	public Set<Person> getAllFollowings(Person person);

	 public abstract Counter getCounts(String userId);
}
