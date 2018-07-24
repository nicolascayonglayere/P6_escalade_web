package oc.P6.escalade.model.bean.exception;
/**
 * Classe SiteException qui transporte les message d'exceptions concernant les {@link Site}
 * @author nicolas
 *
 */
public class SiteException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SiteException(String pMessage) {
		super(pMessage);
	}
}
