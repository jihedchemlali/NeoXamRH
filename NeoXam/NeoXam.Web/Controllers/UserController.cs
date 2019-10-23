
using NeoXam.Web.Models;
using Newtonsoft.Json.Linq;

using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Globalization;
using System.IO;
using System.Linq;
using System.Net.Http;
using System.Net.Http.Headers;
using System.Text;
using System.Web;
using System.Web.Mvc;

namespace NeoXam.Web.Controllers
{
    public class UserController : Controller
    {
       
        public JsonResult GetUserList(String clusterNumber, String typeAlgo)
        {
            IEnumerable<employe> result = null;
            HttpClient Client = new HttpClient();
            List<employe> liste = new List<employe>();
            Debug.WriteLine("clusterNumber=" + clusterNumber);

            Client.DefaultRequestHeaders.Accept.Add(new System.Net.Http.Headers.MediaTypeWithQualityHeaderValue("application/json"));
            HttpResponseMessage response = Client.GetAsync("http://localhost:18080/NeoXamPiDev-web/rest/projets/getMarkersListe/" + clusterNumber + "/" + typeAlgo).Result;

            if (response.IsSuccessStatusCode)
            {
                // ViewBag.result = response.Content.ReadAsAsync<IEnumerable<Models.User>>().Result;
                result = response.Content.ReadAsAsync<IEnumerable<employe>>().Result;

            }


            liste = result.ToList<employe>();

            return Json(liste, JsonRequestBehavior.AllowGet);

        }


        [HttpPost]
        public ActionResult GoogleMapLngLatJsonPost(address model)
        {
            //googleMapModel model = new googleMapModel();
            //model.Lat = Lat;
            //model.Lng = Lng;
            // Debug.WriteLine("Lat="+model.Lat+ "/Lng=" + model.Lng);
            return Json(model, JsonRequestBehavior.AllowGet);

        }







    }
}