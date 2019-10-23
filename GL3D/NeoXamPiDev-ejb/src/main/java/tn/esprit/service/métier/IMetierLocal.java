package tn.esprit.service.métier;

import javax.ejb.Local;

import tn.esprit.entities.Metier;
import tn.esprit.neo.dao.ILocalService;


@Local
public interface IMetierLocal extends ILocalService<Metier, Integer> {

}
