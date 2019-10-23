using NeoXam.Domain.Entities;
using NeoXam.Service;
using NeoXam.Web.Models;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web;
using System.Web.Mvc;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace NeoXam.Web.Controllers
{
    public class CongeController : Controller
    {
        // GET: Conge
        Congeservice sp = new Congeservice();
        EmployerService em = new EmployerService();

        public ActionResult Index()
        {
            var conge = sp.GetMany();
            return View(conge);
        }

        public ActionResult IndexEXEL()
        {
            var conge = sp.GetMany();
            return View(conge);
        }

        public ActionResult ExportData()
        {
            var datasource = sp.GetMany().ToList();

            GridView gv = new GridView();
            gv.DataSource = datasource;
            gv.DataBind();
            Response.ClearContent();
            Response.Buffer = true;
            Response.AddHeader("content-disposition", "attachment; filename=Report.xls");
            Response.ContentType = "application/ms-excel";
            Response.Charset = "";
            StringWriter sw = new StringWriter();
            HtmlTextWriter htw = new HtmlTextWriter(sw);
            gv.RenderControl(htw);
            Response.Output.Write(sw.ToString());
            Response.Flush();
            Response.End();

            return Json("Success");
        }

        // GET: Conge/Details/5
        public ActionResult Details(int id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            Domain.Entities.conge conge = sp.GetById(id);
            if (conge == null)
            {
                return HttpNotFound();
            }
            return View(conge);
        }

        // GET: Conge/Create
        public ActionResult Create()
        {
            var cat = em.GetMany();
            Conge pm = new Conge();
            pm.employer = cat.Select(p => new SelectListItem
            {
                Text = p.Nom,
                Value = p.CIN
            });
            return View(pm);
        }

        // POST: Conge/Create
        [HttpPost]
        public ActionResult Create(Conge pm)
        {

            try
            {
                // TODO: Add insert logic here
                conge p = new conge();
               
                p.dateDebut = pm.dateDebut;
                p.dateFin = pm.dateFin;
                p.etat = pm.etat;
                p.motifs = pm.motifs;
                p.raison = pm.raison;
                p.type = pm.type;
                p.CIN = pm.CIN;

                //2eme etape
                //prods.Add(p);
                //3eme etape
                //Session["Products"] = prods;
                sp.Add(p);
                sp.Commit();


                return RedirectToAction("Index");
            }
            catch
            {
                return View();
            }
        }

        // GET: Conge/Edit/5


        // POST: Conge/Edit/5

        public ActionResult Edit(int id)
        {
            Domain.Entities.conge p = sp.GetById(id);

            //    Domain.Entities.employe cat = se.GetById(id);

            Models.Conge pm = new Models.Conge();


            var congeModel = new Models.Conge
            {
                idConge = p.idConge,
                type = p.type,
                raison = p.raison,
                motifs = p.motifs,
                etat = p.etat,
                dateDebut = p.dateDebut,
                dateFin = p.dateFin,
                CIN = p.CIN

            };

            var emp = em.GetMany();
            Models.Conge pm1 = new Models.Conge();

            pm1.employer = emp.Select(pp => new SelectListItem
            { Text = pp.Nom, Value = pp.CIN.ToString() });

            congeModel.employer = pm1.employer;
            return View(congeModel);


        }






        [HttpPost]

        public ActionResult Edit(int id, Models.Conge congemodel)
        {
            try
            {
                var conge = sp.GetMany().Where(c => c.idConge.Equals(id)).FirstOrDefault();

                // TODO: Add update logic here
                conge.idConge = congemodel.idConge;
                conge.etat = congemodel.etat;
                conge.motifs = congemodel.motifs;
                conge.type = congemodel.type;
                conge.raison = congemodel.raison;
                conge.dateDebut = congemodel.dateDebut;
                conge.dateFin = congemodel.dateFin;
                conge.CIN = congemodel.CIN;



                sp.Update(conge);
                Domain.Entities.employe e = new Domain.Entities.employe();
                var emp = em.GetMany().Where(c => c.CIN.Equals(congemodel.CIN)).FirstOrDefault();


                sp.Commit();
                return RedirectToAction("Index");
            }
            catch
            {
                return View();
            }
        }




        // GET: Conge/Delete/5
        public ActionResult Delete(int id)
        {
            var Conge = sp.GetMany().Where(c => c.idConge.Equals(id)).FirstOrDefault();
            sp.Delete(Conge);
            sp.Commit();
            return RedirectToAction("Index");
        }

        // POST: Conge/Delete/5
        [HttpPost]
        public ActionResult Delete(int id, FormCollection collection)
        {
            try
            {
                var Conge = sp.GetMany().Where(c => c.idConge.Equals(id)).FirstOrDefault();
                sp.Delete(Conge);
                sp.Commit();
                // TODO: Add delete logic here

                return RedirectToAction("Index");
            }
            catch
            {
                return View();
            }
        }

        public ActionResult statistic()
        {
            return View();
        }
        public ActionResult statisticWeb()
        {
            return View();
        }




        [HttpPost]
        public JsonResult GetStat()
        {


            var dt = sp.GetMany().GroupBy(a => a.raison)
                        .Select(g => new { g.Key, Count = g.Count() });
            return new JsonResult { Data = dt };


        }

        [HttpPost]
        public JsonResult GetStatWeb()
        {
            HttpClient Client = new HttpClient();
            String result;
            // Client.BaseAddress = new Uri("http://localhost:18080");
            Client.DefaultRequestHeaders.Accept.Add(new System.Net.Http.Headers.MediaTypeWithQualityHeaderValue("application/json"));
            HttpResponseMessage response = Client.GetAsync("http://localhost:18080/NeoXamPiDev-web/rest/projets/getstat").Result;
            if (response.IsSuccessStatusCode)
            {
                result = response.Content.ReadAsStringAsync().Result;

            }

            else
            {
                result = null;
            }

            return new JsonResult { Data = result };


        }


        public JsonResult GetSearch(string s)
        {
            List<conge> listerconge = sp.GetMany(c => c.raison.Contains(s)).ToList<conge>();

            return new JsonResult { Data = listerconge, JsonRequestBehavior = JsonRequestBehavior.AllowGet };

        }
    }
}
