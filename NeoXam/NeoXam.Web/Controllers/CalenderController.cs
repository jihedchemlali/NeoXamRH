using DHTMLX.Scheduler;
using DHTMLX.Scheduler.Data;
using NeoXam.Domain.Entities;
using NeoXam.Service.Formation;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace NeoXam.Web.Controllers
{
    public class CalenderController : Controller
    {
        FormationService fs = new FormationService();
        // GET: Calender
        public ActionResult Index()
        {

            return View();
        }
        public JsonResult GetEvents()
        {
           
            List<formation> init = new List<formation>();
            List<formation> init1 = new List<formation>();

            init = fs.GetMany().ToList();

            foreach (var x in init)
            {
                DateTime date1 = DateTime.Now;
                DateTime date0 = (DateTime)x.Dateformation;
                int result = DateTime.Compare(date0, date1);
                if (result >= 0)

                { init1.Add(x); }

            }



            return new JsonResult { Data = init1, JsonRequestBehavior = JsonRequestBehavior.AllowGet };
        }

        // GET: Calender/Details/5
        public ActionResult Details(int id)
        {
            return View();
        }

        // GET: Calender/Create
        public ActionResult Create()
        {
            return View();
        }

        // POST: Calender/Create
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

        // GET: Calender/Edit/5
        public ActionResult Edit(int id)
        {
            return View();
        }

        // POST: Calender/Edit/5
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

        // GET: Calender/Delete/5
        public ActionResult Delete(int id)
        {
            return View();
        }

        // POST: Calender/Delete/5
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
