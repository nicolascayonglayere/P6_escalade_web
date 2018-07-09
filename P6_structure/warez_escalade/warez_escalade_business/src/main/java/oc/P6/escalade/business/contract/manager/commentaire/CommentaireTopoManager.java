package oc.P6.escalade.business.contract.manager.commentaire;

import java.util.ArrayList;

import oc.P6.escalade.model.bean.commentaire.CommentaireTopo;

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
	 */
	ArrayList<CommentaireTopo> getListValid(int pIdTopo);
	
	/**
	 * Méthode pour obtenir le {@link CommentaireTopo} avec le nom du {@link Topo} commenté, le nom de l'auteur et le commentaire
	 * @param pNomTopo
	 * @param pPseudo
	 * @param pMessage
	 * @return le {@link CommentaireTopo}
	 */
	CommentaireTopo getCommentaireTopo(String pNomTopo, String pPseudo, String pMessage);
	
	/**
	 * Méthode pour créer le {@link CommentaireTopo} donné en paramètre
	 * @param pCommTopo
	 */
	void creerCommentaireTopo(CommentaireTopo pCommTopo);
	
	/**
	 * Méthode pour modifier le {@link CommentaireTopo} donné en paramètre
	 * @param pCommTopo
	 */
	void modifCommentaireTopo(CommentaireTopo pCommTopo);
	
	/**
	 * Méthode pour supprimer le {@link CommentaireTopo} donné en paramètre
	 * @param pCommTopo
	 */
	void deleteCommentaireTopo(CommentaireTopo pCommTopo);
}
