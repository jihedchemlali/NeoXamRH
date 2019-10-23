using NeoXam.Domain.Entities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Net.Http.Headers;
using System.Web;
using System.Web.Mvc;

namespace NeoXam.Web.Controllers
{
    public class EquipeController : Controller
    {
        // GET: Equipe
        public ActionResult Index()
        {
            HttpClient Client = new HttpClient();
            // Client.BaseAddress = new Uri("http://localhost:18080");
            Client.DefaultRequestHeaders.Accept.Add(new System.Net.Http.Headers.MediaTypeWithQualityHeaderValue("application/json"));
            HttpResponseMessage response = Client.GetAsync("http://localhost:18080/NeoXamPiDev-web/rest/equipe/").Result;
            if (response.IsSuccessStatusCode)
            {
                ViewBag.result = response.Content.ReadAsAsync<IEnumerable<equipe>>().Result;

            }

            else
            {
                ViewBag.result = "error";
            }
            return View(ViewBag.result);
        }

        // GET: Equipe/Details/5
        public ActionResult Details(int id)
        {
            return View();
        }

        // GET: Equipe/Create
        public ActionResult Create()
        {
            HttpClient Client = new HttpClient();
            Client.BaseAddress = new Uri("http://localhost:18080/NeoXamPiDev-web/");
            Client.DefaultRequestHeaders.Accept.Add(new MediaTypeWithQualityHeaderValue("application/json"));
            HttpResponseMessage response = Client.GetAsync("rest/projets").Result;



            var jobs = response.Content.ReadAsAsync<IEnumerable<produit>>().Result;


            ViewBag.mydep =
                new SelectList(jobs, "idPro", "nom");


            return View();
        }

        // POST: Equipe/Create
        [HttpPost]
        public ActionResult Create(equipe j, int ddlVendor, FormCollection form)
        {
            string strDDLValue = form["ddlVendor"].ToString();

            using (var client = new HttpClient())
            {

                //HTTP POST

                //  var putTask = client.PostAsJsonAsync<equipe>("rest/equipe/add", j).ContinueWith((postTask) => postTask.Result.EnsureSuccessStatusCode());
                // putTask.Wait();

                HttpClient Client = new HttpClient();
                HttpResponseMessage response = Client.PostAsJsonAsync<equipe>("http://localhost:18080/NeoXamPiDev-web/rest/equipe/add", j).ContinueWith((postTask) => postTask.Result.EnsureSuccessStatusCode()).Result;



                //  var result0 = putTask.Result;
                if (response.IsSuccessStatusCode)
                {
                    return RedirectToAction("Index");
                }
            }


            return RedirectToAction("Index");
        }

        // GET: Equipe/Edit/5
        public ActionResult Edit(int id)
        {
            return View();
        }

        // POST: Equipe/Edit/5
        [HttpPost]
        public ActionResult Edit(int id, FormCollection collection)
        {
            try
            {
                // TODO: Add update logic here

                return RedirectToAction("Index");
            }
            catch
            {
                return View();
            }
        }

        // GET: Equipe/Delete/5
        public ActionResult Delete(int id)
        {
            return View();
        }

        // POST: Equipe/Delete/5
        [HttpPost]
        public ActionResult Delete(int id, FormCollection collection)
        {
            try
            {
                // TODO: Add delete logic here

                return RedirectToAction("Index");
            }
            catch
            {
                return View();
            }
        }
    }
}
