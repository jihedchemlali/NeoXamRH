using NeoXam.Domain.Entities;
using NeoXam.ServicePattern;
using NeoXam.Data.Infrastructure;
using System.Data.Entity.Migrations;

namespace NeoXam.Service.Recrutement
{
    public class CarriereService : Service<carriere>, ICarriereService
    {
        static IDatabaseFactory df = new DatabaseFactory();
        static IUnitOfWork uf = new UnitOfWork(df);
        public CarriereService() : base(uf)
        {
        }
        public carriere FindKey(string cin,int idexp)
        {
            return df.DataContext.carrieres.Find(cin, idexp);
        }
        public void CheckStat(carriere carriere)
        {
            df.DataContext.Set<carriere>().AddOrUpdate(carriere);

            df.DataContext.SaveChanges();
        }

    }
}
