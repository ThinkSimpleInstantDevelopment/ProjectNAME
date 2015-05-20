using DataAccess;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

namespace PresentationFrontEnd.Controllers
{
    public class StatController : ApiController
    {
        // GET: api/Stat
        public IEnumerable<Stat> GetStat()
        {
            DataAccess.DataAccess data = new DataAccess.DataAccess();
            return data.GetStat();
        }

        // GET: api/Stat/5
        public string Get(int id)
        {
            return "value";
        }

        // POST: api/Stat
        public void Post([FromBody]string value)
        {
        }

        // PUT: api/Stat/5
        public void Put(int id, [FromBody]string value)
        {
        }

        // DELETE: api/Stat/5
        public void Delete(int id)
        {
        }
    }
}
