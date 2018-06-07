package oc.P6.escalade.business.impl.manager.commentaire;

import java.util.ArrayList;
import java.util.Calendar;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.PlatformTransactionManager;

import oc.P6.escalade.business.contract.manager.AbstractDAOManager;
import oc.P6.escalade.business.contract.manager.commentaire.CommentaireTopoManager;
import oc.P6.escalade.consumer.DAO.impl.manager.commentaire.CommentaireTopoDaoImpl;
import oc.P6.escalade.model.bean.commentaire.CommentaireTopo;

@Named
public class CommentaireTopoManagerImpl extends AbstractDAOManager implements CommentaireTopoManager{

	@Inject
	private CommentaireTopo commTopo;
	
	@Inject
	private CommentaireTopoDaoImpl commTopoDao;// = (CommentaireTopoDaoImpl) getDAOFactory().getCommentaireTopoDao();
	
	private PlatformTransactionManager platformTransactionManager;
	
	@Override
	public ArrayList<CommentaireTopo> getListCommentaireTopo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommentaireTopo getCommentaireTopo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void creerCommentaireTopo(CommentaireTopo pCommTopo) {
		System.out.println("CTRL "+pCommTopo.getAuteur().getNom());
			try {
				commTopo.setAuteur(pCommTopo.getAuteur());
				commTopo.setTopo(pCommTopo.getTopo());
				commTopo.setvCommentaire(pCommTopo.getvCommentaire());
				commTopo.setDate(Calendar.getInstance().getTime());
				commTopoDao.create(pCommTopo);
				
			    //TransactionStatus vTScommit = vTransactionStatus;
			    //vTransactionStatus = null;
			    //platformTransactionManager.commit(vTScommit);
			} finally {
				//if (vTransactionStatus != null) {
					//platformTransactionManager.rollback(vTransactionStatus);
			    //}
			}
	}

}
