using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DataAccessTest
{
 
    using MongoDB.Bson;
    using MongoDB.Bson.Serialization.Attributes;
    using MongoDB.Driver;
 
    [BsonIgnoreExtraElements]
    public class Stats : MongoEntity
    {
        [BsonElement("ProgrammingLanguage")]
        public string ProgrammingLanguage { get; set; }

        [BsonElement("Created_at")]
        public string Created_at { get; set; }

        [BsonElement("Text")]
        public string Text { get; set; }

        [BsonElement("Lang")]
        public string Lang { get; set; }
    }
}
