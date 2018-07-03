package oc.P6.escalade.actions.images;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.File;
import java.io.FileInputStream;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;

public class ImageAction extends ActionSupport implements ServletRequestAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	byte[] imageInByte = null;
	String imageId;
	
	private HttpServletRequest servletRequest;

	public String getImageId() {
		return imageId;
	}

	public void setImageId(String imageId) {
		this.imageId = imageId;
	}

	public ImageAction() {
		//System.out.println("constructeur ImageAction");
	}

	public String execute() {
		return SUCCESS;
	}

	public byte[] getCustomImageInBytes() {
		System.out.println("imageId " + imageId);
		//File imgFile = new File("D:\\Documents\\openclassrooms formation\\P6\\P6_escalade_web\\P6_structure\\warez_escalade\\warez_escalade_webapp\\src\\main\\webapp\\assets\\images\\"+imageId);
		BufferedImage originalImage;
		try {
			InputStream is = new FileInputStream(getImageFile(this.imageId)) ;
			originalImage = ImageIO.read(is);
					//.read(getImageFile(this.imageId));
			// convert BufferedImage to byte array
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(originalImage, "JPG", baos);
			baos.flush();
			imageInByte = baos.toByteArray();
			baos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println("trace "+imageInByte.toString());
		return imageInByte;
	}

	private File getImageFile(String imageId) {
		//String filePath = servletRequest.getSession().getServletContext().getRealPath("/");
		String filePath = "D:\\Documents\\openclassrooms formation\\P6\\P6_escalade_web\\P6_structure\\warez_escalade\\warez_escalade_webapp\\src\\main\\webapp\\assets\\images";
		//System.out.println(filePath);
		File file = new File(filePath, imageId);
		//System.out.println(file.toString());
		return file;
	}

	public String getCustomContentType() {
		return "image/JPG";
	}

	public String getCustomContentDisposition() {
		return "anyname.JPG";
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.servletRequest = request;
		
	}

}

