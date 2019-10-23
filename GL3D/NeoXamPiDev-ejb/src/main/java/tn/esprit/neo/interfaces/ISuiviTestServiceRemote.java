package tn.esprit.neo.interfaces;

import tn.esprit.entities.Suivitest;

import java.util.Date;
import java.util.List;

import tn.esprit.entities.Candidat;

public interface ISuiviTestServiceRemote  {

	public void ajouterSuiviTest(int testId, String CandidatId);

	public List<Candidat> getAllCandidatByTest(int testId);


}
