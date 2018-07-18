package oc.P6.escalade.model.bean.exception;

import javax.inject.Named;

import oc.P6.escalade.model.contract.exception.IntVoieException;

//@Named
public class VoieException extends Exception implements IntVoieException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public VoieException(String pMessage) {
		super(pMessage);
	}
}
