package pennant.twitterclone.test;

import java.util.Date;
import java.util.Scanner;
import java.util.Set;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import pennant.twitterclone.config.AppConfig;
import pennant.twitterclone.model.Counter;
import pennant.twitterclone.model.Person;
import pennant.twitterclone.model.Tweet;
import pennant.twitterclone.service.TwitterService;
import pennant.twitterclone.service.TwitterServiceImpl;

/**
 * @author saikiran.v
 *
 */
public class Test {
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		AnnotationConfigApplicationContext cfs = new AnnotationConfigApplicationContext(AppConfig.class);
		TwitterService service = cfs.getBean(TwitterServiceImpl.class);

		Scanner sc = new Scanner(System.in);
		System.out.println("------------------Twitter-------------");
		System.out.println("1. Sign In ");
		System.out.println("2. Sign Up");
		int input = sc.nextInt();
		switch (input) {
		case 1:

			System.out.println("Enter your username");
			String username = sc.next();
			Person person = service.findPerson(username);

			System.out.println("Enter password");
			String password = sc.next();
			person.setPassword(password);
			if (service.loginCheck(person)) {
				System.out.println("Home Page");

				// Getting All Tweets From His Follwers

				boolean flag = true;
				while (flag) {
					System.out.println("1. Home");
					System.out.println("2. Profile");
					System.out.println("3. Follow Users");
					System.out.println("4. Display tweets");
					System.out.println("5. Tweet a user");
					System.out.println("6. Display followers");
					System.out.println("7. Logout");

					int input1 = sc.nextInt();
					switch (input1) {
					case 1:
						System.out.println("Welcome User " + person.getFullName());
						Counter counts = service.getCounts(person.getUserID());
						System.out.println(" No.of Tweets " + counts.getTweetsCount());
						System.out.println("No.Of Followers " + counts.getFollowersCount());
						System.out.println("No.Of.Followings " + counts.getFollowingCount());
						break;
					case 2:
						System.out.println("your Profile details");
						System.out.println("Full Name :" + person.getFullName());
						System.out.println("Email Id  :" + person.getEmail());
						System.out.println("User ID   :" + person.getUserID());
						
						
						break;
					case 3:
						System.out.println("Enter user id to follow");
						String userId = sc.next();
						Person findPerson = service.findPerson(userId);
						if (findPerson != null) {
							service.follow(findPerson, person.getUserID());
							System.out.println("your started following " + findPerson.getUserID());
						} else {
							System.out.println("Person Not Found");
						}
						break;
					case 4:
						System.out.println("********All Your   Followers  Twitters****** ");
						person.setTweets(service.findTweets(person.getUserID()));
						for (Tweet tweet2 : person.getTweets()) {
							System.out.println(tweet2);
							System.out.println("_______________________________________");
						}
						break;
					case 5:
						Tweet tweetMessage = new Tweet();
						System.out.println("Enter twitter Id to write tweet");
						String user_id = sc.next();
						Person findPerson2 = service.findPerson(user_id);
						System.out.println("Write Tweet ");
						sc.nextLine();
						String tweet = sc.nextLine();
						System.out.println(tweet + " ----------");
						Date javaDate = new Date();
						tweetMessage.setCreated(javaDate);
						tweetMessage.setMessage(tweet);
						tweetMessage.setUser(findPerson2.getUserID());
						service.saveTweet(tweetMessage);
						break;
					case 6:
						Set<String> allFollowers = service.getAllFollowers(person);
						System.out.println(" List of Followers");

						for (String string : allFollowers) {
							System.out.println("Name :" + string);
						}
						break;

					case 7:
						flag = false;
						break;
					}
				}

			} else {
				System.out.println("InValid PassWord");
			}

			break;
		case 2:
			System.out.println("Choose a usename");
			String newUsername = sc.next();
			System.out.println("Choose a password");
			String newPassword = sc.next();
			System.out.println("Enter Fullname");
			String fullName = sc.next();
			System.out.println("Enter email id");
			String email = sc.next();
			Person newPerson = new Person();

			newPerson.setFullName(fullName);
			newPerson.setEmail(email);

			newPerson.setUserID(newUsername);
			newPerson.setPassword(newPassword);
			boolean registerUser = service.registerUser(newPerson);
			if (registerUser) {
				System.out.println(" registration successfully completed ");
			} else {
				System.out.println("Try Again After SomeTime");
			}
			break;

		default:
			System.out.println("Choose appropriate value");
			break;

		}
		cfs.close();
		sc.close();

	}

}
