using NeoXam.Domain.Entities;
using NeoXam.Service.Diplome;
using NeoXam.Web.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Web;
using System.Web.Mvc;

namespace NeoXam.Web.Controllers
{
    public class DiplomeController : Controller
    {


        DiplomeService ds = new DiplomeService();

        // GET: Ecole
        public ActionResult Index()
        {
            var diplome = ds.GetMany();

            return ViewBag.result = View(diplome);
        }
        public ActionResult Create()
        {

            Models.diplome d = new Models.diplome();
            //es.ecole = es.GetMany().Select(m => new SelectListItem
            /*
            {
                Text = m.Name,
                Value = m.Id.ToString()
            });
            */
            return View(d);

        }

       

        [HttpPost]
        public ActionResult Create(Models.diplome pm)
        {
            Domain.Entities.diplome m = new Domain.Entities.diplome
            {


                nom = pm.nom,
                type = pm.type,
                idEcole = pm.idEcole,
                //CIN = pm.CIN,
                

            };
            //return View(pm);
            ds.Add(m);
            ds.Commit();
            return RedirectToAction("Index");
        }






        // GET: Diplome
        /*        public ActionResult Index()
                {
                    return View();
                }
                */
        // GET: Diplome/Details/5
        public ActionResult Details(int id)
        {
            Domain.Entities.diplome p = ds.GetById(id);

            var diplome = new Models.diplome
            {
                diplomeId = p.diplomeId,
                idEcole = p.idEcole,
                nom = p.nom,
                type = p.type,
            };
            return View(diplome);
        }

        // GET: Diplome/Create
        /* public ActionResult Create()
         {
             return View();
         }

         // POST: Diplome/Create
         [HttpPost]
         public ActionResult Create(FormCollection collection)
         {
             try
             {
                 // TODO: Add insert logic here

                 return RedirectToAction("Index");
             }
             catch
             {
                 return View();
             }
         }
         */
        // GET: Ecole/Edit/5
        public ActionResult Edit(int id)
        {


            Domain.Entities.diplome p = ds.GetById(id);

            var diplome = new Models.diplome
            {
                nom = p.nom,
                type = p.type,
            };


            return View(diplome);
        }

        // POST: Ecole/Edit/5
        [HttpPost]
        public ActionResult Edit(int id, Models.diplome pm)
        {

            Domain.Entities.diplome p1 = ds.GetById(id);
            p1.nom = pm.nom;
            p1.type = pm.type;
            
            ;
            ds.Update(p1);
            ds.Commit();
            return RedirectToAction("Index");
        }

        // GET: Diplome/Delete/5
        public ActionResult Delete(int id)
        {
            var c = ds.GetById(id);
            ds.Delete(c);
            ds.Commit();
            return RedirectToAction("Index");
        }

        // POST: Diplome/Delete/5
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
