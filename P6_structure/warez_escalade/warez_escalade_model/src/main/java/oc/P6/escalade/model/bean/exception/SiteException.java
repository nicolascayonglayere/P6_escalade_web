package oc.P6.escalade.model.bean.exception;

import javax.inject.Named;

import oc.P6.escalade.model.contract.exception.IntSiteException;

//@Named
public class SiteException extends Exception implements IntSiteException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SiteException(String pMessage) {
		super(pMessage);
	}
}
