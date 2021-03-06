package oc.P6.escalade.actions.topo;


import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;

import com.opensymphony.xwork2.ActionSupport;

import oc.P6.escalade.business.contract.ManagerFactory;
import oc.P6.escalade.model.bean.exception.SecteurException;
import oc.P6.escalade.model.bean.exception.SiteException;
import oc.P6.escalade.model.bean.exception.TopoException;
import oc.P6.escalade.model.bean.topo.Secteur;
import oc.P6.escalade.model.bean.topo.Site;
import oc.P6.escalade.model.bean.topo.Topo;

/**
 * Classe action qui permet la création d'un {@link Secteur}
 * @author nicolas
 *
 */
@Named
@Scope("Protoype")
public class CreerSecteur extends ActionSupport implements SessionAware {

	/**
	 * 
	 */
	static final Logger logger = LogManager.getLogger();
	private static final long serialVersionUID = 1L;
	@Inject
	private ManagerFactory managerFactory;	
	private Secteur secteur;
	private Topo topo;
	private Site site;
	private String nomTopo, nomSite;
	private int selectedSite;
	private int id;
	private HashMap<Integer, String> listSite = new HashMap<Integer, String>();
	private HashMap<Integer, String> listSecteur = new HashMap<Integer, String>();
	private Map<String, Object> session;
	

	/**
	 * Méthode qui crée le {@link Secteur}
	 */
	public String execute() {
		try {
			if (((Topo)(session.get("topo"))).getNomTopo().length() > 0) {
				topo = (Topo)session.get("topo");
			}
			if((Site)session.get("site") != null) {
				site= (Site)session.get("site");
			}
			
			logger.debug(site.getId());
			secteur.setSite(site);
			secteur = managerFactory.getSecteurManager().creerSecteur(secteur);
			
			for(Site s : managerFactory.getSiteManager().getSite(topo)) {
				listSite.put(s.getId(), s.getNomSite());
				for (Secteur se : managerFactory.getSecteurManager().getListSecteur(s))
					listSecteur.put(se.getId(), se.getNomSecteur());
			}			
				
			
			addActionMessage("Le secteur "+secteur.getNomSecteur()+" a bien été crée.");
			session.put("secteur", secteur);
			return ActionSupport.SUCCESS;			
		}catch (SiteException e3) {
			addActionMessage(e3.getMessage());
			//e3.printStackTrace();
			return ActionSupport.INPUT;
		} catch (SecteurException e4) {
			addActionMessage(e4.getMessage());
			//e4.printStackTrace();
			return ActionSupport.INPUT;
		}
	}
	
	/**
	 * Méthode qui récupère les données pour la création du {@link Secteur}
	 */
	public String input() {
		try {
			if ((Topo)session.get("topo") == null) {
				topo = managerFactory.getTopoManager().getTopo(topo.getNomTopo());
				this.session.put("topo", topo);
			}
			else {
				logger.debug("selection : "+selectedSite);
				topo = (Topo)session.get("topo");
				site = managerFactory.getSiteManager().getSite(selectedSite);
				this.session.put("site", site);
			}
			logger.debug("input "+site.getId());
			return ActionSupport.SUCCESS;			
		}catch (TopoException e2) {
			addActionMessage(e2.getMessage());
			//e2.printStackTrace();
			return ActionSupport.INPUT;
		} catch (SiteException e3) {
			addActionMessage(e3.getMessage());
			//e3.printStackTrace();
			return ActionSupport.INPUT;
		} 
	}

	//--Getter et Setter--//
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
	public int getSelectedSite() {
		return selectedSite;
	}
	public void setSelectedSite(int selectedSite) {
		this.selectedSite = selectedSite;
	}
	public HashMap<Integer, String> getListSecteur() {
		return listSecteur;
	}
	public void setListSecteur(HashMap<Integer, String> listSecteur) {
		this.listSecteur = listSecteur;
	}
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
	
}
