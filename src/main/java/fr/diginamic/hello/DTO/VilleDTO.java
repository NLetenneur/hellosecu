package fr.diginamic.hello.DTO;

public class VilleDTO {
	protected int codeVille;
	protected int nbHabitants;
	protected String codeDepartement;
	protected String nomDepartement;
	
	/** Constructor
	 * 
	 */
	public VilleDTO() {
	}

	/** Getter pour codeVille
	 * @return codeVille
	 */
	public int getCodeVille() {
		return codeVille;
	}

	/**Setter pour codeVille
	 * @param codeVille codeVille 
	 */
	public void setCodeVille(int codeVille) {
		this.codeVille = codeVille;
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

	/** Getter pour codeDepartement
	 * @return codeDepartement
	 */
	public String getCodeDepartement() {
		return codeDepartement;
	}

	/**Setter pour codeDepartement
	 * @param codeDepartement codeDepartement 
	 */
	public void setCodeDepartement(String codeDepartement) {
		this.codeDepartement = codeDepartement;
	}

	/** Getter pour nomDepartement
	 * @return nomDepartement
	 */
	public String getNomDepartement() {
		return nomDepartement;
	}

	/**Setter pour nomDepartement
	 * @param nomDepartement nomDepartement 
	 */
	public void setNomDepartement(String nomDepartement) {
		this.nomDepartement = nomDepartement;
	}
	
}
