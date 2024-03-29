package tn.esprit.entities;
// Generated 19 oct. 2018 22:55:32 by Hibernate Tools 5.0.6.Final

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * SuiviRisk generated by hbm2java
 */
@Entity
@Table(name = "suivi_risk", catalog = "neo3")

public class SuiviRisk implements java.io.Serializable {

	private int idSuiviRisk;
	private Critere critere;
	private Employe employe;
	private Equipe equipe;
	private Date dateSuivi;
	private Integer valeur;

	public SuiviRisk() {
	}

	public SuiviRisk(int idSuiviRisk, Critere critere, Employe employe, Equipe equipe) {
		this.idSuiviRisk = idSuiviRisk;
		this.critere = critere;
		this.employe = employe;
		this.equipe = equipe;
	}

	public SuiviRisk(int idSuiviRisk, Critere critere, Employe employe, Equipe equipe, Date dateSuivi, Integer valeur) {
		this.idSuiviRisk = idSuiviRisk;
		this.critere = critere;
		this.employe = employe;
		this.equipe = equipe;
		this.dateSuivi = dateSuivi;
		this.valeur = valeur;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "idSuiviRisk", unique = true, nullable = false)
	public int getIdSuiviRisk() {
		return this.idSuiviRisk;
	}

	public void setIdSuiviRisk(int idSuiviRisk) {
		this.idSuiviRisk = idSuiviRisk;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idcritere", nullable = true)
	public Critere getCritere() {
		return this.critere;
	}

	public void setCritere(Critere critere) {
		this.critere = critere;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CIN", nullable = true)
	public Employe getEmploye() {
		return this.employe;
	}

	public void setEmploye(Employe employe) {
		this.employe = employe;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idEquipe", nullable = true)
	public Equipe getEquipe() {
		return this.equipe;
	}

	public void setEquipe(Equipe equipe) {
		this.equipe = equipe;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "Date_Suivi", length = 19)
	public Date getDateSuivi() {
		return this.dateSuivi;
	}

	public void setDateSuivi(Date dateSuivi) {
		this.dateSuivi = dateSuivi;
	}

	@Column(name = "Valeur", precision = 8, scale = 0)
	public Integer getValeur() {
		return this.valeur;
	}

	public void setValeur(Integer valeur) {
		this.valeur = valeur;
	}

}
