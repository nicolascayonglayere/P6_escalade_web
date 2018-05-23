package oc.P6.escalade.consumer.DAO;

import oc.P6.escalade.consumer.DAO.contract.manager.UtilisateurManagerDAO;

public interface DAOFactory {
	
	UtilisateurManagerDAO getUtilisateurManagerDAO();
}
