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

@Named
public class SecteurManagerImpl extends AbstractDAOManager implements SecteurManager{

	@Inject
	private Secteur secteur;
	
	@Inject
	private SecteurDaoImpl secteurDAO;// = (SecteurDaoImpl) getDAOFactory().getSecteurManagerDao();
	
	private PlatformTransactionManager platformTransactionManager;
	
	@Override
	public ArrayList<Secteur> getListSecteur(Site pSite) {
		ArrayList<Secteur> listSecteur = secteurDAO.getListeSecteur(pSite);
		return listSecteur;
	}

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

	@Override
	public void creerSecteur(Secteur pSecteur) {
		System.out.println("CTRL "+pSecteur.getNom());
		if (secteurDAO.find(pSecteur.getNom(), pSecteur.getSite().getId()) != null) {
			try {
				throw new Exception("Le secteur existe deja.");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			secteur.setNom(pSecteur.getNom());
			secteur.setDescription(pSecteur.getDescription());
			secteur.setSite(pSecteur.getSite());
			secteurDAO.create(pSecteur);
		}
		
	}

}
