package oc.P6.escalade.model.bean.commentaire;

import javax.inject.Named;

import oc.P6.escalade.model.bean.topo.Topo;

@Named("commentaireTopo")
public class CommentaireTopo extends Commentaire{

	private Topo topo;
	
	public CommentaireTopo() {
		super();
	}

	public Topo getTopo() {
		return topo;
	}

	public void setTopo(Topo topo) {
		this.topo = topo;
	}
	
	
	
}
