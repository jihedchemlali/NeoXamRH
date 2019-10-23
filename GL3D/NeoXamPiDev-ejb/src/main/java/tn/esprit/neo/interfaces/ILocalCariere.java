package tn.esprit.neo.interfaces;

import java.util.List;

import javax.ejb.Local;

import tn.esprit.entities.Carriere;
import tn.esprit.entities.CarriereId;
import tn.esprit.neo.dao.ILocalService;

@Local
public interface ILocalCariere extends ILocalService<Carriere, CarriereId>{
	public List<Carriere> findByCand(String cin);
}
