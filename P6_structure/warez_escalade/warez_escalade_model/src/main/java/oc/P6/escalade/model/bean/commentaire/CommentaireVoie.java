package oc.P6.escalade.model.bean.commentaire;

import javax.inject.Named;

import oc.P6.escalade.model.bean.topo.Voie;

@Named("commentaireVoie")
public class CommentaireVoie extends Commentaire{

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
