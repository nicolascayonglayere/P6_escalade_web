package oc.P6.escalade.actions.topo;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;

import oc.P6.escalade.business.contract.ManagerFactory;
import oc.P6.escalade.model.bean.topo.Secteur;
import oc.P6.escalade.model.bean.topo.Site;
import oc.P6.escalade.model.bean.topo.Topo;
import oc.P6.escalade.model.bean.topo.Voie;

public class RechercheTopo extends ActionSupport implements ServletRequestAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	ManagerFactory managerFactory;
	private Topo topo;
	private ArrayList<Topo> listTopo;
	private ArrayList<Site> listSite = new ArrayList<Site>();
	private ArrayList<Secteur> listSecteur = new ArrayList<Secteur>();
	private ArrayList<Voie>listVoie = new ArrayList<Voie>();
	private String nomTopo, nomSite, nomSecteur;
	private HttpServletRequest request;
	
	
	public String execute() {
		System.out.println("recherche "+topo.getNomTopo());
		listTopo = managerFactory.getTopoManager().rechercheTopo(topo.getNomTopo());
		for (Topo t : listTopo)
			listSite.addAll(managerFactory.getSiteManager().getSite(t));
		for(Site si : listSite)
			listSecteur.addAll(managerFactory.getSecteurManager().getListSecteur(si));
		for(Secteur se : listSecteur)
			listVoie.addAll(managerFactory.getVoieManager().getListVoie(se));
		System.out.println(listTopo.size());
		return ActionSupport.SUCCESS;
	}
	
	public String input() {
		System.out.println(nomTopo);
		topo = managerFactory.getTopoManager().getTopo(nomTopo);
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

	public ArrayList<Topo> getListTopo() {
		return listTopo;
	}

	public void setListTopo(ArrayList<Topo> listTopo) {
		this.listTopo = listTopo;
	}

	public ArrayList<Site> getListSite() {
		return listSite;
	}

	public void setListSite(ArrayList<Site> listSite) {
		this.listSite = listSite;
	}

	public ArrayList<Secteur> getListSecteur() {
		return listSecteur;
	}

	public void setListSecteur(ArrayList<Secteur> listSecteur) {
		this.listSecteur = listSecteur;
	}

	public ArrayList<Voie> getListVoie() {
		return listVoie;
	}

	public void setListVoie(ArrayList<Voie> listVoie) {
		this.listVoie = listVoie;
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

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	

}
