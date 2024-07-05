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

@RestController
@RequestMapping("/departements")
public class DepartementControlleur {

	@Autowired
	private DepartementService service;

	@GetMapping
	public List<Departement> trouverDepartements() {
		return service.extractDepartements();
	}

	@GetMapping("/{id}")
	public Departement trouverDepartement(@PathVariable int id) {
		return service.extractDepartement(id);
	}

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

	@PutMapping("/{id}")
	public ResponseEntity<String> modifierDepartement(@PathVariable int id, @Valid @RequestBody Departement departement,
			BindingResult errors) {
		if (errors.hasErrors()) {
			return ResponseEntity.badRequest().body(errors.getAllErrors().get(0).getDefaultMessage());
		}
		service.updateDepartement(id, departement);
		return ResponseEntity.ok("Departement modifié");
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> supprimerDepartement(@PathVariable int id) {
		Departement departement = trouverDepartement(id);
		if (departement != null) {
			service.deleteDepartement(id);
			return ResponseEntity.ok("Departement supprimé");

		}
		return ResponseEntity.badRequest().body("Ce departement n'existe pas");
	}
	
	@GetMapping("/{id}/villesPlusPeuplees/{nb}")
	public List<Ville> trouverVillesPlusPeuplees(@PathVariable int id,@PathVariable int nb) {
		return service.topVillesByNbHabitants(id,nb);
	}
	
	@GetMapping("/{id}/{min}/{max}")
	public List<Ville> trouverVillesEntre2Pop(@PathVariable int id,@PathVariable int min,@PathVariable int max) {
		return service.extractVillesbetweenMinMaxNbHabitants(id,min, max);
	}

}
