package oc.P6.escalade.actions.topo;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;

import oc.P6.escalade.business.contract.ManagerFactory;
import oc.P6.escalade.model.bean.topo.Secteur;
import oc.P6.escalade.model.bean.topo.Site;
import oc.P6.escalade.model.bean.topo.Topo;

public class SupprimerSecteur extends ActionSupport implements ServletRequestAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private ManagerFactory managerFactory;
	private String nomSite, nomTopo, nomSecteur;
	private HttpServletRequest request;
	
	public String execute() {
		//nomSecteur = request.getParameter("nomSecteur");
		//nomTopo = request.getParameter("nomTopo");
		System.out.println(nomSecteur+" - "+nomSite+" - "+nomTopo);
		Topo topo = managerFactory.getTopoManager().getTopo(nomTopo);
		Site site = managerFactory.getSiteManager().getSite(nomSite, topo);
		Secteur secteur = managerFactory.getSecteurManager().getSecteur(nomSecteur, site);
		managerFactory.getSecteurManager().supprimerSecteur(secteur);
		addActionMessage("Vous avez supprimé le secteur "+secteur.getNomSecteur()+" du topo "+topo.getNomTopo());
		return ActionSupport.SUCCESS;
	}
	
	public ManagerFactory getManagerFactory() {
		return managerFactory;
	}
	public void setManagerFactory(ManagerFactory managerFactory) {
		this.managerFactory = managerFactory;
	}

	public String getNomSite() {
		return nomSite;
	}

	public void setNomSite(String nomSite) {
		this.nomSite = nomSite;
	}

	public String getNomTopo() {
		return nomTopo;
	}

	public void setNomTopo(String nomTopo) {
		this.nomTopo = nomTopo;
	}

	public String getNomSecteur() {
		return nomSecteur;
	}

	public void setNomSecteur(String nomSecteur) {
		this.nomSecteur = nomSecteur;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		
	}
	


}
