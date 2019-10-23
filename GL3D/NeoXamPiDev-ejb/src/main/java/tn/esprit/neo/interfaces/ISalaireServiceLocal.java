package tn.esprit.neo.interfaces;

import javax.ejb.Local;

import tn.esprit.entities.Salaire;
import tn.esprit.neo.dao.ILocalService;

@Local
public interface ISalaireServiceLocal extends ILocalService<Salaire, Integer> {

}
