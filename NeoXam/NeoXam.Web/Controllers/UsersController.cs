using NeoXam.Domain.Entities;
using NeoXam.Service.Users;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Web;
using System.Web.Mvc;

namespace NeoXam.Web.Controllers
{
    
    public class UsersController : Controller
    {
        UsersService us = new UsersService();
        EmployeeService es = new EmployeeService();
        // GET: Users
        public ActionResult Index()
        {
            HttpClient Client = new HttpClient();

            Client.DefaultRequestHeaders.Accept.Add(new System.Net.Http.Headers.MediaTypeWithQualityHeaderValue("application/json"));
            HttpResponseMessage response = Client.GetAsync("http://localhost:18080/NeoXamPiDev-web/rest/users/GetAll").Result;
            if (response.IsSuccessStatusCode)
            {
                var result = response.Content.ReadAsAsync<IEnumerable<user>>().Result;
                return View(result);
            }
            else
            {
                ViewBag.result = "error";
            }
            return View();
        }
        public ActionResult IndexCandidat()
        {
            HttpClient Client = new HttpClient();

            Client.DefaultRequestHeaders.Accept.Add(new System.Net.Http.Headers.MediaTypeWithQualityHeaderValue("application/json"));
            HttpResponseMessage response = Client.GetAsync("http://localhost:18080/NeoXamPiDev-web/rest/users/ShowAllCandidat").Result;
            if (response.IsSuccessStatusCode)
            {
                var result = response.Content.ReadAsAsync<IEnumerable<candidat>>().Result;
                return View(result);
            }
            else
            {
                ViewBag.result = "error";
            }
            return View();
        }
        public ActionResult IndexEmploye()
        {
            HttpClient Client = new HttpClient();

            Client.DefaultRequestHeaders.Accept.Add(new System.Net.Http.Headers.MediaTypeWithQualityHeaderValue("application/json"));
            HttpResponseMessage response = Client.GetAsync("http://localhost:18080/NeoXamPiDev-web/rest/users/ShowAllEmployee").Result;
            if (response.IsSuccessStatusCode)
            {
                var result = response.Content.ReadAsAsync<IEnumerable<employe>>().Result;
                return View(result);
            }
            else
            {
                ViewBag.result = "error";
            }
            return View();
        }
        // GET: Users/Details/5
        public ActionResult Details(int id)
        {
            return View();
        }

        // GET: Users/Create
        public ActionResult Create()
        {
            return View();
        }

        // POST: Users/Create
        [HttpPost]
        public ActionResult Create(user user)
        {
            user.email_canonical = user.email;
            user.username_canonical = user.username;
            user.Role = "Admin";
            HttpClient client = new HttpClient();
            client.BaseAddress = new Uri("http://localhost:18080/NeoXamPiDev-web/rest/users/AddNewUser");
            client.PostAsJsonAsync("", user).ContinueWith((postTask) => postTask.Result.EnsureSuccessStatusCode());
            return RedirectToAction("Index");
        }

        // GET: Users/Edit/5
        public ActionResult Edit(int id)
        {
            return View();
        }

        // POST: Users/Edit/5
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

        // GET: Users/Delete/5
        public ActionResult Delete(int id)
        {
            var req = us.GetById(id);
            return View(req);
        }

        // POST: Users/Delete/5
        [HttpPost]
        public ActionResult Delete(int id, FormCollection collection)
        {
            try
            {
                HttpClient Client = new HttpClient();
                Client.BaseAddress = new Uri("http://localhost:18080/NeoXamPiDev-web/rest/users/DeleteById/");
                //HTTP DELETE
                var deleteTask = Client.DeleteAsync("" + id.ToString());
                deleteTask.Wait();


                return RedirectToAction("Index");
            }
            catch
            {
                return View();
            }
        }
        public ActionResult SendEmail(String id)
        {
            var req = es.GetById(id);
            return View(req);
        }
        [HttpPost]
        public ActionResult SendEmail(String id, FormCollection collection)
        {
            try
            {
                HttpClient Client = new HttpClient();
               // Client.BaseAddress = new Uri("http://localhost:18080/NeoXamPiDev-web/rest/users/SendEmail/");
               // Client.PostAsJsonAsync("", id).ContinueWith((postTask) => postTask.Result.EnsureSuccessStatusCode());
                var content = new StringContent(id);
                var response = Client.PostAsync("http://localhost:18080/NeoXamPiDev-web/rest/users/SendEmail/", content).Result;

                

                return RedirectToAction("Index");
            }
            catch
            {
                return View();
            }
        }
    }
}
