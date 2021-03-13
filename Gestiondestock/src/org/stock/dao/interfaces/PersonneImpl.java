package org.stock.dao.interfaces;

import java.util.List;

import org.stock.dao.exceptions.NotFoundException;
import org.stock.model.Personne;

public interface PersonneImpl {
	
	public List<Personne> listPersonnes();
	public Personne getPersonneById(Long id) throws NotFoundException;
	
	public void createPersonne(Personne personne);
	public void updatePersonne(Personne personne);
	
	public void deletePersonne(Personne personne);
	
	public void closeSession();

}
