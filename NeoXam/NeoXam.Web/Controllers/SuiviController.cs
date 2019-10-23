using NeoXam.Service.Emp;
using NeoXam.Service.Suivi;
using NeoXam.Web.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using NeoXam.Domain.Entities;
using System.IO;
using System.Net;
using System.Data.Entity;
using System.Web.UI.WebControls;
using System.Web.UI;
using System.Net.Mail;

namespace NeoXam.Web.Controllers
{
    public class SuiviController : Controller
    {
        ServiceSuivi ss = new ServiceSuivi();
        ServiceEmp se = new ServiceEmp();
        private ApplicationDbContext db = new ApplicationDbContext();
       public string recieverEmail;
       public string subject = "Nouveau Suivi effectuer";
       public string message = "";
        public string subject2 = "Votre Suivi a ete supprimer";
        public string message2 = "Bonjour, votre suivi a ete supprimé avec succé cordialement";
        public string subject3 = "Votre Suivi a ete modifier";
        public string message3 = "";
        // GET: Suivi
        public ActionResult Index()
        {
            var suivi = ss.GetMany();
            return View(suivi);
        }

        // GET: Suivi/Details/5
        public ActionResult Details(int id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            Domain.Entities.suivi suivi = ss.GetById(id);
            if (suivi == null)
            {
                return HttpNotFound();
            }
            return View(suivi);
        }
  
        // GET: Suivi/Create
        public ActionResult Create()
        {
            var emp = se.GetMany();
            Models.suivi pm = new Models.suivi();

            pm.employes = emp.Select(p => new SelectListItem
            { Text = p.Nom, Value = p.CIN.ToString() });
            return View(pm);
         
        }

        // POST: Suivi/Create
        [HttpPost]
        public ActionResult Create(Models.suivi pm)
        {
            try
            {
                // TODO: Add insert logic here
                Domain.Entities.suivi p = new Domain.Entities.suivi();
                p.CIN = pm.CIN;
                p.DateSuivi = pm.DateSuivi;
                p.EntretrienTCarriere = pm.EntretrienTCarriere;
                p.Eval = pm.Eval;
                ss.Add(p);
                Domain.Entities.employe e = new Domain.Entities.employe();
                var emp = se.GetMany().Where(c => c.CIN.Equals(p.CIN)).FirstOrDefault();

                var senderemail = new MailAddress("hayawin2@gmail.com", "NeoXam Salaire");
                var recieveremail = new MailAddress(emp.Email, "Reciever");
                var password = "firas1234@";

                message = "Bonjour, " + "\n" + "votre suivi a ete ajouter avec succé ! " + "\n" +
                " " + "\n" + "Nouvelle Evaluation : " + p.Eval + "\n" +

                " " + "\n" + "Nouveau Entretient : " + p.EntretrienTCarriere + "\n" +

                " " + "\n" + "Nouvelle Date : " + p.DateSuivi + "\n" +

                " " + "\n" + "" + "\n" + "cordialement ";
                var sub = subject;
                var body = message;
                var smtp = new SmtpClient
                {
                    Host = "smtp.gmail.com",
                    Port = 587,
                    EnableSsl = true,
                    DeliveryMethod = SmtpDeliveryMethod.Network,
                    UseDefaultCredentials = false,
                    Credentials = new NetworkCredential(senderemail.Address, password)
                };
                using (var mess = new MailMessage(senderemail, recieveremail)
                {
                    Subject = subject,
                    Body = body
                }
                    )
                {
                    smtp.Send(mess);
                }
                ss.Commit();
                return RedirectToAction("Index");
            }
            catch
            {
                return View();
            }
        }

        // GET: Suivi/Edit/5
        public ActionResult Edit(int id)
        {
            Domain.Entities.suivi p = ss.GetById(id);

        //    Domain.Entities.employe cat = se.GetById(id);

            Models.suivi pm = new Models.suivi();

           
            var suiviModel = new Models.suivi
            {
                idSuiv = p.idSuiv,
                Eval = p.Eval,
                EntretrienTCarriere = p.EntretrienTCarriere,
                DateSuivi = p.DateSuivi,
              
            };

            var emp = se.GetMany();
            Models.suivi pm1 = new Models.suivi();

            pm1.employes = emp.Select(pp => new SelectListItem
            { Text = pp.Nom, Value = pp.CIN.ToString() });

            suiviModel.employes = pm1.employes;
            return View(suiviModel);

         
        }

