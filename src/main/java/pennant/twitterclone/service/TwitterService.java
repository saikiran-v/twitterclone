package pennant.twitterclone.service;

import java.util.Set;

import org.springframework.stereotype.Service;

import pennant.twitterclone.model.Counter;
import pennant.twitterclone.model.Person;
import pennant.twitterclone.model.Tweet;

/**
 * @author saikiran.v
 *
 */
@Service
public interface TwitterService {
	public abstract boolean loginCheck(Person person);

	public abstract Set<Tweet> findTweets(String userId);

	public abstract boolean registerUser(Person person);

	public abstract void saveTweet(Tweet tweetMessage);

	public abstract Person findPerson(String userId);

	public Set<String> getAllFollowers(Person person);

	public abstract void follow(Person follow, String string);

	public abstract Counter getCounts(String userId);
}
