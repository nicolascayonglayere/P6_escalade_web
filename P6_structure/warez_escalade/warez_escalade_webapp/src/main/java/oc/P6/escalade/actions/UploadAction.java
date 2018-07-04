package oc.P6.escalade.actions;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import oc.P6.escalade.model.bean.topo.Topo;

public class UploadAction extends ActionSupport implements SessionAware{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ArrayList<File> upload = new ArrayList<File>();
	private ArrayList<String> uploadFileName = new ArrayList<String>();
	private ArrayList<String> uploadContentType = new ArrayList<String>();

	private Map<String, Object> session;

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
		String nomTopo = ((Topo)session.get("topo")).getNomTopo().replaceAll("\\p{Space}", "");
		Path cheminUpload = Paths.get("D:\\Documents\\openclassrooms formation\\P6\\P6_escalade_web\\P6_structure\\warez_escalade\\warez_escalade_webapp\\src\\main\\webapp\\assets\\images");
	    Path stockImg = Paths.get("D:\\Documents\\openclassrooms formation\\P6\\P6_escalade_web\\P6_structure\\warez_escalade\\warez_escalade_webapp\\src\\main\\webapp\\assets\\images\\"
	    							+nomTopo+"\\"+nomImg);
		System.out.println("\n\n upload1");
		System.out.println("files:");
		for (File u : upload) {
			System.out.println("*** " + u + "\t" + u.length());
			try (DirectoryStream<Path> stream = Files.newDirectoryStream(cheminUpload)) {
				   nomImg = u.getName();
            	   Files.move(Paths.get(u.getAbsolutePath()), stockImg);
		       }catch (FileNotFoundException e) {
					e.printStackTrace();
				}catch (IOException e) {
					e.printStackTrace();
				} 
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
	
	public String deplacer() {
		String nomTopo = ((Topo)session.get("topo")).getNomTopo().replaceAll("\\p{Space}", "");
		Path cheminUpload = Paths.get("D:\\Documents\\openclassrooms formation\\P6\\P6_escalade_web\\P6_structure\\warez_escalade\\warez_escalade_webapp\\src\\main\\webapp\\assets\\images");
	    Path stockImg = Paths.get("D:\\Documents\\openclassrooms formation\\P6\\P6_escalade_web\\P6_structure\\warez_escalade\\warez_escalade_webapp\\src\\main\\webapp\\assets\\images\\"
	    							+nomTopo);
			try (DirectoryStream<Path> stream = Files.newDirectoryStream(cheminUpload)) {
	           for (Path entry : stream) {
	               System.out.println(entry);
	               if (!Files.isDirectory(entry))
	            	   Files.move(entry, stockImg);
	           }
	       }catch (FileNotFoundException e) {
				e.printStackTrace();
			}catch (IOException e) {
				e.printStackTrace();
			}    
		
		return ActionSupport.SUCCESS;
		
	}
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

}
