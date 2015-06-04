package its.tsid.projectNAME.analysis.test;

import static org.junit.Assert.*;
import its.tsid.projectNAME.analysis.Weighting;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class WeightingTest {

	@Test
	public void test() {
		Map<String, Double> weights = new HashMap<>();
		
		weights.put("baseValue", 1.0);
		weights.put("favorites", 10.0);
		weights.put("retweets", 100.0);
		weights.put("hashtags", 1000.0);
		weights.put("friends", 10000.0);
		weights.put("followers", 100000.0);
		
		int fav = 200;
		int rt = 1;
		boolean hash = true;
		int fr = 100;
		int fo = 1000;
		
		assertEquals(2222.0, Weighting.weight(weights, 1.0, fav, rt, hash, fr, fo), 0.0001);
		fav = 600;
		rt = 3;
		hash = false;
		fr = 3000;
		fo = 30000;
		assertEquals(3333.0, Weighting.weight(weights, 1.0, fav, rt, hash, fr, fo), 0.0001);
	}

}
