﻿using NeoXam.Data.Infrastructure;

using NeoXam.ServicePattern;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Linq.Expressions;
using System.Text;
using System.Threading.Tasks;

namespace NeoXam.ServicePattern
{
    public class Service<TEntity> : IService<TEntity> 
        where TEntity : class
    {
        #region Private Fields
        IUnitOfWork utwk;
        #endregion Private Fields

        #region Constructor
        protected Service(IUnitOfWork utwk)
        {
            this.utwk = utwk;
        }
        #endregion Constructor


        public void Dispose()
        {
            utwk.Dispose();
        }
        public virtual void Add(TEntity entity)
        {
            ////_repository.Add(entity);
            utwk.getRepository<TEntity>().Add(entity);
        }

        public virtual void Update(TEntity entity)
        {
            //_repository.Update(entity);
            utwk.getRepository<TEntity>().Update(entity);
        }

        public virtual void Delete(TEntity entity)
        {
            //   _repository.Delete(entity);
            utwk.getRepository<TEntity>().Delete(entity);
        }


        public virtual int getcount()
        {
            return utwk.getRepository<TEntity>().GetMany().Count();

        }
        public virtual void Delete(Expression<Func<TEntity, bool>> where)
        {
            // _repository.Delete(where);
            utwk.getRepository<TEntity>().Delete(where);
        }

        public virtual TEntity GetById(long id)
        {
            //  return _repository.GetById(id);
            return utwk.getRepository<TEntity>().GetById(id);
        }

        public virtual TEntity GetById(string id)
        {
            //return _repository.GetById(id);
            return utwk.getRepository<TEntity>().GetById(id);
        }

        public virtual IEnumerable<TEntity> GetMany(Expression<Func<TEntity, bool>> filter = null, Expression<Func<TEntity, bool>> orderBy = null)
        {
            //  return _repository.GetAll();
            return utwk.getRepository<TEntity>().GetMany(filter,orderBy);
        }

        public virtual TEntity Get(Expression<Func<TEntity, bool>> where)
        {
            //return _repository.Get(where);
            return utwk.getRepository<TEntity>().Get(where);
        }  
        public void Commit()
        {
            try
            {
                utwk.Commit();
            }
            catch (Exception ex)
            {

                System.Console.Write(ex.StackTrace);
            }
        }

       
        
    }
}
