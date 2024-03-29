package tn.esprit.entities;
// Generated 19 oct. 2018 22:55:32 by Hibernate Tools 5.0.6.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Formation generated by hbm2java
 */
@Entity
@Table(name = "formation", catalog = "neo3")
public class Formation implements java.io.Serializable {

	private int idFormation;
	private Employe employe;
	private Formateur formateur;
	private String libelle;
	private String dateDebut;
	private String lieux;
	private String responsable;

	public Formation() {
	}

	public Formation(int idFormation, Formateur formateur) {
		this.idFormation = idFormation;
		this.formateur = formateur;
	}

	public Formation(int idFormation, Employe employe, Formateur formateur, String libelle, String dateDebut,
			String lieux, String responsable) {
		this.idFormation = idFormation;
		this.employe = employe;
		this.formateur = formateur;
		this.libelle = libelle;
		this.dateDebut = dateDebut;
		this.lieux = lieux;
		this.responsable = responsable;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "idFormation", unique = true, nullable = false)
	public int getIdFormation() {
		return this.idFormation;
	}

	public void setIdFormation(int idFormation) {
		this.idFormation = idFormation;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CIN")
	public Employe getEmploye() {
		return this.employe;
	}

	public void setEmploye(Employe employe) {
		this.employe = employe;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "formateurid", nullable = false)
	public Formateur getFormateur() {
		return this.formateur;
	}

	public void setFormateur(Formateur formateur) {
		this.formateur = formateur;
	}

	@Column(name = "libelle", length = 254)
	public String getLibelle() {
		return this.libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	@Column(name = "DateDebut", length = 254)
	public String getDateDebut() {
		return this.dateDebut;
	}

	public void setDateDebut(String dateDebut) {
		this.dateDebut = dateDebut;
	}

	@Column(name = "lieux", length = 254)
	public String getLieux() {
		return this.lieux;
	}

	public void setLieux(String lieux) {
		this.lieux = lieux;
	}

	@Column(name = "Responsable", length = 254)
	public String getResponsable() {
		return this.responsable;
	}

	public void setResponsable(String responsable) {
		this.responsable = responsable;
	}

}
