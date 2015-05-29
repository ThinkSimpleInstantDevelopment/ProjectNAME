package its.tsid.projectNAME.analysis;

import java.util.Map;

public class Weighting {

	/**
	 * Calculates the weight of the tweet based on a map of values for prefixed
	 * areas
	 * 
	 * @param weights
	 *            : map of key-value with the weights of each part
	 * @param favorites
	 *            : how many times the tweet has been starred as favorite
	 * @param retweets
	 *            : how many times the tweet has been retweeted
	 * @param hashtags
	 *            : true if the hashtag contains the programming language, else
	 *            false
	 * @param friends
	 *            : how many friends the user has
	 * @param followers
	 *            : how many followers the user has
	 * @return an int (truncated) value for the tweet, simbolizing its weight on
	 *         the final stats
	 */
	public static int weight(Map<String, Double> weights, double baseValue,
			int favorites, int retweets, boolean hashtags, int friends,
			int followers) {
		double value = baseValue;
		value *= (1.0 + (favorites / 200) * weights.get("favorites"));
		value *= (1.0 + (retweets * weights.get("retweets")));
		value *= (1.0 + ((hashtags ? 1 : 0) * weights.get("hashtags")));
		value *= (1.0 + (friends / 1000) * weights.get("friends"));
		value *= (1.0 + (followers / 10000) * weights.get("followers"));

		return (int) value;

	}
}
