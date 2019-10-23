using NeoXam.Domain.Entities;
using NeoXam.ServicePattern;

using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using NeoXam.Data.Infrastructure;

namespace NeoXam.Service.Ecole
{
    public class EcoleService: Service<Domain.Entities.ecole>,IService<Domain.Entities.ecole>

    {
        static IDatabaseFactory dbf = new DatabaseFactory();
        RepositoryBase<ecole> Repo = new RepositoryBase<ecole>(dbf);
        public static NeoXam.Data.Infrastructure.IUnitOfWork uow = new UnitOfWork(dbf);
        public EcoleService() : base(uow)
        {

        }

    }
}
