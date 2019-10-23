using NeoXam.Domain.Entities;
using NeoXam.ServicePattern;

using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using NeoXam.Data.Infrastructure;

namespace NeoXam.Service.Diplome
{
    public class DiplomeService : Service<Domain.Entities.diplome>, IService<Domain.Entities.diplome>

    {
        static IDatabaseFactory dbf = new DatabaseFactory();
        RepositoryBase<diplome> Repo = new RepositoryBase<diplome>(dbf);
        public static NeoXam.Data.Infrastructure.IUnitOfWork uow = new UnitOfWork(dbf);
        public DiplomeService() : base(uow)
        {

        }

    }
}
