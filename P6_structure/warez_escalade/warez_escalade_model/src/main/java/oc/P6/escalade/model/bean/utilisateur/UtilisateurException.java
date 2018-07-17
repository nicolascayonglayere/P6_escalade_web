package oc.P6.escalade.model.bean.utilisateur;

import oc.P6.escalade.model.contract.utilisateur.IntUtilisateurException;

public class UtilisateurException  extends Exception implements IntUtilisateurException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public UtilisateurException(String pMessage) {
		super(pMessage);
	}

}
