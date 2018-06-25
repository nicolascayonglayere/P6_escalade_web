package oc.P6.escalade.model.bean.commentaire;

import javax.inject.Named;

import oc.P6.escalade.model.bean.topo.Voie;
import oc.P6.escalade.model.contract.commentaire.IntCommentaireVoie;

@Named("commentaireVoie")
public class CommentaireVoie extends Commentaire implements IntCommentaireVoie{

	private Voie voie;
	
	public CommentaireVoie() {
		super();
	}

	public Voie getVoie() {
		return voie;
	}

	public void setVoie(Voie voie) {
		this.voie = voie;
	}
	
	
}
