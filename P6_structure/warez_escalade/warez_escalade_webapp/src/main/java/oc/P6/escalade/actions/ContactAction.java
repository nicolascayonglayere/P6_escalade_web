package oc.P6.escalade.actions;

import java.util.Calendar;
import java.util.Properties;

import javax.inject.Inject;
import javax.inject.Named;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Scope;

import com.opensymphony.xwork2.ActionSupport;

import oc.P6.escalade.WebappHelper.GestionFichierProperties;
import oc.P6.escalade.business.contract.ManagerFactory;
import oc.P6.escalade.model.bean.exception.CoordonneeUtilisateurException;
import oc.P6.escalade.model.bean.exception.UtilisateurException;
import oc.P6.escalade.model.bean.utilisateur.CoordonneeUtilisateur;

/**
 * Classe action qui permet d'envoyer un message aux administrateurs du site
 * @author nicolas
 *
 */
@Named
@Scope("Protoype")
public class ContactAction extends ActionSupport {

	/**
	 * 
	 */
	static final Logger logger = LogManager.getLogger();
	private static final long serialVersionUID = 1L;
	@Inject 
	private ManagerFactory managerFactory;
	private CoordonneeUtilisateur coordonneeUtilisateur;
	private String message;
	private String to;
	
	private Properties properties = new Properties();
	private GestionFichierProperties gfp = new GestionFichierProperties(); 

	/**
	 * Méthode qui gère le message à envoyer et qui l'envoie
	 */
	public String execute() {
		logger.debug("CTRL contact "+coordonneeUtilisateur.getEmail());
	      String vReturn = SUCCESS;
		try {
			to = managerFactory.getCoordonneeUtilisateurManager().getCoordonnee(
					managerFactory.getUtilisateurManager().getListAdmin().get(0).getId()).getEmail();
		} catch (UtilisateurException e1) {
			addActionMessage(e1.getMessage());
			//e1.printStackTrace();
			vReturn = ActionSupport.INPUT;
		} catch (CoordonneeUtilisateurException e2) {
			addActionMessage(e2.getMessage());
			//e2.printStackTrace();
			vReturn = ActionSupport.INPUT;
		}

	    try {
	    	properties = gfp.lireProp();
	
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
	         //e.printStackTrace();
	      }
	      return vReturn;

	}

	//--Getter et Setter--//
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
