using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using NeoXam.Service;
using NeoXam.Web.Models;
using NeoXam.Domain.Entities;

namespace NeoXam.Web.Controllers
{
    public class CritereController : Controller
    {
        ServiceCritere sc = new ServiceCritere();
        // GET: Critere
        public ActionResult Index()
        {
            var criteres = sc.GetMany();
            return View(criteres);
        }

        // GET: Critere/Details/5
        public ActionResult Details(string id)
        {
            var critere = sc.GetMany().Where(c => c.idcritere.Equals(id)).FirstOrDefault();
            return View(critere);
        }

        // GET: Critere/Create
        public ActionResult Create()
        {
            return View();
        }

        // POST: Critere/Create
        [HttpPost]
        public ActionResult Create(critereModel critereModel)
        {
            try
            {


                // TODO: Add insert logic here
                critere c=new critere();
                c.idcritere = critereModel.idcritere;
                c.fait = critereModel.fait;
                c.risque = critereModel.risque;
                sc.Add(c);
                sc.Commit();
                return RedirectToAction("Index");
            }
            catch
            {
                return View();
            }
        }

        // GET: Critere/Edit/5
        public ActionResult Edit(string id)
        {
           
            var critere = sc.GetMany().Where(c=>c.idcritere.Equals(id)).FirstOrDefault();
           
            if (critere == null)
            {
                return HttpNotFound();
            }

         var crite=new critere
            {
                idcritere = critere.idcritere,
                fait = critere.fait,
                risque = critere.risque
            };
            return View(crite);
            
        }

        //POST: Critere/Edit/5
        [HttpPost]
        public ActionResult Edit(String id, critereModel critereModel)
        {
            try
            {
                var critere = sc.GetMany().Where(c => c.idcritere.Equals(id)).FirstOrDefault();

                // TODO: Add update logic here


                critere.idcritere = critereModel.idcritere;
                critere.fait = critereModel.fait;
                critere.risque = critereModel.risque;

                sc.Update(critere);
                sc.Commit();
                return RedirectToAction("Index");
            }
            catch
            {
                return View();
            }
        }

        // GET: Critere/Delete/5
        public ActionResult Delete(string id)
        {
            var critere = sc.GetMany().Where(c => c.idcritere.Equals(id)).FirstOrDefault();
            sc.Delete(critere);
            sc.Commit();
            return RedirectToAction("Index");
        }

        // POST: Critere/Delete/5
        [HttpPost]
        public ActionResult Delete(string id, FormCollection collection)
        {
            try
            {
                var critere = sc.GetMany().Where(c => c.idcritere.Equals(id)).FirstOrDefault();
                sc.Delete(critere);
                sc.Commit();
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
