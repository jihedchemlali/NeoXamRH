package tn.esprit.neo.dao;



import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

public  class ServiceCRUD<T,S> implements ILocalService<T,S>,IRemoteService<T,S>{
	
	@PersistenceContext(unitName="PIDEV-GL3D-NeoXam-ejb")
	protected EntityManager em;
	protected   Class<T> persistentClass;
	protected  Class<S> keyClass;
	 
	    @SuppressWarnings("unchecked")
		public ServiceCRUD(Class<T> pc,Class<S> kc) {

	        this.persistentClass = pc;
	        this.keyClass = kc;
	    }
	    
	public Class<T> getPersistentClass() {
			return persistentClass;
		}

		public void setPersistentClass(Class<T> persistentClass) {
			this.persistentClass = persistentClass;
		}

		public Class<S> getKeyClass() {
			return keyClass;
		}

		public void setKeyClass(Class<S> keyClass) {
			this.keyClass = keyClass;
		}

	protected EntityManager getEm() {
			return em;
		}

		public void setEm(EntityManager em) {
			this.em = em;
		}
		
	

	@Override
	public T add( T ta) {	
		if(ta!=null)
		getEm().persist(ta);
		return ta;
	}
	@Override
	public void delete( S i) {
		T tt= getEm().find(getPersistentClass(), i);
		System.out.println(tt);
		if(tt!=null) {
			getEm().remove(tt);
		}
	}

	@Override
	public T update(T tm) {
		T x=getEm().merge(tm);
		return  x;
	}

	@Override
	public T find( S i) {	
		T entity=getEm().find(getPersistentClass(), i);
		return entity;
	}

	@Override
	public List<T> findAll() {
		TypedQuery<T> q=(TypedQuery<T>) getEm().createQuery("from "+getPersistentClass().getName());
		return q.getResultList();
	}
//	public ServiceStatful() {
//		Type t = getCla	ss().getGenericSuperclass();
//		ParameterizedType pt = (ParameterizedType) t;
//		type = (Class<T>) pt.getActualTypeArguments()[0];
//		typeFK2 = (Class<S>) pt.getActualTypeArguments()[1];
//	}

	
	
}
