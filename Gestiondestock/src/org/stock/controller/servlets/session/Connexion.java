package org.stock.controller.servlets.session;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.stock.metier.ConnexionMetier;
import org.stock.model.Personne;

public class Connexion extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String ATT_LOGINERRORS = "errors";
	public static final String ATT_SESSION = "personne";
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/WEB-INF/connexion.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ConnexionMetier cm = new ConnexionMetier(req);
		Personne personne = cm.seConnecter();	
		HashMap<String,String> erreurs = cm.getErreurs();
		req.setAttribute(ATT_SESSION, personne);
		if(erreurs.size() == 0) {
			resp.sendRedirect(req.getServletContext().getContextPath() + "/consultation");
		}else {
			req.setAttribute(ATT_LOGINERRORS, erreurs);
			System.out.println("[ - ] - Erreurs pour " + req.getLocalAddr());
			this.getServletContext().getRequestDispatcher("/WEB-INF/connexion.jsp").forward(req, resp);
		}
	}
}
