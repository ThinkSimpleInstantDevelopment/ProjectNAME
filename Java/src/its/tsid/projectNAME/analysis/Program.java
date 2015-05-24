package its.tsid.projectNAME.analysis;

import java.util.List;

import com.mongodb.BasicDBObject;

import its.tsid.projectNAME.dataAccess.DbAccess;

public class Program {

	public static void main(String[] args) {
		List<BasicDBObject> rawData = DbAccess.getData("localhost, 27017", "cleaning");
		

	}

}
