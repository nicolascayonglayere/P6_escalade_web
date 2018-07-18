package oc.P6.escalade.model.bean.exception;

import javax.inject.Named;

import oc.P6.escalade.model.contract.exception.IntSecteurException;

//@Named
public class SecteurException extends Exception implements IntSecteurException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SecteurException(String pMessage) {
		super(pMessage);
	}
}
