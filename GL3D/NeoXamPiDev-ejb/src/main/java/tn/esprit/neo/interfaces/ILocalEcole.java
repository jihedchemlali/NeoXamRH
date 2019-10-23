package tn.esprit.neo.interfaces;

import javax.ejb.Local;

import tn.esprit.entities.Ecole;
import tn.esprit.neo.dao.ILocalService;

@Local
public interface ILocalEcole extends ILocalService<Ecole, Integer>{

}
