package org.stock.dao.interfaces;

import java.util.List;

import org.stock.dao.exceptions.NotFoundException;
import org.stock.model.Emplacement;

public interface EmplacementImpl {
	//CRUD methodes
	public List<Emplacement> listerEmplacement();
	public Emplacement listerEmplacementById(Long id) throws NotFoundException;
	public void deleteEmplacement(Emplacement emplacement);
	public void createEmplacement(Emplacement emplacement);
	public void updateEmplacement(Emplacement emplacement);
	
	public void closeSession();

}
