package its.tsid.projectNAME.cleaning.test;

import static org.junit.Assert.*;
import its.tsid.projectNAME.cleaning.Cleaner;

import org.junit.Test;

import com.mongodb.BasicDBObject;

public class CleanerTest {

	@Test
	public void testValidator() {
		BasicDBObject input = new BasicDBObject();
		input.put("text", "Adoro Java");
		input.put("programming_language", "Java");
		input.put("hashtags", new String[]{"#Java"});
		input.put("created_at", "27-01-2014");
		input.put("favorite_count", 10);
		input.put("retweet_count", 15);
		input.put("user_followers_count", 10000);
		input.put("user_friends_count", 1000);
		input.put("place", "IT");
		
		
		
		BasicDBObject expectedOutput = new BasicDBObject();
		expectedOutput.put("ProgLang", "Java");
		expectedOutput.put("Date", "27-01-2014");
		expectedOutput.put("Favorite", 10);
		expectedOutput.put("Retweet", 15);
		expectedOutput.put("Followers", 10000);
		expectedOutput.put("Friends", 1000);
		expectedOutput.put("BaseValue", 0.5);
		expectedOutput.put("Nation", "IT");
		expectedOutput.put("Hashtags", true);
		
		Cleaner checker = new Cleaner();
		assertEquals(expectedOutput, checker.validator(input));
	}

}
