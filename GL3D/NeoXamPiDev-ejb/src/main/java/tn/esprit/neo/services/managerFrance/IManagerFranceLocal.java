package tn.esprit.neo.services.managerFrance;

import java.util.List;

import javax.ejb.Local;

import tn.esprit.entities.Employe;
import tn.esprit.entities.ManagerFrance;
import tn.esprit.neo.dao.ILocalService;
@Local
public interface IManagerFranceLocal extends ILocalService<ManagerFrance, String>{
	public List<Employe> findEmployePArManagerFrance(String cin);
}
