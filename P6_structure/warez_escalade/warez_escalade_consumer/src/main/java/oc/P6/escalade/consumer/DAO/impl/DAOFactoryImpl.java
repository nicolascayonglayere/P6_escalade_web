package oc.P6.escalade.consumer.DAO.impl;

import javax.inject.Inject;
import javax.inject.Named;

import oc.P6.escalade.consumer.DAO.DAOFactory;
import oc.P6.escalade.consumer.DAO.contract.manager.UtilisateurManagerDAO;

@Named("daoFactory")
public class DAOFactoryImpl implements DAOFactory {
	@Inject
	private UtilisateurManagerDAO userManagerDAO;
	@Override
	public UtilisateurManagerDAO getUtilisateurManagerDAO() {
		return this.userManagerDAO;
		
	}

}
