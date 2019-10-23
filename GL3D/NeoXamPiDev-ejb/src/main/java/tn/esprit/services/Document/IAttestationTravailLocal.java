package tn.esprit.services.Document;


import java.util.List;

import javax.ejb.Local;

import tn.esprit.entities.AttestaionDeTravail;
import tn.esprit.entities.Employe;
import tn.esprit.neo.dao.ILocalService;

@Local
public interface IAttestationTravailLocal extends ILocalService<AttestaionDeTravail, Integer>{
	public void affecteDocToEmploye();
	public int ajouterDoc(AttestaionDeTravail at);
	public AttestaionDeTravail findDoc(AttestaionDeTravail at);
	public List<AttestaionDeTravail>  findDocByUser(Employe e);
	public List<Employe>  findallEmploye();

}
