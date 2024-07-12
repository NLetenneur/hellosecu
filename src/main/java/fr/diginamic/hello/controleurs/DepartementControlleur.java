//package fr.diginamic.hello.controleurs;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import fr.diginamic.hello.DTO.DepartementDTO;
//import fr.diginamic.hello.entities.Departement;
//import fr.diginamic.hello.repository.DepartementRepository;
//import fr.diginamic.hello.service.DepartementService;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.validation.Valid;
//
///**
// * Définit les routes liées aux départements
// * 
// */
//@RestController
//@RequestMapping("/departements")
//public class DepartementControlleur {
//
//	@Autowired
//	private DepartementRepository repository;
//	@Autowired
//	private DepartementService service;
//
//	/**
//	 * Ressort tous les départements au format DTO
//	 * 
//	 */
//	@GetMapping
//	public List<DepartementDTO> trouverDepartements() {
//		return service.extractDepartementsDTO();
//	}
//
//	/**
//	 * Ressort un département
//	 * 
//	 * @param id L'ID du département à trouver
//	 */
//	@GetMapping("/{id}")
//	public Departement trouverDepartement(@PathVariable String id) {
//		return repository.getById(id);
//	}
//
//	/**
//	 * Insere un département dans la base de donnée
//	 * 
//	 * @param departement Le département à inserer
//	 */
//	@PostMapping
//	public ResponseEntity<String> ajouterDepartement(@Valid @RequestBody Departement nvDepartement,
//			BindingResult errors) {
//		if (errors.hasErrors()) {
//			return ResponseEntity.badRequest().body(
//					errors.getAllErrors().stream().map(e -> e.getDefaultMessage()).collect(Collectors.joining("\n")));
//		}
//		repository.save(nvDepartement);
//		return ResponseEntity.ok("Departement ajouté");
//	}
//
//	/**
//	 * Modifie un département donné
//	 * 
//	 * @param id          L'id du département à modifier
//	 * @param departement Les nouvelles données
//	 */
//	@PutMapping("/{id}")
//	public ResponseEntity<String> modifierDepartement(@PathVariable String id,
//			@Valid @RequestBody Departement departement, BindingResult errors) {
//		if (errors.hasErrors()) {
//			return ResponseEntity.badRequest().body(errors.getAllErrors().get(0).getDefaultMessage());
//		}
//		Departement depAModifier = repository.getById(id);
//		depAModifier.setNom(departement.getNom());
//		repository.save(depAModifier);
//		return ResponseEntity.ok("Departement modifié");
//	}
//
//	/**
//	 * Supprime un département donné
//	 * 
//	 * @param id L'id du département à supprimer
//	 */
//	@DeleteMapping("/{id}")
//	public void supprimerDepartement(@PathVariable String id) {
//		repository.deleteById(id);
//	}
//	
//	/**Sort au format PDF les villes d'un département donné
//	 * @param id L'id du département
//	 */
//	@GetMapping("/export/pdf/{id}/fiche")
//	public void ficheDepartement(@PathVariable String id, HttpServletResponse response) throws Exception {
//		service.idDepartementToPDF(id, response);
//		
//	}
//
//	// Les méthodes permettant de lister les n plus grandes villes d'un département
//	// et les villes d'un département dont la population est comprise entre deux
//	// valeurs existent déjà dans VilleControlleur
//
//}
