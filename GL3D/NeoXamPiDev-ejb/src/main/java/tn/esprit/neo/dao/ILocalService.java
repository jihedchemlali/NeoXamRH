package tn.esprit.neo.dao;

import java.util.List;

import javax.ejb.Local;


@Local
public interface ILocalService<T,S> {
	public T add(T ta);
	public void delete( S i);
	public T update(T tm);
	public T find(S i);
	public List<T>findAll();	
}
