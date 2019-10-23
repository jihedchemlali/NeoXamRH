package tn.esprit.neo.service.interf;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.entities.Equipe;

@Remote
public interface EquipeServiceRemote {

	
	public int AjouteEquipe(Equipe p);
	
	public List<Equipe>  displayAll();
	
	public void AffecterEquipe(int idProduit,int IdEquipe);
	
	
	
}
