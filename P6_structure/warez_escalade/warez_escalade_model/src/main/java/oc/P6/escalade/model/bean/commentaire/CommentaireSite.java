package oc.P6.escalade.model.bean.commentaire;

import javax.inject.Named;

import oc.P6.escalade.model.bean.topo.Site;
import oc.P6.escalade.model.contract.commentaire.IntCommentaireSite;

@Named("commentaireSite")
public class CommentaireSite extends Commentaire implements IntCommentaireSite{

	private Site site;
	
	public CommentaireSite() {
		super();
	}

	public Site getSite() {
		return site;
	}

	public void setSite(Site site) {
		this.site = site;
	}
	
	
}
