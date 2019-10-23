using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using NeoXam.ServicePattern;
using NeoXam.Domain.Entities;
using NeoXam.Data.Infrastructure;

namespace NeoXam.Service
{
    public class EmployerService : Service<employe>, IEmployerService
    {
        public static IDatabaseFactory dataContext = new DatabaseFactory();
        public static IUnitOfWork utwk = new UnitOfWork(dataContext);
        public EmployerService() : base(utwk)
        {
        }
    }
}
