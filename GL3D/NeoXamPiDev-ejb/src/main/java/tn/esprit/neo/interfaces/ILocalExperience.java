package tn.esprit.neo.interfaces;

import javax.ejb.Local;

import tn.esprit.entities.Experience;
import tn.esprit.neo.dao.ILocalService;

@Local
public interface ILocalExperience extends ILocalService<Experience, Integer>{

}
