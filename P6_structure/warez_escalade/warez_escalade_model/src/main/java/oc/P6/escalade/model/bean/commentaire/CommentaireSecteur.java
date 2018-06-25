package oc.P6.escalade.model.bean.commentaire;

import javax.inject.Named;

import oc.P6.escalade.model.bean.topo.Secteur;
import oc.P6.escalade.model.contract.commentaire.IntCommentaireSecteur;

@Named("commentaireSecteur")
public class CommentaireSecteur extends Commentaire implements IntCommentaireSecteur {

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
