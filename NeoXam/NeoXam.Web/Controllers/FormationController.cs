﻿using NeoXam.Service.Formation;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace NeoXam.Web.Controllers
{
    
    public class FormationController : Controller
    {

        FormationService fs = new FormationService();
        // GET: Formation
        public ActionResult Index()
        {
            var req = fs.GetMany();
            return View(req);
        }

        // GET: Formation/Details/5
        public ActionResult Details(int id)
        {
            return View();
        }

        // GET: Formation/Create
        public ActionResult Create()
        {
            return View();
        }

        // POST: Formation/Create
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

        // GET: Formation/Edit/5
        public ActionResult Edit(int id)
        {
            return View();
        }

        // POST: Formation/Edit/5
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

        // GET: Formation/Delete/5
        public ActionResult Delete(int id)
        {
            return View();
        }

        // POST: Formation/Delete/5
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
        // GET: Formation/Accepter/5
        public ActionResult Accepter(int id)
        {
            var req = fs.GetById(id);
            req.Etat = 1;
            fs.UpdateFormation(req);
            return RedirectToAction("Index");
        }

        // POST: Formation/Delete/5
        [HttpPost]
        public ActionResult Accepter(int id, FormCollection collection)
        {
            try
            {
                var req = fs.GetById(id);
                req.Etat = 1;
                fs.UpdateFormation(req);
                return RedirectToAction("Index");
            }
            catch
            {
                return View();
            }
        }

        // GET: Formation/Accepter/5
        public ActionResult Refusé(int id)
        {
            var req = fs.GetById(id);
            req.Etat = -1;
            fs.UpdateFormation(req);
            return RedirectToAction("Index");
        }
        [HttpPost]
        public ActionResult Refusé(int id, FormCollection collection)
        {
            try
            {
                var req = fs.GetById(id);
                req.Etat = -1;
                fs.UpdateFormation(req);
                return RedirectToAction("Index");
            }
            catch
            {
                return View();
            }
        }
    }
}
