package its.tsid.projectNAME.cleaning;

import java.util.HashMap;
import java.util.Map;

import org.bson.BasicBSONObject;

import com.mongodb.DBObject;

public class CleanProcesses {

	/**
	 * Method to check for validable location hint
	 * 
	 * @param input
	 *            : tweet to be checked
	 * @param validableLocations
	 *            : list of key that must be !null
	 * @return TRUE if the tweet has at least one valid location hint passed as @conditions
	 */
	public static boolean validableLocation(DBObject input,
			String[] validableLocations) {
		boolean check = false;
		for (String cond : validableLocations) {
			if (input.get(cond) != null) {
				check = true;
			}
		}
		return check;
	}

	/**
	 * Infer the tweet's nation based on location or language (if the location
	 * is not avaible or not in an european country) An european language is
	 * accepted if the location is unavaible to guess the user's nationality.
	 * Please note that this is prone to error due to twetter's users mentality.
	 * 
	 * @param input
	 *            : original tweet
	 * @return iso code for the nation or NULL if not inferable
	 */
	public static String inferNation(DBObject input) {

		// TODO: Import list from db.util.countrycodes
		String[] europeanCountryCode = new String[] { "BE", "BG", "CZ", "DK",
				"DE", "EE", "IE", "EL", "ES", "FR", "HR", "IT", "CY", "LV",
				"LT", "LU", "HU", "MT", "NL", "AT", "PL", "PT", "RO", "SI",
				"SK", "FI", "SE", "UK" };

		String place = input.get("place").toString();
		if (place != null) {
			for (String s : europeanCountryCode) {
				if (place.contains(s)) {
					return s;
				}
			}
		} else {
			String language = input.get("lang").toString();
			String langUser = input.get("user_lang").toString();

			if (language != null || langUser != null) {
				// TODO: import map from db
				Map<String, String> nationLanguages = new HashMap<>();
				nationLanguages.put("bg", "BG");
				nationLanguages.put("cs", "CZ");
				nationLanguages.put("da", "DK");
				nationLanguages.put("de", "DE");
				nationLanguages.put("et", "EE");
				nationLanguages.put("ga", "IE");
				nationLanguages.put("el", "EL");
				nationLanguages.put("es", "ES");
				nationLanguages.put("fr", "FR");
				nationLanguages.put("hr", "HR");
				nationLanguages.put("it", "IT");
				nationLanguages.put("lv", "LV");
				nationLanguages.put("lt", "LT");
				nationLanguages.put("hu", "HU");
				nationLanguages.put("mt", "MT");
				nationLanguages.put("nl", "NL");
				nationLanguages.put("pl", "PL");
				nationLanguages.put("pt", "PT");
				nationLanguages.put("ro", "RO");
				nationLanguages.put("sl", "SI");
				nationLanguages.put("sk", "SK");
				nationLanguages.put("fi", "FI");
				nationLanguages.put("sv", "SE");
				nationLanguages.put("en", "UK");

				for (String s : nationLanguages.keySet()) {
					if (language.contains(s) || langUser.contains(s)) {
						return nationLanguages.get(s);
					}
				}
			} else {
				String location = input.get("user_location").toString();
				if (location != null) {
					for (String s : europeanCountryCode) {
						if (location.contains(s)) {
							return s;
						}
					}
				}
			}

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
	public static double baseValue(String text, String progLang, String nation) {
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

	private static String[] blacklist(String progLang, String nation) {
		// TODO: Get blacklist for progLang in nation
		return new String[] {};
	}

	private static String[] whitelist(String progLang, String nation) {
		// TODO: Get whitelist for progLang in nation
		return new String[] {};
	}

	/**
	 * Method to check for the hashtag for that programming language
	 * 
	 * @param hash
	 *            : array with all the hashtags of the tweet
	 * @param progLang
	 *            : programming language that should be present
	 * @return TRUE if the hashtag is present or FALSE if it isn't
	 */
	public static boolean hashtag(String[] hash, String progLang) {
		for (String h : hash) {
			if (h.contains(progLang))
				return true;
		}
		return false;
	}

	/**
	 * Method to search for specific countries in the tweet's "place" and
	 * "user --> location" values
	 * 
	 * @param d
	 *            : Object to be controlled
	 * @param europeanCountyCode
	 *            : list of iso country codes to be searched
	 * @return TRUE if the tweet was sent from a country in the list provided
	 */
	@Deprecated
	public static boolean europeLocation(Object d, String[] europeanCountyCode) {
		boolean check = false;
		String tweetCountry = ((BasicBSONObject) d).get("place").toString();
		String userCountry = ((BasicBSONObject) d).getString("userLocation")
				.toString();

		for (String country : europeanCountyCode) {
			if (tweetCountry.contains(country) || userCountry.contains(country)) {
				check = true;
			}
		}
		return check;
	}

	/**
	 * Method to check if the tweet language is compatible with a list of iso
	 * code of european languages
	 * 
	 * @param d
	 *            : tweet to be checked
	 * @param europeanLanguages
	 *            : list of european languages iso code
	 * @return TRUE if the tweet is in a recognizable language
	 */
	@Deprecated
	public static boolean europeLanguages(Object d, String[] europeanLanguages) {
		boolean check = false;
		String tweetLang = ((BasicBSONObject) d).get("lang").toString();
		for (String language : europeanLanguages) {
			if (tweetLang.contains(language)) {
				check = true;
			}
		}

		return check;
	}
}
