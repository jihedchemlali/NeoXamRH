package tn.esprit.neoxam.managedbeans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import org.primefaces.model.UploadedFile;

import tn.esprit.entities.Question;
import tn.esprit.entities.Test;
import tn.esprit.neo.services.QuestionService;
import tn.esprit.neo.services.TestService;

@ManagedBean
@SessionScoped
public class QuestionBean {

	@EJB
	QuestionService questionService;
	@EJB
	TestService testService;
	
	private String type;
	private String reponse;
	private String contenue;
	private String choixA;
	private String choixB;
	private String choixC;
	private String image;
	private Test test;
	private Question q;
	public Question getQ() {
		return q;
	}


	public void setQ(Question q) {
		this.q = q;
	}

	private int TestId;
	private int idQuestion;
	private UploadedFile fileimage;
	private List<Question> questions = new ArrayList<>();
	private List<Test> questionList;
	private List<Integer> questionID = new ArrayList<Integer>();
	private List<SelectItem> questionsFilterOptions;

	public int getTestId() {
		return TestId;
	}


	public void setTestId(int testId) {
		TestId = testId;
	}


	//@PostConstruct
	public void init(){	
		questionList = testService.getAllTests();
		questionsFilterOptions = new ArrayList<SelectItem>(
				questionList.size() + 1);
		questionsFilterOptions.add(new SelectItem("", "choisir une"));
		for (Test test : questionList) {
			questionsFilterOptions.add(new SelectItem(test.getNom(),
					test.getType()));
		}

	
	
	}

	
	public List<Test> getQuestionList() {
		return questionList;
	}


	public void setQuestionList(List<Test> questionList) {
		this.questionList = questionList;
	}


	public List<SelectItem> getQuestionsFilterOptions() {
		return questionsFilterOptions;
	}


	public void setQuestionsFilterOptions(List<SelectItem> questionsFilterOptions) {
		this.questionsFilterOptions = questionsFilterOptions;
	}


	public List<Integer> getQuestionID() {
		return questionID;
	}
	public void setQuestionID(List<Integer> questionID) {
		this.questionID = questionID;
	}
	public UploadedFile getFileimage() {
		return fileimage;
	}
	public void setFileimage(UploadedFile fileimage) {
		this.fileimage = fileimage;
	}
	public List<Question> getQuestions() {
			questions=questionService.getAllQuestion();
			return questions;
	}
	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}
	public QuestionService getQuestionService() {
		return questionService;
	}
	public void setQuestionService(QuestionService questionService) {
		this.questionService = questionService;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getReponse() {
		return reponse;
	}
	public void setReponse(String reponse) {
		this.reponse = reponse;
	}
	public String getContenue() {
		return contenue;
	}
	public void setContenue(String contenue) {
		this.contenue = contenue;
	}
	public String getChoixA() {
		return choixA;
	}
	public void setChoixA(String choixA) {
		this.choixA = choixA;
	}
	public String getChoixB() {
		return choixB;
	}
	public void setChoixB(String choixB) {
		this.choixB = choixB;
	}
	public String getChoixC() {
		return choixC;
	}
	public void setChoixC(String choixC) {
		this.choixC = choixC;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
	public void addQuestion()
	{
		//init();
		Question question1 = new Question();
		question1.setChoixA(choixA);
		question1.setChoixB(choixB);
		question1.setChoixC(choixC);
		question1.setContenue(contenue);
		question1.setReponse(reponse);
		question1.setType(type);
		//question1.setImage(image);
		//System.out.println("le id de type "+test.getIdTest());
		questionService.ajouterQuestion(question1);
		
		//questionService.affecterQuestionATest(question1.getIdQuestion(), TestId);
	}
	
	public void deleteQuestion(int idQuestion){
		questionService.deleteQuestion(idQuestion);
	}
	public Test getTest() {
		return test;
	}
	public void setTest(Test test) {
		this.test = test;
	}
	public int getIdQuestion() {
		return idQuestion;
	}
	public void setIdQuestion(int idQuestion) {
		this.idQuestion = idQuestion;
	}
	

	public String gotomodifyQuestion(Question question){
		String navigateTo = "null"; 
		Question quss = new Question();
		quss=questionService.getQuestionById2(question.getIdQuestion());
		q=question;
		deleteQuestion(quss.getIdQuestion());
		navigateTo="/pages/Test/ModifyQuestion?faces-redirect=true";
		return navigateTo;
		
	}

	public void modifyTest()
	{
		//System.out.println("id de pn"+p.getIdTest());	
	questionService.UpdateQuestion(q);
		 
	}

	
}
