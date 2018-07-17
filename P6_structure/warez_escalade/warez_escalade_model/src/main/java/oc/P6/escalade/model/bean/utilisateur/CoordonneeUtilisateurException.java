package oc.P6.escalade.model.bean.utilisateur;

import oc.P6.escalade.model.contract.utilisateur.IntCoordonneeUtilisateurException;

public class CoordonneeUtilisateurException extends Exception implements IntCoordonneeUtilisateurException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public CoordonneeUtilisateurException(String pMessage) {
		super (pMessage);
	}

}
