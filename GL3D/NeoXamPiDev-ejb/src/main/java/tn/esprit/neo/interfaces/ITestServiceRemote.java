package tn.esprit.neo.interfaces;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.entities.Categorytest;
import tn.esprit.entities.Test;


@Remote
public interface ITestServiceRemote {
	public int ajouterTest(Test test);
	public int ajouterCategorytest(Categorytest categorytest);
	public void affecterTestACategory(int testId, int categorytestID);
	public List<String> getAllTestsNamesByCategory(int categorytestID);
	public List<Test> getAllTests();
	public void deleteTest(int idTest);
	public void UpdateTest(Test test);
	public Test FindTestById(int idtest);
	

}
