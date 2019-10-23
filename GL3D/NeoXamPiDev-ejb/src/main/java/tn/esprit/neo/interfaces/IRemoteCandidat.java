package tn.esprit.neo.interfaces;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.entities.Candidat;
import tn.esprit.entities.Carriere;
import tn.esprit.entities.Cursus;
import tn.esprit.entities.Skills;
import tn.esprit.neo.dao.IRemoteService;


@Remote
public interface IRemoteCandidat extends IRemoteService<Candidat, String>  {
	public List<Carriere> selectCandidatCarrieres(String cin);
	public List<Cursus> selectCandidatCursus(String cin);
	public List<Skills> selectCandidatSkills(String cin);
}
