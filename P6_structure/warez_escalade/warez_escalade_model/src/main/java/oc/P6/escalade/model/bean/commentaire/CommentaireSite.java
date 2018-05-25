package oc.P6.escalade.model.bean.commentaire;

import javax.inject.Named;

import oc.P6.escalade.model.bean.topo.Site;

@Named("commentaireSite")
public class CommentaireSite extends Commentaire{

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
