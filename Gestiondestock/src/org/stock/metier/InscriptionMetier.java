package org.stock.metier;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;

import org.stock.controller.servlets.session.Connexion;
import org.stock.dao.config.ConfigurationDao;
import org.stock.dao.config.DaoFactory;
import org.stock.dao.implementation.PersonneDao;
import org.stock.dao.interfaces.PersonneImpl;
import org.stock.metier.exceptions.InvalidCredinals;
import org.stock.model.Personne;
import org.stock.model.Produit;

public class InscriptionMetier {
	private HttpServletRequest req;
	private HashMap<String, String> erreurs;

	private static final String CHAMP_NOM = "nom";
	private static final String CHAMP_PRENOM = "prenom";
	private static final String CHAMP_DATE = "date";
	private static final String CHAMP_USERNAME = "email";
	private static final String CHAMP_PASSWORD = "password";

	private static final String CHAMP_NOMERROR = "errornom";
	private static final String CHAMP_PRENOMERROR = "errorprenom";
	private static final String CHAMP_DATEERROR = "errordate";
	private static final String CHAMP_PASSWORDERROR = "errorpass";
	private static final String CHAMP_USERNAMEERROR = "erroremail";
	private static final String CHAMP_TOTAL = "error";

	public InscriptionMetier(HttpServletRequest req) {
		super();
		this.req = req;
		erreurs = new HashMap<String, String>();
	}

	public Personne inscrire() {
		String nom = req.getParameter(CHAMP_NOM);
		String prenom = req.getParameter(CHAMP_PRENOM);
		String date = req.getParameter(CHAMP_DATE);
		String email = req.getParameter(CHAMP_USERNAME);
		String password = req.getParameter(CHAMP_PASSWORD);

		try {
			checkNom(nom);
		} catch (Exception e) {
			erreurs.put(CHAMP_NOMERROR, e.getMessage());
		}

		try {
			checkPrenom(prenom);
		} catch (Exception e) {
			erreurs.put(CHAMP_PRENOMERROR, e.getMessage());
		}

		Date dateobject = null;
		try {
			dateobject = convertToDate(date);
		} catch (Exception e) {
			erreurs.put(CHAMP_DATEERROR, e.getMessage());
		}

		try {
			checkEmail(email);
		} catch (Exception e) {
			erreurs.put(CHAMP_USERNAMEERROR, e.getMessage());
		}
		try {
			checkPassword(password);
		} catch (Exception e) {
			erreurs.put(CHAMP_PASSWORDERROR, e.getMessage());
		}
		Personne personne = new Personne(nom, prenom, dateobject, email, password, new ArrayList<Produit>(), "user");
		if (erreurs.size() == 0) {
			DaoFactory dao = (DaoFactory) req.getServletContext().getAttribute(ConfigurationDao.ATT_DAO_FACTORY);
			PersonneImpl personnedao = new PersonneDao(dao);
			personnedao.createPersonne(personne);
			personnedao.closeSession();
			req.getSession().setAttribute(Connexion.ATT_SESSION, personne);
		} else {
			erreurs.put(CHAMP_TOTAL, "Les champs sont invalide");
		}
		return personne;
	}

	private void checkNom(String nom) throws InvalidCredinals {
		if (nom == null || nom.trim().equals("") || nom.trim().length() < 2)
			throw new InvalidCredinals("Le nom doit être au moins 3 caractères");
	}

	private void checkPrenom(String prenom) throws InvalidCredinals {
		if (prenom == null || prenom.trim().equals("") || prenom.trim().length() < 2)
			throw new InvalidCredinals("Le prenom doit être au moins 3 caractères");
	}

	@SuppressWarnings("deprecation")
	private Date convertToDate(String date) throws InvalidCredinals {
		String year = "", month = "", day = "";
		System.out.println(date);
		try {
			year = date.substring(0, 4);
			month = date.substring(5, 7);
			day = date.substring(8, 10);
		} catch (Exception e) {
			throw new InvalidCredinals("Stop hacking");
		}
		Date dateobject = null;
		try {
			dateobject = new Date(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
		} catch (Exception e) {
			throw new InvalidCredinals("Date est invalide");
		}
		return dateobject;
	}

	private void checkEmail(String email) throws InvalidCredinals {
		if (email == null || email.trim().equals(""))
			throw new InvalidCredinals("Veuillez entrer un email valide");
		List<Personne> listes = getListPersonne();
		Stream<Personne> sp = listes.stream();
		List<Personne> listespersonnes = sp.filter(x -> x.getUsername().equals(email)).collect(Collectors.toList());
		if (listespersonnes.size() > 0)
			throw new InvalidCredinals("Email deja existant");
	}

	private void checkPassword(String password) throws InvalidCredinals {
		if (password.length() < 8)
			throw new InvalidCredinals("Le mot de passe doit avoir au moins 8 caractères");
	}

	private List<Personne> getListPersonne() {
		DaoFactory dao = (DaoFactory) req.getServletContext().getAttribute(ConfigurationDao.ATT_DAO_FACTORY);
		PersonneImpl personneimpl = new PersonneDao(dao);
		List<Personne> listes = personneimpl.listPersonnes();
		personneimpl.closeSession();
		return listes;
	}

	public HashMap<String, String> getErreurs() {
		return erreurs;
	}
}
