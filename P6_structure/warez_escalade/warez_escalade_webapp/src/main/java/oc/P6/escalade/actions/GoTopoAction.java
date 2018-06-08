package oc.P6.escalade.actions;

import java.util.ArrayList;

import com.opensymphony.xwork2.ActionSupport;

import oc.P6.escalade.WebappHelper.WebappHelper;
import oc.P6.escalade.model.bean.topo.Secteur;
import oc.P6.escalade.model.bean.topo.Site;
import oc.P6.escalade.model.bean.topo.Topo;
import oc.P6.escalade.model.bean.topo.Voie;

public class GoTopoAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Topo topo;
	private String nom;
	private Secteur secteur;
	private Site site;
	private Voie voie;
	
	private ArrayList<Secteur> listSecteur;
	private ArrayList<Voie> listVoie;
	
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
	public Voie getVoie() {
		return voie;
	}
	public void setVoie(Voie voie) {
		this.voie = voie;
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

	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String execute() throws Exception {
        //call Service class to store personBean's state in database
		System.out.println(nom);
		topo = (Topo) WebappHelper.getManagerFactory().getTopoManager().getTopo(nom);		
        System.out.println(topo.getNom());
        if (topo != null) {
        	site = (Site) WebappHelper.getManagerFactory().getSiteManager().getSite(topo);
        	listSecteur = (ArrayList<Secteur>) WebappHelper.getManagerFactory().getSecteurManager().getListSecteur(site);
        	for (Secteur s : listSecteur) {
        		listVoie = (ArrayList<Voie>) WebappHelper.getManagerFactory().getVoieManager().getListVoie(s);
        	}

        	return SUCCESS;
        }
        else {
        	addActionMessage("Le topo n'existe pas !");
        	return INPUT;
        }
        	
        	
    }
	

}
