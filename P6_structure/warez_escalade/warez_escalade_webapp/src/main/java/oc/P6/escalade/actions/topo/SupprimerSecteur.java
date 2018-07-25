package oc.P6.escalade.actions.topo;

import javax.inject.Inject;
import javax.inject.Named;

import com.opensymphony.xwork2.ActionSupport;

import oc.P6.escalade.business.contract.ManagerFactory;
import oc.P6.escalade.model.bean.exception.SecteurException;
import oc.P6.escalade.model.bean.exception.SiteException;
import oc.P6.escalade.model.bean.exception.TopoException;
import oc.P6.escalade.model.bean.topo.Secteur;
import oc.P6.escalade.model.bean.topo.Site;
import oc.P6.escalade.model.bean.topo.Topo;
@Named
public class SupprimerSecteur extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private ManagerFactory managerFactory;
	private String nomSite, nomTopo, nomSecteur;

	
	public String execute() {
		try {
			System.out.println(nomSecteur+" - "+nomSite+" - "+nomTopo);
			Topo topo = managerFactory.getTopoManager().getTopo(nomTopo);
			Site site = managerFactory.getSiteManager().getSite(nomSite, topo);
			Secteur secteur = managerFactory.getSecteurManager().getSecteur(nomSecteur, site);
			managerFactory.getSecteurManager().supprimerSecteur(secteur);
			addActionMessage("Vous avez supprimé le secteur "+secteur.getNomSecteur()+" du topo "+topo.getNomTopo());
			return ActionSupport.SUCCESS;
		}catch (TopoException e2) {
			addActionMessage(e2.getMessage());
			e2.printStackTrace();
			return ActionSupport.INPUT;
		} catch (SiteException e3) {
			addActionMessage(e3.getMessage());
			e3.printStackTrace();
			return ActionSupport.INPUT;
		} catch (SecteurException e4) {
			addActionMessage(e4.getMessage());
			e4.printStackTrace();
			return ActionSupport.INPUT;
		}
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
	

}
