package oc.P6.escalade.consumer.DAO.contract.manager.commentaire;

import java.util.ArrayList;

import oc.P6.escalade.model.bean.commentaire.CommentaireTopo;
import oc.P6.escalade.model.bean.exception.CommentaireTopoException;

/**
 * Interface CommentaireTopoDao et ses méthodes interagissant avec la base de donnée
 * @author nicolas
 *
 */
public interface CommentaireTopoDao {

	/**
	 * Méthode pour créer un {@link CommentaireTopo} donné en paramètre dans la base de donnée
	 * @param pCommentaireTopo
	 * @return 
	 * @throws CommentaireTopoException
	 */
	boolean create(CommentaireTopo pCommentaireTopo) throws CommentaireTopoException;
	
	/**
	 * Méthode pour supprimer un {@link CommentaireTopo} donné en paramètre dans la base de donnée
	 * @param pCommentaireTopo
	 * @return
	 * @throws CommentaireTopoException
	 */
	boolean delete (CommentaireTopo pCommentaireTopo) throws CommentaireTopoException;
	
	/**
	 * Méthode pour modifier un {@link CommentaireTopo} donné en paramètre dans la base de donnée
	 * @param pCommentaireTopo
	 * @return
	 * @throws CommentaireTopoException
	 */
	boolean update (CommentaireTopo pCommentaireTopo) throws CommentaireTopoException;
	
	/**
	 * Méthode pour trouver un {@link CommentaireTopo} à partir du nom du {@link Topo}, du pseudo du {@link Utilisateur} et du message donné en paramètre dans la base de donnée
	 * @param pNomTopo
	 * @param pPseudo
	 * @param pMessage
	 * @return {@link CommentaireTopo}
	 */
	CommentaireTopo find(String pNomTopo, String pPseudo, String pMessage);
	
	/**
	 * Méthode pour obtenir la liste des {@link CommentaireTopo} à valider dans la base de donnée
	 * @return la liste des {@link CommentaireTopo}
	 */
	ArrayList<CommentaireTopo> listCommentaire();
	
	/**
	 * Méthode pour obtenir la liste des {@link CommentaireTopo} valide du {@link Topo} d'id donné en paramètre dans la base de donnée
	 * @param pIdTopo
	 * @return la liste des {@link CommentaireTopo}
	 */
	ArrayList<CommentaireTopo> listCommentaireValid(int pIdTopo);
	
	/**
	 * Méthode pour obtenir la liste des {@link CommentaireTopo} du {@link Topo} d'id donné en paramètre dans la base de donnée
	 * @param pIdTopo
	 * @return la liste des {@link CommentaireTopo}
	 */
	ArrayList<CommentaireTopo> listCommentaireTopo(int pIdTopo);
}
