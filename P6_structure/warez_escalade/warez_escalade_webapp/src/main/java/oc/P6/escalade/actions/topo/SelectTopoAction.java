package oc.P6.escalade.actions.topo;

import java.util.ArrayList;

import javax.inject.Inject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.opensymphony.xwork2.ActionSupport;

import oc.P6.escalade.business.contract.ManagerFactory;
import oc.P6.escalade.model.bean.topo.Topo;

public class SelectTopoAction extends ActionSupport {

	/**
	 * 
	 */
	static final Logger logger = LogManager.getLogger();
	private static final long serialVersionUID = 1L;
	@Inject
	private ManagerFactory managerFactory;
	private ArrayList<Topo> listTopo;
	

	public String doListTopo() throws Exception {
        
		listTopo = (ArrayList<Topo>) managerFactory.getTopoManager().getListTopo();
        logger.debug(listTopo.get(0).getNomTopo());
        return SUCCESS;
    }

	public ArrayList<Topo> getListTopo() {
		return listTopo;
	}


	public void setListTopo(ArrayList<Topo> listTopo) {
		this.listTopo = listTopo;
	}

	public ManagerFactory getManagerFactory() {
		return managerFactory;
	}

	public void setManagerFactory(ManagerFactory managerFactory) {
		this.managerFactory = managerFactory;
	}
}
