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

/**
 * Classe action qui permet la finalisation d'un {@link Topo} en cours de construction
 * @author nicolas
 *
 */
@Named
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
	private String imageId;
	private ArrayList<Site> listSite;
	private ArrayList<Secteur> listSecteur = new ArrayList<Secteur>();
	private ArrayList<Voie> listVoie;
	private ArrayList<CommentaireTopo> listCommentaire;
	private ArrayList<String>listImage;
	

	/**
	 * Méthode qui effectue la finalisation (changement de statut) du {@link Topo}
	 */
	public String execute() {

		try {
			if ((Topo)session.get("topo") != null) {
				topo = ((Topo)session.get("topo"));				
			}
			else
				topo = managerFactory.getTopoManager().getTopo(topo.getNomTopo());
			
			managerFactory.getTopoManager().modifTopo(topo);

			nomTopo = topo.getNomTopo();
			listSite = (ArrayList<Site>) managerFactory.getSiteManager().getSite(topo);
        	for (Site s : listSite) {
        		listSecteur.addAll((ArrayList<Secteur>) managerFactory.getSecteurManager().getListSecteur(s));
        		s.setListSecteur((ArrayList<Secteur>) managerFactory.getSecteurManager().getListSecteur(s));
        		for (Secteur sect : s.getListSecteur()) { 
        			listVoie.addAll((ArrayList<Voie>) managerFactory.getVoieManager().getListVoie(sect));
        			sect.setListVoie((ArrayList<Voie>) managerFactory.getVoieManager().getListVoie(sect));
        		}
        	}
        	listCommentaire = managerFactory.getCommentaireTopoManager().getListValid(topo.getId());
			
			this.session.remove("topo");
			this.session.remove("secteur");
			this.session.remove("site");
			
			GestionFichierProperties gfp = new GestionFichierProperties();
    		Path chemin = Paths.get(gfp.lireProp().getProperty("chemin.upload"), topo.getImage());
    				//"D:\\Documents\\openclassrooms formation\\P6\\P6_escalade_web\\P6_structure\\warez_escalade\\warez_escalade_webapp\\src\\main\\webapp\\assets\\images\\", topo.getImage());
    		
    		//System.out.println(repertoire.getPath()+" - "+repertoire.isDirectory());//+" - "+repertoire.listFiles().length);
    		listImage = new ArrayList<String>();
    	    try (DirectoryStream<Path> stream = Files.newDirectoryStream(chemin)){ 
	    	      Iterator<Path> iterator = stream.iterator();
	    	      while(iterator.hasNext()) {
	    	        Path p = iterator.next();
	    	        System.out.println(p);
	    	        listImage.add(topo.getImage()+"\\"+p.getFileName().toString());
	    	        setImageId(listImage.get(0));
	    	     }
    	    } catch (IOException e) {
				e.printStackTrace();
			}
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
	
	//--Getter et Setter--//
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
	public ManagerFactory getManagerFactory() {
		return managerFactory;
	}
	public void setManagerFactory(ManagerFactory managerFactory) {
		this.managerFactory = managerFactory;
	}
	
}
