package oc.P6.escalade.actions.images;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.File;
import java.io.FileInputStream;

import javax.imageio.ImageIO;
import javax.inject.Named;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.opensymphony.xwork2.ActionSupport;

import oc.P6.escalade.WebappHelper.GestionFichierProperties;

/**
 * Méthode qui permet l'affichage dynamique d'image
 * @author nicolas
 *
 */
@Named
public class ImageAction extends ActionSupport {

	/**
	 * 
	 */
	static final Logger logger = LogManager.getLogger();
	private static final long serialVersionUID = 1L;
	byte[] imageInByte = null;
	String imageId;

	public ImageAction() {
		logger.debug("constructeur ImageAction");
	}

	public String execute() {
		return SUCCESS;
	}

	public byte[] getCustomImageInBytes() {
		logger.debug("imageId " + imageId);
		BufferedImage originalImage;
		try {
			InputStream is = new FileInputStream(getImageFile(this.imageId)) ;
			originalImage = ImageIO.read(is);
			// convert BufferedImage to byte array
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(originalImage, "JPG", baos);
			baos.flush();
			imageInByte = baos.toByteArray();
			baos.close();
		} catch (IOException e) {
			logger.debug(e.getMessage());
			//e.printStackTrace();
		}
		logger.debug("trace "+imageInByte.toString());
		return imageInByte;
	}

	private File getImageFile(String imageId) {
		GestionFichierProperties gfp = new GestionFichierProperties();
		String filePath = gfp.lireProp().getProperty("chemin.upload"); 
		File file = new File(filePath, imageId);
		logger.debug(file.toString());
		return file;
	}

	public String getCustomContentType() {
		return "image/JPG";
	}

	public String getCustomContentDisposition() {
		return "anyname.JPG";
	}
	public String getImageId() {
		return imageId;
	}

	public void setImageId(String imageId) {
		this.imageId = imageId;
	}
}

