package its.tsid.projectNAME.cleaning.test;

import static org.junit.Assert.*;
import its.tsid.projectNAME.cleaning.CleanProcesses;

import org.junit.Test;

import com.mongodb.BasicDBObject;

public class CleanProcessesTest {

	@Test
	public void testValidableLocation() {
		BasicDBObject test = new BasicDBObject();
		test.put("testing", "123");
		assert (CleanProcesses.validableLocation(test,
				new String[] { "testing" }));
		test.replace("testing", null);
		assertFalse(CleanProcesses.validableLocation(test,
				new String[] { "testing" }));

	}

	@Test
	public void testInferNation() {
		BasicDBObject test = new BasicDBObject();
		test.put("place", "IT");
		test.put("user_lang", "it");
		test.put("lang", "it");
		test.put("user_location", "IT");

		assertEquals("IT", CleanProcesses.inferNation(test));

		test.replace("place", null);
		assertEquals("IT", CleanProcesses.inferNation(test));

		test.replace("user_lang", null);
		assertEquals("IT", CleanProcesses.inferNation(test));

		test.replace("lang", null);
		assertEquals("IT", CleanProcesses.inferNation(test));

		test.replace("user_location", null);
		assertEquals(null, CleanProcesses.inferNation(test));

	}

	@Test
	public void testBaseValue() {
		extracted();
		
	}

	@SuppressWarnings("deprecation")
	private void extracted() {
		assertEquals(0.5, CleanProcesses.baseValue("java spacca!", "Java", "IT"));
	}

	@Test
	public void testHashtag() {
		assertTrue(CleanProcesses.hashtag(new String[]{"#Java", "#BungaBunga"}, "Java"));
		assertFalse(CleanProcesses.hashtag(new String[]{"#Jumanji"}, "Python"));
	}

	

}
