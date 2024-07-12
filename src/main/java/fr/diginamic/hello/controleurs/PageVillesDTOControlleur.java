package fr.diginamic.hello.controleurs;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import fr.diginamic.hello.DTO.VilleDTO;
import fr.diginamic.hello.entities.Ville;
import fr.diginamic.hello.repository.VilleRepository;
import fr.diginamic.hello.service.VilleService;

@Controller
public class PageVillesDTOControlleur {
	@Autowired
	VilleRepository repository;

	@Autowired
	VilleService service;

	@GetMapping("/townList")
	public String getVillesDTO(Model villesModel) {
		Iterable<Ville> villes = repository.findAll();
		List<VilleDTO> villesDTO = new ArrayList<>();
		for (Ville item : villes) {
			villesDTO.add(service.villeToVilleDTO(item));
		}
		villesModel.addAttribute("villes", villesDTO);
		return "ville/townList";

	}

	@GetMapping("/deleteTown/{id}")
	public String deleteVillebyId(@PathVariable int id) {
		service.deleteVille(id);
		return "redirect:/townList";

	}

}
