using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace PresentationFrontEnd.Controllers
{
    public class HomeController : Controller
    {
        public ActionResult Index()
        {
            ViewBag.Title = "Home Page";

            return View();
        }

        public ActionResult LineChart()
        {
            ViewBag.Title = "LineChart";

            return View();
        }

        public ActionResult Developers()
        {
            ViewBag.Title = "Developers";

            return View();
        }
    }
}
