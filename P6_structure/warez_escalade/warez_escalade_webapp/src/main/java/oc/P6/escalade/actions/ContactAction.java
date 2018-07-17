package oc.P6.escalade.actions;

import java.util.Calendar;
import java.util.Properties;

import javax.inject.Inject;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.opensymphony.xwork2.ActionSupport;

import oc.P6.escalade.WebappHelper.GestionFichierProperties;
import oc.P6.escalade.business.contract.ManagerFactory;
import oc.P6.escalade.model.bean.utilisateur.CoordonneeUtilisateur;
import oc.P6.escalade.model.bean.utilisateur.UtilisateurException;

public class ContactAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject 
	private ManagerFactory managerFactory;
	private CoordonneeUtilisateur coordonneeUtilisateur;
	private String message;
	private String to;

	
	private Properties properties = new Properties();
	private GestionFichierProperties gfp = new GestionFichierProperties(); 
	
	public String execute() {
		System.out.println("CTRL contact "+coordonneeUtilisateur.getEmail());
	      String vReturn = SUCCESS;
		try {
			to = managerFactory.getCoordonneeUtilisateurManager().getCoordonnee(
					managerFactory.getUtilisateurManager().getListAdmin().get(0).getId()).getEmail();
		} catch (UtilisateurException e1) {
			addActionMessage(e1.getMessage());
			e1.printStackTrace();
			vReturn = ActionSupport.INPUT;
		}

	    try {
	    	properties = gfp.lireProp();
		    System.out.println("SimpleEmail Start");
	
	       Session session = Session.getDefaultInstance(properties, 
	      	new javax.mail.Authenticator() {
	      	 protected PasswordAuthentication 
	      	 	getPasswordAuthentication() {
	      		 return new PasswordAuthentication(properties.getProperty("mail.adresse"), properties.getProperty("mail.pass"));
	      	 }
	       	}
	      );
		   session.setDebug(true);
	       Message mail = new MimeMessage(session);
	       mail.setFrom(new InternetAddress(coordonneeUtilisateur.getEmail()));
	       mail.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
	       mail.setSubject("Contact Warez Escalade");
	       mail.setSentDate(Calendar.getInstance().getTime());
	       mail.setText("Message de "+coordonneeUtilisateur.getEmail()+"\n"+mail.getSubject()+"\n"+message+"\n"+mail.getSentDate().toString());
	       Transport.send(mail);
	 		addActionMessage("Votre message a bien été envoyé");
	      } catch(Exception e) {
	         vReturn = INPUT;
	         addActionMessage("Echec de l'envoi du message");
	         e.printStackTrace();
	      }
	      return vReturn;

	}

	public ManagerFactory getManagerFactory() {
		return managerFactory;
	}

	public void setManagerFactory(ManagerFactory managerFactory) {
		this.managerFactory = managerFactory;
	}

	public CoordonneeUtilisateur getCoordonneeUtilisateur() {
		return coordonneeUtilisateur;
	}

	public void setCoordonneeUtilisateur(CoordonneeUtilisateur coordonneeUtilisateur) {
		this.coordonneeUtilisateur = coordonneeUtilisateur;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
