using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DataAccessTest
{
    using MongoDB.Bson;
    using MongoDB.Driver;
    using MongoDB.Driver.Builders;
    using MongoDB.Driver.GridFS;
    using MongoDB.Driver.Linq;

    class Program
    {
        public static void Main(string[] args)
        {
            //// Warning: The following code is an example without the appropriate
            //// Exception handling
            var connectionString = "mongodb://dataretrieval:divanoornitorincoverde1@192.168.1.6";

            //// Get a thread-safe client object by using a connection string
            var mongoClient = new MongoClient(connectionString);

            //// Get a reference to the "retrogames" database object from the Mongo server object
            var databaseName = "test";
            var db = mongoClient.GetDatabase(databaseName);

            //// Get a reference to the "games" collection object from the Mongo database object
            var games = db.GetCollection<Stats>("test");

            var gameQuery = Query<Stats>.EQ(g => g.ProgrammingLanguage, "java");
            var foundGame = games.FindOne(gameQuery);
            Console.WriteLine(foundGame.Id + " " + foundGame.Lang +" "+foundGame.ProgrammingLanguage);
        }
    }
}
