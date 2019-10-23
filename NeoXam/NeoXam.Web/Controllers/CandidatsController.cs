using NeoXam.Service.Recrutement;
using System;
using NeoXam.Domain.Entities;

using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using System.Net;
using System.Data;
using System.IO;

namespace NeoXam.Web.Controllers
{
    public class CandidatsController : Controller
    {
        CandidatService service = new CandidatService();
        CarriereService servCarr = new CarriereService();
        
        // GET: Candidats
        public ActionResult Index()
        {
            return View(service.GetMany().ToList());
        }
        // GET: Candidats
        public ActionResult Fiches()
        {
            return View(service.GetMany().ToList());
        }
        // GET: Candidats/Details/5
        public ActionResult Details(int id)
        {
            return View();
        }

        // GET: Candidats/Create
        public ActionResult Create()
        {
            return View();
        }

        // POST: Candidats/Create
        [HttpPost]
        
        public ActionResult Create(/*HttpPostedFileBase Image*/ Models.candidat pm)
        {
            //CIN,adresse,Email,image,linkedin,Nom,numTel,Per_email,Prenom,resume
            candidat candidat = new candidat();
           // candidat.CIN = "09852147";
            candidat.CIN= pm.CIN;
            candidat.adresse =pm.adresse;
            candidat.Email =pm.Email;
           // if(Image!=null)
           // candidat.image = Image.FileName;
            candidat.linkedin = pm.linkedin;
            candidat.Nom = pm.Nom;
            candidat.numTel = pm.numTel;            
            candidat.Prenom = pm.Prenom;
            candidat.resume = pm.resume;

           
            TempData["msg"] = "<script>alert('Begin');</script>";
            try
            {
                TempData["msg"] = "<script>alert('BeginTry');</script>";
                if (ModelState.IsValid)
                {                   
                    service.Add(candidat);                    
                    service.Commit();
                   // var path = Path.Combine(Server.MapPath("~/Content/Upload/"), Image.FileName);
                    //Image.SaveAs(path);

                    TempData["msg"] = "<script>alert('Begin commit');</script>";
                    return RedirectToAction("Index");
                    TempData["msg"] = "<script>alert('EndTryIf');</script>";
                }
                TempData["msg"] = "<script>alert('EndTryIfAfter');</script>";
                
                return View(candidat);
                

            }
            catch (DataException /* dex */)
            {
                //Log the error (uncomment dex variable name and add a line here to write a log.

                ModelState.AddModelError("", "Unable to save changes. Try again, and if the problem persists see your system administrator.");
                return View();
            }
            catch
            {
               // TempData["msg"] = "<script>alert('Catch it');</script>";
                return View();
            }
            TempData["msg"] = "<script>alert('End');</script>";
        }

        // GET: Candidats/Edit/5
        public ActionResult Edit(string id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            candidat candidat = service.GetById(id);
            if (candidat == null)
            {
                return HttpNotFound();
            }
            return View(candidat);
        }

        // POST: Candidats/Edit/5
        [HttpPost]
        public ActionResult Edit(string id, Models.candidat pm)
        {
            candidat candidat = new candidat();

                if (ModelState.IsValid)
                {

                candidat.CIN = pm.CIN;
                candidat.adresse = pm.adresse;
                candidat.Email = pm.Email;
                candidat.image = pm.image;
                candidat.linkedin = pm.linkedin;
                candidat.Nom = pm.Nom;
                candidat.numTel = pm.numTel;
                candidat.Per_email = pm.Per_email;
                candidat.Prenom = pm.Prenom;
                candidat.resume = pm.resume;
                service.CheckStat(candidat);

                return RedirectToAction("Index");
                }
                return View(candidat);       
            
        }

        // GET: Candidats/Delete/5
        public ActionResult Delete(string id)
        {            
            service.Delete(service.GetById(id));
            if (id==null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            candidat candidat = service.GetById(id);
            if (candidat == null)
            {
                return HttpNotFound();
            }
            return View(candidat);
        }

        // POST: Candidats/Delete/5
        [HttpPost]
        public ActionResult Delete(string id, FormCollection collection)
        {
                candidat candidat = service.GetById(id);
                service.Delete(candidat);
                service.Commit();
            return RedirectToAction("Index");
        }
        // POST: Product
        [HttpPost]
        public ActionResult Index(String SearchString)
        {
            var candis = service.GetMany(p => p.CIN.Contains(SearchString));
            return View(candis);
        }
    }
}
