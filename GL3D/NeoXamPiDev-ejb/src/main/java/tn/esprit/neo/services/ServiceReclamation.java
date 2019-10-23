package tn.esprit.neo.services;



import java.util.List;
import java.util.Properties;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import tn.esprit.entities.Contrat;
import tn.esprit.entities.Reclamation;

import tn.esprit.neo.dao.ServiceCRUD;
import tn.esprit.neo.interfaces.IReclamationServiceLocal;
import tn.esprit.neo.interfaces.IReclamationServiceRemote;

@Stateless
@LocalBean
public class ServiceReclamation extends ServiceCRUD<Reclamation, Integer> implements IReclamationServiceLocal,IReclamationServiceRemote{

	/**
	 * @param pc
	 * @param kc
	 */
	public ServiceReclamation() {
		super(Reclamation.class, Integer.class);

	}

	@Override
	public List<Reclamation> getAllReclamation() {
		List<Reclamation> reclamation = em.createQuery("Select r from Reclamation r",
				Reclamation.class).getResultList();
		return reclamation;
	}
	@Override
	public List<Reclamation> getAllReclamationNonTraiter() {
		List<Reclamation> reclamation = em.createQuery("Select r from Reclamation r where r.dateTraitement is null",
				Reclamation.class).getResultList();
		return reclamation;
	}
	@Override
	public Reclamation findById(int id) {
		return em.find(Reclamation.class, id);
	}
	@Override
	public void modifierReclamation(Reclamation reclamation) {
		System.out.println("In update : ");
		em.merge(reclamation);
		em.flush();
		System.out.println("Out of update : ");

	}
	@Override
	public void SendMail(String to, String sub, String msg) {
		final String user = "hayawin2@gmail.com";
		final  String password = "firas123";


		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.user", user);
		props.put("mail.smtp.password", password);
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, password);
			}
		});

		try {
			MimeMessage msgg = new MimeMessage(session);
			msgg.setFrom(new InternetAddress("hayawin2@gmail.com"));
			msgg.setRecipients(javax.mail.Message.RecipientType.TO, InternetAddress.parse(to));
			msgg.setSubject(sub);
			msgg.setText(msg);
			msgg.reply(true);

			Transport.send(msgg); 
		} catch (Exception e) {

			System.out.println(e);
		}

	}
}
