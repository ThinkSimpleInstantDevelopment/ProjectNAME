package its.tsid.projectNAME.dataAccess;

import its.tsid.projectNAME.cleaning.Tweets;

import java.util.ArrayList;
import java.util.List;

import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.WriteConcern;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;
import com.mongodb.ServerAddress;

public class DbAccess {

	public static Object[] getTweets() {
		List<Tweets> tweets = new ArrayList<>();
		try {
			MongoClient mongoClient = new MongoClient("localhost", 27017);
			DB db = mongoClient.getDB("test");

			// TODO get tweets from db

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}

		return tweets.toArray();
	}

}
