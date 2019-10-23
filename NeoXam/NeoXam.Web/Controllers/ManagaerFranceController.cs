using NeoXam.Domain.Entities;
using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Net.Http.Headers;
using System.Text;
using System.Web;
using System.Web.Mvc;
using System.Web.Script.Serialization;
using NeoXam.Data;
using employe = NeoXam.Web.Models.employe;
using manager_france = NeoXam.Web.Models.manager_france;


namespace NeoXam.Web.Controllers
{
    public class ManagaerFranceController : Controller
    {
        ContextNeoXam cn=new ContextNeoXam();

        public ActionResult Action()
        {
            HttpClient Client = new HttpClient();
            Client.BaseAddress = new Uri("http://localhost:18080");
            Client.DefaultRequestHeaders.Accept.Add(new MediaTypeWithQualityHeaderValue("application/json"));
            HttpResponseMessage response = Client. GetAsync("NeoXamPiDev-web/rest/managerFrances").Result;
            return View();
        }
       
        // GET: ManagaerFrance
        public ActionResult Index()
        {
            //HttpClient Client = new HttpClient();
            //Client.BaseAddress = new Uri("http://localhost:18080");
            //Client.DefaultRequestHeaders.Accept.Add(new MediaTypeWithQualityHeaderValue("application/json"));
            //HttpResponseMessage response = Client.GetAsync("NeoXamPiDev-web/rest/managerFrances").Result;
            //if (response.IsSuccessStatusCode)
            //{
            //   // ViewBag.result = response.Content.ReadAsAsync<IEnumerable<manager_france>>().Result;
            //    var managerFrancesrances =  response.Content.ReadAsAsync<IEnumerable<manager_france>>().Result;
            //    return View(managerFrancesrances);
            //}
            //else
            //{
            //    ViewBag.result = "error";
            //    return HttpNotFound();
            //}
            return View();

        }
   
        public ActionResult ManagerFranceEmploye(string id)
        {
           
            HttpClient Client = new HttpClient();
            Client.BaseAddress = new Uri("http://localhost:18080");
            Client.DefaultRequestHeaders.Accept.Add(new MediaTypeWithQualityHeaderValue("application/json"));
            string url = "NeoXamPiDev-web/rest/managerFrances/manager/cin/"+id;
            HttpResponseMessage responce =   Client.GetAsync("NeoXamPiDev-web/rest/managerFrances/manager/cin/"+id).Result;
            if (responce.IsSuccessStatusCode)
            {
  
                JavaScriptSerializer ser = new JavaScriptSerializer();
                string ret = responce.Content.ReadAsStringAsync().Result;
                dynamic dobj = ser.Deserialize<dynamic>(ret);
                object[] lsitem = (object[]) dobj;
                List<employe> response = new List<employe>(); 
                foreach (var item in (lsitem))
                {
                    
                     
                    var _employe = new employe();
                    Dictionary<string, object> _citem = (Dictionary<string , object>) item; 
                    _employe.CIN = (string) _citem["cin"];

                    //_employe.CIN = emp["cin"].ToString();
                    Dictionary<string, object>  _manager = (Dictionary<string, object>)_citem["managerFrance"];
                    manager_france mm =  new manager_france();
                    mm.CIN = (string) _manager["cin"]; 
                        _employe.manager_france = mm ;  
                    response.Add(_employe);
                }

                
                //response.GetEnumerator().Current.CIN =  

                   // IEnumerable <employe> response = ser.Deserialize<IEnumerable<employe>>();
                
                
               // ViewBag.result = response.Content.ReadAsAsync<IEnumerable<manager_france>>().Result;
               // var employee = response.Content.ReadAsAsync<IEnumerable<employe>>().Result;

                return View(response);
            }
            else
            {
                ViewBag.result = "error";
                return HttpNotFound();
            }
        }
          
    }
}