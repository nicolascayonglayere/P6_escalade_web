package oc.P6.escalade.business.impl.manager.topo;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.PlatformTransactionManager;

import oc.P6.escalade.business.contract.manager.AbstractDAOManager;
import oc.P6.escalade.business.contract.manager.topo.SecteurManager;
import oc.P6.escalade.consumer.DAO.impl.manager.topo.SecteurDaoImpl;
import oc.P6.escalade.model.bean.topo.Secteur;
import oc.P6.escalade.model.bean.topo.Site;

/**
 * Implémentation de {@link SecteurManager}
 * @author nicolas
 *
 */
@Named
public class SecteurManagerImpl extends AbstractDAOManager implements SecteurManager{

	@Inject
	private Secteur secteur;
	
	@Inject
	private SecteurDaoImpl secteurDAO;// = (SecteurDaoImpl) getDAOFactory().getSecteurManagerDao();
	
	private PlatformTransactionManager platformTransactionManager;
	
	/**
	 * Méthode pour obtenir la liste des {@link Secteur} du {@link Site } donné en paramètre
	 */
	@Override
	public ArrayList<Secteur> getListSecteur(Site pSite) {
		ArrayList<Secteur> listSecteur = secteurDAO.getListeSecteur(pSite);
		return listSecteur;
	}

	/**
	 * Méthode pour obtenir le {@link Secteur} nommé pNom du {@link Site} donné en paramètre
	 */
	@Override
	public Secteur getSecteur(String pNom, Site pSite) {
		Secteur secteur = null;
		if (secteurDAO.find(pNom, pSite.getId()) != null) {
			secteur = secteurDAO.find(pNom, pSite.getId());
		}
		else {
			try {
				throw new Exception("Le secteur n'existe pas.");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return secteur;
	}

	/**
	 * Méthode pour créer un {@link Secteur} donné en paramètre
	 */
	@Override
	public void creerSecteur(Secteur pSecteur) {
		System.out.println("CTRL "+pSecteur.getNomSecteur()+" - "+pSecteur.getSite().getId());
		if (secteurDAO.find(pSecteur.getNomSecteur(), pSecteur.getSite().getId()) != null) {
			try {
				throw new Exception("Le secteur existe deja.");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			secteur.setNomSecteur(pSecteur.getNomSecteur());
			secteur.setDescription(pSecteur.getDescription());
			secteur.setSite(pSecteur.getSite());
			secteurDAO.create((Secteur)secteur);
		}
		
	}

	@Override
	public void modifierSecteur(Secteur pSecteur) {
		System.out.println("CTRL "+pSecteur.getNomSecteur());
		if (secteurDAO.find(pSecteur.getNomSecteur(), pSecteur.getSite().getId()) == null) {
			try {
				throw new Exception("Le secteur n'existe pas.");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			secteur.setId(secteurDAO.find(pSecteur.getNomSecteur(), pSecteur.getSite().getId()).getId());
			secteur.setNomSecteur(pSecteur.getNomSecteur());
			secteur.setDescription(pSecteur.getDescription());
			secteur.setSite(pSecteur.getSite());
			secteurDAO.update((Secteur)secteur);
		}
		
	}

	/**
	 * Méthode pour supprimer le {@link Secteur} donné en paramètre
	 */
	@Override
	public void supprimerSecteur(Secteur pSecteur) {
		System.out.println("CTRL "+pSecteur.getNomSecteur());
		if (secteurDAO.find(pSecteur.getNomSecteur(), pSecteur.getSite().getId()) == null) {
			try {
				throw new Exception("Le secteur n'existe pas.");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			secteur.setId(secteurDAO.find(pSecteur.getNomSecteur(), pSecteur.getSite().getId()).getId());
			secteur.setNomSecteur(pSecteur.getNomSecteur());
			secteur.setDescription(pSecteur.getDescription());
			secteur.setSite(pSecteur.getSite());
			secteurDAO.delete((Secteur)secteur);
		}
		
	}

}
