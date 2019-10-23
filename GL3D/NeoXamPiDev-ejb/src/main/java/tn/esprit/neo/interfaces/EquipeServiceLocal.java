package tn.esprit.neo.interfaces;

import java.util.List;

import javax.ejb.Local;

import tn.esprit.entities.Equipe;

@Local
public interface EquipeServiceLocal {

public void AjouteEquipe(Equipe p);
	
	public List<Equipe>  displayAll();
	public void AffecterEquipe(int idProduit,int IdEquipe);
	
	
}
