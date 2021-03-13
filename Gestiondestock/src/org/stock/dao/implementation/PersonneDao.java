package org.stock.dao.implementation;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.stock.dao.config.DaoFactory;
import org.stock.dao.exceptions.NotFoundException;
import org.stock.dao.interfaces.PersonneImpl;
import org.stock.model.Personne;

public class PersonneDao implements PersonneImpl{
	
	private Session session;
	public PersonneDao(DaoFactory dao) {
		session = dao.getFactory().openSession();	
	}
	
	@Override
	public List<Personne> listPersonnes() {
		Transaction transaction = session.beginTransaction();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Personne> query = builder.createQuery(Personne.class);
		query.from(Personne.class);
		List<Personne> listes = session.createQuery(query).getResultList();
		transaction.commit();
		return listes;
	}

	@Override
	public Personne getPersonneById(Long id) throws NotFoundException {
		Transaction transaction = session.beginTransaction();
		Personne personne = null;
		try {
			personne = session.load(Personne.class,id);
			transaction.commit();
		}catch (Exception e) {
			throw new NotFoundException("Cannot found data");
		}
		return personne;
	}

	@Override
	public void createPersonne(Personne personne) {
		Transaction transaction = session.beginTransaction();
		session.save(personne);
		transaction.commit();
	}

	@Override
	public void updatePersonne(Personne personne) {
		Transaction transaction = session.beginTransaction();
		session.update(personne);
		transaction.commit();
		
	}

	@Override
	public void deletePersonne(Personne personne) {
		Transaction transaction = session.beginTransaction();
		session.delete(personne);
		transaction.commit();
		
	}

	@Override
	public void closeSession() {
		session.close();
	}

}
