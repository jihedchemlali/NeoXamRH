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
    public class Congeservice : Service<conge> , ICongeservice
    {
        public static IDatabaseFactory dataContext = new DatabaseFactory();
        public static IUnitOfWork utwk = new UnitOfWork(dataContext);
        public Congeservice() : base(utwk)
        {
        }
    }
}
