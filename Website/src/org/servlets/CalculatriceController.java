package org.servlets;

import java.io.IOException;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.metier.CalculImpl;
import org.metier.CalculImplemente;
import org.model.Calcul;

public class CalculatriceController extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/WEB-INF/calcul.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Im in Post");
		CalculImplemente ci = new CalculImpl(req,resp);
		if(ci.getErreurs().size() == 0) {
			Calcul calcul = ci.getCalcul();
			req.setAttribute("calcul", calcul);
		}else {
			req.setAttribute("erreurs", ci.getErreurs());
		}
		this.getServletContext().getRequestDispatcher("/WEB-INF/calcul.jsp").forward(req, resp);
	}
}
