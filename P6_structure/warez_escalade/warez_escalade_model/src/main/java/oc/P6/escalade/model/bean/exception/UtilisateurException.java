package oc.P6.escalade.model.bean.exception;

/**
 * Classe UtilisateurException qui transporte les message d'exceptions concernant les  Utilisateur
 * @author nicolas
 *
 */
public class UtilisateurException  extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public UtilisateurException(String pMessage) {
		super(pMessage);
	}

}
