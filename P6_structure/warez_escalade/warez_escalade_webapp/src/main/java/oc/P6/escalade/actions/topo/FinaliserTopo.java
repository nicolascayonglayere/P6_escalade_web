package oc.P6.escalade.actions.topo;

import java.util.ArrayList;
import java.util.Map;

import javax.inject.Inject;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import oc.P6.escalade.business.contract.ManagerFactory;
import oc.P6.escalade.model.bean.commentaire.CommentaireTopo;
import oc.P6.escalade.model.bean.exception.SecteurException;
import oc.P6.escalade.model.bean.exception.SiteException;
import oc.P6.escalade.model.bean.exception.TopoException;
import oc.P6.escalade.model.bean.exception.VoieException;
import oc.P6.escalade.model.bean.topo.Secteur;
import oc.P6.escalade.model.bean.topo.Site;
import oc.P6.escalade.model.bean.topo.Topo;
import oc.P6.escalade.model.bean.topo.Voie;

public class FinaliserTopo extends ActionSupport implements SessionAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private ManagerFactory managerFactory;
	private Map<String, Object> session;
	private Topo topo;
	private String nomTopo; 
	private ArrayList<Site> listSite;
	private ArrayList<Secteur> listSecteur;
	private ArrayList<Voie> listVoie;
	private ArrayList<CommentaireTopo> listCommentaire;
	
	public ManagerFactory getManagerFactory() {
		return managerFactory;
	}
	public void setManagerFactory(ManagerFactory managerFactory) {
		this.managerFactory = managerFactory;
	}

	public String execute() {

		try {
			//--recup le topo en construction et maj de son statut
			if ((Topo)session.get("topo") != null) {
				topo = ((Topo)session.get("topo"));				
				//topo = managerFactory.getTopoManager().getTopo(nomTopo);
			}
			else
				topo = managerFactory.getTopoManager().getTopo(topo.getNomTopo());
			managerFactory.getTopoManager().modifTopo(topo);

			nomTopo = topo.getNomTopo();
			listSite = managerFactory.getSiteManager().getSite(topo);
			for (Site s : listSite)
				listSecteur = managerFactory.getSecteurManager().getListSecteur(s);
			for (Secteur se : listSecteur)
				listVoie = managerFactory.getVoieManager().getListVoie(se);
			listCommentaire = managerFactory.getCommentaireTopoManager().getListValid(topo.getId());
			
			this.session.remove("topo");
			this.session.remove("secteur");
			this.session.remove("site");
			addActionMessage("La construction du topo"+topo.getNomTopo()+" est terminée.");
			return ActionSupport.SUCCESS;
		} catch (TopoException e1) {
			addActionMessage(e1.getMessage());
			e1.printStackTrace();
			return ActionSupport.INPUT;
		} catch (SiteException e2) {
			addActionMessage(e2.getMessage());
			e2.printStackTrace();
			return ActionSupport.INPUT;
		} catch (SecteurException e3) {
			addActionMessage(e3.getMessage());
			e3.printStackTrace();
			return ActionSupport.INPUT;
		} catch (VoieException e4) {
			addActionMessage(e4.getMessage());
			e4.printStackTrace();
			return ActionSupport.INPUT;
		}

		
	}
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
		
	}
	public Topo getTopo() {
		return topo;
	}
	public void setTopo(Topo topo) {
		this.topo = topo;
	}
	public String getNomTopo() {
		return nomTopo;
	}
	public void setNomTopo(String nomTopo) {
		this.nomTopo = nomTopo;
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
	public ArrayList<CommentaireTopo> getListCommentaire() {
		return listCommentaire;
	}
	public void setListCommentaire(ArrayList<CommentaireTopo> listCommentaire) {
		this.listCommentaire = listCommentaire;
	}

	
}
