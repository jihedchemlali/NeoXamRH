using NeoXam.Domain.Entities;
using NeoXam.Service.Recrutement;
using System;
using System.Collections.Generic;
using System.Data;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Net.Http.Headers;
using System.Web.Mvc;

namespace NeoXam.Web.Controllers
{
    public class CarriereController : Controller
    {
        CarriereService service = new CarriereService();
        CandidatService servCand = new CandidatService();
        ExperienceService servExp = new ExperienceService();
        // GET: Carriere
        public ActionResult Index()
        { 

            List<carriere> carrs = new List<carriere>();
             carrs = service.GetMany().ToList();
            return View(carrs);
        }

        // GET: Carriere/Details/5
        public ActionResult Details(int id)
        {
            
            return View();
        }

        // GET: Carriere/Create
        public ActionResult Create()
        {
            
            HttpClient Client = new HttpClient();
            Client.BaseAddress = new Uri("http://localhost:18080");
            Client.DefaultRequestHeaders.Accept.Add(new MediaTypeWithQualityHeaderValue("application/json"));
            HttpResponseMessage response = Client.GetAsync("NeoXamPiDev-web/rest/experiences").Result;
            if (response.IsSuccessStatusCode)
            {
                ViewBag.experienceslist = new SelectList(response.Content.ReadAsAsync<IEnumerable<experience>>().Result, "idExpe", "Nom");          
            }
            else
            {
                ViewBag.result = "error";
                return HttpNotFound();
            }

            ViewBag.candidatslist = new SelectList(servCand.GetMany(), "CIN", "CIN");
            return View();
        }

        // POST: Carriere/Create
        [HttpPost]
        public ActionResult Create(Models.carriere carriereModel, FormCollection formi)
        {

            carriere ca = new carriere();
            ca.DateDep = carriereModel.DateDep;
            ca.DateFin = carriereModel.DateFin;
            ca.niveau = carriereModel.niveau;
            ca.societe = carriereModel.societe;
            //ca.CIN = carriereModel.CIN;

            string strid = formi["experienceslist"].ToString();
            int id= Int32.Parse(strid);            
            ca.idExpe = id;
            ca.CIN = formi["candidatslist"].ToString();

            if (ModelState.IsValid)
            {
               
                service.Add(ca);
                service.Commit();
                return RedirectToAction("Index");

            }
            ViewBag.candidatslist = new SelectList(servCand.GetMany(), "CIN", "CIN");

            HttpClient Client = new HttpClient();
            Client.BaseAddress = new Uri("http://localhost:18080");
            Client.DefaultRequestHeaders.Accept.Add(new MediaTypeWithQualityHeaderValue("application/json"));
            HttpResponseMessage response = Client.GetAsync("NeoXamPiDev-web/rest/experiences").Result;
            if (response.IsSuccessStatusCode)
            {
                ViewBag.experienceslist = new SelectList(response.Content.ReadAsAsync<IEnumerable<experience>>().Result, "idExpe", "Nom");
            }
            else
            {
                ViewBag.result = "error";
                return HttpNotFound();
            }
            return View(ca);

        }

        // GET: Carriere/Edit/5
        public ActionResult Edit(string CIN,int  idExpe)
        {
            if (CIN == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            carriere carriere = service.FindKey(CIN,idExpe);
            if (carriere == null)
            {
                return HttpNotFound();
            }
            return View(carriere);
        }

        // POST: Carriere/Edit/5
        [HttpPost]
        public ActionResult Edit(string CIN, int idExpe,Models.carriere pm)
        {
            carriere carriere = new carriere();

            if (ModelState.IsValid)
            {
                carriere.CIN = CIN;
                carriere.idExpe = idExpe;
                carriere.DateDep = pm.DateDep;
                carriere.DateFin = pm.DateFin;
                carriere.niveau = pm.niveau;
                carriere.societe = pm.societe;
                service.CheckStat(carriere);
                
                return RedirectToAction("Index");
            }
            return View(carriere);
        }

        // GET: Carriere/Delete/5
        public ActionResult Delete(string CIN, int idExpe)
        {
            service.Delete(service.FindKey(CIN, idExpe));
            if (CIN == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            carriere carriere = service.FindKey(CIN, idExpe);
            if (carriere == null)
            {
                return HttpNotFound();
            }
            return View(carriere);
        }

        // POST: Carriere/Delete/5
        [HttpPost]
        public ActionResult Delete(string CIN,int idExpe, FormCollection collection)
        {
            carriere carriere = service.FindKey(CIN, idExpe);
            service.Delete(carriere);
            service.Commit();
            return RedirectToAction("Index");
        }
    }
}
