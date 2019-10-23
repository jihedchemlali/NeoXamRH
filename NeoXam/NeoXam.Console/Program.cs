using NeoXam.Domain.Entities;
using NeoXam.Service.Recrutement;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace NeoXam.Console
{
    public class Program
    {
        static void Main(string[] args)
        {
            
            candidat cce = new candidat();
            cce.CIN = "06933258";
            cce.Email = "zz@zz.com";
            CandidatService candidatService = new CandidatService();
            candidatService.Add(cce);
            candidatService.Commit();
            System.Console.WriteLine("ajouter avec succés");
            System.Console.ReadKey();
        }
    }
}
