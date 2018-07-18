package oc.P6.escalade.model.bean.exception;

import javax.inject.Named;

import oc.P6.escalade.model.contract.exception.IntCommentaireTopoException;

//@Named
public class CommentaireTopoException extends Exception implements IntCommentaireTopoException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CommentaireTopoException(String pMessage) {
		super(pMessage);
	}
}
