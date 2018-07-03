package oc.P6.escalade.actions.topo;

import javax.inject.Inject;

import com.opensymphony.xwork2.ActionSupport;

import oc.P6.escalade.business.contract.ManagerFactory;
import oc.P6.escalade.model.bean.topo.Secteur;
import oc.P6.escalade.model.bean.topo.Site;
import oc.P6.escalade.model.bean.topo.Topo;
import oc.P6.escalade.model.bean.topo.Voie;

public class ModifierVoie extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private ManagerFactory managerFactory;
	private String nom, nomTopo, nomSite, nomSecteur;
	private Voie voie;
	
	public String execute() {
		System.out.println(nom+" - "+nomTopo);
		
		Topo topo = managerFactory.getTopoManager().getTopo(nomTopo);
		Site site = managerFactory.getSiteManager().getSite(nomSite, topo);
		Secteur secteur = managerFactory.getSecteurManager().getSecteur(nomSecteur, site);
		voie.setSecteur(secteur);
		managerFactory.getVoieManager().majVoie(voie);
		
		addActionMessage("La voie "+voie.getNomVoie()+"a bien été modifiée.");
		return ActionSupport.SUCCESS;	
	}
	//--methode input
	
	public ManagerFactory getManagerFactory() {
		return managerFactory;
	}
	public void setManagerFactory(ManagerFactory managerFactory) {
		this.managerFactory = managerFactory;
	}
	public String getNomTopo() {
		return nomTopo;
	}
	public void setNomTopo(String nomTopo) {
		this.nomTopo = nomTopo;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getNomSecteur() {
		return nomSecteur;
	}
	public void setNomSecteur(String nomSecteur) {
		this.nomSecteur = nomSecteur;
	}
	public Voie getVoie() {
		return voie;
	}
	public void setVoie(Voie voie) {
		this.voie = voie;
	}
	


}
