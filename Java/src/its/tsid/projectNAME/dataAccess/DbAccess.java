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

	/**
	 * Deprecated, use "processCollection" instead for better ram performance
	 * @param client : mongo DB to be used
	 * @param collection : collection wanted
	 * @return List of all DBObject in the db.collection
	 */
	@Deprecated
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

	/**
	 *  Takes a connection string to a mongo db, two collection and a checker to apply to each element in the origin collection.
	 * @param client : mongodb connection string (es: localhost, 27017)
	 * @param origin : collection that needs to be analyzed
	 * @param destination : collection where the analyzed datas will be stored
	 * @param checker : Checker class with analyzing processes (returns NULL if the datas are not valid) 
	 */
	public static void processCollections(String client, String origin,
			String destination, Checker checker) {

		try {
			MongoClient mongoclient = new MongoClient(client);
			DB db = mongoclient.getDB("test");
			DBCollection cllOrigin = db.getCollection(origin);
			DBCollection cllDestination = db.getCollection(destination);

			DBCursor cursor = cllOrigin.find();
			try {
				while (cursor.hasNext()) {
					//TODO: use lambda expression for better code optimization
					cllDestination.insert(checker.validator(cursor.next()));
				}
			} finally {
				cursor.close();
			}

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}

	/*
	 * public static List<BasicDBObject> getSpecificData(String client, String
	 * collection, String progLanguage) { List<BasicDBObject> dbo = new
	 * ArrayList<>(); try { // MongoClient mongoClient = new
	 * MongoClient("localhost", 27017); MongoClient mongoClient = new
	 * MongoClient(client); DB db = mongoClient.getDB("test");
	 * 
	 * // DBCollection collection = db.getCollection("tweets"); DBCollection cll
	 * = db.getCollection(collection); cll.getFullName(); //TODO correct query
	 * builder //String query = null; //DBCursor cursor = cll.aggregate( [ {
	 * $match : { programmingLanguage : proglanguage } } ]); //try { // while
	 * (cursor.hasNext()) { // dbo.add((BasicDBObject) cursor.next()); // } //}
	 * finally { // cursor.close(); //}
	 * 
	 * } catch (Exception e) { System.err.println(e.getClass().getName() + ": "
	 * + e.getMessage()); }
	 * 
	 * return dbo; }
	 */

	
	/**
	 *  Deprecated, use the "processCollections" for better memory usage 
	 * @param client : mongodb connection string (es: "localhost, 27017")
	 * @param collection : destination collection
	 * @param d : object to be added to the collection (will be cast to BasicDBObject)
	 */
	@Deprecated
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
