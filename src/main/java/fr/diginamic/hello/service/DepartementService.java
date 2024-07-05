package fr.diginamic.hello.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import fr.diginamic.hello.dao.DepartementDAO;
import fr.diginamic.hello.entities.Departement;
import fr.diginamic.hello.entities.Ville;
import jakarta.validation.Valid;

/**Classe de service pour les méthodes liées à la classe Departement
* 
*/
@Service
public class DepartementService {
	@Autowired
	private DepartementDAO depDAO;

	/**Ressort tous les départements
	 * 
	 */
	public List<Departement> extractDepartements() {
		return depDAO.extractDepartements();
	}

	/**Ressort un département
	 * @param id L'ID du département à trouver
	 */
	public Departement extractDepartement(int id) {
		return depDAO.extractDepartement(id);
	}
	
	/**Insere un département dans la base de donnée
	 * @param departement Le département à inserer
	 */
	public void insertDepartement(@Valid Departement nvDepartement) {
		depDAO.insertDepartement(nvDepartement);

	}

	/**Modifie un département donné
	 * @param id L'id du département à modifier
	 * @param departement Les nouvelles données
	 */
	public void updateDepartement(int id, @Valid Departement departement) {
		depDAO.updateDepartement(id, departement);
	}

	/**Supprime un département donné
	 * @param id L'id du département à supprimer
	 */
	public ResponseEntity<String> deleteDepartement(int id) {
		Departement departement = extractDepartement(id);
		if (departement != null) {
			depDAO.deleteDepartement(id);
			return ResponseEntity.ok("Departement supprimé");
		}
		return ResponseEntity.badRequest().body("Ce departement n'existe pas");
	}

	/**Ressort les nb villes les plus peuplées d'un département
	 * @param id L'id du département en question
	 * @param nb Le nombre maximum de villes à sortir
	 */
	public List<Ville> topVillesByNbHabitants(int id, int nb) {
		return depDAO.topVillesByNbHabitants(id, nb);
	}

	/**Ressort toutes les villes d'un département dont le nombre d'habitants est compris entre min et max
	 * @param id L'id du département en question
	 * @param min le nombre minimum d'habitants
	 * @param max le nombre maximum d'habitants
	 */
	public List<Ville> extractVillesbetweenMinMaxNbHabitants(int id, int min, int max) {
		if (min<max) {
			return depDAO.extractVillesbetweenMinMaxNbHabitants(id,min, max);
		}
		return null;
	}
}
