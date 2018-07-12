package oc.P6.escalade.actions.topo;

import java.util.ArrayList;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import oc.P6.escalade.business.contract.ManagerFactory;
import oc.P6.escalade.model.bean.topo.Secteur;
import oc.P6.escalade.model.bean.topo.Site;
import oc.P6.escalade.model.bean.topo.Topo;
import oc.P6.escalade.model.bean.topo.Voie;

public class CreerVoie extends ActionSupport implements SessionAware, ServletRequestAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private ManagerFactory managerFactory;
	private Voie voie;
	private String nomTopo, nomSite, nomSecteur;
	private Topo topo;
	private Secteur secteur;
	private Site site;
	private int id;
	private int selectedSecteur;
	private ArrayList<Secteur> listSecteur;
	private Map<String, Object> session;
	private HttpServletRequest request;
	private ArrayList<Site> listSite;
	
	public Voie getVoie() {
		return voie;
	}
	public void setVoie(Voie voie) {
		this.voie = voie;
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
	public String getNomSecteur() {
		return nomSecteur;
	}
	public void setNomSecteur(String nomSecteur) {
		this.nomSecteur = nomSecteur;
	}
		
	public String execute() {
		System.out.println("trace creation voie");
		if ((Topo)session.get("topo") != null) {
			nomTopo = ((Topo)session.get("topo")).getNomTopo();
			topo = managerFactory.getTopoManager().getTopo(nomTopo);
		}

	//if ((Site)session.get("site") != null) {
	//	nomSite=((Site)session.get("site")).getNomSite();
	//	site = managerFactory.getSiteManager().getSite(nomSite, topo);
	//}
		if((Secteur)session.get("secteur") != null) {
			nomSecteur = ((Secteur)session.get("secteur")).getNomSecteur();
			Site vSite = ((Secteur)session.get("secteur")).getSite();
			secteur = managerFactory.getSecteurManager().getSecteur(nomSecteur, vSite);
		}
		System.out.println(secteur.getNomSecteur()+" - "+topo.getNomTopo());
		voie.setSecteur(secteur);
		System.out.println(voie.getNomVoie()+" - "+voie.getCotation());
		managerFactory.getVoieManager().creerVoie(voie);
		listSite = managerFactory.getSiteManager().getSite(topo);
		for(Site s : listSite)
			listSecteur = managerFactory.getSecteurManager().getListSecteur(s);
		addActionMessage("La voie "+voie.getNomVoie()+" a bien été créee.");
		return ActionSupport.SUCCESS;
	}
	
	public String input() {
		System.out.println("selection : "+selectedSecteur);
		if (nomTopo != null)
			topo = managerFactory.getTopoManager().getTopo(nomTopo);
		else
			topo = managerFactory.getTopoManager().getTopo(topo.getNomTopo());
		this.session.put("topo", topo);
		secteur = managerFactory.getSecteurManager().getSecteur(selectedSecteur);
		this.session.put("secteur", secteur);
		listSite = managerFactory.getSiteManager().getSite(topo);
		for(Site s : listSite)
			listSecteur = managerFactory.getSecteurManager().getListSecteur(s);
		return ActionSupport.SUCCESS;
	}
	
	public String select() {
		secteur = managerFactory.getSecteurManager().getSecteur(id);
		return ActionSupport.SUCCESS;
	}
	
	public ManagerFactory getManagerFactory() {
		return managerFactory;
	}
	public void setManagerFactory(ManagerFactory managerFactory) {
		this.managerFactory = managerFactory;
	}
	public Topo getTopo() {
		return topo;
	}
	public void setTopo(Topo topo) {
		this.topo = topo;
	}
	public Secteur getSecteur() {
		return secteur;
	}
	public void setSecteur(Secteur secteur) {
		this.secteur = secteur;
	}
	public Site getSite() {
		return site;
	}
	public void setSite(Site site) {
		this.site = site;
	}
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
		
	}
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public ArrayList<Secteur> getListSecteur() {
		return listSecteur;
	}
	public void setListSecteur(ArrayList<Secteur> listSecteur) {
		this.listSecteur = listSecteur;
	}
	public int getSelectedSecteur() {
		return selectedSecteur;
	}
	public void setSelectedSecteur(int selectedSecteur) {
		this.selectedSecteur = selectedSecteur;
	}
	public ArrayList<Site> getListSite() {
		return listSite;
	}
	public void setListSite(ArrayList<Site> listSite) {
		this.listSite = listSite;
	}
	

}
