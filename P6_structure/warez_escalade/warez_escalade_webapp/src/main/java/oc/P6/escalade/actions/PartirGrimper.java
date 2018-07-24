package oc.P6.escalade.actions;

import java.util.ArrayList;

import javax.inject.Inject;

import com.opensymphony.xwork2.ActionSupport;

import oc.P6.escalade.business.contract.ManagerFactory;
import oc.P6.escalade.model.bean.exception.SiteException;
import oc.P6.escalade.model.bean.exception.TopoException;
import oc.P6.escalade.model.bean.topo.Secteur;
import oc.P6.escalade.model.bean.topo.Site;
import oc.P6.escalade.model.bean.topo.Topo;
import oc.P6.escalade.model.bean.topo.Voie;

public class PartirGrimper extends ActionSupport {

	/**
	 * 
	 */
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
	
	
	public String input() {
		String[] vDiff = {"1", "2", "3", "4a", "4b", "4c", "5a", "5b", "5c", "6a", "6b", "6c", "7a", "7b", "7c", "8a", "8b", "8c", "9a", "9b", "9c" }; 		
		for (int i = 0; i < vDiff.length ; i++)
			listDiff.add(vDiff[i]);
		return ActionSupport.SUCCESS;
	}
	
	public String execute() {
		System.out.println("rech multi : "+nom+" - "+selectedMin+" - "+checkMeTopo);
		listTopo = new ArrayList<Topo>();
		listSite = new ArrayList<Site>();
		listSecteur = new ArrayList<Secteur>();
		listVoie = new ArrayList<Voie>();

			try {
				if (checkMeTopo) {
					listTopo = managerFactory.getTopoManager().rechercheMultiTopo(nom, selectedMin, selectedMax);
					System.out.println(listTopo.size());
					for (Topo t : listTopo) {//--un if test sur t.getListSite
						if(t.getListVoie().size() > 0) {
							for(Voie v : t.getListVoie()) {
								listVoie.addAll(t.getListVoie());
							}
						}
					}
					listResultat.addAll(listTopo);
				}
				if(checkMeSite) {
					listSite = new ArrayList<Site>();
					listSite = managerFactory.getSiteManager().rechercheMultiSite(nom, selectedMin, selectedMax);
					for(Site si : listSite) {
						listSecteur.addAll(si.getListSecteur());
						for (Secteur se : listSecteur) {
							listVoie.addAll(se.getListVoie());
						}
					}
					listResultat.addAll(listSite);
				}
				return ActionSupport.SUCCESS;
			} catch (TopoException | SiteException e) {
				addActionMessage(e.getMessage());
				e.printStackTrace();
				return ActionSupport.INPUT;
			}		
	}

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
