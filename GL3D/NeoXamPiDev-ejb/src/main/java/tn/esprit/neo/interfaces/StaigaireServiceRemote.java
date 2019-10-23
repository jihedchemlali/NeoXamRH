package tn.esprit.neo.interfaces;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.entities.Stagiaire;

@Remote
public interface StaigaireServiceRemote {
	public void addStagiaire(Stagiaire s);
	public List<Stagiaire> getStagiares();
	public void deleteStagiaires(int id);
	public String updateStagiaires(Stagiaire s);
	public Stagiaire findStagiaireById(int id);

}
