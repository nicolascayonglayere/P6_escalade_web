package oc.P6.escalade.actions.topo;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;

import com.opensymphony.xwork2.ActionSupport;

import oc.P6.escalade.business.contract.ManagerFactory;
import oc.P6.escalade.model.bean.exception.SecteurException;
import oc.P6.escalade.model.bean.exception.SiteException;
import oc.P6.escalade.model.bean.exception.TopoException;
import oc.P6.escalade.model.bean.exception.VoieException;
import oc.P6.escalade.model.bean.topo.Secteur;
import oc.P6.escalade.model.bean.topo.Site;
import oc.P6.escalade.model.bean.topo.Topo;
import oc.P6.escalade.model.bean.topo.Voie;

@Named
@Scope("Protoype")
public class CreerVoie extends ActionSupport implements SessionAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private ManagerFactory managerFactory;
	private Voie voie;
	private String nomTopo, nomSite, nomSecteur;
	private Topo topo;
	private Secteur secteur;
	private Site site;
	private int id;
	private int selectedSecteur;
	private HashMap<Integer, String> listSecteur = new HashMap<Integer, String>();
	private Map<String, Object> session;
	private HashMap<Integer, String> listSite = new HashMap<Integer, String>();
	
	public Voie getVoie() {
		return voie;
	}
	public void setVoie(Voie voie) {
		this.voie = voie;
	}
	
	public String getNomTopo() {
		return nomTopo;
	}
	public void setNomTopo(String nomTopo) {
		this.nomTopo = nomTopo;
	}
	public String getNomSite() {
		return nomSite;
	}
	public void setNomSite(String nomSite) {
		this.nomSite = nomSite;
	}
	public String getNomSecteur() {
		return nomSecteur;
	}
	public void setNomSecteur(String nomSecteur) {
		this.nomSecteur = nomSecteur;
	}
		
	public String execute() {
		try {
			System.out.println("trace creation voie");
			if ((Topo)session.get("topo") != null) {
				topo = (Topo)session.get("topo");
			}
			if((Secteur)session.get("secteur") != null) {
				secteur = (Secteur)session.get("secteur");
			}
			System.out.println(secteur.getNomSecteur()+" - "+topo.getNomTopo());
			voie.setSecteur(secteur);
			System.out.println(voie.getNomVoie()+" - "+voie.getCotation());
			voie = managerFactory.getVoieManager().creerVoie(voie);
			
			for (Site s : managerFactory.getSiteManager().getSite(topo)) {
				listSite.put(s.getId(), s.getNomSite());
				for (Secteur se : managerFactory.getSecteurManager().getListSecteur(s)) {
					listSecteur.put(se.getId(), se.getNomSecteur());
				}
			}
			
			addActionMessage("La voie "+voie.getNomVoie()+" a bien été créee.");
			return ActionSupport.SUCCESS;			
		}catch (SiteException e3) {
			addActionMessage(e3.getMessage());
			e3.printStackTrace();
			return ActionSupport.INPUT;
		} catch (VoieException e5) {
			addActionMessage (e5.getMessage());
			e5.printStackTrace();
			return ActionSupport.INPUT;
		} catch (SecteurException e7) {
			addActionMessage (e7.getMessage());
			e7.printStackTrace();
			return ActionSupport.INPUT;
		} 

	}
	
	public String input() {
		try {
			if (((Topo)session.get("topo")).getNomTopo().length() > 0) {
				topo = (Topo)session.get("topo");
				secteur = (Secteur)session.get("secteur");
			}
			else {
				System.out.println("selection : "+selectedSecteur);
				topo = managerFactory.getTopoManager().getTopo(nomTopo);
				this.session.put("topo", topo);
				secteur = managerFactory.getSecteurManager().getSecteur(selectedSecteur);
				this.session.put("secteur", secteur);
				
			}
			
			for (Site s : managerFactory.getSiteManager().getSite(topo)) {
				listSite.put(s.getId(), s.getNomSite());
				for (Secteur se : managerFactory.getSecteurManager().getListSecteur(s)) {
					listSecteur.put(se.getId(), se.getNomSecteur());
				}
			}

			return ActionSupport.SUCCESS;			
		}catch (TopoException e2) {
			addActionMessage(e2.getMessage());
			e2.printStackTrace();
			return ActionSupport.INPUT;
		} catch (SiteException e3) {
			addActionMessage(e3.getMessage());
			e3.printStackTrace();
			return ActionSupport.INPUT;
		} catch (SecteurException e4) {
			addActionMessage(e4.getMessage());
			e4.printStackTrace();
			return ActionSupport.INPUT;
		}
	}
	
	public String select() {
		try {
			secteur = managerFactory.getSecteurManager().getSecteur(id);
			return ActionSupport.SUCCESS;
		} catch (SecteurException e) {
			addActionMessage(e.getMessage());
			e.printStackTrace();
			return ActionSupport.INPUT;
		}
		
	}
	
	public ManagerFactory getManagerFactory() {
		return managerFactory;
	}
	public void setManagerFactory(ManagerFactory managerFactory) {
		this.managerFactory = managerFactory;
	}
	public Topo getTopo() {
		return topo;
	}
	public void setTopo(Topo topo) {
		this.topo = topo;
	}
	public Secteur getSecteur() {
		return secteur;
	}
	public void setSecteur(Secteur secteur) {
		this.secteur = secteur;
	}
	public Site getSite() {
		return site;
	}
	public void setSite(Site site) {
		this.site = site;
	}
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
		
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public int getSelectedSecteur() {
		return selectedSecteur;
	}
	public void setSelectedSecteur(int selectedSecteur) {
		this.selectedSecteur = selectedSecteur;
	}
	public HashMap<Integer, String> getListSecteur() {
		return listSecteur;
	}
	public void setListSecteur(HashMap<Integer, String> listSecteur) {
		this.listSecteur = listSecteur;
	}
	public HashMap<Integer, String> getListSite() {
		return listSite;
	}
	public void setListSite(HashMap<Integer, String> listSite) {
		this.listSite = listSite;
	}

	

}
