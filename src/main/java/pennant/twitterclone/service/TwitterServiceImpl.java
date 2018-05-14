package pennant.twitterclone.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pennant.twitterclone.dao.TwitterDao;
import pennant.twitterclone.model.Counter;
import pennant.twitterclone.model.Person;
import pennant.twitterclone.model.Tweet;

/**
 * @author saikiran.v
 *
 */
@Service
public class TwitterServiceImpl implements TwitterService {

	@Autowired
	private TwitterDao twitterDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see pennant.twitterclone.service.TwitterService#loginCheck(pennant.
	 * twitterclone.model.Person)
	 */

	public void follow(Person follow, String follower) {
		twitterDao.follow(follow, follower);
	}

	public boolean loginCheck(Person person) {

		return twitterDao.loginCheck(person);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pennant.twitterclone.service.TwitterService#findTweets(java.lang.String)
	 */
	public Set<Tweet> findTweets(String userId) {
		// TODO Auto-generated method stub
		return twitterDao.findTweets(userId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pennant.twitterclone.service.TwitterService#registerUser(pennant.
	 * twitterclone.model.Person)
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see pennant.twitterclone.service.TwitterService#registerUser(pennant.
	 * twitterclone.model.Person)
	 */
	@Transactional
	public boolean registerUser(Person person) {
		return twitterDao.registerUser(person);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pennant.twitterclone.service.TwitterService#saveTweet(pennant.
	 * twitterclone.model.Tweet)
	 */
	@Transactional
	public void saveTweet(Tweet tweetMessage) {
		twitterDao.saveTweet(tweetMessage);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pennant.twitterclone.service.TwitterService#findPerson(java.lang.String)
	 */
	public Person findPerson(String userId) {

		return twitterDao.findPerson(userId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pennant.twitterclone.service.TwitterService#getAllFollowers(pennant.
	 * twitterclone.model.Person)
	 */

	public Set<String> getAllFollowers(Person person) {

		return twitterDao.getAllFollowers(person);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pennant.twitterclone.service.TwitterService#getCounts(java.lang.String)
	 */

	public Counter getCounts(String userId) {
		// TODO Auto-generated method stub
		return twitterDao.getCounts(userId);
	}
}
