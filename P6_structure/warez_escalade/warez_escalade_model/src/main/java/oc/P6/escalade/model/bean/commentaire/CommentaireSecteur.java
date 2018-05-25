package oc.P6.escalade.model.bean.commentaire;

import javax.inject.Named;

import oc.P6.escalade.model.bean.topo.Secteur;

@Named("commentaireSecteur")
public class CommentaireSecteur extends Commentaire {

	private Secteur secteur;
	
	public CommentaireSecteur() {
		super();
	}

	public Secteur getSecteur() {
		return secteur;
	}

	public void setSecteur(Secteur secteur) {
		this.secteur = secteur;
	}
	
	
}
