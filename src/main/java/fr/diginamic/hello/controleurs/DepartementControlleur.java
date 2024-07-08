package fr.diginamic.hello.controleurs;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.diginamic.hello.entities.Departement;
import fr.diginamic.hello.entities.Ville;
import fr.diginamic.hello.service.DepartementService;
import jakarta.validation.Valid;

/**Définit les routes liées aux départements
 * 
 */
@RestController
@RequestMapping("/departements")
public class DepartementControlleur {

	@Autowired
	private DepartementService service;


	/**Ressort tous les départements
	 * 
	 */
	@GetMapping
	public List<Departement> trouverDepartements() {
		return service.extractDepartements();
	}

	/**Ressort un département
	 * @param id L'ID du département à trouver
	 */
	@GetMapping("/{id}")
	public Departement trouverDepartement(@PathVariable String id) {
		return service.extractDepartement(id);
	}

	/**Insere un département dans la base de donnée
	 * @param departement Le département à inserer
	 */
	@PostMapping
	public ResponseEntity<String> ajouterDepartement(@Valid @RequestBody Departement nvDepartement,
			BindingResult errors) {
		if (errors.hasErrors()) {
			return ResponseEntity.badRequest().body(
					errors.getAllErrors().stream().map(e -> e.getDefaultMessage()).collect(Collectors.joining("\n")));
		}
		service.insertDepartement(nvDepartement);
		return ResponseEntity.ok("Departement ajouté");
	}

	/**Modifie un département donné
	 * @param id L'id du département à modifier
	 * @param departement Les nouvelles données
	 */
	@PutMapping("/{id}")
	public ResponseEntity<String> modifierDepartement(@PathVariable String id, @Valid @RequestBody Departement departement,
			BindingResult errors) {
		if (errors.hasErrors()) {
			return ResponseEntity.badRequest().body(errors.getAllErrors().get(0).getDefaultMessage());
		}
		service.updateDepartement(id, departement);
		return ResponseEntity.ok("Departement modifié");
	}

	/**Supprime un département donné
	 * @param id L'id du département à supprimer
	 */
	@DeleteMapping("/{id}")
	public void supprimerDepartement(@PathVariable String id) {
		service.deleteDepartement(id);
	}
	
	/**Ressort les nb villes les plus peuplées d'un département
	 * @param id L'id du département en question
	 * @param nb Le nombre maximum de villes à sortir
	 */
	@GetMapping("/{id}/villesPlusPeuplees/{nb}")
	public List<Ville> trouverVillesPlusPeuplees(@PathVariable String id,@PathVariable int nb) {
		return service.topVillesByNbHabitants(id,nb);
	}

	
	/**Ressort toutes les villes d'un département dont le nombre d'habitants est compris entre min et max
	 * @param id L'id du département en question
	 * @param min le nombre minimum d'habitants
	 * @param max le nombre maximum d'habitants
	 */
	@GetMapping("/{id}/{min}/{max}")
	public List<Ville> trouverVillesEntre2Pop(@PathVariable int id,@PathVariable int min,@PathVariable int max) {
		return service.extractVillesbetweenMinMaxNbHabitants(id,min, max);
	}

}
