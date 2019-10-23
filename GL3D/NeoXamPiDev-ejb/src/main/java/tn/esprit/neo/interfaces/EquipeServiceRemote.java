package tn.esprit.neo.interfaces;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.entities.Equipe;

@Remote
public interface EquipeServiceRemote {

	
	public void AjouteEquipe(Equipe p);
	
	public List<Equipe>  displayAll();
	
	public void AffecterEquipe(int idProduit,int IdEquipe);
	
	
	
}
