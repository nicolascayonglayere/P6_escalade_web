package oc.P6.escalade.actions;

import java.util.ArrayList;
import java.util.Arrays;

import javax.inject.Inject;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.opensymphony.xwork2.ActionSupport;

import oc.P6.escalade.WebappHelper.WebappHelper;
import oc.P6.escalade.business.contract.ManagerFactory;
import oc.P6.escalade.business.impl.ManagerFactoryImpl;
import oc.P6.escalade.model.bean.utilisateur.Utilisateur;

public class PresentationAction extends ActionSupport{

	/**
	 * 
	 */
	//@Inject
	//private WebappHelper webappHelper;
	private static final long serialVersionUID = 1L;
	
	@Inject
	private ManagerFactory managerFactory;
	private ArrayList<Utilisateur> listAdmin;
	private ArrayList<Utilisateur> listModo;
	
	public ManagerFactory getManagerFactory() {
		System.out.println("trace getmanagerFacto");
		return managerFactory;
	}
	public void setManagerFactory(ManagerFactory managerFactory) {
		System.out.println("trace setManagerFacto");
		this.managerFactory = managerFactory;
	}
	public ArrayList<Utilisateur> getListAdmin() {
		return listAdmin;
	}
	public void setListAdmin(ArrayList<Utilisateur> listAdmin) {
		this.listAdmin = listAdmin;
	}
	public ArrayList<Utilisateur> getListModo() {
		return listModo;
	}
	public void setListModo(ArrayList<Utilisateur> listModo) {
		this.listModo = listModo;
	}

	public String execute() {
		
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("bootstrapContext.xml");
		System.out.println("Bean names web-app: " + Arrays.toString(context.getBeanNamesForType(ManagerFactoryImpl.class)));
		// get the bean from spring container
		//ManagerFactoryImpl managerFactory = (ManagerFactoryImpl) context.getBean("managerFactory", ManagerFactory.class);
		//listModo = WebappHelper.getManagerFactory().getUtilisateurManager().getListModo();
		listAdmin = managerFactory.getUtilisateurManager().getListAdmin();
		
		//context.close();
		return SUCCESS;
	}

}
