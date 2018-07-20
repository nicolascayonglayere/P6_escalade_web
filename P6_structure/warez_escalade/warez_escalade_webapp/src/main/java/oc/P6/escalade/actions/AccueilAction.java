package oc.P6.escalade.actions;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;

import javax.inject.Inject;

import com.opensymphony.xwork2.ActionSupport;

import oc.P6.escalade.business.contract.ManagerFactory;
import oc.P6.escalade.model.bean.topo.Topo;

public class AccueilAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private ManagerFactory managerFactory;
	private ArrayList<Topo> listTopo;
	private String imageId;
	
	public String execute() {
		listTopo = managerFactory.getTopoManager().getListTopo();
		//Path chemin;
		String img;
		for (Topo t : listTopo) {
			System.out.println("image : "+t.getImage());
			Path chemin = Paths.get("D:\\Documents\\openclassrooms formation\\P6\\P6_escalade_web\\P6_structure\\warez_escalade\\warez_escalade_webapp\\src\\main\\webapp\\assets\\images\\", t.getImage());
			img = "";
			try (DirectoryStream<Path> stream = Files.newDirectoryStream(chemin)){ 
      	      Iterator<Path> iterator = stream.iterator();
      	      while(iterator.hasNext()) {
      	        Path p = iterator.next();
      	        System.out.println(p.getFileName().toString());
      	        if(p.getFileName().toString().equals("imageCouv.JPG"))
      	        	//imageId = t.getImage()+"\\"+p.getFileName().toString();
      	        	img = t.getImage()+"\\"+p.getFileName().toString();
      	        	t.setImage(img);
      	      }
      	    } catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return ActionSupport.SUCCESS;
	}
	
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
