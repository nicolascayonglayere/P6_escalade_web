package oc.P6.escalade.business.impl.manager.commentaire;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import oc.P6.escalade.business.contract.manager.AbstractDAOManager;
import oc.P6.escalade.business.contract.manager.commentaire.CommentaireTopoManager;
import oc.P6.escalade.consumer.DAO.contract.manager.commentaire.CommentaireTopoDao;
import oc.P6.escalade.model.bean.commentaire.CommentaireTopo;
import oc.P6.escalade.model.bean.exception.CommentaireTopoException;
import oc.P6.escalade.model.bean.exception.TopoException;
import oc.P6.escalade.model.contract.commentaire.IntCommentaireTopo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Implémentation de {@link CommentaireTopoManger}
 * @author nicolas
 *
 */
@Named
public class CommentaireTopoManagerImpl extends AbstractDAOManager implements CommentaireTopoManager{

	private IntCommentaireTopo commTopo;
	static final Logger logger = LogManager.getLogger("ihm");
	@Inject
	private CommentaireTopoDao commTopoDao;
	@Inject
	@Named("platformTransactionManager")
	private PlatformTransactionManager platformTransactionManager;
	
	/**
	 * Méthode pour obtenir la liste des {@link CommentaireTopo} à valider
	 */
	@Override
	public ArrayList<CommentaireTopo> getListCommentaireTopo() {
		DefaultTransactionDefinition vDefinition = new DefaultTransactionDefinition();
		vDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		vDefinition.setTimeout(30); // 30 secondes
        TransactionStatus vTransactionStatus = platformTransactionManager.getTransaction(vDefinition);
        ArrayList<CommentaireTopo> listCommentaire = new ArrayList<CommentaireTopo>();
		try {

			listCommentaire = commTopoDao.listCommentaire();
			
		    TransactionStatus vTScommit = vTransactionStatus;
		    vTransactionStatus = null;
		    platformTransactionManager.commit(vTScommit);
		} finally {
			if (vTransactionStatus != null) {
				platformTransactionManager.rollback(vTransactionStatus);
		    }
		}
		return listCommentaire;
	}

	/**
	 * Méthode pour obtenir la liste des {@link CommentaireTopo} validé du {@link Topo} d'id donné en paramètre
	 */
	@Override
	public ArrayList<CommentaireTopo> getListValid(int pIdTopo) throws TopoException {
		DefaultTransactionDefinition vDefinition = new DefaultTransactionDefinition();
		vDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		vDefinition.setTimeout(30); // 30 secondes
        TransactionStatus vTransactionStatus = platformTransactionManager.getTransaction(vDefinition);
        ArrayList<CommentaireTopo> listCommentaire = new ArrayList<CommentaireTopo>();
		try {

			listCommentaire = commTopoDao.listCommentaireValid(pIdTopo);
			
		    TransactionStatus vTScommit = vTransactionStatus;
		    vTransactionStatus = null;
		    platformTransactionManager.commit(vTScommit);
		} finally {
			if (vTransactionStatus != null) {
				platformTransactionManager.rollback(vTransactionStatus);
				throw new TopoException("Le topo d'id "+pIdTopo+" n'existe pas.");
		    }
		}
		return listCommentaire;
	}
	
	/**
	 * Méthode pour obtenir le {@link CommentaireTopo} du {@link Topo} de nom pNom écrit par {@link Utilisateur} de pseudo pPseudo et contenant le texte pMessage 
	 */
	@Override
	public CommentaireTopo getCommentaireTopo(String pNomTopo, String pPseudo, String pMessage) throws CommentaireTopoException {
		DefaultTransactionDefinition vDefinition = new DefaultTransactionDefinition();
		vDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		vDefinition.setTimeout(30); // 30 secondes
        TransactionStatus vTransactionStatus = platformTransactionManager.getTransaction(vDefinition);

		try {

			commTopo = commTopoDao.find(pNomTopo, pPseudo, pMessage);

		    TransactionStatus vTScommit = vTransactionStatus;
		    vTransactionStatus = null;
		    platformTransactionManager.commit(vTScommit);
		} finally {
			if (vTransactionStatus != null) {
				platformTransactionManager.rollback(vTransactionStatus);
				throw new CommentaireTopoException("Le commentaire n'existe pas.");
		    }
		}
		logger.debug("CTRL business find "+commTopo.getTopo().getId());
		return (CommentaireTopo) commTopo;
	}
	
	/**
	 * Méthode pour créer le {@link CommentaireTopo} donné en paramètre
	 */
	@Override
	public void creerCommentaireTopo(CommentaireTopo pCommTopo) throws CommentaireTopoException {
		DefaultTransactionDefinition vDefinition = new DefaultTransactionDefinition();
		vDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		vDefinition.setTimeout(30); // 30 secondes
        TransactionStatus vTransactionStatus = platformTransactionManager.getTransaction(vDefinition);
		System.out.println("CTRL "+pCommTopo.getAuteur().getNom());
			try {

				commTopoDao.create(pCommTopo);
				
			    TransactionStatus vTScommit = vTransactionStatus;
			    vTransactionStatus = null;
			    platformTransactionManager.commit(vTScommit);
			} finally {
				if (vTransactionStatus != null) {
					platformTransactionManager.rollback(vTransactionStatus);
					throw new CommentaireTopoException("Le commentaire existe deja. "+pCommTopo.getId());
			    }
			}
	}

	/**
	 * Méthode pour modifier le {@link CommentaireTopo} donné en paramètre (méthode de validation)
	 */
	@Override
	public void modifCommentaireTopo(CommentaireTopo pCommTopo) throws CommentaireTopoException {
		DefaultTransactionDefinition vDefinition = new DefaultTransactionDefinition();
		vDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		vDefinition.setTimeout(30); // 30 secondes
        TransactionStatus vTransactionStatus = platformTransactionManager.getTransaction(vDefinition);
		logger.debug("CTRL "+pCommTopo.getAuteur().getNom());
			try {

				commTopoDao.update(pCommTopo);
				
			    TransactionStatus vTScommit = vTransactionStatus;
			    vTransactionStatus = null;
			    platformTransactionManager.commit(vTScommit);
			} finally {
				if (vTransactionStatus != null) {
					platformTransactionManager.rollback(vTransactionStatus);
					throw new CommentaireTopoException("Le commentaire n'existe pas. "+pCommTopo.getId());
			    }
			}
	}

	/**
	 * Méthode pour supprimer le {@link CommentaireTopo} donné en paramètre
	 */
	@Override
	public void deleteCommentaireTopo(CommentaireTopo pCommTopo) throws CommentaireTopoException {
		DefaultTransactionDefinition vDefinition = new DefaultTransactionDefinition();
		vDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		vDefinition.setTimeout(30); // 30 secondes
        TransactionStatus vTransactionStatus = platformTransactionManager.getTransaction(vDefinition);
		logger.debug("CTRL "+pCommTopo.getAuteur().getNom());
			try {

				commTopoDao.delete(pCommTopo);
				
			    TransactionStatus vTScommit = vTransactionStatus;
			    vTransactionStatus = null;
			    platformTransactionManager.commit(vTScommit);
			} finally {
				if (vTransactionStatus != null) {
					platformTransactionManager.rollback(vTransactionStatus);
					throw new CommentaireTopoException("Le commentaire n'existe pas. "+pCommTopo.getId());
			    }
			}
		
	}


	//--Getter et Setter--//
	public CommentaireTopoDao getCommTopoDao() {
		return commTopoDao;
	}

	public void setCommTopoDao(CommentaireTopoDao commTopoDao) {
		this.commTopoDao = commTopoDao;
	}






}
