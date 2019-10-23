using NeoXam.Domain.Entities;
using NeoXam.Service.Ecole;
using NeoXam.Web.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace NeoXam.Web.Controllers
{
    public class EcoleController : Controller
    {



        EcoleService es = new EcoleService();

        // GET: Ecole
        public ActionResult Index()
        {
            var ecole = es.GetMany();

            return ViewBag.result = View(ecole);
        }

        // GET: Diplome/Details/5
        public ActionResult Details(int id)
        {
            Domain.Entities.ecole p = es.GetById(id);

            var ecole = new Models.ecole
            {
                
                idEcole = p.idEcole,
                Nom = p.Nom,
                pays = p.pays,
                ville = p.ville,
                rue = p.rue,
                fax = p.fax,
            };
            return View(ecole);
        }


        public ActionResult Create()
        {

            Models.ecole e = new Models.ecole();
            //es.ecole = es.GetMany().Select(m => new SelectListItem
            /*
            {
                Text = m.Name,
                Value = m.Id.ToString()
            });
            */
            return View(e);

        }

        [HttpPost]
        public ActionResult Create(Models.ecole pm)
        {
            Domain.Entities.ecole m = new Domain.Entities.ecole
            {

                Nom = pm.Nom,
                pays = pm.pays,
                rue = pm.rue,
                ville = pm.ville,
                fax = pm.fax

            };
            //return View(pm);
            es.Add(m);
            es.Commit();
            return RedirectToAction("Index");
        }
        //---------------------------- Delete --------------------------------
        /*
        [HttpGet]
        public ActionResult Delete(int id)
        {
            Domain.Entities.ecole e = es.GetById(id);

            return View(e);
        }

        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public ActionResult DeleteConfirmed(int id)
        {
            Domain.Entities.ecole p = es.GetById(id);

            es.Delete(p);
            es.Commit();

            return RedirectToAction("Index");
        }

*/

        
        // GET: Project/Delete/5
        public ActionResult Delete(int id)
        {
            var c = es.GetById(id);
            es.Delete(c);
            es.Commit();
            return RedirectToAction("Index");

        }

        // POST: Project/Delete/5
        [HttpPost]
        public ActionResult Delete(int id, Models.ecole pm)
        {
            try
            {
                // TODO: Add delete logic here
                Domain.Entities.ecole p = es.GetById(id);
                es.Delete(p);
                es.Commit();

                return RedirectToAction("Index");
            }
            catch
            {
                return View();
            }
        }

    
        




        // GET: Ecole/Details/5
       /* public ActionResult Details(int id)
        {
            return View();
        }*/
        /*
                // GET: Ecole/Create
                public ActionResult Create()
                {
                    return View();
                }

                // POST: Ecole/Create
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


            Domain.Entities.ecole p = es.GetById(id);

            var ecole = new Models.ecole
            {
                Nom = p.Nom,
                pays = p.pays,
                ville = p.ville,
                rue = p.rue,
                fax = p.fax
            };


            return View(ecole);
        }

         // POST: Ecole/Edit/5
         [HttpPost]
         public ActionResult Edit(int id, Models.ecole pm)
         {

            Domain.Entities.ecole p1 = es.GetById(id);
                 p1.Nom = pm.Nom;
                 p1.pays = pm.pays;
                 p1.ville = pm.ville;
                 p1.rue = pm.rue;
                 p1.fax = pm.fax;

       ;
                 es.Update(p1);
                 es.Commit();
                 return RedirectToAction("Index");
         }
         /*
         // GET: Ecole/Delete/5
         public ActionResult Delete(int id)
         {
             return View();
         }

         // POST: Ecole/Delete/5
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
         */
    }
}