package its.tsid.projectNAME.cleaning;

import java.util.List;

import com.mongodb.BasicDBObject;

import its.tsid.projectNAME.dataAccess.DbAccess;

public class Program {

	public static void main(String[] args) {
		String connectionString = "localhost, 27017";
		List<BasicDBObject> cleaning = DbAccess.getData(connectionString,
				"tweets");

		// removing all tweets with no validable field for a location
		// TODO: Import list from db.util.conditions
		String[] validableLocations = new String[] {
				// "coordinates"
				"place", "userLocation",
				// "userGeo_Enabled",
				"userTime_zone", "lang" };

		for (BasicDBObject d : cleaning) {
			if (!CleanProcesses.validableLocation(d, validableLocations)) {
				cleaning.remove(d);
			}
		}

		// removing all non valid countries (based on iso codes)
		// TODO: Import list from db.util.countrycodes
		String[] europeanCountyCode = new String[] { "BE", "BG", "CZ", "DK",
				"DE", "EE", "IE", "EL", "ES", "FR", "HR", "IT", "CY", "LV",
				"LT", "LU", "HU", "MT", "NL", "AT", "PL", "PT", "RO", "SI",
				"SK", "FI", "SE", "UK" };

		for (Object d : cleaning) {
			if (!CleanProcesses.europeLocation(d, europeanCountyCode)) {
				cleaning.remove(d);
			}
		}

		// Removing invalid languages (all non-european)
		// TODO: Import list from db.util.europeanlanguages
		String[] europeanLanguages = new String[] { "bg", "es", "cs", "da",
				"de", "et", "el", "en", "fr", "ga", "hr", "it", "lv", "lt",
				"hu", "mt", "np", "pl", "pt", "ro", "sk", "sl", "fi", "sv" };

		for (Object d : cleaning) {
			if (!CleanProcesses.europeLanguages(d, europeanLanguages)) {
				cleaning.remove(d);
			}
		}

		// adding up all the tweets still valid to the collection "Cleaning"
		for (BasicDBObject dbo : cleaning) {
			DbAccess.putObj(connectionString, "cleaning", dbo);
		}
	}

}
