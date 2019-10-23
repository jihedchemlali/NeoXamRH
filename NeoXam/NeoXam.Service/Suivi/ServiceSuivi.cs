using NeoXam.Domain.Entities;
using NeoXam.ServicePattern;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using NeoXam.Data.Infrastructure;

namespace NeoXam.Service.Suivi
{
    public class ServiceSuivi : Service<suivi>, IService<suivi>
    {
        static IDatabaseFactory df = new DatabaseFactory();
        static IUnitOfWork uf = new UnitOfWork(df);
        public ServiceSuivi() : base(uf)
        {
        }
        //public List<employe> getEmploye()
        //{
        //    IEnumerable<employe> p = (from employe in uf.getRepository<employe>().GetMany
        //                                  //where project.State == EnumProjStat.Accepted
        //                              select employe);
        //    List<employe> list = p.ToList<employe>();
        //    return list;
        //}
    }
}
