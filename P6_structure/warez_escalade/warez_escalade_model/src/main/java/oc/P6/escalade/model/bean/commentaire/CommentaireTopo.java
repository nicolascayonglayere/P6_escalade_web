package oc.P6.escalade.model.bean.commentaire;

import java.util.Date;

import javax.inject.Named;

import oc.P6.escalade.model.bean.topo.Topo;
import oc.P6.escalade.model.bean.utilisateur.Utilisateur;
import oc.P6.escalade.model.contract.commentaire.IntCommentaireTopo;

@Named("commentaireTopo")
public class CommentaireTopo extends Commentaire implements IntCommentaireTopo{

	private Topo topo;
	private int id;
	private Utilisateur auteur;
	private Date date;
	private String message;
	
	public CommentaireTopo() {
		super();
	}

	public Topo getTopo() {
		return topo;
	}

	public void setTopo(Topo topo) {
		this.topo = topo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Utilisateur getAuteur() {
		return auteur;
	}

	public void setAuteur(Utilisateur auteur) {
		this.auteur = auteur;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
}
