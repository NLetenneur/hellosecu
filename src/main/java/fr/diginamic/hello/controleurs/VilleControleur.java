package fr.diginamic.hello.controleurs;

import java.util.ArrayList;
import java.util.Collections;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.diginamic.hello.entities.Ville;

@RestController
@RequestMapping("/villes")
public class VilleControleur {

	ArrayList<Ville> villes = new ArrayList<>();

	@GetMapping
	public ArrayList<Ville> CreerVilles() {
		// Création de la liste de villes
		Ville v01 = new Ville("Nice", 343000);
		Ville v02 = new Ville("Carcassone", 47800);
		Ville v03 = new Ville("Narbonne", 53400);
		Ville v04 = new Ville("Lyon", 484000);
		Ville v05 = new Ville("Foix", 9700);
		Ville v06 = new Ville("Pau", 77200);
		Ville v07 = new Ville("Marseille", 850700);
		Ville v08 = new Ville("Tarbes", 40600);

		Collections.addAll(villes, v01, v02, v03, v04, v05, v06, v07, v08);

		return villes;
	}

	@PostMapping
	public ResponseEntity<String> insertPet(@RequestBody Ville nvVille) {
		for (Ville item : villes) {
			if (item.getNom().equals(nvVille.getNom())) {
				return ResponseEntity.badRequest().body("Il y a déjà une ville avec ce nom !");
			}

		}
		villes.add(nvVille);
		return ResponseEntity.ok("Ville ajoutée");
	}

}
