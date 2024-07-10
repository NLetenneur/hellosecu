package fr.diginamic.hello.DTO;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class DepartementDTO {
	protected String codeDepartement;
	protected String nomDepartement;
	protected int nbHabitants;
	
	/** Constructor
	 * 
	 */
	public DepartementDTO() {
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

	public String getNomFromAPI(String id) {
		RestTemplate restTemplate = new RestTemplate();
		DepartementAPIDto response = restTemplate.getForObject("https://geo.api.gouv.fr/departements/"+id+"?fields=nom,code,codeRegion", DepartementAPIDto.class);
		String nom = response.getNom();
		return nom;
	}
	
	
	
	
}
