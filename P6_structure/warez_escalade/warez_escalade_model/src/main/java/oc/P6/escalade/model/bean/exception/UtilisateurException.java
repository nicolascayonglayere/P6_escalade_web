package oc.P6.escalade.model.bean.exception;

import javax.inject.Named;

import oc.P6.escalade.model.contract.exception.IntUtilisateurException;

//@Named
public class UtilisateurException  extends Exception implements IntUtilisateurException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public UtilisateurException(String pMessage) {
		super(pMessage);
	}

}
