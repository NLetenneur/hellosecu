package fr.diginamic.hello.repository;

import org.springframework.data.repository.CrudRepository;

import fr.diginamic.hello.entities.Departement;

public interface DepartementRepository extends CrudRepository<Departement, String>{

	public boolean existsById(String string);

	public Departement getById(String string);
	
}
