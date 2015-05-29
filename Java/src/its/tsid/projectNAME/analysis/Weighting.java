package its.tsid.projectNAME.analysis;

import java.util.Date;
import java.util.Map;
import com.mongodb.BasicDBObject;

public class Weighting {

	public static BasicDBObject weight(Map<String, Double> weights,
			BasicDBObject bson) {
		double value = (double) weights.get("baseValue");
		value *= (1.0 + ((Integer
				.parseInt(bson.get("FavoriteCount").toString()) / 200) * weights
				.get("favorites")));
		value *= (1.0 + (Integer.parseInt(bson.get("retweetCount").toString()) * weights
				.get("retweets")));
		value *= (1.0 + (Integer.parseInt(bson.get("hashtags").toString()) * weights
				.get("hashtags")));
		value *= (1.0 + ((Integer.parseInt(bson.get("userFriendCount")
				.toString()) / 1000) * weights.get("friends")));
		value *= (1.0 + ((Integer.parseInt(bson.get("userFollowerCount")
				.toString()) / 10000) * weights.get("followers")));

		BasicDBObject output = new BasicDBObject();
		Date date = (Date) bson.get("CreatedAt");
		output.put("Period", date);
		output.put("Nation", bson.get("Nation").toString());
		output.put("ProgrammingLanguage", bson.get("programmingLanguage")
				.toString());
		output.put("Value", (int) value);

		return output;

	}
}
