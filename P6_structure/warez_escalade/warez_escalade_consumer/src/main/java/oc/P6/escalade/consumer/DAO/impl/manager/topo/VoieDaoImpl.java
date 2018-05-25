package oc.P6.escalade.consumer.DAO.impl.manager.topo;

import javax.inject.Named;

import oc.P6.escalade.consumer.DAO.contract.manager.topo.VoieManagerDao;
import oc.P6.escalade.consumer.DAO.impl.manager.AbstractDAO;
import oc.P6.escalade.model.bean.topo.Voie;

@Named
public class VoieDaoImpl extends AbstractDAO implements VoieManagerDao{

	@Override
	public boolean create(Voie pVoie) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Voie pVoie) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Voie pVoie) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Voie find(String pNom) {
		// TODO Auto-generated method stub
		return null;
	}

}
