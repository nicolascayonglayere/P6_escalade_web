package oc.P6.escalade.business.impl.manager.topo;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.PlatformTransactionManager;

import oc.P6.escalade.business.contract.manager.AbstractDAOManager;
import oc.P6.escalade.business.contract.manager.topo.VoieManager;
import oc.P6.escalade.consumer.DAO.contract.manager.topo.VoieManagerDao;
import oc.P6.escalade.consumer.DAO.impl.manager.topo.VoieDaoImpl;
import oc.P6.escalade.model.bean.topo.Secteur;
import oc.P6.escalade.model.bean.topo.Voie;
import oc.P6.escalade.model.contract.topo.IntVoie;

/**
 * Implémentation de {@link VoieManager}
 * @author nicolas
 *
 */
@Named
public class VoieManagerImpl extends AbstractDAOManager implements VoieManager{

	@Inject
	private IntVoie voie;
	
	@Inject
	private VoieManagerDao voieDao;// = (VoieDaoImpl) getDAOFactory().getVoieManagerFao();
	
	private PlatformTransactionManager platformTransactionManager;
	
	/**
	 * Méthode pour obtenir la liste des {@link Voie} du {@link Secteur} donné en paramètre
	 */
	@Override
	public ArrayList<Voie> getListVoie(Secteur pSecteur) {
		ArrayList<Voie> listVoie = voieDao.getlistVoie(pSecteur);
		return listVoie;
	}

	/**
	 * Méthode pour créer la {@link Voie} donnée en paramètre
	 */
	@Override
	public void creerVoie(Voie pVoie) {
		System.out.println("CTRL "+pVoie.getNomVoie());
		if (voieDao.find(pVoie.getNomVoie(), pVoie.getSecteur().getId()) != null) {
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
			voie.setNomVoie(pVoie.getNomVoie());
			voie.setSecteur(pVoie.getSecteur());
			voieDao.create((Voie)voie);
		}
		
	}

	/**
	 * Méthode pour modifier la {@link Voie} donnée en paramètre
	 */
	@Override
	public void majVoie(Voie pVoie) {
		System.out.println("CTRL "+pVoie.getNomVoie());
		if (voieDao.find(pVoie.getNomVoie(), pVoie.getSecteur().getId()) == null) {
			try {
				throw new Exception("La voie n'existe pas.");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			voie.setId(voieDao.find(pVoie.getNomVoie(), pVoie.getSecteur().getId()).getId());
			voie.setCotation(pVoie.getCotation());
			voie.setDescription(pVoie.getDescription());
			voie.setHauteur(pVoie.getHauteur());
			voie.setNbLgueur(pVoie.getNbLgueur());
			voie.setNbPoint(pVoie.getNbPoint());
			voie.setNomVoie(pVoie.getNomVoie());
			voie.setSecteur(pVoie.getSecteur());
			voieDao.update((Voie)voie);
		}
	}

	/**
	 * Méthode pour obtenir la {@link Voie} du {@link Secteur} avec son nom donné en paramètre
	 */
	@Override
	public Voie getVoie(String pNom, Secteur pSecteur) {
		Voie voie = null;
		if (voieDao.find(pNom, pSecteur.getId()) != null) {
			voie = voieDao.find(pNom, pSecteur.getId());
		}
		else {
			try {
				throw new Exception("Le secteur n'existe pas.");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return voie;
	}

	/**
	 * Méthode pour supprime la {@link Voie} donnée en paramètre
	 */
	@Override
	public void supprimerVoie(Voie pVoie) {
		System.out.println("CTRL "+pVoie.getNomVoie());
		if (voieDao.find(pVoie.getNomVoie(), pVoie.getSecteur().getId()) == null) {
			try {
				throw new Exception("La voie n'existe pas.");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			voie.setId(voieDao.find(pVoie.getNomVoie(), pVoie.getSecteur().getId()).getId());
			voie.setCotation(pVoie.getCotation());
			voie.setDescription(pVoie.getDescription());
			voie.setHauteur(pVoie.getHauteur());
			voie.setNbLgueur(pVoie.getNbLgueur());
			voie.setNbPoint(pVoie.getNbPoint());
			voie.setNomVoie(pVoie.getNomVoie());
			voie.setSecteur(pVoie.getSecteur());
			voieDao.delete((Voie)voie);
		}
	}

}
