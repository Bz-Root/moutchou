package org.stock.metier;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.stock.model.Emplacement;
import org.stock.model.Produit;

public class ConsultationMetier {
	private HttpServletRequest req;
	private HttpServletResponse resp;

	private final static String PATHCONSULTATION = "/WEB-INF/consultation.jsp";
	private final static String PATHEMPLACEMENT = "/WEB-INF/emplacement.jsp";

	private final static String PARAMACTION = "action";
	private final static String CONSULTATIONACTION = "emplacment";

	private static final String ATT_PRODUITS = "produits";
	private static final String ATT_PRODUIT = "produit";
	private static final String ATT_EMPLACEMENTS = "emplacements";

	public ConsultationMetier(HttpServletRequest req, HttpServletResponse resp) {
		super();
		this.req = req;
		this.resp = resp;
	}

	public void consulter() {
		String action = (String) req.getParameter(PARAMACTION);
		ConsultationRedirect cr = new ConsultationRedirect(req, resp);
		if (action != null && action.equals(CONSULTATIONACTION)) {
			try {
				HashMap<Produit, List<Emplacement>> listes = cr.getEmplacements();
				List<Emplacement> emplacements = null;
				Produit produit = null;
				for (Produit sproduit : listes.keySet()) {
					produit = sproduit;
					emplacements = listes.get(produit);
				}
				req.setAttribute(ATT_PRODUIT, produit);
				req.setAttribute(ATT_EMPLACEMENTS, emplacements);
				try {
					req.getServletContext().getRequestDispatcher(PATHEMPLACEMENT).forward(req, resp);
				} catch (ServletException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		} else {
			try {
				req.setAttribute(ATT_PRODUITS, cr.getProduits());
				req.getServletContext().getRequestDispatcher(PATHCONSULTATION).forward(req, resp);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
