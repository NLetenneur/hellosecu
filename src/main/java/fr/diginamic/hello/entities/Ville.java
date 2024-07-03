package fr.diginamic.hello.entities;

public class Ville {
	protected String nom;
	protected int nbHabitants;
	
	/** Constructor
	 * @param nom
	 * @param nbHabitants
	 */
	public Ville(String nom, int nbHabitants) {
		this.nom = nom;
		this.nbHabitants = nbHabitants;
	}

	/** Getter pour nom
	 * @return nom
	 */
	public String getNom() {
		return nom;
	}

	/**Setter pour nom
	 * @param nom nom 
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/** Getter pour nbHabitants
	 * @return nbHabitants
	 */
	public int getNbHabitants() {
		return nbHabitants;
	}

	/**Setter pour nbHabitants
	 * @param nbHabitants nbHabitants 
	 */
	public void setNbHabitants(int nbHabitants) {
		this.nbHabitants = nbHabitants;
	}
	
	
	

}
