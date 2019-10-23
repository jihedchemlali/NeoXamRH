package tn.esprit.neo.interfaces;

import javax.ejb.Remote;

import tn.esprit.entities.Salaire;
import tn.esprit.neo.dao.ILocalService;

@Remote
public interface ISalaireServiceRemote extends ILocalService<Salaire, Integer> {

}
