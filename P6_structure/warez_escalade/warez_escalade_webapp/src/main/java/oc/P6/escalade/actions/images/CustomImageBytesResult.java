package oc.P6.escalade.actions.images;

import javax.inject.Named;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.Result;

/**
 * Classe resultat qui définit le résultat de l'action {@link ImageAction}
 * @author nicolas
 *
 */
@Named
public class CustomImageBytesResult implements Result {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void execute(ActionInvocation invocation) throws Exception {
		ImageAction action = (ImageAction) invocation.getAction();
		HttpServletResponse response = ServletActionContext.getResponse();

		response.setContentType(action.getCustomContentType());
		response.getOutputStream().write(action.getCustomImageInBytes());
		response.getOutputStream().flush();
		//System.out.println("resultat image "+response.toString());
	}

}
