package oc.P6.escalade.model.bean.topo;

import java.util.ArrayList;

import javax.inject.Named;

import oc.P6.escalade.model.contract.topo.IntSite;

/**
 * Objet métier représentant un site d'un topo
 * @author nicolas
 *
 */
@Named("site")
public class Site implements IntSite{

	private int id;
	private String nomSite;
	private Topo topo;
	private String description;
	private ArrayList<Secteur> listSecteur;
	private ArrayList<Voie> listVoie;
	
	/**
	 * Constructeurs
	 */
	public Site () {}
	
	public Site(String pNom) {
		this.nomSite = pNom;
	}

	/**
	 * Getter et Setter
	 * @return
	 */
	@Override
	public int getId() {
		return id;
	}

	@Override
	public void setId(int id) {
		this.id = id;
	}

	@Override
	public Topo getTopo() {
		return topo;
	}

	@Override
	public void setTopo(Topo topo) {
		this.topo = topo;
	}

	@Override
	public String getDescription() {
		return description;
	}
	
	@Override
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String getNomSite() {
		return this.nomSite;
	}

	@Override
	public void setNomSite(String pNom) {
		this.nomSite = pNom;		
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
	
	
}
