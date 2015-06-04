using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace DataAccessTest
{
    using MongoDB.Bson;
    using MongoDB.Bson.Serialization.Attributes;

    public class MongoEntity
    {
        [BsonId]
        public ObjectId Id { get; set; }
    }
}
