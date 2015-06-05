package its.tsid.projectNAME.analysis;

import java.util.Map;
import java.util.TreeMap;
import its.tsid.projectNAME.analysis.Weighting;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

import its.tsid.projectNAME.dataAccess.Checker;

public class Analyzer extends Checker {

	@Override
	public BasicDBObject validator(DBObject input) {
		// TODO: Import map from db.util.weights
		// Weight map for the analysis
		Map<String, Double> weights = new TreeMap<String, Double>();

		Double d = 0.01;
		weights.put("favorites", d * 5);
		weights.put("retweets", d);
		weights.put("hashtags", d * 100);
		weights.put("friends", d);
		weights.put("followers", d * 10);

		// Calculating the tweets's weight
		double baseValue = (double) input.get("BaseValue");
		int favorites = (int) input.get("Favorites");
		int retweets = (int) input.get("Retweets");
		boolean hashtags = (boolean) input.get("Hashtags");
		int friends = (int) input.get("Friends");
		int followers = (int) input.get("Followers");

		double value = Weighting.weight(weights, baseValue, favorites,
				retweets, hashtags, friends, followers);

		// Generating output bson datas
		BasicDBObject output = new BasicDBObject();
		output.put("Date", input.get("Date"));
		output.put("Nation", input.get("Nation"));
		output.put("ProgLang", input.get("ProgLang"));
		output.put("Value", value);

		return output;
	}

}
