package oc.P6.escalade.business.contract.manager;

import oc.P6.escalade.consumer.DAO.DAOFactory;

public interface AbstractDAOManager<T> {
	
	DAOFactory<T> getDAOFactory<T>();
}
