package servlets;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletDiagramme
 */
@WebServlet("/ServletDiagramme")
public class ServletDiagramme extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletDiagramme() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String nomImg = (String) request.getParameter("image");
		System.out.println(nomImg);
		System.out.println(request.getParameter("dossier"));
		String path = "D:\\Documents\\openclassrooms formation\\P6\\P6_escalade_web\\UML_DIAG\\"+(String)request.getParameter("dossier")+"\\"+nomImg;
		request.setAttribute("nomIllus", nomImg);
		request.setAttribute("cheminIllus", path);
		System.out.println(path);

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

		//--on envoie les infos à la JSP
		this.getServletContext().getRequestDispatcher("/WEB-INF/diagramme.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
