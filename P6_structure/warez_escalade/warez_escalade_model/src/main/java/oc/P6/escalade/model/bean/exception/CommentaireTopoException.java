package oc.P6.escalade.model.bean.exception;

/**
 * Classe CommentaireTopoException qui transporte les message d'exceptions concernant les CommentaireTopo
 * @author nicolas
 *
 */
public class CommentaireTopoException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CommentaireTopoException(String pMessage) {
		super(pMessage);
	}
}
