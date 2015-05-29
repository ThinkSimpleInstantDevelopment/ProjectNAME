package its.tsid.projectNAME.cleaning;

import java.util.HashMap;
import java.util.Map;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import its.tsid.projectNAME.dataAccess.Checker;

public class Cleaner extends Checker {

	// TODO: Import list from db.util.countrycodes
	String[] europeanCountyCode = new String[] { "BE", "BG", "CZ", "DK", "DE",
			"EE", "IE", "EL", "ES", "FR", "HR", "IT", "CY", "LV", "LT", "LU",
			"HU", "MT", "NL", "AT", "PL", "PT", "RO", "SI", "SK", "FI", "SE",
			"UK" };

	// TODO: Import list from db.util.europeanlanguages
	String[] europeanLanguages = new String[] { "bg", "es", "cs", "da", "de",
			"et", "el", "en", "fr", "ga", "hr", "it", "lv", "lt", "hu", "mt",
			"np", "pl", "pt", "ro", "sk", "sl", "fi", "sv" };

	@Override
	public BasicDBObject validator(DBObject input) {
		// removing all tweets with no validable field for a location
		// TODO: Import list from db.util.conditions
		String[] validableLocations = new String[] {
				// "coordinates"
				"place", "userLocation",
				// "userGeo_Enabled",
				"userTime_zone", "lang" };

		if (!CleanProcesses.validableLocation(input, validableLocations)) {
			return null;
		}

		// removing all non valid countries (based on iso codes)
		if (!CleanProcesses.europeLocation(input, europeanCountyCode)) {
			return null;
		}

		// Removing invalid languages (all non-european)
		if (!CleanProcesses.europeLanguages(input, europeanLanguages)) {
			return null;
		}

		// Building output
		String nation = inferNation(input);
		input.get("text").toString();
		double bv = baseValue(input.get("text").toString(),
				input.get("ProgrammingLanguage").toString(), nation);
		boolean hashtags = false;
		BasicDBObject output = new BasicDBObject();
		output.put("ProgLang", input.get("ProgrammingLanguage"));
		output.put("Date", input.get("CreatedAt"));
		output.put("Favorite", input.get("Favorite"));
		output.put("Retweet", input.get("Retweet"));
		output.put("Followers", input.get("UserFollowers"));
		output.put("Friends", input.get("UserFriends"));
		output.put("BaseValue", bv);
		output.put("Nation", nation);
		output.put("Hashtags", hashtags);

		return output;
	}

	/**
	 * Infer the tweet's nation based on location or language (if the location
	 * is not avaible or not in an european country) An european language is
	 * accepted if the location is unavaible to guess the user's nationality.
	 * 
	 * @param input
	 *            : original tweet
	 * @return iso code for the nation or NULL if not inferable
	 */
	private String inferNation(DBObject input) {
		String language = input.get("Lang").toString();
		String place = input.get("Place").toString();
		String langUser = input.get("UserLang").toString();
		String location = input.get("location").toString();

		for (String s : europeanCountyCode)
			if (place.contains(s)) {
				return s;
			} else {
				if (location.contains(s)) {
					return s;
				}
			}

		Map<String, String> nationLanguages = new HashMap<>();
		// TODO: implement nationLanguages map to track nations from languages
		// TODO: import map from db
		for (String s : europeanLanguages)
			if (language.contains(s) || langUser.contains(s)) {
				return nationLanguages.get(s);
			}

		return null;
	}

	/**
	 * Checks the text camp for words in the white and blacklist for that
	 * programming language for that nation, calculates the base value for
	 * analysis
	 * 
	 * @param input
	 *            : DBObject that contains the text
	 * @return : double value representing the base value
	 */
	public double baseValue(String text, String progLang, String nation) {
		double bv = 0.5;
		String[] white = whitelist(progLang, nation);
		String[] black = blacklist(progLang, nation);
		for (String s : white) {
			if (text.contains(s)) {
				bv += 0.15;
			}
		}
		for (String s : black) {
			if (text.contains(s)) {
				bv -= 0.15;
			}
		}

		return bv;
	}

	private String[] blacklist(String progLang, String nation) {
		// TODO Auto-generated method stub
		return null;
	}

	private String[] whitelist(String progLang, String nation) {
		// TODO Auto-generated method stub
		return null;
	}

}
