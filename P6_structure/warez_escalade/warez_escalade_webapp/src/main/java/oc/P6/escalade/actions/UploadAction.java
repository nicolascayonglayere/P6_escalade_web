package oc.P6.escalade.actions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.CopyOption;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import oc.P6.escalade.WebappHelper.GestionFichierProperties;
import oc.P6.escalade.model.bean.topo.Topo;

public class UploadAction extends ActionSupport implements SessionAware, ServletRequestAware{
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

	private HttpServletRequest request;

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
		byte[] bytesRead;
		String nomImg="";
		String nomDuTopo = "";
		String chemin = gfp.lireProp().getProperty("chemin.upload");
		if(((Topo)session.get("topo")) != null)
			nomDuTopo = ((Topo)session.get("topo")).getNomTopo().replaceAll("\\p{Space}", "");
		else
			nomDuTopo = ((String)session.get("topoModif"));
		System.out.println(nomDuTopo);
		Path cheminUpload = Paths.get(chemin);
				//"D:\\Documents\\openclassrooms formation\\P6\\P6_escalade_web\\P6_structure\\warez_escalade\\warez_escalade_webapp\\src\\main\\webapp\\assets\\images");

		System.out.println("\n\n upload1");
		System.out.println("files:");
		//--Renommer la premiere image : imageCouv.JPG
		String nomImg1 = "imageCouv.JPG";
		uploadFileName.get(0).replace(uploadFileName.get(0), nomImg1);
		
		for (File u : upload) {
			nomImg = uploadFileName.get(upload.indexOf(u));
			gfp.uploadImg(Paths.get(u.getAbsolutePath()), Paths.get(chemin+"\\"+nomDuTopo, nomImg));
		//try {
		//	System.out.println("*** " + u + "\t" + u.length());
		//	
		//	Path stockImg = Paths.get(chemin+nomDuTopo, nomImg);
		//	System.out.println(nomImg+" - "+stockImg.toString());
		//	Path uPath = Paths.get(u.getAbsolutePath());
		//	System.out.println(uPath.toString());
		//	Files.copy(uPath, stockImg, StandardCopyOption.REPLACE_EXISTING);
		//}catch(FileNotFoundException e) {
		//	e.printStackTrace();
		//}catch (IOException e) {
		//	e.printStackTrace();
		//	addActionMessage("Erreur lors de l'upload. Veuillez recommencer.");
		//	return ActionSupport.INPUT;
		//}
		}
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
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		
	}
	public String getNomTopo() {
		return nomTopo;
	}
	public void setNomTopo(String nomTopo) {
		this.nomTopo = nomTopo;
	}

}
