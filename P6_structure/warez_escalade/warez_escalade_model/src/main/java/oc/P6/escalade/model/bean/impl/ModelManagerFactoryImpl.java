package oc.P6.escalade.model.bean.impl;

import javax.inject.Inject;
import javax.inject.Named;

import oc.P6.escalade.model.bean.commentaire.CommentaireSecteur;
import oc.P6.escalade.model.bean.commentaire.CommentaireSite;
import oc.P6.escalade.model.bean.commentaire.CommentaireTopo;
import oc.P6.escalade.model.bean.commentaire.CommentaireVoie;
import oc.P6.escalade.model.bean.emprunt.TopoEmprunt;
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
import oc.P6.escalade.model.contract.topo.IntSecteur;
import oc.P6.escalade.model.contract.topo.IntSite;
import oc.P6.escalade.model.contract.topo.IntTopo;
import oc.P6.escalade.model.contract.topo.IntVoie;
import oc.P6.escalade.model.contract.utilisateur.IntCoordonneeUtilisateur;
import oc.P6.escalade.model.contract.utilisateur.IntUtilisateur;

/**
 * Implémentation de la ModelManagerFactory
 * @author nicolas
 *
 */
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

}
