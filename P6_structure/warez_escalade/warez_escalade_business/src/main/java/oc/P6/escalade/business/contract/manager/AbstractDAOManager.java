package oc.P6.escalade.business.contract.manager;


import javax.inject.Inject;

import oc.P6.escalade.consumer.DAO.DAOFactory;

public abstract class AbstractDAOManager {
	@Inject
	private DAOFactory daoFactory;
	
	
	public DAOFactory getDAOFactory() {
		return this.daoFactory;
	}
	
	public void setDAOFactory(DAOFactory pDaoFacto) {
		this.daoFactory = pDaoFacto;
	}
	
}
