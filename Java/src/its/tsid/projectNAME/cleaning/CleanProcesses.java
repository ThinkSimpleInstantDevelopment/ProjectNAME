package its.tsid.projectNAME.cleaning;


import java.util.ArrayList;
import java.util.List;

import org.bson.BasicBSONObject;

public class CleanProcesses {
	
	
	/**
	 * Return TRUE if the DB object has a valid location or it could be interpreted
	 * @param d
	 * @return TRUE for valid DBObject
	 */
	public static boolean location(Object d){
		List<String> conditions = new ArrayList<>();
		conditions.add("coordinates");
		conditions.add("Place");
		conditions.add("userLocation");
		conditions.add("userGeo_Enabled ");
		conditions.add("userTime_zone");
		conditions.add("lang");
		
		boolean check = false;

		for (String cond : conditions){
			if(((BasicBSONObject) d).get(cond) != null){
				check = true;
			}
		}
		return check;
	}
	
	
	
	

}
