package tn.esprit.neo.interfaces;

import javax.ejb.Local;

import tn.esprit.entities.Competence;
import tn.esprit.neo.dao.ILocalService;

@Local	
public interface ILocalCompetence extends ILocalService<Competence, Integer>{

}
