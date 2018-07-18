package oc.P6.escalade.model.contract;

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

public interface ModelManagerFactory {

		IntCommentaireTopo getCommentaireTopo();
		IntCommentaireSite getCommentaireSite();
		IntCommentaireSecteur getCommentaireSecteur();
		IntCommentaireVoie getCommentaireVoie();
		
		IntTopoEmprunt getTopoEmprunt();
		
		IntSecteur getSecteur();
		IntVoie getVoie();
		IntSite getSite();
		IntTopo getTopo();
		
		IntCoordonneeUtilisateur getCoordonneeUtilisateur();
		IntUtilisateur getUtilisateur();
		
	//IntCommentaireTopoException getCommentaireTopoException();
	//IntCoordonneeUtilisateurException getCoordonneeUtilisateurException();
	//IntSecteurException getSecteurException();
	//IntSiteException getSiteException();
	//IntTopoException getTopoException();
	//IntVoieException getVoieException();
	//IntUtilisateurException getUtilisateurException();
}
