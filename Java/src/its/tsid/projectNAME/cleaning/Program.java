package its.tsid.projectNAME.cleaning;

import java.util.List;

import com.mongodb.BasicDBObject;

import its.tsid.projectNAME.dataAccess.DbAccess;

public class Program {

	public static void main(String[] args) {
		List<BasicDBObject> datas = DbAccess.getData("localhost, 27071",
				"tweets");
		for (Object d : datas) {
			if (CleanProcesses.location(d)) {
				DbAccess.putObj("localhost, 27071", "cleaning", d);
			}
		}

	}

}
