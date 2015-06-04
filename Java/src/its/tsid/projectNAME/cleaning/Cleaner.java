package its.tsid.projectNAME.cleaning;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

import its.tsid.projectNAME.dataAccess.Checker;

public class Cleaner extends Checker {

	@Override
	public BasicDBObject validator(DBObject input) {
		// removing all tweets with no validable field for a location
		// TODO: Import list from db.util.conditions
		String[] validableLocations = new String[] {
				// "coordinates"
				"place", "user_location",
				// "userGeo_Enabled",
				//"user_time_zone", 
				"lang", "user_lang" };

		if (!CleanProcesses.validableLocation(input, validableLocations)) {
			return null;
		}

		// Building output
		String nation = CleanProcesses.inferNation(input);
		if (nation != null) {

			String text = input.get("text").toString();
			String progLang = input.get("programming_language").toString();
			double bv = CleanProcesses.baseValue(text, progLang, nation);

			String[] hash = (String[]) input.get("hashtags");
			boolean hashtags = CleanProcesses.hashtag(hash, progLang);

			// TODO: Get key name from db
			BasicDBObject output = new BasicDBObject();
			output.put("ProgLang", input.get("programming_language"));
			output.put("Date", input.get("created_at"));
			output.put("Favorite", input.get("favorite_count"));
			output.put("Retweet", input.get("retweet_count"));
			output.put("Followers", input.get("user_followers_count"));
			output.put("Friends", input.get("user_friends_count"));
			output.put("BaseValue", bv);
			output.put("Nation", nation);
			output.put("Hashtags", hashtags);

			return output;
		} else {
			return null;
		}
	}

}
