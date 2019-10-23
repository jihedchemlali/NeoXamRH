using NeoXam.Domain.Entities;
using NeoXam.Service;
using Rotativa;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Web;
using System.Web.Mvc;

namespace NeoXam.Web.Controllers
{
    public class ProjetController : Controller
    {
        IproductService productservice = new Productservice();
        // GET: Projet
        public ActionResult Index()
        {
            HttpClient Client = new HttpClient();
            // Client.BaseAddress = new Uri("http://localhost:18080");
            Client.DefaultRequestHeaders.Accept.Add(new System.Net.Http.Headers.MediaTypeWithQualityHeaderValue("application/json"));
            HttpResponseMessage response = Client.GetAsync("http://localhost:18080/NeoXamPiDev-web/rest/projets").Result;
            if (response.IsSuccessStatusCode)
            {
                ViewBag.result = response.Content.ReadAsAsync<IEnumerable<Models.produit>>().Result;

            }

            else
            {
                ViewBag.result = "error";
            }
            return View(ViewBag.result);
        }

        // GET: Projet/Details/5
        public ActionResult Details(int id)
        {
            Models.produit produit1 = null;

            HttpClient Client = new HttpClient();
            Client.DefaultRequestHeaders.Accept.Add(new System.Net.Http.Headers.MediaTypeWithQualityHeaderValue("application/json"));
            HttpResponseMessage response = Client.GetAsync("http://localhost:18080/NeoXamPiDev-web/rest/projets/getProduit/" + id).Result;
            if (response.IsSuccessStatusCode)
            {
                produit1 = response.Content.ReadAsAsync<Models.produit>().Result;

            }

            else
            {
                ViewBag.result = "error";
            }



            return View(produit1);
        }


        public ActionResult Details22()
        {
            return new ActionAsPdf("Index")
            {
                FileName = Server.MapPath("~/Content/Conge.pdf")
            };
        } 

        // GET: Projet/Create
        public ActionResult Create()
        {
            return View();
        }

        // POST: Projet/Create
        [HttpPost]
        public ActionResult Create(produit produit)
        {
            try
            {

                HttpClient Client = new HttpClient();
                HttpResponseMessage response = Client.PostAsJsonAsync<produit>("http://localhost:18080/NeoXamPiDev-web/rest/projets/test", produit).ContinueWith((postTask) => postTask.Result.EnsureSuccessStatusCode()).Result;

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

        // GET: Projet/Edit/5
        public ActionResult Edit(int id)
        {
            Models.produit produit = null;

            HttpClient Client = new HttpClient();
            Client.DefaultRequestHeaders.Accept.Add(new System.Net.Http.Headers.MediaTypeWithQualityHeaderValue("application/json"));
            HttpResponseMessage response = Client.GetAsync("http://localhost:18080/NeoXamPiDev-web/rest/projets/getProduit/" + id).Result;
            if (response.IsSuccessStatusCode)
            {
                produit = response.Content.ReadAsAsync<Models.produit>().Result;

            }

            else
            {
                ViewBag.result = "error";
            }



            return View(produit);
        }

        // POST: Projet/Edit/5
        [HttpPost]
        public ActionResult Edit(produit produit)
        {
            try
            {

                HttpClient Client = new HttpClient();
                HttpResponseMessage response = Client.PutAsJsonAsync<produit>("http://localhost:18080/NeoXamPiDev-web/rest/projets/update/", produit).ContinueWith((postTask) => postTask.Result.EnsureSuccessStatusCode()).Result;

                if (response.IsSuccessStatusCode)
                    return RedirectToAction("../Email/Create");
                else
                    return View();
            }
            catch
            {
                return View();
            }
        }

        // GET: Projet/Delete/5
        public void Delete(int id)
        {
            //void car vu on a async et ajax meme avec return to action ou view la page ne s'actualise pas  ViewData["Success"] = true;
            HttpClient Client = new HttpClient();
            Client.DefaultRequestHeaders.Accept.Add(new System.Net.Http.Headers.MediaTypeWithQualityHeaderValue("application/json"));
            HttpResponseMessage response = Client.DeleteAsync("http://localhost:18080/NeoXamPiDev-web/rest/projets/" + id).Result;

        }

        // POST: Projet/Delete/5
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

        [HttpPost]
        public JsonResult GetSearch(String search)
        {
            HttpClient Client = new HttpClient();
            IEnumerable<Models.produit> result;
            // Client.BaseAddress = new Uri("http://localhost:18080");
            Client.DefaultRequestHeaders.Accept.Add(new System.Net.Http.Headers.MediaTypeWithQualityHeaderValue("application/json"));
            HttpResponseMessage response = Client.GetAsync("http://localhost:18080/NeoXamPiDev-web/rest/projets/getProduitbyname/" + search).Result;
            if (response.IsSuccessStatusCode)
            {
                result = response.Content.ReadAsAsync<IEnumerable<Models.produit>>().Result;

            }

            else
            {
                result = null;
            }

            return new JsonResult { Data = result };

            // return "getsearch";

        }
    }
}
