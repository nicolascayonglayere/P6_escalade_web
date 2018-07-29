package oc.P6.escalade.actions;

import java.io.IOException;
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
import oc.P6.escalade.model.bean.topo.Topo;

/**
 * Classe action qui permet le bon affichage de la jsp index.jsp
 * @author nicolas
 *
 */
@Named
public class AccueilAction extends ActionSupport{

	/**
	 * 
	 */
	static final Logger logger = LogManager.getLogger();
	private static final long serialVersionUID = 1L;
	@Inject
	private ManagerFactory managerFactory;
	private ArrayList<Topo> listTopo;
	private String imageId;
	
	/**
	 * Méthode qui construit et envoie les données nécessaires à la jsp
	 */
	public String execute() {
		GestionFichierProperties gfp = new GestionFichierProperties();
		listTopo = managerFactory.getTopoManager().getListTopo();

		String img;
		for (Topo t : listTopo) {
			logger.debug("image : "+t.getImage());
			Path chemin = Paths.get(gfp.lireProp().getProperty("chemin.upload"), t.getImage());
			img = "";
			try (DirectoryStream<Path> stream = Files.newDirectoryStream(chemin)){ 
      	      Iterator<Path> iterator = stream.iterator();
      	      while(iterator.hasNext()) {
      	        Path p = iterator.next();
      	        logger.debug(p.getFileName().toString());
      	        if(p.getFileName().toString().equals("imageCouv.JPG")) {
      	        	img = t.getImage()+"\\"+p.getFileName().toString();
      	        	logger.debug("accueil "+img);
      	        	t.setImage(img);
      	        }
      	      }
      	    } catch (IOException e) {
      	    	logger.debug(e.getMessage());
				//e.printStackTrace();
			}
		}
		return ActionSupport.SUCCESS;
	}
	
	//--Getter et Setter--//
	public ManagerFactory getManagerFactory() {
		return managerFactory;
	}
	public void setManagerFactory(ManagerFactory managerFactory) {
		this.managerFactory = managerFactory;
	}
	public ArrayList<Topo> getListTopo() {
		return listTopo;
	}
	public void setListTopo(ArrayList<Topo> listTopo) {
		this.listTopo = listTopo;
	}

	public String getImageId() {
		return imageId;
	}

	public void setImageId(String imageId) {
		this.imageId = imageId;
	}


}
