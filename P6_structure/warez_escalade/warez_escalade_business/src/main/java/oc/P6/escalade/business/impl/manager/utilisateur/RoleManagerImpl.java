package oc.P6.escalade.business.impl.manager.utilisateur;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import oc.P6.escalade.business.contract.manager.utilisateur.RoleManager;
import oc.P6.escalade.consumer.DAO.impl.DAOFactoryImpl;
import oc.P6.escalade.model.bean.utilisateur.Role;


@Named
public class RoleManagerImpl implements RoleManager {

    @Inject
    private DAOFactoryImpl daoFactory;
    @Inject
    @Named("platformTransactionManager")
    private PlatformTransactionManager platformTransactionManager;
    
    private ArrayList<Role> listRole = new ArrayList<Role>();
    
	@Override
	public ArrayList<Role> getListRole() {
		DefaultTransactionDefinition vDefinition = new DefaultTransactionDefinition();
		vDefinition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		vDefinition.setTimeout(30); // 30 secondes
        TransactionStatus vTransactionStatus = platformTransactionManager.getTransaction(vDefinition);
           
        try {
    		listRole = daoFactory.getRoleDao().getListRole();
			
		    TransactionStatus vTScommit = vTransactionStatus;
		    vTransactionStatus = null;
		    platformTransactionManager.commit(vTScommit);
		    
		} finally {
			if (vTransactionStatus != null) {
				platformTransactionManager.rollback(vTransactionStatus);
				
		    }
		}
        return listRole;
	}

	public DAOFactoryImpl getDaoFactory() {
		return daoFactory;
	}

	public void setDaoFactory(DAOFactoryImpl daoFactory) {
		this.daoFactory = daoFactory;
	}

	
}
