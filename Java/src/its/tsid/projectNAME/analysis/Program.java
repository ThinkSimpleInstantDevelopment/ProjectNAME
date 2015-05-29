package its.tsid.projectNAME.analysis;

import its.tsid.projectNAME.dataAccess.DbAccess;

public class Program {

	public static void main(String[] args) {
		String connectionString = "localhost, 27017";
		String origin = "cleaning";
		String destination = "analysis";
		Analyzer checker = new Analyzer();
		
		DbAccess.processCollections(connectionString, origin, destination, checker);
		

	}

}
