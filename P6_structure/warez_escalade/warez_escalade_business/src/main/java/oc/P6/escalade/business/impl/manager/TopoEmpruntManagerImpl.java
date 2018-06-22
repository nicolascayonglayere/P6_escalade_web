package oc.P6.escalade.business.impl.manager;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.PlatformTransactionManager;

import oc.P6.escalade.business.contract.manager.AbstractDAOManager;
import oc.P6.escalade.business.contract.manager.TopoEmpruntManager;
import oc.P6.escalade.consumer.DAO.impl.manager.TopoEmpruntDaoImpl;
import oc.P6.escalade.consumer.DAO.impl.manager.topo.TopoDaoImpl;
import oc.P6.escalade.consumer.DAO.impl.manager.utilisateur.UtilisateurDaoImpl;
import oc.P6.escalade.model.bean.emprunt.TopoEmprunt;
import oc.P6.escalade.model.bean.topo.Topo;
import oc.P6.escalade.model.bean.utilisateur.Utilisateur;

@Named
public class TopoEmpruntManagerImpl extends AbstractDAOManager implements TopoEmpruntManager{

	@Inject
	private TopoEmprunt topoEmprunt;
	
	@Inject
	private TopoEmpruntDaoImpl topoEmpruntDao;
	
	@Inject
	private TopoDaoImpl topoDAO;
	
	@Inject
	private UtilisateurDaoImpl userDAO;
	
	private PlatformTransactionManager platformTransactionManager;
	
	@Override
	public ArrayList<TopoEmprunt> getListTopoEmprunt(int pId_utilisateur) {
		ArrayList<TopoEmprunt> listTopoEmprunt = topoEmpruntDao.getListTopoEmprunt(pId_utilisateur);
		return listTopoEmprunt;
	}

	@Override
	public TopoEmprunt getTopoEmprunt(String pNom, Utilisateur pEmprunteur) {
		System.out.println("topo : "+topoDAO.find(pNom).getNom()+" - "+topoDAO.find(pNom).getId());
		Calendar cal = Calendar.getInstance();
		Topo vTopo = topoDAO.find(pNom);
    	if(topoEmpruntDao.find(vTopo.getId(), pEmprunteur.getId()) != null) { 
    			topoEmprunt.setNom(pNom);
    			System.out.println("topoEmp id : "+topoEmpruntDao.find(vTopo.getId(), pEmprunteur.getId()).getId());
    			topoEmprunt.setId(topoEmpruntDao.find(vTopo.getId(), pEmprunteur.getId()).getId());
    			topoEmprunt.setDateEmprunt(topoEmpruntDao.find(vTopo.getId(), pEmprunteur.getId()).getDateEmprunt());
    			topoEmprunt.setEmprunteur(topoEmpruntDao.find(vTopo.getId(), pEmprunteur.getId()).getEmprunteur());
    			cal.setTime(cal.getTime());
    			cal.add(Calendar.DATE, 20);
    			topoEmprunt.setDateRetour(cal.getTime());
    			topoEmprunt.setTopo(vTopo);
    			System.out.println("CTRL "+topoEmprunt.getNom()+" - "+topoEmprunt.getEmprunteur().getPseudo()+" - "+topoEmprunt.getTopo().getNom());
    	} 
    	else {
			try {
				topoEmprunt = null ;
				throw new Exception("Cet emprunt n'existe pas : NOM=" + pNom);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	
    	return topoEmprunt;
	}

	@Override
	public void creerTopoEmprunt(Topo topo, Utilisateur pEmprunteur) {
		System.out.println(topo.getNom()+" - "+pEmprunteur.getPseudo());
		Calendar cal = Calendar.getInstance();
		cal.setTime(cal.getTime());
		if (topoDAO.find(topo.getNom()) != null && userDAO.find(pEmprunteur.getPseudo())!= null) {
			topoEmprunt.setDateEmprunt(cal.getTime());
			cal.add(Calendar.DATE, 20);
			topoEmprunt.setEmprunteur(pEmprunteur);
			topoEmprunt.setTopo(topo);
			topoEmprunt.setDateRetour(cal.getTime());
			topoEmprunt.setNom(topo.getNom());
			topoEmpruntDao.create(topoEmprunt);
		}
		
	}

	@Override
	public void ajoutTopoEmprunt(Topo topo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void retourTopoEmprunt(TopoEmprunt pTopoEmprunt, Utilisateur pEmprunteur) {
		int idTopo = topoDAO.find(pTopoEmprunt.getNom()).getId();
		int idEmprunteur = userDAO.find(pEmprunteur.getPseudo()).getId();
		Calendar cal = Calendar.getInstance();
		System.out.println(pTopoEmprunt.getNom()+" - "+pEmprunteur.getPseudo()+" - "+topoEmpruntDao.find(idTopo, idEmprunteur).getId());
		if (topoEmpruntDao.find(idTopo, idEmprunteur) != null) {
			pTopoEmprunt.setId(topoEmpruntDao.find(idTopo, idEmprunteur).getId());
			pTopoEmprunt.setDateRetour(cal.getTime());
			pTopoEmprunt.setEmprunteur(pEmprunteur);
			pTopoEmprunt.setTopo(pTopoEmprunt.getTopo());
			pTopoEmprunt.setNom(pTopoEmprunt.getNom());
			topoEmpruntDao.delete(pTopoEmprunt);
		}
		
	}

	@Override
	public int getNbExemplaire(Topo pTopo) {
		int nbEx = topoDAO.find(pTopo.getNom()).getNbreEx() - topoEmpruntDao.getListTopoEmprunt(pTopo).size();
		
		return nbEx;
	}

}
