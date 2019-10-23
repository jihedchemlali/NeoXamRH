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
   public class Productservice : Service<produit>, IproductService
    {
        private static IDatabaseFactory dbFactory = new DatabaseFactory();
        private static IUnitOfWork utwk = new UnitOfWork(dbFactory);
        public Productservice() : base(utwk)
        {
        }
    }
}
