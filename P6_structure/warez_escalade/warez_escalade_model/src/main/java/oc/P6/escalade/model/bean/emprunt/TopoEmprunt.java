package oc.P6.escalade.model.bean.emprunt;

import java.util.Date;

import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import oc.P6.escalade.model.bean.topo.Topo;
import oc.P6.escalade.model.bean.utilisateur.Utilisateur;
import oc.P6.escalade.model.contract.emprunt.IntTopoEmprunt;
/**
 * Objet métier TopoEmprunt
 * @author nicolas
 *
 */
@Named("topoEmprunt")
@Scope("prototype")
public class TopoEmprunt implements IntTopoEmprunt{

	private int id;
	private Topo topo;
	private Utilisateur emprunteur;
	private Date dateEmprunt;
	private String nom;
	private Date dateRetour;
	
	/**
	 * Constructeurs
	 */
	public TopoEmprunt() {}

	public int getId() {
		return id;
	}

	/**
	 * Getter et Setter
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	public Topo getTopo() {
		return topo;
	}

	public void setTopo(Topo topo) {
		this.topo = topo;
	}

	public Utilisateur getEmprunteur() {
		return emprunteur;
	}

	public void setEmprunteur(Utilisateur emprunteur) {
		this.emprunteur = emprunteur;
	}

	public Date getDateEmprunt() {
		return dateEmprunt;
	}

	public void setDateEmprunt(Date dateEmprunt) {
		this.dateEmprunt = dateEmprunt;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Date getDateRetour() {
		return dateRetour;
	}

	public void setDateRetour(Date dateRetour) {
		this.dateRetour = dateRetour;
	}
	
	public void destroy() {
		this.destroy();
	}
	
}
