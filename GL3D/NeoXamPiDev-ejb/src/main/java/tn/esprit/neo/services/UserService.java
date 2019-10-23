package tn.esprit.neo.services;


import java.util.List;
import java.util.*; 
import javax.mail.*; 
import javax.mail.PasswordAuthentication;
import javax.mail.internet.*;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.mail.Session;
import javax.persistence.TypedQuery;

import tn.esprit.entities.Candidat;
import tn.esprit.entities.Employe;
import tn.esprit.entities.User;

import tn.esprit.neo.dao.ServiceCRUD;

import tn.esprit.neo.interfaces.IUserServiceLocal;
import tn.esprit.neo.interfaces.IUserServiceRemote;
@Stateless
@LocalBean
public class UserService extends  ServiceCRUD<User, Integer>implements IUserServiceLocal, IUserServiceRemote {

	public UserService() {
		super(User.class, Integer.class);

	}

	@Override
	public void SendEmail(String id,String passwordC) {
		System.out.println("testService");
		Candidat c  = em.find(Candidat.class,id);
		
		final String username = "soukelmedina@gmail.com";
		final String password = "123456*/";
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(username, password);
			}
		  });
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("rabiy.bennour@esprit.tn"));
			message.setRecipients(Message.RecipientType.TO,
			InternetAddress.parse(c.getEmail()));
			message.setSubject("Candidature Neoxam");
			message.setText("Dear "+c.getNom()+","
				+ "\n\n Vous etes Accepetez pour passer les testes de Neoxam"
				+ "\n\n Veuillez acceder  a notre Plateform avec les Cordonées ci dessous"
				+ "\n\n Username: "+c.getEmail()
				+ "\n\n Password: "+passwordC
				+ "\n\n Merci Pour Votre Attention");
			Transport.send(message);
			System.out.println("Done");
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
		System.out.println("testService");
	}
		
	@Override
	public void SendEmailEmp(String id,String passwordC) {
		System.out.println("testService");
		Employe e  = em.find(Employe.class,id);
		
		final String username = "soukelmedina@gmail.com";
		final String password = "123456*/";
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(username, password);
			}
		  });
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("soukelmedina@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
			InternetAddress.parse(e.getEmail()));
			message.setSubject("Compte Neoxam");
			message.setText("Dear "+e.getNom()+","
				+ "\n\n Votre compte NeoXam a ete crée "
				+ "\n\n Veuillez acceder  a votre Compte avec les Cordonées ci dessous"
				+ "\n\n Username: "+e.getEmail()
				+ "\n\n Password: "+passwordC
				+ "\n\n Merci Pour Votre Attention");
			Transport.send(message);
			System.out.println("Done");
		} catch (MessagingException e1) {
			throw new RuntimeException(e1);
		}
		System.out.println("testService");
	}
		

	@Override
	public User getUser(String email) {
		TypedQuery<User> query =
				em.createQuery("SELECT u FROM  User u WHERE u.email=:email ",User.class);
				query.setParameter("email",email);
				
				
				User utilsiateur = null;
				try { utilsiateur = query.getSingleResult();
				
				}
				catch (Exception e) { System.out.println("Erreur : " + e); }
				return utilsiateur;
	}
	@Override
	public List<Candidat> getAllCandidates() {
			List<Candidat> candidates = em.createQuery("Select c from Candidat c", Candidat.class).getResultList();
			return candidates;
	}
	public void ChangePass(User user)
	{
		em.merge(user);
	}

	@Override
	public List<User> getAllUser() {
		List<User> users = em.createQuery("Select u from User u", User.class).getResultList();
		return users;
	}

	@Override
	public List<Employe> getAllEmployee() {
		List<Employe> employees = em.createQuery("Select e from Employe e where e.haveC=0", Employe.class).getResultList();
		return employees;
	}
	@Override
	public void SupprimerCin(String cin )
	{
		Candidat c=em.find(Candidat.class, cin);
		em.remove(c);
	}
	@Override
	public void SupprimerCinEmp(String cin )
	{
		Employe c=em.find(Employe.class, cin);
		em.remove(c);
	}

	@Override
	public Employe getEmloyeByIdU(String id) {
		
		Employe employe = em.find(Employe.class, id);
		return employe;
	}

	@Override
	public Candidat getCandidatByIdU(String id) {
		Candidat c = em.find(Candidat.class, id);
		return c;
	}
 
	
	
}
