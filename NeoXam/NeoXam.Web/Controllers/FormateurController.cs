using NeoXam.Domain.Entities;
using NeoXam.Service.Formation;
using NeoXam.Web.Models;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Net;
using System.Web;
using System.Web.Mvc;

namespace NeoXam.Web.Controllers
{
   
    public class FormateurController : Controller
    {
        FormateurService sf = new FormateurService();
        // GET: Formateur
        public ActionResult Index()
        {
            var formateurs = sf.GetMany();
            return View(formateurs);
        }

        

        // GET: Formateur/Create
        public ActionResult Create()
        {
            return View();
        }

        // POST: Formateur/Create
        [HttpPost]
        public ActionResult Create(FormateurModel fm, HttpPostedFileBase photo)
        {
           //fm.photo = photo.FileName;
            
            try
            {
                // TODO: Add insert logic here
                Domain.Entities.formateur f = new Domain.Entities.formateur();
                f.nom = fm.nom;
                f.prenom = fm.prenom;
                f.address = fm.address;
                f.email = fm.email;
                f.facebook = fm.facebook;
                f.linkedin = fm.linkedin;
                f.Description = fm.Description;
                f.numTel = fm.numTel;
                f.photo = fm.photo;
                sf.Add(f);
                sf.Commit();
                //enregistrer l'image
                var path = Path.Combine(Server.MapPath("~/Content/Upload/"), photo.FileName);
                photo.SaveAs(path);
                return RedirectToAction("Index");
            }
            catch
            {
                return View();
            }
        }

        // GET: Formateur/Edit/5
        public ActionResult Edit(int id)
        {
            var req = sf.GetById(id);

            return View(req);
        }

        // POST: Formateur/Edit/5
        [HttpPost]
        public ActionResult Edit(int id ,[Bind(Include = "nom,prenom,address, numTel,email,facebook,linkedin,Description,photo")]  FormateurModel fm)
        {
            if (ModelState.IsValid)
            {
                var formateur = sf.GetById(id);
                formateur.nom = fm.nom;
                formateur.prenom = fm.prenom;
                formateur.address = fm.address;
                formateur.numTel = fm.numTel;
                formateur.email = fm.email;
                formateur.facebook = fm.facebook;
                formateur.linkedin = fm.linkedin;
                formateur.Description = fm.Description;
                if (fm.photo == null)
                { formateur.photo = fm.photo; } 
                else
                formateur.photo = formateur.photo;
                sf.UpdateFormateur(formateur);
               // pi.Entry(ap).State = EntityState.Modified;
               // pi.SaveChanges();
                return RedirectToAction("Index");
            }
            var req = sf.GetById(id);
            return View(req);
        }



        // GET: Formateur/Delete/5
        public ActionResult Delete(int id)
        {
            try
            {
                // TODO: Add delete logic here
                var req = sf.GetById(id);
                sf.Delete(req);
                sf.Commit();
                return RedirectToAction("Index", "Formateur");
            }
            catch
            {
                return View();
            }
        }

        // POST: Formateur/Delete/5
        [HttpPost]
        public ActionResult Delete(int id, FormationModel fm)
        {
            try
            {
                // TODO: Add delete logic here
                var req = sf.GetById(id);
                sf.Delete(req);
                sf.Commit();
                return RedirectToAction("Index", "Formateur");
            }
            catch
            {
                return View();
            }
        }
        public ActionResult Details(int id)
        {
            var req = sf.GetById(id);
            return View(req);
        }
    }
}
