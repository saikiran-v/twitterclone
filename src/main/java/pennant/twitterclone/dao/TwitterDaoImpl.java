/**
 * 
 */
package pennant.twitterclone.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import pennant.twitterclone.model.Counter;
import pennant.twitterclone.model.Person;
import pennant.twitterclone.model.Tweet;

/**
 * @author saikiran.v
 *
 */
@Repository
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class TwitterDaoImpl implements TwitterDao {
	@Autowired
	private JdbcTemplate jTemp;
	// To got Person Data
	private ResultSetExtractor<Person> rm = new ResultSetExtractor<Person>() {

		public Person extractData(ResultSet rs) throws SQLException, DataAccessException {
			Person person = null;
			if (rs.next()) {
				person = new Person();
				person.setUserID(rs.getString(1));
				person.setPassword(rs.getString(2));
				person.setFullName(rs.getString(3));
				person.setEmail(rs.getString(4));
				person.setJoined(rs.getDate(5));
				person.setActive(rs.getBoolean(6));
				/*
				 * if (rs.getInt(6) == 1) { person.setActive(true); } else {
				 * person.setActive(false); }
				 */
			}
			return person;
		}
	};

	private ResultSetExtractor<Set<String>> allFollowers = new ResultSetExtractor<Set<String>>() {

		public Set<String> extractData(ResultSet rs) throws SQLException, DataAccessException {
			Set<String> allFollowersForAPerson = new HashSet<String>();
			while (rs.next()) {
				allFollowersForAPerson.add(rs.getString(1));

			}
			return allFollowersForAPerson;
		}
	};
	private ResultSetExtractor<Set<Tweet>> allFollowersTweets = new ResultSetExtractor<Set<Tweet>>() {

		public Set<Tweet> extractData(ResultSet rs) throws SQLException, DataAccessException {
			Set<Tweet> allFollowersForAPerson = new HashSet<Tweet>();

			while (rs.next()) {
				Tweet t = new Tweet();
				t.setTweetId(rs.getInt(1));
				t.setUser(rs.getString(2));
				t.setMessage(rs.getString(3));
				t.setCreated(rs.getDate(4));
				allFollowersForAPerson.add(t);
			}
			return allFollowersForAPerson;
		}
	};

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pennant.twitterclone.dao.TwitterDao#loginCheck(pennant.twitterclone.model
	 * .Person)
	 */

	public boolean loginCheck(Person person) {

		Integer result = jTemp.queryForObject("select count(*) from t_person where user_id='" + person.getUserID()
				+ "' and password='" + person.getPassword() + "'", Integer.class);

		if (result == 1) {
			return true;
		} else
			return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pennant.twitterclone.dao.TwitterDao#findTweets(java.lang.String)
	 */
	public Set<Tweet> findTweets(String userId) {
		Person person = findPerson(userId);
		Set<Tweet> listOfTweets = new HashSet<Tweet>();

		if (person != null) {
			Set<String> query = jTemp.query(
					"select following_id from t_person p,t_following f where p.user_id=f.user_id and p.user_id=?",
					allFollowers, person.getUserID());
                       
			for (String string : query) {
				Set<Tweet> query2 = jTemp.query("select * from t_tweet where user_id=?", allFollowersTweets, string);
				for (Tweet tweet : query2) {
					listOfTweets.add(tweet);
				}
			}

		}
		return listOfTweets;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pennant.twitterclone.dao.TwitterDao#registerUser(pennant.twitterclone.
	 * model.Person)
	 */
	public boolean registerUser(Person person) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String format = sdf.format(new Date());

		int result = jTemp.update("insert into t_person values(?,?,?,?,convert(datetime,'" + format + "'),?)",
				person.getUserID(), person.getPassword(), person.getFullName(), person.getEmail(), 1);
		if (result == 1) {
			return true;
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pennant.twitterclone.dao.TwitterDao#saveTweet(pennant.twitterclone.model.
	 * Tweet)
	 */

	public void saveTweet(Tweet tweetMessage) {
		// tweet_id , user_id , message varchar(140),created DateTime

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String format = sdf.format(tweetMessage.getCreated());

		// insert into t_tweet values('saikiran1','hello
		// world',convert(datetime,'2018-04-30 04:19:23'))
		int results = jTemp.update("insert into t_tweet values(?,?,convert(datetime,'" + format + "'))",
				tweetMessage.getUser(), tweetMessage.getMessage());
		if (results == 1)
			System.out.println("---Tweet Posted----");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pennant.twitterclone.dao.TwitterDao#findPerson(java.lang.String)
	 */
	public Person findPerson(String userId) {

	//	jTemp.query("select * from t_person where user_id=?", new Object[]{userId}, new int[]{Types.VARCHAR}, rm);
		
		return jTemp.query("select * from t_person where user_id=?", rm, userId);
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pennant.twitterclone.dao.TwitterDao#follow(pennant.twitterclone.model.
	 * Person, int)
	 */
	public void follow(Person followingTo, String followingId) {
		jTemp.update("insert into t_following values(?,?)", followingId, followingTo.getUserID());

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pennant.twitterclone.dao.TwitterDao#getAllFollowers()
	 */
	public Set<String> getAllFollowers(Person person) {

		return jTemp.query(
				"select following_id from t_person p INNER JOIN t_following f  on p.user_id=f.user_id and p.user_id=?",
				allFollowers, person.getUserID());

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pennant.twitterclone.dao.TwitterDao#getAllFollowings(pennant.twitterclone
	 * .model.Person)
	 */
	public Set<Person> getAllFollowings(Person person) {

		return null;
	}

	public Counter getCounts(String userId) {

		int tweetsCount = jTemp.queryForObject("select count(*)from t_tweet where user_id='" + userId + "'",
				Integer.class);
		int followingCount = jTemp.queryForObject(
				"select count(following_id) from t_following f where f.user_id='" + userId + "'", Integer.class);
		int followers = jTemp.queryForObject(
				"select count(following_id) from t_following f where f.following_id='" + userId + "'", Integer.class);

		Counter c = new Counter();
		c.setTweetsCount(tweetsCount);
		c.setFollowingCount(followingCount);
		c.setFollowersCount(followers);
		return c;

	}
}
