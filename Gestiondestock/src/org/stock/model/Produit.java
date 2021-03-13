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
@Table(name="produits")
public class Produit implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "Produit")
	private String nomproduit;
	@Column(name="Prix")
	private Double prix;
	@ManyToMany(mappedBy = "listesdeproduit")
	private List<Personne> personnes;
	
	@ManyToMany(mappedBy = "listesproduits")
	private List<Emplacement> emplacements;
	
	public Produit(long id, String nomProduit, Double prix,List<Emplacement> emplacements) {
		super();
		this.id = id;
		nomproduit = nomProduit;
		this.prix = prix;
		this.emplacements = emplacements;
	}
	
	public Produit() {
		
	}
	
	public Produit(Produit produit) {
		this.id = produit.getId();
		this.nomproduit = produit.getNomproduit();
		this.prix = produit.getPrix();
		this.emplacements = produit.emplacements;
	}

	public Long getId() {
		return id;
	}

	public String getNomproduit() {
		return nomproduit;
	}

	public Double getPrix() {
		return prix;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setNomproduit(String nomProduit) {
		nomproduit = nomProduit;
	}

	public void setPrix(Double prix) {
		this.prix = prix;
	}

	@Override
	public String toString() {
		return "Produit [id=" + id + ", NomProduit=" + nomproduit + ", Prix=" + prix + "]";
	}

	public List<Personne> getPersonnes() {
		return personnes;
	}

	public List<Emplacement> getEmplacements() {
		return emplacements;
	}

	public void setPersonnes(List<Personne> personnes) {
		this.personnes = personnes;
	}

	public void setEmplacements(List<Emplacement> emplacements) {
		this.emplacements = emplacements;
	}
}
