package its.tsid.projectNAME.cleaning;

import java.util.ArrayList;
import java.util.List;

import com.mongodb.BasicDBObject;

import its.tsid.projectNAME.dataAccess.DbAccess;

public class Program {

	public static void main(String[] args) {
		String connectionString = "localhost, 27017";
		List<BasicDBObject> cleaning = DbAccess.getData(connectionString,
				"tweets");

		//removing all tweets with no validable field for a location
		List<String> validableLocations = new ArrayList<>();
		// conditions.add("coordinates");
		validableLocations.add("place");
		validableLocations.add("userLocation");
		// conditions.add("userGeo_Enabled ");
		validableLocations.add("userTime_zone");
		validableLocations.add("lang");

		for (Object d : cleaning) {
			if (!CleanProcesses.validableLocation(d, validableLocations)) {
				cleaning.remove(d);
			}
		}

		// removing all non valid countries (based on iso codes)
		List<String> europeanCountyCode = new ArrayList<>();
		europeanCountyCode.add("BE");
		europeanCountyCode.add("BG");
		europeanCountyCode.add("CZ");
		europeanCountyCode.add("DK");
		europeanCountyCode.add("DE");
		europeanCountyCode.add("EE");
		europeanCountyCode.add("IE");
		europeanCountyCode.add("EL");
		europeanCountyCode.add("ES");
		europeanCountyCode.add("FR");
		europeanCountyCode.add("HR");
		europeanCountyCode.add("IT");
		europeanCountyCode.add("CY");
		europeanCountyCode.add("LV");
		europeanCountyCode.add("LT");
		europeanCountyCode.add("LU");
		europeanCountyCode.add("HU");
		europeanCountyCode.add("MT");
		europeanCountyCode.add("NL");
		europeanCountyCode.add("AT");
		europeanCountyCode.add("PL");
		europeanCountyCode.add("PT");
		europeanCountyCode.add("RO");
		europeanCountyCode.add("SI");
		europeanCountyCode.add("SK");
		europeanCountyCode.add("FI");
		europeanCountyCode.add("SE");
		europeanCountyCode.add("UK");

		for (Object d : cleaning) {
			if (!CleanProcesses.europeLocation(d, europeanCountyCode)) {
				cleaning.remove(d);
			}
		}

		
		//Removing invalid languages (all non-european)
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
