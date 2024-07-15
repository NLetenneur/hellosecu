package fr.diginamic.hello.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import fr.diginamic.hello.DTO.VilleDTO;
import fr.diginamic.hello.dao.VilleDAO;
import fr.diginamic.hello.entities.Ville;
import fr.diginamic.hello.exceptions.FonctionnalException;
import fr.diginamic.hello.repository.DepartementRepository;
import fr.diginamic.hello.repository.VilleRepository;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Classe de service pour les méthodes liées à la classe Ville
 * 
 */
@Service
public class VilleService {
	@Autowired
	private VilleDAO villeDAO;
	@Autowired
	private VilleRepository repository;
	@Autowired
	private DepartementRepository depRepository;

	/**
	 * Ressort toutes les villes
	 * 
	 */
	public List<Ville> extractVilles() {
		return villeDAO.extractVilles();
	}

	/**
	 * Ressort une ville
	 * 
	 * @param id l'ID de la ville à trouver
	 */
	public Ville extractVille(int idVille) {
		return villeDAO.extractVille(idVille);
	}

	/**
	 * Insere une ville dans la base de donnée
 * 
	 * @param ville La ville à inserer
	 * @throws FonctionnalException
	 */
	public List<Ville> insertVille(Ville ville) throws FonctionnalException {
		if ((ville.getNbHabitants() < 10) || (ville.getNom().length() < 2)
				) {
			throw new FonctionnalException(
					"Le format de la ville n'est pas bon. Une ville doit avoir au moins dix habitants, son nom doit faire minimum deux lettres et le code département doit faire deux ou trois caractères de long.");
		}
		if (villeDAO.isVilleInDB(ville)) {
			throw new FonctionnalException("Cette ville est déjà en base");
		}
		villeDAO.insertVille(ville);
		return extractVilles();
	}

//	/**
//	 * Modifie une ville donnée
//	 * 
//	 * @param id    L'id de la ville à modifier
//	 * @param ville Les nouvelles données
//	 * @throws FonctionnalException
//	 */
	public List<Ville> updateVille(int id, Ville ville) throws FonctionnalException {
		if ((ville.getNbHabitants() < 10) || (ville.getNom().length() < 2)) {
			throw new FonctionnalException(
					"Le format de la ville n'est pas bon. Une ville doit avoir au moins dit habitants, son nom doit faire minimum deux lettres et le code département doit faire deux ou trois caractères de long.");
		}
		if (villeDAO.isVilleInDB(ville)) {
			throw new FonctionnalException("Cette ville est déjà en base");
		}
		villeDAO.updateVille(id, ville);
		return extractVilles();
	}

	/**
	 * Supprime une ville donnée
	 * 
	 * @param id L'id de la ville à supprimer
	 */
	@PreAuthorize("hasRole('ADMIN')")
	public List<Ville> deleteVille(int idVille) {
		villeDAO.deleteVille(idVille);
		return extractVilles();
	}

	/**
	 * Insere une ville depuis un fichier
	 * 
	 * @param tab Tableau contenant les données à enregistrer
	 */
	public void insertVilleFromFile(String[] tab) {
		String nbHab = tab[9].replace(" ", "");
		String codeDep = tab[2];
		if (tab[2].length() == 1) {
			codeDep = "0" + codeDep;
		}
		if (!repository.existsByNomAndDepartementCodeDep(tab[6], codeDep) && (Integer.parseInt(nbHab) > 0)) {
			Ville ville = new Ville(tab[6], Integer.parseInt(nbHab), depRepository.getByCodeDep(codeDep));
			repository.save(ville);
		}
	}

	/**
	 * Transforme une ville en villeDTO
	 * 
	 * @param ville La ville à transformer
	 */
	public VilleDTO villeToVilleDTO(Ville ville) {
		VilleDTO dto = new VilleDTO();
		dto.setCodeVille(ville.getId());
		dto.setNom(ville.getNom());
		dto.setNbHabitants(ville.getNbHabitants());
		dto.setCodeDepartement(ville.getDepartement().getCodeDep());
		dto.setNomDepartement(ville.getDepartement().getNom());
		return dto;
	}

	public void villesWithMinHabitants(int min, HttpServletResponse response) throws IOException {
		response.setContentType("text/csv");
		response.setHeader("Content-Disposition", "attachment; filename=\"fichier.csv\"");
		String CSV_HEADER = "Nom,Nb_habitants,code_departement, nom_departement\n";
		StringBuilder csvContent = new StringBuilder();
		csvContent.append(CSV_HEADER);
		List<Ville> villes = extractVillesWithMinHabitants(min);
		PrintWriter writer = response.getWriter();
		writer.print("Nom,NbHabitants,codeDepartement,nomDepartement\n");
		for (Ville ville : villes) {
			writer.print(ville.getNom() + "," + ville.getNbHabitants() + "," 
		+ ville.getDepartement().getId() + ","
					+ ville.getDepartement().getNom() 
					+ "\n");
		}
		writer.println();
		response.flushBuffer();

	}

	private List<Ville> extractVillesWithMinHabitants(int min) {
		List<Ville> villes = repository.getByNbHabitantsGreaterThan(min);
		return villes;
	}

}
