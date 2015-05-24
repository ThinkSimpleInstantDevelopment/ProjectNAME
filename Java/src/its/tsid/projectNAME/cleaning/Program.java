package its.tsid.projectNAME.cleaning;

import java.util.ArrayList;
import java.util.List;

import com.mongodb.BasicDBObject;

import its.tsid.projectNAME.dataAccess.DbAccess;

public class Program {

	public static void main(String[] args) {
		List<BasicDBObject> cleaningFirstStep = DbAccess.getData("localhost, 27071",
				"tweets");
		List<BasicDBObject> cleaningSecondStep = new ArrayList<>();
		
		for (Object d : cleaningFirstStep) {
			if (CleanProcesses.europeLocation(d)) {
				cleaningSecondStep.add((BasicDBObject) d);
			}
		}
		
		for (Object d : cleaningSecondStep){
			if (CleanProcesses.validableLocation(d)){
				DbAccess.putObj("localhost, 27071", "cleaning", d);
			}
		}
		

	}

}
