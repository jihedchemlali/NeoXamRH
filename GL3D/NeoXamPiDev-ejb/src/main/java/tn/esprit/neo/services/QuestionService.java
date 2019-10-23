package tn.esprit.neo.services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.entities.Question;
import tn.esprit.entities.Test;
import tn.esprit.neo.interfaces.IQuestionServiceRemote;



@Stateless
@LocalBean
public class QuestionService implements IQuestionServiceRemote {
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public int ajouterQuestion(Question question) {
		em.persist(question);	
		return question.getIdQuestion();
	}

	@Override
	public void affecterQuestionATest(int questionID, int TestId) {
		Question question = em.find(Question.class, questionID );
		Test test = em.find(Test.class, TestId );	
		
		question.setTest(test);
		test.getQuestions().add(question);
		
	}

	@Override
	public String getQuestionById(int questionID) {
		Question question = em.find(Question.class, questionID );
		return question.getContenue();
	}
	@Override
	public Question getQuestionById2(int questionID) {
		Question question = em.find(Question.class, questionID );
		return question;
	}

	@Override
	public List<String> getAllQuestions() {
		List <Question> questions = em.createQuery("SELECT i FROM Question i ",Question.class).getResultList();
		List lst=new ArrayList<String>();
		
		for(Question e: questions)
		{
		lst.add(e.getContenue()+" "+e.getType());
		}
		return lst;

	}
	@Override
	public void deleteQuestion(int idQuestion) {
		Question question = em.find(Question.class, idQuestion);
		em.remove(question);
		
	}
	@Override
	public void UpdateQuestion(Question question) {
		em.merge(question);
	}
	@Override
	public List<Question> getAllQuestion() {
		return (em.createQuery("SELECT t FROM Question t")).getResultList();
	}
	@Override
	public List<Question> FindQuestionsByTest(Test test) {
		return em.createQuery("SELECT t FROM Question t WHERE t.test=?1", Question.class)
				.setParameter(1, test).getResultList();
	}

}
