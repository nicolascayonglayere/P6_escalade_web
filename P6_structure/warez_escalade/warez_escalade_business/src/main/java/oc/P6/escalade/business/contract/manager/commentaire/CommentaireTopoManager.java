package oc.P6.escalade.business.contract.manager.commentaire;

import java.util.ArrayList;

import oc.P6.escalade.model.bean.commentaire.CommentaireTopo;
import oc.P6.escalade.model.bean.exception.CommentaireTopoException;
import oc.P6.escalade.model.bean.exception.TopoException;

public interface CommentaireTopoManager {

	/**
	 * Méthode pour obtenir la liste des {@link CommentaireTopo} en cours de validation
	 * @return la liste des {@link CommentaireTopo}
	 */
	ArrayList<CommentaireTopo> getListCommentaireTopo();
	
	/**
	 * Méthode pour obtenir la liste des {@link CommentaireTopo} qui ont été validé pour le {@link Topo} d'id donné en paramètre
	 * @param pIdTopo
	 * @return la liste des {@link CommentaireTopo}
	 * @throws TopoException 
	 */
	ArrayList<CommentaireTopo> getListValid(int pIdTopo) throws TopoException;
	
	/**
	 * Méthode pour obtenir le {@link CommentaireTopo} avec le nom du {@link Topo} commenté, le nom de l'auteur et le commentaire
	 * @param pNomTopo
	 * @param pPseudo
	 * @param pMessage
	 * @return le {@link CommentaireTopo}
	 * @throws CommentaireTopoException 
	 */
	CommentaireTopo getCommentaireTopo(String pNomTopo, String pPseudo, String pMessage) throws CommentaireTopoException;
	
	/**
	 * Méthode pour créer le {@link CommentaireTopo} donné en paramètre
	 * @param pCommTopo
	 * @throws CommentaireTopoException 
	 */
	void creerCommentaireTopo(CommentaireTopo pCommTopo) throws CommentaireTopoException;
	
	/**
	 * Méthode pour modifier le {@link CommentaireTopo} donné en paramètre
	 * @param pCommTopo
	 * @throws CommentaireTopoException 
	 */
	void modifCommentaireTopo(CommentaireTopo pCommTopo) throws CommentaireTopoException;
	
	/**
	 * Méthode pour supprimer le {@link CommentaireTopo} donné en paramètre
	 * @param pCommTopo
	 * @throws CommentaireTopoException 
	 */
	void deleteCommentaireTopo(CommentaireTopo pCommTopo) throws CommentaireTopoException;
}
