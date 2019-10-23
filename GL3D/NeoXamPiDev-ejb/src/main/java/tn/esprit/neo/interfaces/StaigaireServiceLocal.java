package tn.esprit.neo.interfaces;

import java.util.List;

import javax.ejb.Local;

import tn.esprit.entities.Stagiaire;

@Local
public interface StaigaireServiceLocal {
	public void addStagiaire(Stagiaire s);
	public List<Stagiaire> getStagiares();
	public void deleteStagiaires(int id);
	public String updateStagiaires(Stagiaire s);
	public Stagiaire findStagiaireById(int id);

}
