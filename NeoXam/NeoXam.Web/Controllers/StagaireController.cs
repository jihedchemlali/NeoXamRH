using NeoXam.Data;
using NeoXam.Domain;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Net.Http.Headers;
using System.Threading;
using System.Web;
using System.Web.Mvc;
using NeoXam.Web.Models;
using NeoXam.Domain.Entities;

namespace NeoXam.Web.Controllers
{
    public class StagaireController : Controller
    {
        //-------------------------------------- Index --------------------------------------------------
        // GET: Stagaire
        public ActionResult Index()
        {
            HttpClient Client = new HttpClient();
            Client.BaseAddress = new Uri("http://localhost:18080");
            Client.DefaultRequestHeaders.Accept.Add(new MediaTypeWithQualityHeaderValue("application/json"));
            HttpResponseMessage response = Client.GetAsync("NeoXamPiDev-web/rest/stagiaire").Result;
            if (response.IsSuccessStatusCode)
            {
                ViewBag.result = response.Content.ReadAsAsync<IEnumerable<Models.stagiaire>>().Result;
            }
            else
            {
                ViewBag.result = "error";
            }
            return View();

        }

        //-------------------------------------- Details --------------------------------------------------
        // GET: Diplome/Details/5
        public ActionResult Details(int id)
        {
            Models.stagiaire r = new Models.stagiaire();
            HttpClient Client = new HttpClient();
            String ch = "NeoXamPiDev-web/rest/stagiaire/" + id;
            Client.BaseAddress = new Uri("http://localhost:18080");
            Client.DefaultRequestHeaders.Accept.Add(new System.Net.Http.Headers.MediaTypeWithQualityHeaderValue("application/json"));
            HttpResponseMessage response = Client.GetAsync(ch).Result;
            if (response.IsSuccessStatusCode)
            {

                r = response.Content.ReadAsAsync<Models.stagiaire>().Result;

            }
            return View(r);
        }





        //-------------------------------------- Create --------------------------------------------------

        // GET: Stagaire/Create
        [HttpGet]
        public ActionResult Create()
        {
            return View();
        }

        // POST: Stagaire/Create               
        [HttpPost]
        public ActionResult Create(Models.stagiaire s)
        {
            try
            {
                HttpClient Client = new HttpClient();
                Client.BaseAddress = new Uri("http://localhost:18080");

                Client.PostAsJsonAsync<Models.stagiaire>("NeoXamPiDev-web/rest/stagiaire/addStagiaire", s).ContinueWith((postTask) => postTask.Result.EnsureSuccessStatusCode());
                // TODO: Add insert logic here

                return RedirectToAction("Index");
            }
            catch
            {
                return View();
            }
        }

        //-------------------------------Edit------------------------------------------------------
        // GET: Stagiaire/Edit/5
        public ActionResult Edit(int id)
        {
            Models.stagiaire r = new Models.stagiaire();
            HttpClient Client = new HttpClient();
            String ch = "NeoXamPiDev-web/rest/stagiaire/" + id;
            Client.BaseAddress = new Uri("http://localhost:18080");
            Client.DefaultRequestHeaders.Accept.Add(new System.Net.Http.Headers.MediaTypeWithQualityHeaderValue("application/json"));
            HttpResponseMessage response = Client.GetAsync(ch).Result;
            if (response.IsSuccessStatusCode)
            {

                r = response.Content.ReadAsAsync<Models.stagiaire>().Result;

            }
            return View(r);
        }


        // POST: Stagiaire/Edit/5
        [HttpPost]
        public ActionResult Edit(Models.stagiaire s)
        {
            try
            {

                HttpClient Client = new HttpClient();
                HttpResponseMessage response = Client.PutAsJsonAsync<Models.stagiaire>("http://localhost:18080/NeoXamPiDev-web/rest/stagiaire/modifiestg", s).ContinueWith((postTask) => postTask.Result.EnsureSuccessStatusCode()).Result;

                return RedirectToAction("Index");
            }
            catch
            {
                return View();
            }

        }

        //--------------------------------------Delete--------------------------------------------------

        // GET: Stagiaire/Delete/5
        public ActionResult Delete(int id)
        {
            System.Diagnostics.Debug.WriteLine("***** Id ******");
            System.Diagnostics.Debug.WriteLine(id);
            return View();
        }

        // DELETE: Stagiaire/Delete/5
        [HttpPost]
        public ActionResult Delete(int id, FormCollection collection)
        {
            HttpClient client = new HttpClient();
            client.BaseAddress = new Uri("http://localhost:18080");
            String ch = "NeoXamPiDev-web/rest/stagiaire/" + id;
            HttpResponseMessage response = client.DeleteAsync(ch).Result;
            if (response.IsSuccessStatusCode)
            {
                Thread.Sleep(8000);
                return RedirectToAction("Index");
            }
            else
            {
                ViewBag.result = "stagiaire does not exist";
            }
            return View();
        }


    }
}
