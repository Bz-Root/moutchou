package org.stock.dao.interfaces;

import java.util.List;

import org.stock.dao.exceptions.NotFoundException;
import org.stock.model.Produit;

public interface ProduitInt {

	
	public List<Produit> listerProduits();
	
	public Produit getProduitById(Long id) throws NotFoundException;
	
	public void createProduit(Produit produit);
	
	public void modifiyProduit(Produit produit);
	
	public void deleteProduit(Produit produit);
	
	public void closeSession();
}
