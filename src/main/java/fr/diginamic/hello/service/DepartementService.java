package fr.diginamic.hello.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.diginamic.hello.dao.DepartementDAO;
import fr.diginamic.hello.entities.Departement;
import fr.diginamic.hello.entities.Ville;
import jakarta.validation.Valid;

@Service
public class DepartementService {
	@Autowired
	private DepartementDAO depDAO;

	public List<Departement> extractDepartements() {
		return depDAO.extractDepartements();
	}

	public Departement extractDepartement(int id) {
		return depDAO.extractDepartement(id);
	}

	public void insertDepartement(@Valid Departement nvDepartement) {
		depDAO.insertDepartement(nvDepartement);

	}

	public void updateDepartement(int id, @Valid Departement departement) {
		depDAO.updateDepartement(id, departement);
	}

	public void deleteDepartement(int id) {
		depDAO.deleteDepartement(id);
	}

	public List<Ville> topVillesByNbHabitants(int id, int nb) {
		return depDAO.topVillesByNbHabitants(id, nb);
	}

	public List<Ville> extractVillesbetweenMinMaxNbHabitants(int id, int min, int max) {
		return depDAO.extractVillesbetweenMinMaxNbHabitants(id,min, max);
	}
}
