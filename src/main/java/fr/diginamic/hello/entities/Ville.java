package fr.diginamic.hello.entities;

import jakarta.validation.constraints.*;


public class Ville {
	@NotNull
	@Min(value=1)
	protected int id;
	@NotNull
	@Size(min=2)
	protected String nom;
	@Min(value=1)
	protected int nbHabitants;

	/**
	 * Constructor
	 * 
	 * @param id
	 * @param nom
	 * @param nbHabitants
	 */
	public Ville(int id,String nom, int nbHabitants) {
		this.id= id;
		this.nom = nom;
		this.nbHabitants = nbHabitants;
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

}
