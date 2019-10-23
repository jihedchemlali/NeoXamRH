using System;
using System.Collections.Generic;
using System.Data;
using System.Data.Entity;
using System.Linq;
using System.Net;
using System.Web;
using System.Web.Mvc;
using NeoXam.Data;
using NeoXam.Domain;
using NeoXam.Domain.Entities;

namespace NeoXam.Web.Controllers
{
    public class suivi_riskController : Controller
    {
        private ContextNeoXam db = new ContextNeoXam();

        // GET: suivi_risk
        public ActionResult Index()
        {
            var suivi_risk = db.suivi_risk.Include(s => s.critere).Include(s => s.employe).Include(s => s.equipe);
            return View(suivi_risk.ToList());
        }


        public ActionResult SumCritere(string id)
        {
            var suivicritere = db.suivi_risk.Where(e=>e.employe.CIN==id).Sum(e=>e.Valeur);
           

            var a = db.suivi_risk.GroupBy(i => i.employe)
                .Select(g => new {
                    employe = g.Key,
                    Count = g.Count(),
                    Total = g.Sum(i => i.Valeur),
                    Average = g.Average(i => i.Valeur)
                });
            var suivi_risk = db.suivi_risk.Include(s => s.critere).Include(s => s.employe).Include(s => s.equipe);
            return View();
        }

        // GET: suivi_risk/Details/5
        public ActionResult Details(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            suivi_risk suivi_risk = db.suivi_risk.Find(id);
            if (suivi_risk == null)
            {
                return HttpNotFound();
            }
            return View(suivi_risk);
        }

        // GET: suivi_risk/Create
        public ActionResult Create()
        {
            ViewBag.idcritere = new SelectList(db.criteres, "idcritere", "idcritere");
            ViewBag.CIN = new SelectList(db.employes, "CIN", "Nom");
            ViewBag.idEquipe = new SelectList(db.equipes, "idEquipe", "labelEquipe");
            return View();
        }

        // POST: suivi_risk/Create
        // Afin de déjouer les attaques par sur-validation, activez les propriétés spécifiques que vous voulez lier. Pour 
        // plus de détails, voir  http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
     
        public ActionResult Create([Bind(Include = "idSuiviRisk,Valeur,idcritere,CIN,idEquipe")] suivi_risk suivi_risk)
        {
            if (ModelState.IsValid)
            {
                suivi_risk.Date_Suivi= DateTime.Now;
                db.suivi_risk.Add(suivi_risk);
                db.SaveChanges();
                return RedirectToAction("Index");
            }

            ViewBag.idcritere = new SelectList(db.criteres, "idcritere", "idcritere", suivi_risk.idcritere);
            ViewBag.CIN = new SelectList(db.employes, "CIN", "Nom", suivi_risk.CIN);
            ViewBag.idEquipe = new SelectList(db.equipes, "idEquipe", "labelEquipe", suivi_risk.idEquipe);
            return View(suivi_risk);
        }

        // GET: suivi_risk/Edit/5
        public ActionResult Edit(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            suivi_risk suivi_risk = db.suivi_risk.Find(id);
            if (suivi_risk == null)
            {
                return HttpNotFound();
            }
            ViewBag.idcritere = new SelectList(db.criteres, "idcritere", "fait", suivi_risk.idcritere);
            ViewBag.CIN = new SelectList(db.employes, "CIN", "adresse", suivi_risk.CIN);
            ViewBag.idEquipe = new SelectList(db.equipes, "idEquipe", "labelEquipe", suivi_risk.idEquipe);
            return View(suivi_risk);
        }

        // POST: suivi_risk/Edit/5
        // Afin de déjouer les attaques par sur-validation, activez les propriétés spécifiques que vous voulez lier. Pour 
      
        [HttpPost]
  
        public ActionResult Edit([Bind(Include = "idSuiviRisk,Date_Suivi,Valeur,idcritere,CIN,idEquipe")] suivi_risk suivi_risk)
        {
            if (ModelState.IsValid)
            {
                db.Entry(suivi_risk).State = EntityState.Modified;
                db.SaveChanges();
                return RedirectToAction("Index");
            }
            ViewBag.idcritere = new SelectList(db.criteres, "idcritere", "idcritere", suivi_risk.idcritere);
            ViewBag.CIN = new SelectList(db.employes, "CIN", "Nom", suivi_risk.CIN);
            ViewBag.idEquipe = new SelectList(db.equipes, "idEquipe", "labelEquipe", suivi_risk.idEquipe);
            return View(suivi_risk);
        }

        // GET: suivi_risk/Delete/5
        public ActionResult Delete(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            suivi_risk suivi_risk = db.suivi_risk.Find(id);
            if (suivi_risk == null)
            {
                return HttpNotFound();
            }
            return View(suivi_risk);
        }

        // POST: suivi_risk/Delete/5
        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public ActionResult DeleteConfirmed(int id)
        {
            suivi_risk suivi_risk = db.suivi_risk.Find(id);
            db.suivi_risk.Remove(suivi_risk);
            db.SaveChanges();
            return RedirectToAction("Index");
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
