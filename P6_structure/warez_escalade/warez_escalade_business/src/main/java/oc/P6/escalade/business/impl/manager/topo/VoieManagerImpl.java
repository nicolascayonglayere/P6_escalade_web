package oc.P6.escalade.business.impl.manager.topo;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.PlatformTransactionManager;

import oc.P6.escalade.business.contract.manager.AbstractDAOManager;
import oc.P6.escalade.business.contract.manager.topo.VoieManager;
import oc.P6.escalade.consumer.DAO.impl.manager.topo.VoieDaoImpl;
import oc.P6.escalade.model.bean.topo.Secteur;
import oc.P6.escalade.model.bean.topo.Voie;

@Named
public class VoieManagerImpl extends AbstractDAOManager implements VoieManager{

	@Inject
	private Voie voie;
	
	@Inject
	private VoieDaoImpl voieDao;// = (VoieDaoImpl) getDAOFactory().getVoieManagerFao();
	
	private PlatformTransactionManager platformTransactionManager;
	
	@Override
	public ArrayList<Voie> getListVoie(Secteur pSecteur) {
		ArrayList<Voie> listVoie = voieDao.getlistVoie(pSecteur);
		return listVoie;
	}

	@Override
	public Voie getVoie() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void creerVoie(Voie pVoie) {
		System.out.println("CTRL "+pVoie.getNom());
		if (voieDao.find(pVoie.getNom(),pVoie.getSecteur().getId()) != null) {
			try {
				throw new Exception("La voie existe deja.");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			voie.setCotation(pVoie.getCotation());
			voie.setDescription(pVoie.getDescription());
			voie.setHauteur(pVoie.getHauteur());
			voie.setNbLgueur(pVoie.getNbLgueur());
			voie.setNbPoint(pVoie.getNbPoint());
			voie.setNom(pVoie.getNom());
			voie.setSecteur(pVoie.getSecteur());
			voieDao.create(pVoie);
		}
		
	}

	@Override
	public void majVoie(Voie pVoie) {
		// TODO Auto-generated method stub
		
	}

}
