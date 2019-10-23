package tn.esprit.neo.interfaces;

import java.util.List;

import javax.ejb.Local;

import tn.esprit.entities.Candidat;
import tn.esprit.entities.Carriere;
import tn.esprit.entities.Cursus;
import tn.esprit.entities.Skills;
import tn.esprit.neo.dao.ILocalService;
@Local
public interface ILocalCandidat  extends ILocalService<Candidat, String> {
	public List<Carriere> selectCandidatCarrieres(String cin);
	public List<Cursus> selectCandidatCursus(String cin);
	public List<Skills> selectCandidatSkills(String cin);
}
