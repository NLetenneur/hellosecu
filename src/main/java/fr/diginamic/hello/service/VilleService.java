package fr.diginamic.hello.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.diginamic.hello.DTO.VilleDTO;
import fr.diginamic.hello.dao.VilleDAO;
import fr.diginamic.hello.entities.Ville;
import fr.diginamic.hello.exceptions.FonctionnalException;
import fr.diginamic.hello.repository.DepartementRepository;
import fr.diginamic.hello.repository.VilleRepository;

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
				|| ((ville.getDepartement().getId().length() != 2) && ville.getDepartement().getId().length() != 3)) {
			throw new FonctionnalException(
					"Le format de la ville n'est pas bon. Une ville doit avoir au moins dit habitants, son nom doit faire minimum deux lettres et le code département doit faire deux ou trois caractères de long.");
		}
		if (villeDAO.isVilleInDB(ville)) {
			throw new FonctionnalException("Cette ville est déjà en base");
		}
		villeDAO.insertVille(ville);
		return extractVilles();
	}

	/**
	 * Modifie une ville donnée
	 * 
	 * @param id    L'id de la ville à modifier
	 * @param ville Les nouvelles données
	 * @throws FonctionnalException 
	 */
	public List<Ville> updateVille(int id, Ville ville) throws FonctionnalException {
		if ((ville.getNbHabitants() < 10) || (ville.getNom().length() < 2)
				|| ((ville.getDepartement().getId().length() != 2) && ville.getDepartement().getId().length() != 3)) {
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
		String id = tab[2];
		if (tab[2].length() == 1) {
			id = "0" + id;
		}
		if (!repository.existsByNomAndDepartementId(tab[6], id) && (Integer.parseInt(nbHab) > 0)) {
			Ville ville = new Ville(tab[6], Integer.parseInt(nbHab), depRepository.getById(id));
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
		dto.setNbHabitants(ville.getNbHabitants());
		dto.setCodeDepartement(ville.getDepartement().getId());
		dto.setNomDepartement(ville.getDepartement().getNom());
		return dto;
	}

}
