using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using NeoXam.Web.Models;
using System.Net.Http;
using System.Net.Http.Headers;
using Newtonsoft.Json.Linq;
using System.Web.UI.WebControls;

namespace NeoXam.Web.Controllers
{   
    public class SalairesController : Controller
    {
        private ApplicationDbContext db = new ApplicationDbContext();

        // GET: Salaires
        public ActionResult Index()
        {
            HttpClient Client = new HttpClient();
            Client.DefaultRequestHeaders.Accept.Add(new System.Net.Http.Headers.MediaTypeWithQualityHeaderValue("application/json"));
            HttpResponseMessage response = Client.GetAsync("http://localhost:18080/NeoXamPiDev-web/rest/salaires").Result;
            if (response.IsSuccessStatusCode)
            {
                ViewBag.result = response.Content.ReadAsAsync<IEnumerable<Models.salaire>>().Result;

            }

            else
            {
                ViewBag.result = "error";
            }
            return View(ViewBag.result);
        }

        // GET: Salaires/Details/5
        public ActionResult Details(int? id)
        {
            Models.salaire salaire1 = null;

            HttpClient Client = new HttpClient();
            Client.DefaultRequestHeaders.Accept.Add(new System.Net.Http.Headers.MediaTypeWithQualityHeaderValue("application/json"));
            HttpResponseMessage response = Client.GetAsync("http://localhost:18080/NeoXamPiDev-web/rest/salaires/getSalaire/" + id).Result;
            if (response.IsSuccessStatusCode)
            {
                salaire1 = response.Content.ReadAsAsync<Models.salaire>().Result;

            }

            else
            {
                ViewBag.result = "error";
            }



            return View(salaire1);
        }

        // GET: Salaires/Create
        public ActionResult Create()
        {
            return View();
        }

        // POST: Salaires/Create
        // To protect from overposting attacks, please enable the specific properties you want to bind to, for 
        // more details see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        public ActionResult Create(salaire salaire)
        {
            try
            {

                HttpClient Client = new HttpClient();
                HttpResponseMessage response = Client.PostAsJsonAsync<salaire>("http://localhost:18080/NeoXamPiDev-web/rest/salaires", salaire).ContinueWith((postTask) => postTask.Result.EnsureSuccessStatusCode()).Result;

                if (response.IsSuccessStatusCode)
                    return RedirectToAction("Index");
                else
                    return View();
            }
            catch
            {
                return View();
            }
        }

        // GET: Salaires/Edit/5
        public ActionResult Edit(int id)
        {
            Models.salaire salaire = null;

            HttpClient Client = new HttpClient();
            Client.DefaultRequestHeaders.Accept.Add(new System.Net.Http.Headers.MediaTypeWithQualityHeaderValue("application/json"));
            HttpResponseMessage response = Client.GetAsync("http://localhost:18080/NeoXamPiDev-web/rest/salaires/getSalaire/" + id).Result;
            if (response.IsSuccessStatusCode)
            {
                salaire = response.Content.ReadAsAsync<Models.salaire>().Result;

            }

            else
            {
                ViewBag.result = "error";
            }



            return View(salaire);
        }

        // POST: Salaires/Edit/5
        // To protect from overposting attacks, please enable the specific properties you want to bind to, for 
        // more details see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        public ActionResult Edit(int id,salaire salaire)
        {
            try
            {

                HttpClient Client = new HttpClient();
                HttpResponseMessage response = Client.PutAsJsonAsync<salaire>("http://localhost:18080/NeoXamPiDev-web/rest/salaires/"+id, salaire).ContinueWith((postTask) => postTask.Result.EnsureSuccessStatusCode()).Result;

                if (response.IsSuccessStatusCode)
                    return RedirectToAction("Index");
                else
                    return View();
            }
            catch
            {
                return View();
            }
        }

        // GET: Salaires/Delete/5
        public ActionResult Delete(int id)
        {
            Models.salaire salaire = null;

            HttpClient Client = new HttpClient();
            Client.DefaultRequestHeaders.Accept.Add(new System.Net.Http.Headers.MediaTypeWithQualityHeaderValue("application/json"));
            HttpResponseMessage response = Client.GetAsync("http://localhost:18080/NeoXamPiDev-web/rest/salaires/getSalaire/" + id).Result;
            if (response.IsSuccessStatusCode)
            {
                salaire = response.Content.ReadAsAsync<Models.salaire>().Result;

            }

            else
            {
                ViewBag.result = "error";
            }



            return View(salaire);
        }

        // POST: Salaires/Delete/5
        [HttpPost]
        public ActionResult Delete(int id, FormCollection collection)
        {
            try
            {
                HttpClient Client = new HttpClient();
                Client.DefaultRequestHeaders.Accept.Add(new System.Net.Http.Headers.MediaTypeWithQualityHeaderValue("application/json"));
                HttpResponseMessage response = Client.DeleteAsync("http://localhost:18080/NeoXamPiDev-web/rest/salaires/" + id).Result;
                return RedirectToAction("Index");
            }
            catch
            {
                return View();
            }
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }

    }

}
