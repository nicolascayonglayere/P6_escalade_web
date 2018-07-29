package oc.P6.escalade.actions;

import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.opensymphony.xwork2.ActionSupport;

import oc.P6.escalade.WebappHelper.GestionFichierProperties;
import oc.P6.escalade.business.contract.ManagerFactory;
import oc.P6.escalade.model.bean.commentaire.CommentaireTopo;
import oc.P6.escalade.model.bean.topo.Secteur;
import oc.P6.escalade.model.bean.topo.Site;
import oc.P6.escalade.model.bean.topo.Topo;
import oc.P6.escalade.model.bean.topo.Voie;

/**
 * Classe action qui peuple la jsp topo.jsp affichant un {@link Topo}
 * @author nicolas
 *
 */
@Named
public class GoTopoAction extends ActionSupport {

	/**
	 * 
	 */
	static final Logger logger = LogManager.getLogger();
	private static final long serialVersionUID = 1L;
	@Inject
	private ManagerFactory managerFactory;	
	private Topo topo;
	private String nomTopo;
	private Secteur secteur;
	private Site site;
	private Voie voie;
	private String imageId;
	private String repoId;
	private ArrayList<Site> listSite;
	private ArrayList<Secteur> listSecteur = new ArrayList<Secteur>();
	private ArrayList<Voie> listVoie = new ArrayList<Voie>();
	private ArrayList<String>listImage;
	private ArrayList<CommentaireTopo> listCommentaire;
	private String latitude, longitude;
		
	/**
	 * Méthode qui envoie les données nécessaires à la jsp
	 */
	public String execute() throws Exception {
		//--construction du topo 
		logger.debug(nomTopo);
		topo = (Topo) managerFactory.getTopoManager().getTopo(nomTopo);

        if (topo != null) {
        	//--conversion des coordonnees GPS
    		latitude = String.valueOf(topo.getLatitude()).replace(',', '.');
    		longitude = String.valueOf(topo.getLongitude()).replace(',', '.');

    		//--gestion des images
    		repoId = topo.getImage();
    		GestionFichierProperties gfp = new GestionFichierProperties();
    		Path chemin = Paths.get(gfp.lireProp().getProperty("chemin.upload"), topo.getImage());
    		listImage = new ArrayList<String>();
    	    try (DirectoryStream<Path> stream = Files.newDirectoryStream(chemin)){ 
    	      Iterator<Path> iterator = stream.iterator();
    	      while(iterator.hasNext()) {
    	        Path p = iterator.next();
    	        logger.debug(p);
    	        listImage.add(repoId+"\\"+p.getFileName().toString());
    	        imageId = listImage.get(0);
    	      }
    	    } 

    	    //--construction du topo et de ses sites/secteurs/voies
        	listSite = (ArrayList<Site>) managerFactory.getSiteManager().getSite(topo);
        	for (Site s : listSite) {
        		listSecteur.addAll((ArrayList<Secteur>) managerFactory.getSecteurManager().getListSecteur(s));
        		s.setListSecteur((ArrayList<Secteur>) managerFactory.getSecteurManager().getListSecteur(s));
        		for (Secteur sect : s.getListSecteur()) { 
        			listVoie.addAll((ArrayList<Voie>) managerFactory.getVoieManager().getListVoie(sect));
        			sect.setListVoie((ArrayList<Voie>) managerFactory.getVoieManager().getListVoie(sect));
        		}
        	}
        	//--construction des commentaires
        	listCommentaire = managerFactory.getCommentaireTopoManager().getListValid(topo.getId());
       	
        	return SUCCESS;
        }
        else {
        	addActionMessage("Le topo n'existe pas !");
        	return INPUT;
        }       	
    }
	
	//--Getter et Setter--//

	public ManagerFactory getManagerFactory() {
		return managerFactory;
	}
	public void setManagerFactory(ManagerFactory managerFactory) {
		this.managerFactory = managerFactory;
	}
	public ArrayList<CommentaireTopo> getListCommentaire() {
		return listCommentaire;
	}
	public void setListCommentaire(ArrayList<CommentaireTopo> listCommentaire) {
		this.listCommentaire = listCommentaire;
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
	public String getNomTopo() {
		return nomTopo;
	}
	public void setNomTopo(String nomTopo) {
		this.nomTopo = nomTopo;
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
		
	public ArrayList<Site> getListSite() {
		return listSite;
	}
	public void setListSite(ArrayList<Site> listSite) {
		this.listSite = listSite;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
}
