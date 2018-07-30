package oc.P6.escalade.model.bean.exception;

/**
 * Classe CoordonneUtilisateurException qui transporte les message d'exceptions concernant les CoordonneeUtilisateur
 * @author nicolas
 *
 */
public class CoordonneeUtilisateurException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public CoordonneeUtilisateurException(String pMessage) {
		super (pMessage);
	}

}
