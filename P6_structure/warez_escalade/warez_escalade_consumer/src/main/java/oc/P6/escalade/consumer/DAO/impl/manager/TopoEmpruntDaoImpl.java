package oc.P6.escalade.consumer.DAO.impl.manager;

import javax.inject.Named;

import oc.P6.escalade.consumer.DAO.contract.manager.TopoEmpruntDao;
import oc.P6.escalade.model.bean.emprunt.TopoEmprunt;
import oc.P6.escalade.model.bean.utilisateur.Utilisateur;

@Named
public class TopoEmpruntDaoImpl extends AbstractDAO implements TopoEmpruntDao{

	@Override
	public boolean create(TopoEmprunt pTopoEmprunt) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(TopoEmprunt pTopoEmprunt) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(TopoEmprunt pTopoEmprunt) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public TopoEmprunt find(Utilisateur pEmprunteur) {
		// TODO Auto-generated method stub
		return null;
	}

}
