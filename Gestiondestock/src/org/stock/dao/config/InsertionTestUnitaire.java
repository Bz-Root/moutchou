package org.stock.dao.config;

import java.util.Date;

import org.stock.dao.implementation.EmplacementDao;
import org.stock.dao.implementation.PersonneDao;
import org.stock.dao.implementation.ProduitDao;
import org.stock.model.Emplacement;
import org.stock.model.Personne;
import org.stock.model.Produit;

public class InsertionTestUnitaire {
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		DaoFactory dao = new DaoFactory();		
		
		//Creating product 
		ProduitDao produitdao = new ProduitDao(dao);
		Produit produit1 = new Produit();
		produit1.setNomproduit("Chips");
		produit1.setPrix(1d);
		
		Produit produit2 = new Produit();
		produit2.setNomproduit("Kora");
		produit2.setPrix(60d);
		
		produitdao.createProduit(produit1);
		produitdao.createProduit(produit2);
		
		produitdao.closeSession();
		
		//Creating emplacement 
		
		EmplacementDao emplacementdao = new EmplacementDao(dao);
		Emplacement empl1 = new Emplacement();
		empl1.setNom("Emplacement1");
		Emplacement empl2 = new Emplacement();
		empl2.setNom("Emplacement2");
		
		empl1.getListesproduits().add(produit1);
		empl1.getListesproduits().add(produit2);
		
		empl2.getListesproduits().add(produit1);
		
		
		emplacementdao.createEmplacement(empl1);
		emplacementdao.createEmplacement(empl2);
		emplacementdao.closeSession();
		
		//Getting my account
		PersonneDao personnedao = new PersonneDao(dao);
		Personne personne = new Personne();
		personne.setNom("Achraf");
		personne.setPrenom("hamad");
		personne.setDate(new Date(2020,01,02));
		personne.setPassword("achraf123");
		personne.setUsername("ziad@ziad.com");
		
		
		personne.getListesdeproduit().add(produit1);
		personne.getListesdeproduit().add(produit2);
		personnedao.createPersonne(personne);
		
		Personne personne2 = new Personne();
		personne2.setNom("Ziad");
		personne2.setPrenom("Prenom");
		personne2.setRole("admin");
		personne2.setUsername("admin@localhost.com");
		personne2.setPassword("123456789");
		personne2.setDate(new Date(2002,06,02));
		
		
		personnedao.createPersonne(personne2);
		personnedao.closeSession();
			
	}
}
