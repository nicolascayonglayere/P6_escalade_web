package oc.P6.escalade.consumer.DAO.impl;

import javax.inject.Inject;
import javax.inject.Named;

import oc.P6.escalade.consumer.DAO.DAOFactory;
import oc.P6.escalade.consumer.DAO.contract.manager.commentaire.CommentaireSecteurDao;
import oc.P6.escalade.consumer.DAO.contract.manager.commentaire.CommentaireSiteDao;
import oc.P6.escalade.consumer.DAO.contract.manager.commentaire.CommentaireTopoDao;
import oc.P6.escalade.consumer.DAO.contract.manager.commentaire.CommentaireVoieDao;
import oc.P6.escalade.consumer.DAO.contract.manager.topo.SecteurManagerDao;
import oc.P6.escalade.consumer.DAO.contract.manager.topo.SiteManagerDAO;
import oc.P6.escalade.consumer.DAO.contract.manager.topo.TopoManagerDao;
import oc.P6.escalade.consumer.DAO.contract.manager.topo.VoieManagerDao;
import oc.P6.escalade.consumer.DAO.contract.manager.utilisateur.CoordonneeUtilisateurDao;
import oc.P6.escalade.consumer.DAO.contract.manager.utilisateur.UtilisateurManagerDAO;

@Named("daoFactory")
public class DAOFactoryImpl implements DAOFactory {
	@Inject
	private UtilisateurManagerDAO userManagerDAO;
	@Override
	public UtilisateurManagerDAO getUtilisateurManagerDAO() {
		return this.userManagerDAO;		
	}
	
	@Inject
	private TopoManagerDao topoManagerDao;
	@Override
	public TopoManagerDao getTopoManagerDao() {
		return this.topoManagerDao;
	}
	
	@Inject
	private SiteManagerDAO siteManagerDao;
	@Override
	public SiteManagerDAO getSiteManagerDao() {
		return this.siteManagerDao;
	}
	
	@Inject
	private SecteurManagerDao secteurManagerDao;
	@Override
	public SecteurManagerDao getSecteurManagerDao() {
		return this.secteurManagerDao;
	}
	
	@Inject
	private VoieManagerDao voieManagerDao;
	@Override
	public VoieManagerDao getVoieManagerFao() {
		return this.voieManagerDao;
	}
	
	@Inject
	private CommentaireTopoDao commTopoDao;
	@Override
	public CommentaireTopoDao getCommentaireTopoDao() {
		return this.commTopoDao;
	}
	
	@Inject
	private CommentaireSiteDao commSiteDao;
	@Override
	public CommentaireSiteDao getCommentaireSiteDao() {
		return this.commSiteDao;
	}
	
	@Inject
	private CommentaireSecteurDao commSecteurDao;
	@Override
	public CommentaireSecteurDao getCommentaireSecteurDao() {
		return this.commSecteurDao;
	}
	
	@Inject
	private CommentaireVoieDao commVoieDao;
	@Override
	public CommentaireVoieDao getCommentaireVoieDao() {
		return this.commVoieDao;
	}
	
	@Inject
	private CoordonneeUtilisateurDao coordonneeUtilisateurDao;
	@Override
	public CoordonneeUtilisateurDao getCoordonneeUtilisateurDao() {
		return this.coordonneeUtilisateurDao;
	}

}
