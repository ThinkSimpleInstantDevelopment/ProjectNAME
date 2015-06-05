package its.tsid.projectNAME.analysis.test;

import static org.junit.Assert.*;
import its.tsid.projectNAME.analysis.Analyzer;

import org.junit.Test;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class AnalyzerTest {

	@Test
	public void test() {
		DBObject input = new BasicDBObject();
		input.put("Nation", "IT");
		input.put("ProgLang", "Java");
		input.put("Date", "27-01-2014");
		input.put("BaseValue", 1.0);
		input.put("Favorites", 0);
		input.put("Retweets", 0);
		input.put("Hashtags", false);
		input.put("Friends", 0);
		input.put("Followers", 0);
		
		
		DBObject expectedOutput = new BasicDBObject();
		expectedOutput.put("Nation", "IT");
		expectedOutput.put("ProgLang", "Java");
		expectedOutput.put("Date", "27-01-2014");
		expectedOutput.put("Value", 1.0);
		
		Analyzer checker = new Analyzer();
		DBObject output = checker.validator(input);

		for(String s : output.keySet()){
			assertEquals(expectedOutput.get(s), output.get(s));
		}
	}

}
