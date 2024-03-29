package tn.esprit.entities;
// Generated 19 oct. 2018 22:55:32 by Hibernate Tools 5.0.6.Final

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * LangageNaturel generated by hbm2java
 */
@Entity
@Table(name = "langage_naturel", catalog = "neo3")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="DTYPE")
public class LangageNaturel implements java.io.Serializable {

	private int idLang;
	private Analyseur analyseur;
	private String type;
	private Set<Mot> mots = new HashSet<Mot>(0);

	public LangageNaturel() {
	}

	public LangageNaturel(int idLang, Analyseur analyseur) {
		this.idLang = idLang;
		this.analyseur = analyseur;
	}

	public LangageNaturel(int idLang, Analyseur analyseur, String type, Set<Mot> mots) {
		this.idLang = idLang;
		this.analyseur = analyseur;
		this.type = type;
		this.mots = mots;
	}
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "idLang", unique = true, nullable = false)
	public int getIdLang() {
		return this.idLang;
	}

	public void setIdLang(int idLang) {
		this.idLang = idLang;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idAnalyseur", nullable = false)
	public Analyseur getAnalyseur() {
		return this.analyseur;
	}

	public void setAnalyseur(Analyseur analyseur) {
		this.analyseur = analyseur;
	}

	@Column(name = "type", length = 254)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "langageNaturel")
	public Set<Mot> getMots() {
		return this.mots;
	}

	public void setMots(Set<Mot> mots) {
		this.mots = mots;
	}

}
