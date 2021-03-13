package org.stock.dao.implementation;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.stock.dao.config.DaoFactory;
import org.stock.dao.exceptions.NotFoundException;
import org.stock.dao.interfaces.EmplacementImpl;
import org.stock.model.Emplacement;

public class EmplacementDao implements EmplacementImpl{
	
	private Session session;
	public EmplacementDao(DaoFactory dao) {
		session = dao.getFactory().openSession();
	}
	@Override
	public List<Emplacement> listerEmplacement() {
		Transaction transaction = session.beginTransaction();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Emplacement> query = builder.createQuery(Emplacement.class);
		List<Emplacement> listemplacement = session.createQuery(query).getResultList();
		transaction.commit();
		return listemplacement;
		
	}

	@Override
	public Emplacement listerEmplacementById(Long id) throws NotFoundException{
		Transaction transaction = session.beginTransaction();
		Emplacement emplacement = null;
		try {
			emplacement = session.load(Emplacement.class,id);
			transaction.commit();
		}catch (Exception e) {
			throw new NotFoundException("Cannot found data");
		}
		return emplacement;
	}

	@Override
	public void deleteEmplacement(Emplacement emplacement) {
		Transaction transaction = session.beginTransaction();
		session.delete(emplacement);
		transaction.commit();
	}

	@Override
	public void createEmplacement(Emplacement emplacement) {
		Transaction transaction = session.beginTransaction();
		session.save(emplacement);
		transaction.commit();
		
	}

	@Override
	public void updateEmplacement(Emplacement emplacement) {
		Transaction transaction = session.beginTransaction();
		session.update(emplacement);
		transaction.commit();
		
	}
	
	@Override
	public void closeSession() {
		session.close();
	}

}
