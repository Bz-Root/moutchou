package org.stock.metier;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.stock.controller.servlets.session.Connexion;
import org.stock.dao.config.ConfigurationDao;
import org.stock.dao.config.DaoFactory;
import org.stock.dao.exceptions.NotFoundException;
import org.stock.dao.implementation.PersonneDao;
import org.stock.dao.implementation.ProduitDao;
import org.stock.dao.interfaces.ProduitInt;
import org.stock.metier.exceptions.InvalidCredinals;
import org.stock.model.Emplacement;
import org.stock.model.Personne;
import org.stock.model.Produit;

public class ConsultationRedirect {

	private HttpServletRequest req;
	private HttpServletResponse resp;
	private DaoFactory dao;

	private final static String ATT_ID = "id";

	public ConsultationRedirect(HttpServletRequest req, HttpServletResponse resp) {
		this.req = req;
		this.resp = resp;
		dao = (DaoFactory) req.getServletContext().getAttribute(ConfigurationDao.ATT_DAO_FACTORY);
	}

	public List<Produit> getProduits() {
		Personne personne = (Personne) req.getSession().getAttribute(Connexion.ATT_SESSION);
		PersonneDao personnedao = new PersonneDao(
				(DaoFactory) req.getServletContext().getAttribute(ConfigurationDao.ATT_DAO_FACTORY));
		try {
			personne = personnedao.getPersonneById(personne.getId());
			return personne.getListesdeproduit();
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public HashMap<Produit, List<Emplacement>> getEmplacements() throws InvalidCredinals {
		String idstring = req.getParameter(ATT_ID);
		int id = 0;
		try {
			id = Integer.parseInt(idstring);
		} catch (Exception e) {
			System.out.println("redirect");
			try {
				resp.getWriter().println(
						"Nice try don 't execute SQL Injection , I added your ip in the log -> " + req.getLocalAddr());
			} catch (IOException e1) {
				System.out.println("cannot");
				e1.printStackTrace();
			}
			throw new InvalidCredinals();
		}
		ProduitInt produitdao = new ProduitDao(dao);
		Produit produit = null;
		try {
			produit = produitdao.getProduitById((long) id);
			List<Emplacement> emplacement = produit.getEmplacements();
			HashMap<Produit, List<Emplacement>> listes = new HashMap<Produit, List<Emplacement>>();
			listes.put(produit, emplacement);
			return listes;
		} catch (NotFoundException e) {
			e.printStackTrace();
			try {
				resp.getWriter().println("Nice try to change id value");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return null;

	}
}
