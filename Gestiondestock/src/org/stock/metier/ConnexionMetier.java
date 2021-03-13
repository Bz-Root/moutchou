package org.stock.metier;

import java.security.NoSuchAlgorithmException;
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
import org.stock.metier.algorithme.EncoderAlgo;
import org.stock.model.Personne;

public class ConnexionMetier {

	private HttpServletRequest req;
	private HashMap<String, String> erreurs;

	private static final String CHAMP_USERNAME = "email";
	private static final String CHAMP_PASSWORD = "password";

	private static final String CHAMP_PASSWORDERROR = "errorpass";
	private static final String CHAMP_USERNAMEERROR = "erroremail";
	private static final String CHAMP_TOTAL = "error";

	public ConnexionMetier(HttpServletRequest req) {
		super();
		this.req = req;
		erreurs = new HashMap<String, String>();
	}

	public Personne seConnecter() {

		String email = req.getParameter(CHAMP_USERNAME);
		String password = req.getParameter(CHAMP_PASSWORD);
		Personne personnestranger = new Personne();
		personnestranger.setUsername(email);
		personnestranger.setPassword(password);
		try {
			verifierEmail(email);
		} catch (Exception e) {
			erreurs.put(CHAMP_USERNAMEERROR, "Email invalide");
		}

		try {
			verifierMotdepasse(password);
		} catch (Exception e) {
			erreurs.put(CHAMP_PASSWORDERROR, "Password invalide");
		}

		if (erreurs.size() == 0) {
			List<Personne> listes = getListPersonne();
			try {
				final String encryptpass = EncoderAlgo.encodeMD5(password);
				Stream<Personne> sp = listes.stream();
				List<Personne> listespersonnes = sp
						.filter(x -> x.getUsername().equals(email) && x.getPassword().equals(encryptpass))
						.collect(Collectors.toList());
				
				if (listespersonnes.size() == 0) {
					erreurs.put(CHAMP_TOTAL, "Mot de passe ou adresse sont incorrecte");
				} else {
					Personne personne = listespersonnes.get(0);
					req.getSession().setAttribute(Connexion.ATT_SESSION, personne);
					return personne;	
				}
			} catch (NoSuchAlgorithmException e) {
				// ignorer
			}

		}
		return personnestranger;
	}

	private void verifierEmail(String email) throws Exception {

	}

	private void verifierMotdepasse(String pwd) throws Exception {

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
