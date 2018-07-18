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

@Named
public class CommentaireTopoManagerImpl extends AbstractDAOManager implements CommentaireTopoManager{

	private IntCommentaireTopo commTopo;
	
	@Inject
	private CommentaireTopoDao commTopoDao;
	@Inject
	@Named("platformTransactionManager")
	private PlatformTransactionManager platformTransactionManager;
	
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
		System.out.println("CTRL business find "+commTopo.getTopo().getId());
		return (CommentaireTopo) commTopo;
	}
	
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

	@Override
	public void modifCommentaireTopo(CommentaireTopo pCommTopo) throws CommentaireTopoException {
		DefaultTransactionDefinition vDefinition = new DefaultTransactionDefinition();
		vDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		vDefinition.setTimeout(30); // 30 secondes
        TransactionStatus vTransactionStatus = platformTransactionManager.getTransaction(vDefinition);
		System.out.println("CTRL "+pCommTopo.getAuteur().getNom());
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

	@Override
	public void deleteCommentaireTopo(CommentaireTopo pCommTopo) throws CommentaireTopoException {
		DefaultTransactionDefinition vDefinition = new DefaultTransactionDefinition();
		vDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		vDefinition.setTimeout(30); // 30 secondes
        TransactionStatus vTransactionStatus = platformTransactionManager.getTransaction(vDefinition);
		System.out.println("CTRL "+pCommTopo.getAuteur().getNom());
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


	public CommentaireTopoDao getCommTopoDao() {
		return commTopoDao;
	}

	public void setCommTopoDao(CommentaireTopoDao commTopoDao) {
		this.commTopoDao = commTopoDao;
	}






}
