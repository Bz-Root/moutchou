package org.stock.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.transaction.Transactional;

import org.stock.metier.algorithme.EncoderAlgo;

@Entity
@Transactional
@Table(name = "personnes")
public class Personne implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "Nom")
	private String nom;
	@Column(name = "Prenom")
	private String prenom;
	@Column(name = "Date")
	private Date date;
	@Column(name = "Username")
	private String username;
	@Column(name = "Password")
	private String password;

	@Column(name = "role")
	private String role;

	@ManyToMany
	private List<Produit> listesdeproduit;

	public Personne(String nom, String prenom, java.util.Date date, String username, String password,
			ArrayList<Produit> listesdeproduit, String role) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.date = date;
		this.username = username;
		try {
			this.password = EncoderAlgo.encodeMD5(password);
		} catch (Exception e) {
			// ignorer
		}
		this.listesdeproduit = listesdeproduit;
		this.role = role;
	}

	public Personne() {

	}

	public Personne(Personne personne) {
		nom = personne.getNom();
		prenom = personne.getPrenom();
		date = personne.getDate();
		username = personne.getUsername();
		password = personne.password;
		id = personne.id;
		listesdeproduit = personne.listesdeproduit;
		role = personne.role;
	}

	public String getNom() {
		return nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public Date getDate() {
		return date;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		try {
			this.password = EncoderAlgo.encodeMD5(password);
		} catch (Exception e) {
			// Ignorer
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Produit> getListesdeproduit() {
		return listesdeproduit;
	}

	public void setListesdeproduit(List<Produit> listesdeproduit) {
		this.listesdeproduit = listesdeproduit;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
