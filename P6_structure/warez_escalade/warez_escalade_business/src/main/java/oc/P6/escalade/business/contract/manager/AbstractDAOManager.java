package oc.P6.escalade.business.contract.manager;


import oc.P6.escalade.consumer.DAO.DAOFactory;

public abstract class AbstractDAOManager {
	
	private DAOFactory daoFacto;
	
	
	public DAOFactory getDAOFactory() {
		return this.daoFacto;
	}
	
	public void setDAOFactory(DAOFactory pDaoFacto) {
		this.daoFacto=pDaoFacto;
	}
	
}
