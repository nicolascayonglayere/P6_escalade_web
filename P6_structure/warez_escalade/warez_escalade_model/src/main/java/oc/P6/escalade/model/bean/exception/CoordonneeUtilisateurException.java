package oc.P6.escalade.model.bean.exception;

import javax.inject.Named;

import oc.P6.escalade.model.contract.exception.IntCoordonneeUtilisateurException;

//@Named
public class CoordonneeUtilisateurException extends Exception implements IntCoordonneeUtilisateurException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public CoordonneeUtilisateurException(String pMessage) {
		super (pMessage);
	}

}
