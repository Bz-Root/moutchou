package org.stock.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
@Entity
@Table(name="Emplacements")
public class Emplacement implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="Nom")
	private String nom;
	
	@ManyToMany
	private List<Produit> listesproduits;

	public Emplacement(int id, String nom, List<Produit> listesproduits) {
		super();
		this.id = id;
		this.nom = nom;
		this.listesproduits = listesproduits;
	}
	
	public Emplacement() {
		
	}
	
	public Emplacement(Emplacement e) {
		this.id = e.id;
		nom = e.nom;
		this.listesproduits = e.listesproduits;
	}
	public int getId() {
		return id;
	}

	public String getNom() {
		return nom;
	}

	public List<Produit> getListesproduits() {
		return listesproduits;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setListesproduits(List<Produit> listesproduits) {
		this.listesproduits = listesproduits;
	}
	
	
}
