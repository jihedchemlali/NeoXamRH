package tn.esprit.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "stagiaire", catalog = "neo3")
public class Stagiaire implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	private int id;
	
	private String nom;
	private int age;
	private String prenom;
	private String sexe;

	private Contrat contract;
	
	private Produit produit;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getSexe() {
		return sexe;
	}

	public void setSexe(String sexe) {
		this.sexe = sexe;
	}
	
	@OneToOne
	public Contrat getContract() {
		return contract;
	}

	public void setContract(Contrat contract) {
		this.contract = contract;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idPro")
	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	public Stagiaire(int id, String nom, int age, String prenom, String sexe, Contrat contract,
			Produit produit) {

		this.id = id;
		this.nom = nom;
		this.age = age;
		this.prenom = prenom;
		this.sexe = sexe;
		this.contract = contract;
		this.produit = produit;
	}

	public Stagiaire(String nom, int age, String prenom, String sexe, Contrat contract, Produit produit) {
		this.nom = nom;
		this.age = age;
		this.prenom = prenom;
		this.sexe = sexe;
		this.contract = contract;
		this.produit = produit;
	}

	public Stagiaire(String nom, int age, String prenom, String sexe) {
		this.nom = nom;
		this.age = age;
		this.prenom = prenom;
		this.sexe = sexe;
	}

	
	public Stagiaire(int id, String nom, int age, String prenom, String sexe) {
		super();
		this.id = id;
		this.nom = nom;
		this.age = age;
		this.prenom = prenom;
		this.sexe = sexe;
	}
	public Stagiaire() {
	}

	@Override
	public String toString() {
		return "Stagiaire [id=" + id + ", nom=" + nom + ", age=" + age + ", prenom=" + prenom + ", sexe=" + sexe + "]";
	}
	
	

}
