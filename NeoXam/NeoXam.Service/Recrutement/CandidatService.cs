using NeoXam.Domain.Entities;
using NeoXam.ServicePattern;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using NeoXam.Data.Infrastructure;
using System.Data.Entity;
using System.Data.Entity.Migrations;

namespace NeoXam.Service.Recrutement
{
    public class CandidatService : Service<candidat>, ICandidatService
    {
        static IDatabaseFactory df = new DatabaseFactory();
        static IUnitOfWork uf = new UnitOfWork(df);
        public CandidatService() : base(uf)
        {
                
        }
        /*
         * Cette méthode fait l'update *
         */
        public void CheckStat(candidat candidat)
        {
            df.DataContext.Set<candidat>().AddOrUpdate(candidat); 
           
            df.DataContext.SaveChanges();                
        }
       
    }

}
