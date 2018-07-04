package oc.P6.escalade.actions;

import java.io.File;
import java.util.ArrayList;

import javax.inject.Inject;

import com.opensymphony.xwork2.ActionSupport;

import oc.P6.escalade.business.contract.ManagerFactory;
import oc.P6.escalade.model.bean.topo.Secteur;
import oc.P6.escalade.model.bean.topo.Site;
import oc.P6.escalade.model.bean.topo.Topo;
import oc.P6.escalade.model.bean.topo.Voie;

public class GoTopoAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private ManagerFactory managerFactory;	
	private Topo topo;
	private String nom;
	private Secteur secteur;
	private Site site;
	private Voie voie;
	private String imageId;
	private String repoId;
	private ArrayList<Site> listSite;
	private ArrayList<Secteur> listSecteur;
	private ArrayList<Voie> listVoie;
	private ArrayList<String>listImage;
	private String[] listLieux = {"topo", "site", "secteur"};
	
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
	public Voie getVoie() {
		return voie;
	}
	public void setVoie(Voie voie) {
		this.voie = voie;
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

	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public ArrayList<String> getListImage() {
		return listImage;
	}
	public void setListImage(ArrayList<String> listImage) {
		this.listImage = listImage;
	}
	
	public String getImageId() {
		return imageId;
	}
	public void setImageId(String imageId) {
		this.imageId = imageId;
	}
	public String getRepoId() {
		return repoId;
	}
	public void setRepoId(String repoId) {
		this.repoId = repoId;
	}
	public String[] getListLieux() {
		return listLieux;
	}
	public void setListLieux(String[] listLieux) {
		this.listLieux = listLieux;
	}
		
	public ArrayList<Site> getListSite() {
		return listSite;
	}
	public void setListSite(ArrayList<Site> listSite) {
		this.listSite = listSite;
	}
	
	public String execute() throws Exception {
        //call Service class to store personBean's state in database
		//--recupe le nom du topo dans la requete
		System.out.println(nom);
		topo = (Topo) managerFactory.getTopoManager().getTopo(nom);
        if (topo != null) {
    		repoId = topo.getImage();
    		//System.out.println(topo.getImage());
     		//File repertoire = new File("webapp\\assets\\images\\"+topo.getImage());
    		File repertoire = new File("D:\\Documents\\openclassrooms formation\\P6\\P6_escalade_web\\P6_structure\\warez_escalade\\warez_escalade_webapp\\src\\main\\webapp\\assets\\images\\"+topo.getImage());//
    		//System.out.println(repertoire.getPath()+" - "+repertoire.isDirectory());//+" - "+repertoire.listFiles().length);
    		listImage = new ArrayList<String>();
    		if((repertoire.listFiles()).length > 0) {
    			for (File img : repertoire.listFiles())
    				listImage.add(repoId+"\\"+img.getName());//repertoire.getPath()+"\\"+img.getName());
    	        //System.out.println(topo.getNom());
    			imageId = listImage.get(0);
    		}
    		//System.out.println(imageId);        	
        	listSite = (ArrayList<Site>) managerFactory.getSiteManager().getSite(topo);
        	for (Site s : listSite) {
        		listSecteur = (ArrayList<Secteur>) managerFactory.getSecteurManager().getListSecteur(s);
        		for (Secteur sect : listSecteur) 
        			listVoie = (ArrayList<Voie>) managerFactory.getVoieManager().getListVoie(sect);
        	}

        	return SUCCESS;
        }
        else {
        	addActionMessage("Le topo n'existe pas !");
        	return INPUT;
        }
        	
        	
    }
	public String[] getDefaultLieux(){
		return new String [] {"topo"};
	}
	public ManagerFactory getManagerFactory() {
		return managerFactory;
	}
	public void setManagerFactory(ManagerFactory managerFactory) {
		this.managerFactory = managerFactory;
	}

}
