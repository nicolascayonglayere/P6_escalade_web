package oc.P6.escalade.model.bean.exception;

import javax.inject.Named;

import oc.P6.escalade.model.contract.exception.IntTopoException;

//@Named
public class TopoException extends Exception implements IntTopoException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public TopoException(String pMessage) {
		super(pMessage);
	}

}
