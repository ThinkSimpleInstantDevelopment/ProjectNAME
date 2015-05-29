package its.tsid.projectNAME.cleaning;

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
	public static boolean validableLocation(DBObject input, String[] validableLocations) {
		boolean check = false;
		for (String cond : validableLocations) {
			if (input.get(cond) != null) {
				check = true;
			}
		}
		return check;
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
	 * Method to check if the tweet language is compatible with a list of iso code of european languages
	 * 
	 * @param d : tweet to be checked
	 * @param europeanLanguages : list of european languages iso code
	 * @return TRUE if the tweet is in a recognizable language
	 */
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
