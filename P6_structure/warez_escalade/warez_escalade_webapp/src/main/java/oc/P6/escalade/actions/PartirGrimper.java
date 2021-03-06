package oc.P6.escalade.actions;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

/**
 * Classe action qui permet la recherche multi critère
 * @author nicolas
 *
 */
@Named
@Scope("Protoype")
public class PartirGrimper extends ActionSupport {

	/**
	 * 
	 */
	static final Logger logger = LogManager.getLogger();
	private static final long serialVersionUID = 1L;
	private ArrayList<String> listDiff= new ArrayList<String>();
	private boolean checkMeTopo, checkMeSite, checkMeSecteur, checkMeVoie;
	private ArrayList<Topo> listTopo;
	private ArrayList<Site> listSite;
	private ArrayList<Secteur> listSecteur;
	private ArrayList<Voie> listVoie;
	private ArrayList<Object> listResultat = new ArrayList<Object>();
	private String nom;
	private String selectedMin, selectedMax;
	@Inject
	private ManagerFactory managerFactory;
	
	/**
	 * Méthode qui envoie les données nécessaires à la jsp partir.jsp
	 */
	public String input() {
		String[] vDiff = {"1", "2", "3", "4a", "4b", "4c", "5a", "5b", "5c", "6a", "6b", "6c", "7a", "7b", "7c", "8a", "8b", "8c", "9a", "9b", "9c" }; 		
		for (int i = 0; i < vDiff.length ; i++)
			listDiff.add(vDiff[i]);
		return ActionSupport.SUCCESS;
	}
	
	/**
	 * Méthode qui envoie le résultat de la recherche selon les critères reçus.
	 */
	public String execute() {
		logger.debug("rech multi : "+nom+" - "+selectedMin+" - "+checkMeTopo+" - "+checkMeSite);
		listTopo = new ArrayList<Topo>();
		listSite = new ArrayList<Site>();
		listSecteur = new ArrayList<Secteur>();
		listVoie = new ArrayList<Voie>();
		String vResult ="";
		
		if (checkMeTopo) {
			try {
				listTopo = managerFactory.getTopoManager().rechercheMultiTopo(nom, selectedMin, selectedMax);
				logger.debug(listTopo.size());
				for (Topo t : listTopo) {
					if(t.getListVoie().size() > 0) {
							listVoie.addAll(t.getListVoie());
					}
				}
				listResultat.addAll(listTopo);
				vResult = ActionSupport.SUCCESS;
			} catch (TopoException e1) {
				addActionMessage(e1.getMessage());
				//e1.printStackTrace();
				vResult = ActionSupport.INPUT;
			}
	
		}
		if(checkMeSite) {
			try {
				listSite = managerFactory.getSiteManager().rechercheMultiSite(nom, selectedMin, selectedMax);
				logger.debug(listSite.size());
				for(Site si : listSite) {
					if(si.getListVoie().size() > 0) {
						listVoie.addAll(si.getListVoie());
					}
					listTopo.add(si.getTopo());
				}
				listResultat.addAll(listSite);
				vResult = ActionSupport.SUCCESS;
			} catch (SiteException e2) {
				addActionMessage(e2.getMessage());
				//e2.printStackTrace();
				vResult = ActionSupport.INPUT;
			}
	
		}
		if(checkMeSecteur) {
			try {
				listSecteur = managerFactory.getSecteurManager().rechercheMultiSecteur(nom, selectedMin, selectedMax);
				logger.debug(listSecteur.size());
				for(Secteur se : listSecteur) {
					if(se.getListVoie().size() > 0) {
						listVoie.addAll(se.getListVoie());
					}
					listTopo.add(se.getSite().getTopo());
				}
				listResultat.addAll(listSecteur);
				vResult = ActionSupport.SUCCESS;
			}catch (SecteurException e3) {
				addActionMessage(e3.getMessage());
				//e3.printStackTrace();
				vResult = ActionSupport.INPUT;
			}
		}
		if (checkMeVoie) {
			try {
				listVoie = managerFactory.getVoieManager().rechercheMultiVoie(nom, selectedMin, selectedMax);
				logger.debug(listVoie.size());
				listTopo.add(listVoie.get(0).getSecteur().getSite().getTopo());
				for (Voie v : listVoie) {
					Topo vTopo = v.getSecteur().getSite().getTopo();
					if(!(vTopo.getNomTopo().equals(listTopo.get(0).getNomTopo())))
						listTopo.add(vTopo);
				}
				listResultat.addAll(listVoie);
				vResult = ActionSupport.SUCCESS;
			}catch(VoieException e4) {
				addActionMessage(e4.getMessage());
				//e4.printStackTrace();
				vResult = ActionSupport.INPUT;					
			}
		}
		if(!checkMeTopo && !checkMeSite && !checkMeSecteur && !checkMeVoie) {
			vResult = ActionSupport.INPUT;
			addActionMessage("Séléctionnez un lieu pour la recherche.");
		}
		String[] vDiff = {"1", "2", "3", "4a", "4b", "4c", "5a", "5b", "5c", "6a", "6b", "6c", "7a", "7b", "7c", "8a", "8b", "8c", "9a", "9b", "9c" }; 		
		for (int i = 0; i < vDiff.length ; i++)
			listDiff.add(vDiff[i]);
		return vResult;		
	}
	
	//--Getter et Setter--//
	public ArrayList<String> getListDiff() {
		return listDiff;
	}

	public void setListDiff(ArrayList<String> listDiff) {
		this.listDiff = listDiff;
	}

	public boolean isCheckMeTopo() {
		return checkMeTopo;
	}

	public void setCheckMeTopo(boolean checkMeTopo) {
		this.checkMeTopo = checkMeTopo;
	}

	public boolean isCheckMeSite() {
		return checkMeSite;
	}

	public void setCheckMeSite(boolean checkMeSite) {
		this.checkMeSite = checkMeSite;
	}

	public boolean isCheckMeSecteur() {
		return checkMeSecteur;
	}

	public void setCheckMeSecteur(boolean checkMeSecteur) {
		this.checkMeSecteur = checkMeSecteur;
	}

	public boolean isCheckMeVoie() {
		return checkMeVoie;
	}

	public void setCheckMeVoie(boolean checkMeVoie) {
		this.checkMeVoie = checkMeVoie;
	}

	public ArrayList<Topo> getListTopo() {
		return listTopo;
	}

	public void setListTopo(ArrayList<Topo> listTopo) {
		this.listTopo = listTopo;
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

	public ManagerFactory getManagerFactory() {
		return managerFactory;
	}

	public void setManagerFactory(ManagerFactory managerFactory) {
		this.managerFactory = managerFactory;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getSelectedMin() {
		return selectedMin;
	}

	public void setSelectedMin(String selectedMin) {
		this.selectedMin = selectedMin;
	}

	public String getSelectedMax() {
		return selectedMax;
	}

	public void setSelectedMax(String selectedMax) {
		this.selectedMax = selectedMax;
	}

	public ArrayList<Object> getListResultat() {
		return listResultat;
	}

	public void setListResultat(ArrayList<Object> listResultat) {
		this.listResultat = listResultat;
	}




}
