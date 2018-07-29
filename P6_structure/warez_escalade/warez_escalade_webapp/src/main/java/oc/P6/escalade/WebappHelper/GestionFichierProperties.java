package oc.P6.escalade.WebappHelper;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.opensymphony.xwork2.ActionSupport;





/**
 * Classe g�rant le fichier properties
 * @author nicolas
 *
 */
public class GestionFichierProperties {
	static final Logger logger = LogManager.getLogger();
	
	private File configFile;
	private Properties prop;

	private ObjectInputStream ois;

	/**
	 * Constructeur sans parametre
	 */
	public GestionFichierProperties(){
		this.configFile = new File(
				"D:\\Documents\\openclassrooms formation\\P6\\P6_escalade_web\\P6_structure\\warez_escalade\\warez_escalade_technical\\src\\data\\conf\\configMail.properties");
		this.prop = new Properties();
	}

	
	public Properties lireProp() {
		//--Si le fichier existe, on verifie s'il n'est pas vide auquel cas on le lit et on cree le contenu de notre liste de propritété
		try {
			logger.debug(configFile.exists());
			if (configFile.exists()) {
				if (configFile.length() !=0) {
					InputStream ois = new FileInputStream(configFile);
					this.prop.load(ois);
					//System.out.println("list proprietes recup sur le fichier lors de lecture : \n"+this.prop.toString());//Controle
					logger.debug("list proprietes recup sur le fichier lors de lecture : \n"+this.prop.toString());
					ois.close();
				}
			}
			//--Sinon, on recupere les prop par defaut
		//else {
		//	ois = new ObjectInputStream(
		//			new BufferedInputStream(
		//					new FileInputStream(defautConfigFile)));
		//	this.prop.load(ois);
		//	System.out.println("list proprietes recup sur le fichier lors de lecture : \n"+this.prop.toString());//Controle
		//	//logger.info("list proprietes recup sur le fichier lors de lecture : \n"+this.prop.toString());
		//	ois.close();
		//}
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
		return this.prop;
	}
	
	public void supprimerImg(Path pPath) {
	       try (DirectoryStream<Path> stream = Files.newDirectoryStream(pPath)) {
	           for (Path entry : stream) {
	               logger.debug(entry);
	               Files.deleteIfExists(entry);
	           }
	           Files.delete(pPath);
	       } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void uploadImg(Path pPathOrigin, Path pPathCible) {
		try {
			Files.copy(pPathOrigin, pPathCible, StandardCopyOption.COPY_ATTRIBUTES);
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void creerDossierImg(Path pPath) {
		try {
			Files.createDirectories(pPath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
