package tn.esprit.neo.services.managerFrance;


import java.util.List;

import javax.ejb.Remote;

import tn.esprit.entities.Employe;
import tn.esprit.entities.ManagerFrance;
import tn.esprit.neo.dao.IRemoteService;
@Remote
public interface IManagerFranceRemote extends IRemoteService<ManagerFrance, String>{
public List<Employe> findEmployePArManagerFrance(String cin);
}
