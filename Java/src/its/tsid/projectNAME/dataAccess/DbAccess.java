package its.tsid.projectNAME.dataAccess;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import com.mongodb.MongoClient;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;

public class DbAccess {

	public static List<BasicDBObject> getData(String client, String collection) {
		List<BasicDBObject> dbo = new ArrayList<>();
		try {
			// MongoClient mongoClient = new MongoClient("localhost", 27017);
			MongoClient mongoClient = new MongoClient(client);
			DB db = mongoClient.getDB("test");

			// DBCollection collection = db.getCollection("tweets");
			DBCollection cll = db.getCollection(collection);

			DBCursor cursor = cll.find();
			try {
				while (cursor.hasNext()) {
					dbo.add((BasicDBObject) cursor.next());
				}
			} finally {
				cursor.close();
			}

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}

		return dbo;
	}

	public static List<BasicDBObject> getSpecificData(String client,
			String collection, String progLanguage) {
		List<BasicDBObject> dbo = new ArrayList<>();
		try {
			// MongoClient mongoClient = new MongoClient("localhost", 27017);
			MongoClient mongoClient = new MongoClient(client);
			DB db = mongoClient.getDB("test");

			// DBCollection collection = db.getCollection("tweets");
			DBCollection cll = db.getCollection(collection);

			//String query = null;
			//DBCursor cursor = cll.aggregate( [ { $match : { programmingLanguage : proglanguage } } ]);
			//try {
			//	while (cursor.hasNext()) {
			//		dbo.add((BasicDBObject) cursor.next());
			//	}
			//} finally {
			//	cursor.close();
			//}

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}

		return dbo;
	}

	public static void putObj(String client, String collection, Object d) {
		try {
			MongoClient mongo = new MongoClient(client);
			DB db = mongo.getDB("test");
			DBCollection cll = db.getCollection(collection);

			cll.insert((BasicDBObject) d);

		} catch (UnknownHostException e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}

	}

}
