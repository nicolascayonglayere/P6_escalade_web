package oc.P6.escalade.model.bean.impl;

import javax.inject.Inject;
import javax.inject.Named;

import oc.P6.escalade.model.bean.commentaire.CommentaireSecteur;
import oc.P6.escalade.model.bean.commentaire.CommentaireSite;
import oc.P6.escalade.model.bean.commentaire.CommentaireTopo;
import oc.P6.escalade.model.bean.commentaire.CommentaireVoie;
import oc.P6.escalade.model.bean.emprunt.TopoEmprunt;
import oc.P6.escalade.model.bean.exception.CommentaireTopoException;
import oc.P6.escalade.model.bean.exception.CoordonneeUtilisateurException;
import oc.P6.escalade.model.bean.exception.SecteurException;
import oc.P6.escalade.model.bean.exception.SiteException;
import oc.P6.escalade.model.bean.exception.TopoException;
import oc.P6.escalade.model.bean.exception.UtilisateurException;
import oc.P6.escalade.model.bean.exception.VoieException;
import oc.P6.escalade.model.bean.topo.Secteur;
import oc.P6.escalade.model.bean.topo.Site;
import oc.P6.escalade.model.bean.topo.Topo;
import oc.P6.escalade.model.bean.topo.Voie;
import oc.P6.escalade.model.bean.utilisateur.CoordonneeUtilisateur;
import oc.P6.escalade.model.bean.utilisateur.Utilisateur;
import oc.P6.escalade.model.contract.ModelManagerFactory;
import oc.P6.escalade.model.contract.commentaire.IntCommentaireSecteur;
import oc.P6.escalade.model.contract.commentaire.IntCommentaireSite;
import oc.P6.escalade.model.contract.commentaire.IntCommentaireTopo;
import oc.P6.escalade.model.contract.commentaire.IntCommentaireVoie;
import oc.P6.escalade.model.contract.emprunt.IntTopoEmprunt;
import oc.P6.escalade.model.contract.exception.IntCommentaireTopoException;
import oc.P6.escalade.model.contract.exception.IntCoordonneeUtilisateurException;
import oc.P6.escalade.model.contract.exception.IntSecteurException;
import oc.P6.escalade.model.contract.exception.IntSiteException;
import oc.P6.escalade.model.contract.exception.IntTopoException;
import oc.P6.escalade.model.contract.exception.IntUtilisateurException;
import oc.P6.escalade.model.contract.exception.IntVoieException;
import oc.P6.escalade.model.contract.topo.IntSecteur;
import oc.P6.escalade.model.contract.topo.IntSite;
import oc.P6.escalade.model.contract.topo.IntTopo;
import oc.P6.escalade.model.contract.topo.IntVoie;
import oc.P6.escalade.model.contract.utilisateur.IntCoordonneeUtilisateur;
import oc.P6.escalade.model.contract.utilisateur.IntUtilisateur;

@Named
public class ModelManagerFactoryImpl implements ModelManagerFactory {

	@Inject 
	TopoEmprunt topoEmprunt;
	@Override
	public IntTopoEmprunt getTopoEmprunt() {
		return topoEmprunt;
	}

	@Inject
	Secteur secteur;
	@Override
	public IntSecteur getSecteur() {
		return secteur;
	}

	@Inject
	Voie voie;
	@Override
	public IntVoie getVoie() {
		return voie;
	}

	@Inject
	Site site;
	@Override
	public IntSite getSite() {
		return site;
	}
	
	@Inject
	Topo topo;
	@Override
	public IntTopo getTopo() {
		return topo;
	}

	@Inject
	CoordonneeUtilisateur coordonneeUtilisateur;
	@Override
	public IntCoordonneeUtilisateur getCoordonneeUtilisateur() {
		return coordonneeUtilisateur;
	}

	@Inject
	Utilisateur utilisateur;
	@Override
	public IntUtilisateur getUtilisateur() {
		return utilisateur;
	}
	
	@Inject
	CommentaireTopo commTopo;
	@Override
	public IntCommentaireTopo getCommentaireTopo() {
		return commTopo;
	}
	
	@Inject
	CommentaireSite commSite;
	@Override
	public IntCommentaireSite getCommentaireSite() {
		return commSite;
	}
	
	@Inject
	CommentaireSecteur commSecteur;
	@Override
	public IntCommentaireSecteur getCommentaireSecteur() {
		return commSecteur;
	}
	
	@Inject
	CommentaireVoie commVoie;
	@Override
	public IntCommentaireVoie getCommentaireVoie() {
		return commVoie;
	}
	
//@Inject
//CommentaireTopoException commentaireTopoException;
//@Override
//public IntCommentaireTopoException getCommentaireTopoException() {
//	return commentaireTopoException;
//}
//
//@Inject
//CoordonneeUtilisateurException coordonneeUtilisateurException;
//@Override
//public IntCoordonneeUtilisateurException getCoordonneeUtilisateurException() {
//	return coordonneeUtilisateurException;
//}
//
//@Inject 
//SecteurException secteurException;
//@Override
//public IntSecteurException getSecteurException() {
//	return secteurException;
//}
//
//@Inject
//SiteException siteException;
//@Override
//public IntSiteException getSiteException() {
//	return siteException;
//}
//
//@Inject 
//TopoException topoException;
//@Override
//public IntTopoException getTopoException() {
//	return topoException;
//}
//
//@Inject
//VoieException voieException;
//@Override
//public IntVoieException getVoieException() {
//	return voieException;
//}
//
//@Inject
//UtilisateurException utilisateurException;
//@Override
//public IntUtilisateurException getUtilisateurException() {
//	return utilisateurException;
//}

}
