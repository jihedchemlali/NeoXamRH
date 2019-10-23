using Microsoft.AspNet.Identity;
using NeoXam.Service.Formation;
using NeoXam.Web.Models;
using System.Web.Mvc;

namespace NeoXam.Web.Controllers
{
    public class DemandeFormationController : Controller
    {
        FormationService fs = new FormationService();
        // GET: DemandeFormation
        public ActionResult Index()
        {
          
            var req =fs.getFormationByUserId(User.Identity.GetUserId());
            return View(req);
        }

        // GET: DemandeFormation/Details/5
        public ActionResult Details(int id)
        {
            var req = fs.GetById(id);
            return View(req);
        }

        // GET: DemandeFormation/Create
        public ActionResult Create()
        {
            return View();
        }

        // POST: DemandeFormation/Create
        [HttpPost]
        public ActionResult Create(Domain.Entities.formation fm)
        {
            try
            {
                // TODO: Add insert logic here
                Domain.Entities.formation f = new Domain.Entities.formation();
                f.Dateformation = fm.Dateformation;
                f.libelle = fm.libelle;
                f.lieux = fm.lieux;
                f.Etat = 0;
                f.formateurid = 1;
                f.IdUser = User.Identity.GetUserId();
                fs.Add(f);
                fs.Commit();
                //enregistrer l'image
                //var path = Path.Combine(Server.MapPath("~/Content/Upload/"), photo.FileName);
               // photo.SaveAs(path);
                return RedirectToAction("Create");
            }
            catch
            {
                return View();
            }
        }

        // GET: DemandeFormation/Edit/5
        public ActionResult Edit(int id)
        {
            var req = fs.GetById( id);
            return View(req);
           
        }

        // POST: DemandeFormation/Edit/5
        [HttpPost]
        public ActionResult Edit(int id,  FormationModel fm)
        {
            try
            {
                var formation = fs.GetById(id);
                formation.Dateformation = fm.Dateformation;
                formation.libelle = fm.libelle;
                formation.lieux = fm.lieux;
                fs.UpdateFormation(formation);
                return RedirectToAction("Index");
            }
            catch
            {
                return View();
            }
        }

        // GET: DemandeFormation/Delete/5
        public ActionResult Delete(int id)
        {
            try
            {
                // TODO: Add delete logic here
                var req = fs.GetById(id);
                fs.Delete(req);
                fs.Commit();
                return RedirectToAction("Index");
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
                var req = fs.GetById(id);
                fs.Delete(req);
               fs.Commit();
                return RedirectToAction("Index");
            }
            catch
            {
                return View();
            }
        }
    }
}
