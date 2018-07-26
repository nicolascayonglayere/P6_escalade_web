package oc.P6.escalade.actions;

import java.io.File;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.nio.file.Files.walk;
import static java.nio.file.Paths.get;
import static java.util.stream.Collectors.toList;

import javax.inject.Named;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;

import com.opensymphony.xwork2.ActionSupport;

import oc.P6.escalade.WebappHelper.GestionFichierProperties;
import oc.P6.escalade.model.bean.topo.Topo;

@Named
@Scope("Protoype")
public class UploadAction extends ActionSupport implements SessionAware {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private ArrayList<File> upload = new ArrayList<File>();
	private ArrayList<String> uploadFileName = new ArrayList<String>();
	private ArrayList<String> uploadContentType = new ArrayList<String>();
	private String nomTopo;
	private Map<String, Object> session;
	private GestionFichierProperties gfp = new GestionFichierProperties();

	public ArrayList<File> getUpload() {
		return this.upload;
	}
	public void setUpload(ArrayList<File> upload) {
		this.upload = upload;
	}
	public ArrayList<String> getUploadFileName() {
		return this.uploadFileName;
	}
	public void setUploadFileName(ArrayList<String> uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	public ArrayList<String> getUploadContentType() {
		return this.uploadContentType;
	}
	public void setUploadContentType(ArrayList<String> contentType) {
		this.uploadContentType = contentType;
	}
	@Override
	public String execute() throws Exception {
		String nomImg="";
		String nomDuTopo = "";
		String chemin = gfp.lireProp().getProperty("chemin.upload");
		if(((Topo)session.get("topo")) != null)
			nomDuTopo = ((Topo)session.get("topo")).getNomTopo().replaceAll("\\p{Space}", "");
		else
			nomDuTopo = ((String)session.get("topoModif"));
		System.out.println(nomDuTopo);
		System.out.println("\n\n upload1");
		System.out.println("files:");
		
		for (File u : upload) {
			nomImg = uploadFileName.get(upload.indexOf(u));
			gfp.uploadImg(Paths.get(u.getAbsolutePath()), Paths.get(chemin+"\\"+nomDuTopo, nomImg));

		}
		//--Renommer la premiere image : imageCouv.JPG

		 Path homePath = Paths.get(chemin+"\\"+nomDuTopo);
		 ArrayList<Path> paths = new ArrayList<Path>();
		 try (DirectoryStream<Path> stream = Files.newDirectoryStream(homePath)) {
		     for (Path entry : stream) {
		  	   paths.add(entry);
		     }
		 }    
		// = walk(get(chemin+"\\"+nomDuTopo)).collect(toList());
		
		
		Path cheminRenomme = Paths.get(chemin+"\\"+nomDuTopo, "imageCouv.JPG");
		Files.move(paths.get(0), cheminRenomme, StandardCopyOption.REPLACE_EXISTING);

		
		System.out.println("filenames:");
		for (String n : uploadFileName) {
			System.out.println("*** " + n);
		}
		System.out.println("content types:");
		for (String c : uploadContentType) {
			System.out.println("*** " + c);
		}
		System.out.println("\n\n");
 	   return ActionSupport.SUCCESS;
	}
	
	public String input() {
		System.out.println(nomTopo);
		if(((Topo)session.get("topo")) == null)
			this.session.put("topoModif", nomTopo.replaceAll("\\p{Space}", ""));
		return ActionSupport.SUCCESS;
	}
	
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public String getNomTopo() {
		return nomTopo;
	}
	public void setNomTopo(String nomTopo) {
		this.nomTopo = nomTopo;
	}

}
