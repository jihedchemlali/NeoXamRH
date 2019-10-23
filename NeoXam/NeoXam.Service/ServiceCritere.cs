using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using NeoXam.Domain.Entities;

using NeoXam.ServicePattern;
using NeoXam.Data.Infrastructure;

namespace NeoXam.Service
{
    public class ServiceCritere:Service<critere>,IServiceCritere
    {
        public static IDatabaseFactory dbf = new DatabaseFactory();
        public static IUnitOfWork uow = new UnitOfWork(dbf);
        public ServiceCritere() : base(uow)
        {

        }
    }
}
