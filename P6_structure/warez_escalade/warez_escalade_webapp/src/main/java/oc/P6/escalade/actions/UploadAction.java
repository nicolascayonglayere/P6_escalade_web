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
		byte[] bytesRead;
		String nomImg="";
		String nomTopo = ((Topo)session.get("topo")).getNomTopo().replaceAll("\\p{Space}", "");
		Path cheminUpload = Paths.get("D:\\Documents\\openclassrooms formation\\P6\\P6_escalade_web\\P6_structure\\warez_escalade\\warez_escalade_webapp\\src\\main\\webapp\\assets\\images");

		System.out.println("\n\n upload1");
		System.out.println("files:");
		for (File u : upload) {
			try {
				System.out.println("*** " + u + "\t" + u.length());
				nomImg = uploadFileName.get(upload.indexOf(u));
				Path stockImg = Paths.get("D:\\Documents\\openclassrooms formation\\P6\\P6_escalade_web\\P6_structure\\warez_escalade\\warez_escalade_webapp\\src\\main\\webapp\\assets\\images\\"
						+nomTopo, nomImg);
				System.out.println(nomImg+" - "+stockImg.toString());
				Path uPath = Paths.get(u.getAbsolutePath());
				System.out.println(uPath.toString());
				Files.copy(uPath, stockImg, StandardCopyOption.COPY_ATTRIBUTES);
			}catch(FileNotFoundException e) {
				e.printStackTrace();
			}catch (IOException e) {
				e.printStackTrace();
				addActionMessage("Erreur lors de l'upload. Veuillez recommencer.");
				return ActionSupport.INPUT;
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
	
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

}
