package oc.P6.escalade.actions.topo;

import java.nio.file.Paths;
import java.util.ArrayList;

import javax.inject.Inject;

import com.opensymphony.xwork2.ActionSupport;

import oc.P6.escalade.WebappHelper.GestionFichierProperties;
import oc.P6.escalade.business.contract.ManagerFactory;
import oc.P6.escalade.model.bean.exception.SecteurException;
import oc.P6.escalade.model.bean.exception.SiteException;
import oc.P6.escalade.model.bean.exception.TopoException;
import oc.P6.escalade.model.bean.exception.VoieException;
import oc.P6.escalade.model.bean.topo.Secteur;
import oc.P6.escalade.model.bean.topo.Site;
import oc.P6.escalade.model.bean.topo.Topo;
import oc.P6.escalade.model.bean.topo.Voie;

public class SupprimerTopo extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private ManagerFactory managerFactory;
	private String nomTopo;
	private String checkMe;
	private Topo topo;
	private ArrayList<Site> listSite;
	private ArrayList<Secteur> listSecteur;
	private ArrayList<Voie> listVoie;
	
	public String execute() {
		GestionFichierProperties gfp = new GestionFichierProperties();
		try {
			System.out.println(nomTopo);
			Topo topo = managerFactory.getTopoManager().getTopo(nomTopo);
			managerFactory.getTopoManager().supprimerTopo(topo);
			gfp.supprimerImg(Paths.get("D:\\Documents\\openclassrooms formation\\P6\\P6_escalade_web\\P6_structure\\warez_escalade\\warez_escalade_webapp\\src\\main\\webapp\\assets\\images\\", topo.getImage()));
			addActionMessage("Vous avez supprimé le topo "+topo.getNomTopo());
			return ActionSupport.SUCCESS;
		}catch (TopoException e2) {
			addActionMessage(e2.getMessage());
			e2.printStackTrace();
			return ActionSupport.INPUT;
		} catch (VoieException e3) {
			addActionMessage(e3.getMessage());
			e3.printStackTrace();
			return ActionSupport.INPUT;
		} catch (SecteurException e4) {
			addActionMessage(e4.getMessage());
			e4.printStackTrace();
			return ActionSupport.INPUT;
		} catch (SiteException e5) {
			addActionMessage(e5.getMessage());
			e5.printStackTrace();
			return ActionSupport.INPUT;
		}
	}
	
	public String input() {
		System.out.println("input "+checkMe);
		System.out.println(checkMe);
		try {
			topo = managerFactory.getTopoManager().getTopo(checkMe);
			listSite = managerFactory.getSiteManager().getSite(topo);
			for(Site si : listSite) {
				listSecteur = managerFactory.getSecteurManager().getListSecteur(si);
				for (Secteur se : listSecteur)
					listVoie = managerFactory.getVoieManager().getListVoie(se);
			}
			addActionMessage("Vous avez sélectionner le topo :  "+topo.getNomTopo()+".");
			return ActionSupport.SUCCESS;
		} catch (TopoException e1) {
			addActionMessage(e1.getMessage());
			e1.printStackTrace();
			return ActionSupport.SUCCESS;
		} catch (SiteException e2) {
			addActionMessage(e2.getMessage());
			e2.printStackTrace();
			return ActionSupport.SUCCESS;
		} catch (SecteurException e3) {
			addActionMessage(e3.getMessage());
			e3.printStackTrace();
			return ActionSupport.SUCCESS;
		} catch (VoieException e4) {
			addActionMessage(e4.getMessage());
			e4.printStackTrace();
			return ActionSupport.SUCCESS;
		}
		
	}
	
	public ManagerFactory getManagerFactory() {
		return managerFactory;
	}
	public void setManagerFactory(ManagerFactory managerFactory) {
		this.managerFactory = managerFactory;
	}

	public String getNomTopo() {
		return nomTopo;
	}

	public void setNomTopo(String nomTopo) {
		this.nomTopo = nomTopo;
	}

	public String getCheckMe() {
		return checkMe;
	}

	public void setCheckMe(String checkMe) {
		this.checkMe = checkMe;
	}

	public Topo getTopo() {
		return topo;
	}

	public void setTopo(Topo topo) {
		this.topo = topo;
	}

	public ArrayList<Site> getListSite() {
		return listSite;
	}

	public void setListSite(ArrayList<Site> listSite) {
		this.listSite = listSite;
	}

	public ArrayList<Secteur> getListSecteur() {
		return listSecteur;
	}

	public void setListSecteur(ArrayList<Secteur> listSecteur) {
		this.listSecteur = listSecteur;
	}

	public ArrayList<Voie> getListVoie() {
		return listVoie;
	}

	public void setListVoie(ArrayList<Voie> listVoie) {
		this.listVoie = listVoie;
	}

}
