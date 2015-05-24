package its.tsid.projectNAME.cleaning;

import java.util.ArrayList;
import java.util.List;

import org.bson.BasicBSONObject;

public class CleanProcesses {

	/**
	 * Return TRUE if the DB object has a valid location or it could be
	 * interpreted
	 * 
	 * @param d
	 * @return TRUE for valid DBObject
	 */
	public static boolean validableLocation(Object d) {
		List<String> conditions = new ArrayList<>();
		conditions.add("coordinates");
		conditions.add("place");
		conditions.add("userLocation");
		conditions.add("userGeo_Enabled ");
		conditions.add("userTime_zone");
		conditions.add("lang");

		boolean check = false;

		for (String cond : conditions) {
			if (((BasicBSONObject) d).get(cond) != null) {
				check = true;
			}
		}
		return check;
	}

	public static boolean europeLocation(Object d) {
		List<String> europeanCountyCode = new ArrayList<>();
		europeanCountyCode.add("");
		europeanCountyCode.add("");
		europeanCountyCode.add("");
		europeanCountyCode.add("");
		europeanCountyCode.add("");

		boolean check = false;
		for (String country : europeanCountyCode) {
			String tweetCounty = ((BasicBSONObject) d).get("place").toString();
			if (tweetCounty.contains(country)) {
				check = true;
			}
		}
		return check;
	}

}
