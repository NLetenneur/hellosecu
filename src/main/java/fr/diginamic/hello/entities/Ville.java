package fr.diginamic.hello.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**Organise les entités ville
 * 
 */
@Entity
@Table(name = "ville")
public class Ville {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected int id;
	@NotNull
	@Size(min = 1)
	protected String nom;
	@Min(value = 1)
	protected int nbHabitants;
	@NotNull
	@ManyToOne
	@JoinColumn(name = "ID_Departement")
	protected Departement departement;

	/**
	 * Constructor
	 * 
	 * @param nom
	 * @param nbHabitants
	 * @param departement
	 */
	public Ville(@NotNull @Size(min = 2) String nom, @Min(value = 1) int nbHabitants, Departement departement) {
		this.nom = nom;
		this.nbHabitants = nbHabitants;
		this.departement = departement;
	}

	/** Constructor jpa **/
	public Ville() {
	}

	/**
	 * Getter pour nom
	 * 
	 * @return nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * Setter pour nom
	 * 
	 * @param nom nom
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * Getter pour nbHabitants
	 * 
	 * @return nbHabitants
	 */
	public int getNbHabitants() {
		return nbHabitants;
	}

	/**
	 * Setter pour nbHabitants
	 * 
	 * @param nbHabitants nbHabitants
	 */
	public void setNbHabitants(int nbHabitants) {
		this.nbHabitants = nbHabitants;
	}

	/**
	 * Getter pour id
	 * 
	 * @return id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Setter pour id
	 * 
	 * @param id id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/** Getter pour departement
	 * @return departement
	 */
	public Departement getDepartement() {
		return departement;
	}

	/**Setter pour departement
	 * @param departement departement 
	 */
	public void setDepartement(Departement departement) {
		this.departement = departement;
	}
	
	


}
