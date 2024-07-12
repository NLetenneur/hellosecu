package fr.diginamic.hello.repository;

import org.springframework.data.repository.CrudRepository;

import fr.diginamic.hello.entities.Departement;

public interface DepartementRepository extends CrudRepository<Departement, Integer>{

	public boolean existsByCodeDep(String string);

	public Departement getByCodeDep(String string);
	
}
