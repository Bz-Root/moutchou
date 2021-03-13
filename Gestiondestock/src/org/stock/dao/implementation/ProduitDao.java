package org.stock.dao.implementation;

import java.util.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.stock.dao.config.DaoFactory;
import org.stock.dao.exceptions.NotFoundException;
import org.stock.dao.interfaces.ProduitInt;
import org.stock.model.Produit;


public class ProduitDao implements ProduitInt{
	
	private Session session;
	public ProduitDao(DaoFactory dao) {
		session = dao.getFactory().openSession();	
	}
	@Override
	public List<Produit> listerProduits() {
		Transaction transaction = session.beginTransaction();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Produit> query = builder.createQuery(Produit.class);
		query.from(Produit.class);
		List<Produit> listes = session.createQuery(query).getResultList();
		transaction.commit();
		return listes;
	}

	@Override
	public Produit getProduitById(Long id) throws NotFoundException{
		Transaction transaction = session.beginTransaction();
		Produit produit = null;
		try {
			System.out.println(id);
			produit = session.load(Produit.class,id);
			transaction.commit();
		}catch (Exception e) {
			throw new NotFoundException("Cannot found data");
		}
		return produit;
	}

	@Override
	public void createProduit(Produit produit) {
		Transaction transaction = session.beginTransaction();
		session.save(produit);
		transaction.commit();
	}

	@Override
	public void modifiyProduit(Produit produit) {
		Transaction transaction = session.beginTransaction();
		session.update(produit);
		transaction.commit();
	}

	@Override
	public void deleteProduit(Produit produit) {
		Transaction transaction = session.beginTransaction();
		session.delete(produit);
		transaction.commit();
	}
	
	@Override
	public void closeSession() {
		session.close();
	}

}
