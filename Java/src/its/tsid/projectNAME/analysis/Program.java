package its.tsid.projectNAME.analysis;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import com.mongodb.BasicDBObject;
import its.tsid.projectNAME.dataAccess.DbAccess;
import its.tsid.projectNAME.analysis.Weighting;

public class Program {

	public static void main(String[] args) {
		String connectionString = "localhost, 27017";
		List<BasicDBObject> rawData = DbAccess.getData(connectionString,
				"cleaning");

		// Mappa dei pesi per poter pesare il tweet
		Map<String, Double> weights = new TreeMap<>();
		weights.put("baseValue", 0.5);
		weights.put("favorites", 0.05);
		weights.put("retweets", 0.01);
		weights.put("hashtags", 1.0);
		weights.put("friends", 0.01);
		weights.put("followers", 0.1);

		for (BasicDBObject dbo : rawData) {
			DbAccess.putObj(connectionString, "presentation",
					Weighting.weight(weights, dbo));
		}

	}

}
