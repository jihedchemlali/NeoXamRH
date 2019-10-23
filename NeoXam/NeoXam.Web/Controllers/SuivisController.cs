using System;
using System.Collections.Generic;
using System.Data;
using System.Data.Entity;
using System.Linq;
using System.Net;
using System.Web;
using System.Web.Mvc;
using NeoXam.Web.Models;

namespace NeoXam.Web.Controllers
{
    public class SuivisController : Controller
    {
        private ApplicationDbContext db = new ApplicationDbContext();

        // GET: Suivis
        public ActionResult Index()
        {
            var suivis = db.suivis.Include(s => s.employe);
            return View(suivis.ToList());
        }

        // GET: Suivis/Details/5
        public ActionResult Details(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            suivi suivi = db.suivis.Find(id);
            if (suivi == null)
            {
                return HttpNotFound();
            }
            return View(suivi);
        }

        // GET: Suivis/Create
        public ActionResult Create()
        {
            ViewBag.CIN = new SelectList(db.employes, "CIN", "adresse");
            return View();
        }

        // POST: Suivis/Create
        // To protect from overposting attacks, please enable the specific properties you want to bind to, for 
        // more details see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Create([Bind(Include = "idSuiv,DateSuivi,EntretrienTCarriere,Eval,CIN")] suivi suivi)
        {
            if (ModelState.IsValid)
            {
                db.suivis.Add(suivi);
                db.SaveChanges();
                return RedirectToAction("Index");
            }

            ViewBag.CIN = new SelectList(db.employes, "CIN", "adresse", suivi.CIN);
            return View(suivi);
        }

        // GET: Suivis/Edit/5
        public ActionResult Edit(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            suivi suivi = db.suivis.Find(id);
            if (suivi == null)
            {
                return HttpNotFound();
            }
            ViewBag.CIN = new SelectList(db.employes, "CIN", "adresse", suivi.CIN);
            return View(suivi);
        }

        // POST: Suivis/Edit/5
        // To protect from overposting attacks, please enable the specific properties you want to bind to, for 
        // more details see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Edit([Bind(Include = "idSuiv,DateSuivi,EntretrienTCarriere,Eval,CIN")] suivi suivi)
        {
            if (ModelState.IsValid)
            {
                db.Entry(suivi).State = EntityState.Modified;
                db.SaveChanges();
                return RedirectToAction("Index");
            }
            ViewBag.CIN = new SelectList(db.employes, "CIN", "adresse", suivi.CIN);
            return View(suivi);
        }

        // GET: Suivis/Delete/5
        public ActionResult Delete(int? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            suivi suivi = db.suivis.Find(id);
            if (suivi == null)
            {
                return HttpNotFound();
            }
            return View(suivi);
        }

        // POST: Suivis/Delete/5
        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public ActionResult DeleteConfirmed(int id)
        {
            suivi suivi = db.suivis.Find(id);
            db.suivis.Remove(suivi);
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
