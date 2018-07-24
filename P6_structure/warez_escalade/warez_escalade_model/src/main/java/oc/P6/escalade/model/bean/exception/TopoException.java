package oc.P6.escalade.model.bean.exception;

/**
 * Classe TopoException qui transporte les message d'exceptions concernant les {@link Topo}
 * @author nicolas
 *
 */
public class TopoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public TopoException(String pMessage) {
		super(pMessage);
	}

}
