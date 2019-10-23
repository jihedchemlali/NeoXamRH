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
    public class FormateurService : Service<formateur>, IFormateurService
    {
        static IDatabaseFactory df = new DatabaseFactory();
        static IUnitOfWork uf = new UnitOfWork(df);
        public FormateurService() : base(uf)
        {
        }
        public void UpdateFormateur(formateur formateur)
        {
            df.DataContext.Set<formateur>().AddOrUpdate(formateur);

            df.DataContext.SaveChanges();
        }
    }
}
