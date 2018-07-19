package oc.P6.escalade.actions.topo;

import java.util.ArrayList;
import java.util.Map;

import javax.inject.Inject;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import oc.P6.escalade.business.contract.ManagerFactory;
import oc.P6.escalade.model.bean.exception.SecteurException;
import oc.P6.escalade.model.bean.exception.SiteException;
import oc.P6.escalade.model.bean.exception.TopoException;
import oc.P6.escalade.model.bean.topo.Secteur;
import oc.P6.escalade.model.bean.topo.Site;
import oc.P6.escalade.model.bean.topo.Topo;


public class CreerSecteur extends ActionSupport implements SessionAware {

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	@Inject
	private ManagerFactory managerFactory;	
	private Secteur secteur;
	private Topo topo;
	private Site site;
	private String nomTopo, nomSite;
	private int selectedSite;
	private int id;
	private ArrayList<Site> listSite;
	private ArrayList<Secteur> listSecteur;
	private Map<String, Object> session;
	
	public Secteur getSecteur() {
		return secteur;
	}
	public void setSecteur(Secteur secteur) {
		this.secteur = secteur;
	}

	public String getNomTopo() {
		return nomTopo;
	}
	public void setNomTopo(String nomTopo) {
		this.nomTopo = nomTopo;
	}
	public String getNomSite() {
		return nomSite;
	}
	public void setNomSite(String nomSite) {
		this.nomSite = nomSite;
	}
	
	public String execute() {
		try {
			if (((Topo)(session.get("topo"))).getNomTopo().length() > 0) {
				topo = (Topo)session.get("topo");
				//topo = managerFactory.getTopoManager().getTopo(nomTopo);
			}
			if((Site)session.get("site") != null) {
				site= (Site)session.get("site");
				//site = managerFactory.getSiteManager().getSite(nomSite, topo);
			}
			System.out.println(site.getId());
			secteur.setSite(site);
			secteur = managerFactory.getSecteurManager().creerSecteur(secteur);
			listSite = managerFactory.getSiteManager().getSite(topo);
			for(Site s : listSite)
				setListSecteur(managerFactory.getSecteurManager().getListSecteur(s));
			//secteur = managerFactory.getSecteurManager().getSecteur(secteur.getNomSecteur(), site);
			addActionMessage("Le secteur "+secteur.getNomSecteur()+" a bien été crée.");
			session.put("secteur", secteur);
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
	
	public String input() {
		try {
			System.out.println("selection : "+selectedSite);
			topo = managerFactory.getTopoManager().getTopo(topo.getNomTopo());
			this.session.put("topo", topo);
			site = managerFactory.getSiteManager().getSite(selectedSite);
			this.session.put("site", site);
			System.out.println("input "+site.getId());
			return ActionSupport.SUCCESS;			
		}catch (TopoException e2) {
			addActionMessage(e2.getMessage());
			e2.printStackTrace();
			return ActionSupport.INPUT;
		} catch (SiteException e3) {
			addActionMessage(e3.getMessage());
			e3.printStackTrace();
			return ActionSupport.INPUT;
		} 

	}
	public ManagerFactory getManagerFactory() {
		return managerFactory;
	}
	public void setManagerFactory(ManagerFactory managerFactory) {
		this.managerFactory = managerFactory;
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
	public Site getSite() {
		return site;
	}
	public void setSite(Site site) {
		this.site = site;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public ArrayList<Site> getListSite() {
		return listSite;
	}
	public void setListSite(ArrayList<Site> listSite) {
		this.listSite = listSite;
	}
	public int getSelectedSite() {
		return selectedSite;
	}
	public void setSelectedSite(int selectedSite) {
		this.selectedSite = selectedSite;
	}
	public ArrayList<Secteur> getListSecteur() {
		return listSecteur;
	}
	public void setListSecteur(ArrayList<Secteur> listSecteur) {
		this.listSecteur = listSecteur;
	}
	
}
