package oc.P6.escalade.business.impl;

import javax.inject.Inject;
import javax.inject.Named;

import oc.P6.escalade.business.contract.ManagerFactory;
import oc.P6.escalade.business.contract.manager.TopoEmpruntManager;
import oc.P6.escalade.business.contract.manager.commentaire.CommentaireSecteurManager;
import oc.P6.escalade.business.contract.manager.commentaire.CommentaireSiteManager;
import oc.P6.escalade.business.contract.manager.commentaire.CommentaireTopoManager;
import oc.P6.escalade.business.contract.manager.commentaire.CommentaireVoieManager;
import oc.P6.escalade.business.contract.manager.topo.SecteurManager;
import oc.P6.escalade.business.contract.manager.topo.SiteManager;
import oc.P6.escalade.business.contract.manager.topo.TopoManager;
import oc.P6.escalade.business.contract.manager.topo.VoieManager;
import oc.P6.escalade.business.contract.manager.utilisateur.CoordonneeUtilisateurManager;
import oc.P6.escalade.business.contract.manager.utilisateur.UtilisateurManager;
import oc.P6.escalade.business.impl.manager.utilisateur.UtilisateurManagerImpl;




/**
 * Implémentation de la {@link ManagerFactory}.
 */
@Named("managerFactory")
public class ManagerFactoryImpl implements ManagerFactory {

	public ManagerFactoryImpl() {}
	
    @Inject
    private UtilisateurManagerImpl utilisateurManager;
    @Override
    public UtilisateurManagerImpl getUtilisateurManager() {
        return this.utilisateurManager;
    }
       
	public void setUtilisateurManager(UtilisateurManagerImpl utilisateurManager) {
		this.utilisateurManager = utilisateurManager;
	}

	@Inject
    private TopoManager topoManager;
	@Override
	public TopoManager getTopoManager() {
		return this.topoManager;
	}
	
	@Inject
	private SecteurManager secteurManager;
	@Override
	public SecteurManager getSecteurManager() {
		return this.secteurManager;
	}
	
	@Inject
	private SiteManager siteManager;
	@Override
	public SiteManager getSiteManager() {
		return this.siteManager;
	}
	
	@Inject
	private VoieManager voieManager;
	@Override
	public VoieManager getVoieManager() {
		return this.voieManager;
	}
	
	@Inject
	private CommentaireTopoManager commTopoManager;
	@Override
	public CommentaireTopoManager getCommentaireTopoManager() {
		return this.commTopoManager;
	}
	
	@Inject 
	private CommentaireSiteManager commSiteManager;
	@Override
	public CommentaireSiteManager getCommentaireSiteManager() {
		return this.commSiteManager;
	}
	
	@Inject 
	private CommentaireSecteurManager commSecteurManager;
	@Override
	public CommentaireSecteurManager getCommentaireSecteurManager() {
		return this.commSecteurManager;
	}
	
	@Inject
	private CommentaireVoieManager commVoieManager;
	@Override
	public CommentaireVoieManager getCommentaireVoieManager() {
		return this.commVoieManager;
	}
	
	@Inject
	private CoordonneeUtilisateurManager coordonneeUtilisateur;
	@Override
	public CoordonneeUtilisateurManager getCoordonneeUtilisateurManager() {
		return this.coordonneeUtilisateur;
	}
	
	@Inject
	private TopoEmpruntManager topoEmprunt;
	@Override
	public TopoEmpruntManager getTopoEmpruntManager() {
		return this.topoEmprunt;
	}


}
