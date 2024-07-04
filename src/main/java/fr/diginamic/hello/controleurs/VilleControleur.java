package fr.diginamic.hello.controleurs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.*;

import fr.diginamic.hello.entities.Ville;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/villes")
public class VilleControleur {

	ArrayList<Ville> villes = creerVilles();
	@Autowired
	private Validator validator;

	public ArrayList<Ville> creerVilles() {
		ArrayList<Ville> villes = new ArrayList<>();
		// Création de la liste de villes
		Ville v01 = new Ville(1, "Nice", 343000);
		Ville v02 = new Ville(2, "Carcassone", 47800);
		Ville v03 = new Ville(3, "Narbonne", 53400);
		Ville v04 = new Ville(4, "Lyon", 484000);
		Ville v05 = new Ville(5, "Foix", 9700);
		Ville v06 = new Ville(6, "Pau", 77200);
		Ville v07 = new Ville(7, "Marseille", 850700);
		Ville v08 = new Ville(8, "Tarbes", 40600);
		Collections.addAll(villes, v01, v02, v03, v04, v05, v06, v07, v08);
		return villes;
	}

	@GetMapping
	public ArrayList<Ville> trouverVilles() {
		return villes;
	}

	@GetMapping("/{id}")
	public Ville trouverVille(@PathVariable int id) {
		Ville ville = null;
		for (Ville item : villes) {
			if (item.getId() == id) {
				ville = item;
			}
		}
		return ville;
	}

	@PostMapping
	public ResponseEntity<String> ajouterVille( @Valid @RequestBody Ville nvVille, BindingResult errors) {
		if (errors.hasErrors()) {
			return ResponseEntity.badRequest().body(errors.getAllErrors().stream().map(e->e.getDefaultMessage()).collect(Collectors.joining("\n")));
		}

		for (Ville item : villes) {
			if (item.getId() == (nvVille.getId())) {
				return ResponseEntity.badRequest().body("Il y a déjà une ville avec cet ID !");
			}

		}
		villes.add(nvVille);
		return ResponseEntity.ok("Ville ajoutée");
	}

	@PutMapping("/{id}")
	public ResponseEntity<String> modifierVille(@PathVariable int id, @Valid @RequestBody Ville ville, BindingResult errors) {
		Ville villeATrouver = trouverVille(id);
		if (errors.hasErrors()) {
			return ResponseEntity.badRequest().body(errors.getAllErrors().get(0).getDefaultMessage());
		}
		villeATrouver.setNom(ville.getNom());
		villeATrouver.setNbHabitants(ville.getNbHabitants());
		return ResponseEntity.ok("Ville modifiée");
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> supprimerVille(@PathVariable int id) {
		Ville ville = trouverVille(id);
		if (ville != null) {
			for (int i = 0; i < villes.size(); i++) {
				if (villes.get(i).getId() == (ville.getId())) {
					villes.remove(i);
					return ResponseEntity.ok("Ville modifiée");
				}
			}
		}
		return ResponseEntity.badRequest().body("Cette ville n'existe pas");
	}

}
