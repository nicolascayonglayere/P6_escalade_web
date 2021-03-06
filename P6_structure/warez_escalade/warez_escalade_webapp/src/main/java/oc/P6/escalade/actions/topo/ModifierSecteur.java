package oc.P6.escalade.actions.topo;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;

import com.opensymphony.xwork2.ActionSupport;

import oc.P6.escalade.WebappHelper.GestionFichierProperties;
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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
/**
 * Classe action qui modifie un {@link Secteur}
 * @author nicolas
 *
 */
@Named
@Scope("Protoype")
public class ModifierSecteur extends ActionSupport implements SessionAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static final Logger logger = LogManager.getLogger();
	@Inject
	private ManagerFactory managerFactory;
	private String nomSecteur, nomTopo, nomSite;
	private Secteur secteur;
	private Site site;
	private Topo topo;
	private int id;
	private String imageId;
	private String repoId;
	private ArrayList<Site> listSite;
	private ArrayList<Secteur> listSecteur = new ArrayList<Secteur>();
	private ArrayList<Voie> listVoie =  new ArrayList<Voie>();
	private ArrayList<String>listImage;
	private ArrayList<CommentaireTopo> listCommentaire;
	private Map<String, Object> session;
	private String latitude;
	private String longitude;
	
	/**
	 * Méthode qui effectue la modification
	 */
	public String execute() {
		logger.debug(secteur.getNomSecteur()+" - "+topo.getNomTopo());
		secteur.setId(((Secteur)session.get("secteurModif")).getId());
		secteur.setSite(((Secteur)session.get("secteurModif")).getSite());
		if (secteur.equals(((Secteur)session.get("secteurModif")))) {
			addActionMessage("Aucune modification enregistrée ! ");
			return ActionSupport.INPUT;
		}
		else {
			try {
				managerFactory.getSecteurManager().modifierSecteur(secteur);
				this.session.remove("secteurModif");
	    		repoId = secteur.getSite().getTopo().getImage();
	    		logger.debug("repositary : "+repoId);
	    		GestionFichierProperties gfp = new GestionFichierProperties();
	    		Path chemin = Paths.get(gfp.lireProp().getProperty("chemin.upload"), repoId);
	    		listImage = new ArrayList<String>();
	    	    try (DirectoryStream<Path> stream = Files.newDirectoryStream(chemin)){ 
	    	      Iterator<Path> iterator = stream.iterator();
	    	      while(iterator.hasNext()) {
	    	        Path p = iterator.next();
	    	        logger.debug(p);
	    	        listImage.add(repoId+"\\"+p.getFileName().toString());
	    	        imageId = listImage.get(0);
	    	      }
	    	    } catch (IOException e) {
					logger.debug(e.getMessage());
					//e.printStackTrace();
				} 
	    		  
	    	    topo = secteur.getSite().getTopo();
	        	//--conversion des coordonnees GPS
	    		setLatitude(String.valueOf(topo.getLatitude()).replace(',', '.'));
	    		setLongitude(String.valueOf(topo.getLongitude()).replace(',', '.'));
	        	listSite = (ArrayList<Site>) managerFactory.getSiteManager().getSite(secteur.getSite().getTopo());
	        	for (Site s : listSite) {
	        		listSecteur.addAll((ArrayList<Secteur>) managerFactory.getSecteurManager().getListSecteur(s));
	        		s.setListSecteur((ArrayList<Secteur>) managerFactory.getSecteurManager().getListSecteur(s));
	        		for (Secteur sect : s.getListSecteur()) { 
	        			listVoie.addAll((ArrayList<Voie>) managerFactory.getVoieManager().getListVoie(sect));
	        			sect.setListVoie((ArrayList<Voie>) managerFactory.getVoieManager().getListVoie(sect));
	        		}
	        	}
	        	listCommentaire = managerFactory.getCommentaireTopoManager().getListValid(topo.getId());
				addActionMessage("Le secteur "+secteur.getNomSecteur()+"a bien été modifié.");
				return ActionSupport.SUCCESS;
			} catch (TopoException e1) {
				addActionMessage(e1.getMessage());
				//e1.printStackTrace();
				return ActionSupport.INPUT;
			} catch (SiteException e2) {
				addActionMessage(e2.getMessage());
				//e2.printStackTrace();
				return ActionSupport.INPUT;
			} catch (SecteurException e3) {
				addActionMessage(e3.getMessage());
				//e3.printStackTrace();
				return ActionSupport.INPUT;
			} catch (VoieException e4) {
				addActionMessage(e4.getMessage());
				//e4.printStackTrace();
				return ActionSupport.INPUT;
			}
		}
	}
	
	/**
	 * Méthode qui construit le {@link Secteur} à modifier
	 */
	public String input() {
		try {
			topo = managerFactory.getTopoManager().getTopo(nomTopo);
			site = managerFactory.getSiteManager().getSite(nomSite, topo);
			secteur = managerFactory.getSecteurManager().getSecteur(nomSecteur, site);
			this.session.put("secteurModif", secteur);
			return ActionSupport.SUCCESS;
		} catch (TopoException e1) {
			addActionMessage(e1.getMessage());
			//e1.printStackTrace();
			return ActionSupport.INPUT;
		} catch (SiteException e2) {
			addActionMessage(e2.getMessage());
			//e2.printStackTrace();
			return ActionSupport.INPUT;
		} catch (SecteurException e3) {
			addActionMessage(e3.getMessage());
			//e3.printStackTrace();
			return ActionSupport.INPUT;
		}
	}
	
	//--Getter et Setter--//
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
	public Secteur getSecteur() {
		return secteur;
	}
	public void setSecteur(Secteur secteur) {
		this.secteur = secteur;
	}

	public String getNomSecteur() {
		return nomSecteur;
	}

	public void setNomSecteur(String nomSecteur) {
		this.nomSecteur = nomSecteur;
	}

	public String getNomSite() {
		return nomSite;
	}

	public void setNomSite(String nomSite) {
		this.nomSite = nomSite;
	}

	public Site getSite() {
		return site;
	}

	public void setSite(Site site) {
		this.site = site;
	}

	public Topo getTopo() {
		return topo;
	}

	public void setTopo(Topo topo) {
		this.topo = topo;
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

	public ArrayList<String> getListImage() {
		return listImage;
	}

	public void setListImage(ArrayList<String> listImage) {
		this.listImage = listImage;
	}

	public ArrayList<CommentaireTopo> getListCommentaire() {
		return listCommentaire;
	}

	public void setListCommentaire(ArrayList<CommentaireTopo> listCommentaire) {
		this.listCommentaire = listCommentaire;
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
