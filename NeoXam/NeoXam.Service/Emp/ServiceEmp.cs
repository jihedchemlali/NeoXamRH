using NeoXam.Data.Infrastructure;
using NeoXam.Domain.Entities;
using NeoXam.ServicePattern;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace NeoXam.Service.Emp
{
  public  class ServiceEmp : Service<employe>, IService<employe>
    {
        static IDatabaseFactory df = new DatabaseFactory();
        static IUnitOfWork uf = new UnitOfWork(df);
        public ServiceEmp() : base(uf)
        {
        }
    }
}
