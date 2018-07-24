package oc.P6.escalade.model.bean.exception;

/**
 * Classe SecteurException qui transporte les message d'exceptions concernant les {@link Secteur}
 * @author nicolas
 *
 */
public class SecteurException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SecteurException(String pMessage) {
		super(pMessage);
	}
}