        // POST: Suivi/Edit/5
        [HttpPost]

        public ActionResult Edit(int id, Models.suivi suiviModel)
        {
            try
            {
                var suivi = ss.GetMany().Where(c => c.idSuiv.Equals(id)).FirstOrDefault();
                var s = suivi;
                // TODO: Add update logic here
                suivi.idSuiv = suiviModel.idSuiv;
                suivi.Eval = suiviModel.Eval;
                suivi.EntretrienTCarriere = suiviModel.EntretrienTCarriere;
                suivi.DateSuivi = suiviModel.DateSuivi;
                suivi.CIN = suiviModel.CIN;

                ss.Update(suivi);
                Domain.Entities.employe e = new Domain.Entities.employe();
                var emp = se.GetMany().Where(c => c.CIN.Equals(suiviModel.CIN)).FirstOrDefault();

                var senderemail = new MailAddress("hayawin2@gmail.com", "NeoXam Salaire");
                var recieveremail = new MailAddress(emp.Email, "Reciever");
                var password = "firas1234@";
              
                message3 = "Bonjour, " + "\n" + "votre suivi a ete modifer avec succé ! " + "\n" +
                    " " + "\n" + "Ancienne Evaluation : " + s.Eval + "\n" +
               " " + "\n" + "Nouvelle Evaluation : " + suiviModel.Eval + "\n" +
                       " " + "\n" + "Ancien Entretient : " + s.EntretrienTCarriere + "\n" +
               " " + "\n" + "Nouveau Entretient : " + suiviModel.EntretrienTCarriere + "\n" +
       
               " " + "\n" + "Ancienne Date : " + s.DateSuivi + "\n" +
               " " + "\n" + "Nouvelle Date : " + suiviModel.DateSuivi + "\n" +

               " " + "\n" + "" + "\n" + "cordialement ";
                var sub = subject3;
                var body = message3;
                var smtp = new SmtpClient
                {
                    Host = "smtp.gmail.com",
                    Port = 587,
                    EnableSsl = true,
                    DeliveryMethod = SmtpDeliveryMethod.Network,
                    UseDefaultCredentials = false,
                    Credentials = new NetworkCredential(senderemail.Address, password)
                };
                using (var mess = new MailMessage(senderemail, recieveremail)
                {
                    Subject = subject3,
                    Body = body
                }
                    )
                {
                    smtp.Send(mess);
                }
                ss.Commit();
                return RedirectToAction("Index");
            }
            catch
            {
                return View();
            }
        }

        // GET: Suivi/Delete/5
        public ActionResult Delete(int id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            Models.suivi suivi = db.suivis.Find(id);
            if (suivi == null)
            {
                return HttpNotFound();
            }
            return View(suivi);
        }

        // POST: Suivi/Delete/5
        [HttpPost]
        public ActionResult Delete(int id, FormCollection collection)
        {
            try
            {
                var suivi = ss.GetMany().Where(c => c.idSuiv.Equals(id)).FirstOrDefault();
                var emp = se.GetMany().Where(c => c.CIN.Equals(suivi.CIN)).FirstOrDefault();
                ss.Delete(suivi);

                Domain.Entities.employe e = new Domain.Entities.employe();
                                
                var senderemail = new MailAddress("hayawin2@gmail.com", "NeoXam Salaire");
                var recieveremail = new MailAddress(emp.Email, "Reciever");
                var password = "firas1234@";

                var sub = subject2;
                var body = message2;
                var smtp = new SmtpClient
                {
                    Host = "smtp.gmail.com",
                    Port = 587,
                    EnableSsl = true,
                    DeliveryMethod = SmtpDeliveryMethod.Network,
                    UseDefaultCredentials = false,
                    Credentials = new NetworkCredential(senderemail.Address, password)
                };
                using (var mess = new MailMessage(senderemail, recieveremail)
                {
                    Subject = subject2,
                    Body = body
                }
                    )
                {
                    smtp.Send(mess);
                }
                ss.Commit();
                // TODO: Add delete logic here

                return RedirectToAction("Index");
            }
            catch
            {
                return View();
            }
        }
        public ActionResult ExportData()
        {
            var datasource = ss.GetMany().ToList();

            GridView gv = new GridView();
            gv.DataSource = datasource;
            gv.DataBind();
            Response.ClearContent();
            Response.Buffer = true;
            Response.AddHeader("content-disposition", "attachment; filename=MesSuivis.xls");
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
    }
}
