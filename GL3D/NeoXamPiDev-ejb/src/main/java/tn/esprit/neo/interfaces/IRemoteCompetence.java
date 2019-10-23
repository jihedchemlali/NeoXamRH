package tn.esprit.neo.interfaces;

import javax.ejb.Remote;

import tn.esprit.entities.Competence;
import tn.esprit.neo.dao.IRemoteService;

@Remote
public interface IRemoteCompetence extends IRemoteService<Competence,Integer>{

}
