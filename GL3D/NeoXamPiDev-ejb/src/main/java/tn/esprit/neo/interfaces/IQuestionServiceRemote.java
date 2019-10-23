package tn.esprit.neo.interfaces;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.entities.Question;
import tn.esprit.entities.Test;
import tn.esprit.neo.dao.IRemoteService;
@Remote
public interface IQuestionServiceRemote {
	
	public int ajouterQuestion(Question question);
	public void affecterQuestionATest(int questionID, int TestId);
	public String getQuestionById(int questionID);
	public List<String> getAllQuestions();
	public void deleteQuestion(int idQuestion);
	public void UpdateQuestion(Question question) ;
	public List<Question> getAllQuestion() ;
	public List<Question> FindQuestionsByTest(Test test) ;
	public Question getQuestionById2(int questionID);
}
