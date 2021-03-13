package org.stock.controller.servlets.session;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.stock.metier.InscriptionMetier;
import org.stock.model.Personne;

public class Inscription extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String ATT_LOGINERRORS = "errors";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/WEB-INF/inscription.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		InscriptionMetier inscrire = new InscriptionMetier(req);
		Personne personne = inscrire.inscrire();
		HashMap<String, String> erreurs = inscrire.getErreurs();
		if (erreurs.size() != 0) {
			req.setAttribute(ATT_LOGINERRORS, erreurs);
			req.setAttribute(Connexion.ATT_SESSION, personne);
			this.getServletContext().getRequestDispatcher("/WEB-INF/inscription.jsp").forward(req, resp);
		} else {
			resp.sendRedirect(req.getServletContext().getContextPath() + "/");
		}
	}
}
