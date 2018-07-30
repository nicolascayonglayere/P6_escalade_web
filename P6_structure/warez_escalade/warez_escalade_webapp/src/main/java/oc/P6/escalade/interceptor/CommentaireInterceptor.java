package oc.P6.escalade.interceptor;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.opensymphony.xwork2.interceptor.ValidationAware;

/**
 * Classe Interceptor qui annule tous {@link CommentaireTopo} vulgaire
 * @author nicolas
 *
 */
public class CommentaireInterceptor implements Interceptor {

	/**
	 * 
	 */
	static final Logger logger = LogManager.getLogger();
	private static final long serialVersionUID = 1L;
	private String[] motsClefs = {"grossierté"};
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		String message = invocation.getInvocationContext().getParameters().get("commentaireTopo.message").getValue();
		System.out.println("intercept "+message);
		logger.debug("intercept "+message);
		String vResult = "";
		//ici je souhaite filtrer les messages injurieux, qui contiennent des string non souhaitees
		if(message != null) {
			for (String s : motsClefs) {
				if(!(message.contains(s)))
					vResult = invocation.invoke(); 			
				else {
					addActionError(invocation, "Merci de rester courtois !");
					vResult =  Action.INPUT;
				}
			}
		}
		else
			vResult = invocation.invoke();

		return vResult;

	}
	
	private void addActionError(ActionInvocation invocation, String message) {
		Object action = invocation.getAction();
		if(action instanceof ValidationAware) {
			((ValidationAware) action).addActionError(message);
		}
	}
}
