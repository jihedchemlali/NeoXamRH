package tn.esprit.neoxam.managedbeans;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;

import org.primefaces.event.SelectEvent;

import tn.esprit.entities.CategoryT;
import tn.esprit.entities.Question;
import tn.esprit.entities.Test;
import tn.esprit.neo.services.QuestionService;
import tn.esprit.neo.services.TestService;

@ManagedBean
@SessionScoped
public class TestBean {

	/*@EJB
	UserServiceLocal userServiceLocal;*/
	@EJB
	TestService TestService;
	
	@EJB
	QuestionService QuestionService;
	
	
	//@ManagedProperty("#{authBean.user}")
	
	//private Employee employee = new Employee();
	//private Employee e = new Employee();
	private List<Test> tests = new ArrayList<>();
	//private List<User> employees = new ArrayList<>();
	private List<Question> questions = new ArrayList<>();
	private List<Question> questionslist = new ArrayList<>();
	private Question question = new Question();
	private Test test;
	private Test p;
	private String nom;
	private String type;
	private String description;
	private Date dateExpiration;
	private CategoryT module;
	private int idtestapasser;
	private int resultattst;


	public List<Question> getQuestionslist() {
		return questionslist;
	}

	public void setQuestionslist(List<Question> questionslist) {
		this.questionslist = questionslist;
	}

	String result;
	/*private boolean isselected = false;
	private boolean isdetail = false;
	private boolean istask = false;
	private boolean isNewtask = false;
	private boolean isAdd = false;
	private boolean isUpdate = false;*/
	

	/*@PostConstruct
	public void init(){
		
		test=TestService.FindTestById(15);
		questionslist=QuestionService.FindQuestionsByTest(test);
		resultattst=0;
		}
		*/
	public int resultatcalc(int res){
		if (res==3){
			resultattst++;
		}
		return resultattst;
		
	}
	public List<Test> getTests() {
		//Tests = employeService.getAllEmployes(); 
		tests=TestService.getAllTests();
		return tests;

	}

	public void setTests(List<Test> tests) {
		this.tests = tests;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public Question getQuestion() {
		if (question == null) {
			question = new Question();
		}
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public Test getTest() {
		return test;
	}

	public void setTest(Test test) {
		this.test = test;
	}

	public Test getP() {
		if (p == null) {
			p = new Test();
		}
		return p;
	}

	public void setP(Test p) {
		this.p = p;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}



	public Date getDateExpiration() {
		return dateExpiration;
	}

	public void setDateExpiration(Date dateExpiration) {
		this.dateExpiration = dateExpiration;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public void addTest()
	{
		Test test1 =new Test();
		test1.setNom(nom);
		test1.setType(type);
		test1.setDescription(description);
		System.out.println("date expiration"+dateExpiration);
		test1.setDateExpiration(dateExpiration);
		
		TestService.ajouterTest(test1); 
		 
	}
	public String gotomodifyTest(Test test){
		String navigateTo = "null"; 
		Test tet=new Test();
		tet=TestService.FindTestById(test.getIdTest());
		p=test;
		deleteTest(tet.getIdTest());
		navigateTo="/pages/Test/ModifyTest?faces-redirect=true";
		return navigateTo;
		
	}

	public void modifyTest()
	{

		System.out.println("id de pn"+p.getIdTest());
		
		
		TestService.UpdateTest(p);
		 
	}
	public int getResultattst() {
		return resultattst;
	}

	public void setResultattst(int resultattst) {
		this.resultattst = resultattst;
	}

	public CategoryT getModule() {
		return module;
	}

	public void setModule(CategoryT module) {
		this.module = module;
	}

	public void onDateSelect(SelectEvent event) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getObject())));
    }

	public void deleteTest(int idTest){
		
		TestService.deleteTest(idTest);
	}
	/*public void ajaxListener(AjaxBehaviorEvent event) {
		result = "called by " + event.getComponent().getClass().getName();
		setIsselected(true);
	}*/
	
	public int getIdtestapasser() {
		return idtestapasser;
	}

	public void setIdtestapasser(int idtestapasser) {
		this.idtestapasser = idtestapasser;
	}

	public String passerTest(int idTest,Date dateexp){
		String navigateTo = "null"; 
			
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date datenow = new Date();
		System.out.println(dateFormat.format(datenow));
		System.out.println(dateFormat.format(dateexp));

		if(dateFormat.format(datenow).compareTo(dateFormat.format(dateexp))<0/*datenow.compareTo(dateexp)<0*/){
            System.out.println("you can pass test");
    	    p=TestService.FindTestById(idTest);
    	    questionslist=QuestionService.FindQuestionsByTest(p);
    		navigateTo="/pages/Test/passerTest2?faces-redirect=true";
    	    
    	    
		}

		else 
		{
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("You can't Pass the expired test."));
		}


		return navigateTo;
	}
	

	
}
