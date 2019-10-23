using NeoXam.Domain.Entities;
using NeoXam.ServicePattern;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using NeoXam.Data.Infrastructure;
using System.Data.Entity.Migrations;

namespace NeoXam.Service.Formation
{
   public class FormationService : Service<formation>, IFormationService
    {
        static IDatabaseFactory df = new DatabaseFactory();
        static IUnitOfWork uf = new UnitOfWork(df);
        public FormationService() : base(uf)
        {
        }
        public IEnumerable<formation> getFormationByUserId(string id)
        {
            var req = from formation
                      in GetMany()
                      where formation.IdUser == id
                      select formation;
            return req;
        }
        public void UpdateFormation(formation formation)
        {
            df.DataContext.Set<formation>().AddOrUpdate(formation);

            df.DataContext.SaveChanges();
        }
    }
}
