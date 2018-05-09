package servlets;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Servlet
 */
@WebServlet("/Servlet")
public class Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		//--je recupere la liste des images du dossier image
		File repertoire = new File("D:\\Documents\\openclassrooms formation\\P6\\P6_escalade_web\\UML_DIAG");
		File[]listDossier = repertoire.listFiles();
		ArrayList<String> nomDossier = new ArrayList<String>();
		ArrayList<String> nomIllus = new ArrayList<String>();
		
		System.out.println(listDossier.length);
		for (File rep : listDossier) {
			nomDossier.add(rep.getName());
			
			File[]listIllus = rep.listFiles();
			System.out.println(listIllus.length);
			for (File f : listIllus) {
				nomIllus.add(f.getName());
			}
			request.setAttribute("image", nomIllus);
			
		}
		System.out.println(nomDossier);
		request.setAttribute("dossier", nomDossier);

		

	//File rep = new File("D:\\Documents\\openclassrooms formation\\P6\\P6_escalade_web\\UML_DIAG\\"+request.getQueryString());
	//ArrayList<String> nomIllus = new ArrayList<String>();
	//File[]listIllus = rep.listFiles();
	//System.out.println(listIllus.length);
		



				
		//--on envoie les infos à la JSP
		this.getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	


	
	
}
