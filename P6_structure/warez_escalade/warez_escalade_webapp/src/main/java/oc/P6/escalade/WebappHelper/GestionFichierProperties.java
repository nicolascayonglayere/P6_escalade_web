package oc.P6.escalade.WebappHelper;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.Properties;





/**
 * Classe g�rant le fichier properties
 * @author nicolas
 *
 */
public class GestionFichierProperties {
	//static Logger logger = Logger.getLogger("ihm");
	
	private File configFile;
	private File defautConfigFile;
	private Properties prop;

	private ObjectInputStream ois;
	
	/**
	 * Constructeur sans parametre
	 */
	public GestionFichierProperties(){
		this.configFile = new File(
				"D:\\Documents\\openclassrooms formation\\P6\\P6_escalade_web\\P6_structure\\warez_escalade\\warez_escalade_technical\\src\\data\\conf\\configMail.properties");
		//this.defautConfigFile = new File("Ressources/Fichiers/defaut_config.properties");
		this.prop = new Properties();
	}

	
	public Properties lireProp() {
		//--Si le fichier existe, on verifie s'il n'est pas vide auquel cas on le lit et on cree le contenu de notre liste de propritété
		try {
			System.out.println(configFile.exists());
			if (configFile.exists()) {
				if (configFile.length() !=0) {
					InputStream ois = new FileInputStream(configFile);
					this.prop.load(ois);
					System.out.println("list proprietes recup sur le fichier lors de lecture : \n"+this.prop.toString());//Controle
					//logger.info("list proprietes recup sur le fichier lors de lecture : \n"+this.prop.toString());
					ois.close();
				}
			}
			//--Sinon, on recupere les prop par defaut
			else {
				ois = new ObjectInputStream(
						new BufferedInputStream(
								new FileInputStream(defautConfigFile)));
				this.prop.load(ois);
				System.out.println("list proprietes recup sur le fichier lors de lecture : \n"+this.prop.toString());//Controle
				//logger.info("list proprietes recup sur le fichier lors de lecture : \n"+this.prop.toString());
				ois.close();
			}
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
		return this.prop;
	}
}
