package oc.P6.escalade.actions.topo;

import java.util.Map;

import javax.inject.Inject;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import oc.P6.escalade.business.contract.ManagerFactory;
import oc.P6.escalade.model.bean.topo.Topo;

public class FinaliserTopo extends ActionSupport implements SessionAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private ManagerFactory managerFactory;
	private Map<String, Object> session;
	private Topo topo;
	private String nomTopo; 
	
	public ManagerFactory getManagerFactory() {
		return managerFactory;
	}
	public void setManagerFactory(ManagerFactory managerFactory) {
		this.managerFactory = managerFactory;
	}

	public String execute() {
		//--recup le topo en construction et maj de son statut
		if ((Topo)session.get("topo") != null) {
			nomTopo = ((Topo)session.get("topo")).getNomTopo();
			topo = managerFactory.getTopoManager().getTopo(nomTopo);
		}
		else
			topo = managerFactory.getTopoManager().getTopo(topo.getNomTopo());
		managerFactory.getTopoManager().modifTopo(topo);
		this.session.remove("topo");
		this.session.remove("secteur");
		this.session.remove("site");
		nomTopo = topo.getNomTopo();
		addActionMessage("La construction du topo"+topo.getNomTopo()+" est terminée.");
		return ActionSupport.SUCCESS;
		
	}
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
		
	}
	public Topo getTopo() {
		return topo;
	}
	public void setTopo(Topo topo) {
		this.topo = topo;
	}
	public String getNomTopo() {
		return nomTopo;
	}
	public void setNomTopo(String nomTopo) {
		this.nomTopo = nomTopo;
	}

	
}
