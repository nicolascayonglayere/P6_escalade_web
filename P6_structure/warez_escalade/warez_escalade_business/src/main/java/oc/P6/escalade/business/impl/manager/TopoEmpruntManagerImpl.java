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
	public TopoEmprunt getTopoEmprunt(String pNom) {
		System.out.println("topo : "+topoDAO.find(pNom).getNom()+" - "+topoDAO.find(pNom).getId());
    	if(topoDAO.find(pNom) != null) { 
    			topoEmprunt.setNom(pNom);
    			System.out.println("topoEmp id : "+topoEmpruntDao.find(topoDAO.find(pNom).getId()).getId());
    			topoEmprunt.setId(topoEmpruntDao.find(topoDAO.find(pNom).getId()).getId());
    			topoEmprunt.setDateEmprunt(topoEmpruntDao.find(topoDAO.find(pNom).getId()).getDateEmprunt());
    			topoEmprunt.setEmprunteur(topoEmpruntDao.find(topoDAO.find(pNom).getId()).getEmprunteur());
    			topoEmprunt.setDateRetour(topoEmpruntDao.find(topoDAO.find(pNom).getId()).getDateRetour());
    			topoEmprunt.setTopo(topoDAO.find(pNom));
        
    	} 
    	else {
			try {
				throw new Exception("topo non trouve : NOM=" + pNom);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	System.out.println("CTRL "+topoEmprunt.getNom()+" - "+topoEmprunt.getEmprunteur().getPseudo()+" - "+topoEmprunt.getTopo().getNom());
    	return topoEmprunt;
	}

	@Override
	public void creerTopoEmprunt(Topo topo, Utilisateur pEmprunteur) {
		Calendar cal = Calendar.getInstance();
		System.out.println(topo.getNom()+" - "+pEmprunteur.getPseudo());
		if (topoDAO.find(topo.getNom()) != null && userDAO.find(pEmprunteur.getPseudo())!= null) {
			topoEmprunt.setDateEmprunt(cal.getTime());
			cal.setTime(cal.getTime());
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
	public void retourTopoEmprunt(TopoEmprunt topoEmprunt, Utilisateur pEmprunteur) {
		Calendar cal = Calendar.getInstance();
		int id = topoDAO.find(topoEmprunt.getNom()).getId();
		System.out.println(topoEmprunt.getNom()+" - "+pEmprunteur.getPseudo()+" - "+topoEmpruntDao.find(id).getId());
		if (topoEmpruntDao.find(id) != null && userDAO.find(pEmprunteur.getPseudo())!= null) {
			topoEmprunt.setId(topoEmpruntDao.find(id).getId());
			topoEmprunt.setDateRetour(cal.getTime());
			topoEmprunt.setEmprunteur(pEmprunteur);
			topoEmprunt.setTopo(topoEmprunt.getTopo());
			topoEmprunt.setNom(topoEmprunt.getNom());
			topoEmpruntDao.delete(topoEmprunt);
		}
		
	}

}
