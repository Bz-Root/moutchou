package org.stock.controller.servlets.session;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Deconnexion extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getSession().invalidate();//J'ai deja fait dans SessionFilter un filter sur 
		// /* pour filtrer tous les requêtes venantes , pas la peine de refaire ici
		resp.sendRedirect(req.getServletContext().getContextPath() + "/");
	}

}
