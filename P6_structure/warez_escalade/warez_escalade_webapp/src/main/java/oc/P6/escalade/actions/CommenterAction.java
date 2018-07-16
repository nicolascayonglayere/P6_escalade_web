package oc.P6.escalade.actions;


import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Map;

import javax.inject.Inject;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import oc.P6.escalade.business.contract.ManagerFactory;
import oc.P6.escalade.model.bean.commentaire.CommentaireTopo;
import oc.P6.escalade.model.bean.topo.Secteur;
import oc.P6.escalade.model.bean.topo.Site;
import oc.P6.escalade.model.bean.topo.Topo;
import oc.P6.escalade.model.bean.topo.Voie;
import oc.P6.escalade.model.bean.utilisateur.Utilisateur;

public class CommenterAction extends ActionSupport implements SessionAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private Utilisateur utilisateur;
	@Inject
	private ManagerFactory managerFactory;
	private String nom;
	private String message;
	private CommentaireTopo commentaireTopo;
	private Topo topo;
	private String imageId;
	private String repoId;
	private ArrayList<Site> listSite;
	private ArrayList<Secteur> listSecteur;
	private ArrayList<Voie> listVoie;
	private ArrayList<String>listImage;
	private ArrayList<CommentaireTopo> listCommentaire;
	private Map<String, Object> session; 
	
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}
	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
	public Topo getTopo() {
		return topo;
	}
	public void setTopo(Topo topo) {
		this.topo = topo;
	}
	public CommentaireTopo getCommentaireTopo() {
		return commentaireTopo;
	}
	public void setCommentaireTopo(CommentaireTopo commentaireTopo) {
		this.commentaireTopo = commentaireTopo;
	}
	
	
	public String execute() {
		System.out.println(((Utilisateur) session.get("utilisateur")).getPseudo());
		utilisateur = (Utilisateur) (session.get("utilisateur"));
		System.out.println("comentaire du topo "+topo.getNomTopo()+" - "+utilisateur.getPseudo()+" - "+commentaireTopo.getMessage());
		//utilisateur = managerFactory.getUtilisateurManager().getUtilisateur(utilisateur.getPseudo());
		topo = managerFactory.getTopoManager().getTopo(topo.getNomTopo());
		
		repoId = topo.getImage();
		//System.out.println(topo.getImage());
 		//File repertoire = new File("webapp\\assets\\images\\"+topo.getImage());
		Path chemin = Paths.get("D:\\Documents\\openclassrooms formation\\P6\\P6_escalade_web\\P6_structure\\warez_escalade\\warez_escalade_webapp\\src\\main\\webapp\\assets\\images\\", topo.getImage());
		//File repertoire = new File("D:\\Documents\\openclassrooms formation\\P6\\P6_escalade_web\\P6_structure\\warez_escalade\\warez_escalade_webapp\\src\\main\\webapp\\assets\\images\\"+topo.getImage());//
		//System.out.println(repertoire.getPath()+" - "+repertoire.isDirectory());//+" - "+repertoire.listFiles().length);
		listImage = new ArrayList<String>();
	    try (DirectoryStream<Path> stream = Files.newDirectoryStream(chemin)){ 
	      Iterator<Path> iterator = stream.iterator();
	      while(iterator.hasNext()) {
	        Path p = iterator.next();
	        System.out.println(p);
	        listImage.add(repoId+"\\"+p.getFileName().toString());
	        setImageId(listImage.get(0));
	      }
	    } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		//System.out.println(imageId);        	
    	listSite = (ArrayList<Site>) managerFactory.getSiteManager().getSite(topo);
    	for (Site s : listSite) {
    		listSecteur = (ArrayList<Secteur>) managerFactory.getSecteurManager().getListSecteur(s);
    		for (Secteur sect : listSecteur) 
    			setListVoie((ArrayList<Voie>) managerFactory.getVoieManager().getListVoie(sect));
    	}
    	listCommentaire = managerFactory.getCommentaireTopoManager().getListValid(topo.getId());
		
		if(!(commentaireTopo.getMessage()==(null))) {
			commentaireTopo.setMessage(commentaireTopo.getMessage());
			commentaireTopo.setAuteur(utilisateur);
			commentaireTopo.setTopo(topo);
			commentaireTopo.setDate(Calendar.getInstance().getTime());
			commentaireTopo.setValidation(false);
			managerFactory.getCommentaireTopoManager().creerCommentaireTopo(commentaireTopo);
			addActionMessage("Votre commentaire a bien été envoyé");
			return ActionSupport.SUCCESS;
		}
		else {
			addActionMessage("Merci de rester courtois !");
			return ActionSupport.INPUT;
		}
	}
	
	public String input() {
		addActionMessage("Merci de rester courtois !");
		return ActionSupport.INPUT;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
		
	}
	public ManagerFactory getManagerFactory() {
		return managerFactory;
	}
	public void setManagerFactory(ManagerFactory managerFactory) {
		this.managerFactory = managerFactory;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getImageId() {
		return imageId;
	}
	public void setImageId(String imageId) {
		this.imageId = imageId;
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
	public ArrayList<String> getListImage() {
		return listImage;
	}
	public void setListImage(ArrayList<String> listImage) {
		this.listImage = listImage;
	}

}
