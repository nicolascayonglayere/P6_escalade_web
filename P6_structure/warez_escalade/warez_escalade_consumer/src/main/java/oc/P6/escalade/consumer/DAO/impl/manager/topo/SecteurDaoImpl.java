package oc.P6.escalade.consumer.DAO.impl.manager.topo;

import javax.inject.Named;

import oc.P6.escalade.consumer.DAO.contract.manager.topo.SecteurManagerDao;
import oc.P6.escalade.consumer.DAO.impl.manager.AbstractDAO;
import oc.P6.escalade.model.bean.topo.Secteur;

@Named
public class SecteurDaoImpl extends AbstractDAO implements SecteurManagerDao{

	@Override
	public boolean create(Secteur pSecteur) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Secteur pSecteur) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Secteur pSecteur) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Secteur find(String pNom) {
		// TODO Auto-generated method stub
		return null;
	}

}
