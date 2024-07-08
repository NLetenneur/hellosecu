package fr.diginamic.hello.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import fr.diginamic.hello.entities.Ville;

@Service
public interface VilleRepository extends CrudRepository<Ville, Integer>{

	boolean existsByNomAndDepartementId(String string, String string2);
	List<Ville> getByNomIsStartingWith(String string);
	List<Ville> getByNbHabitantsGreaterThan(int min);
	List<Ville> getByNbHabitantsBetween(int min, int max);
	List<Ville> getByDepartementIdAndNbHabitantsGreaterThan(String id, int min);
	List<Ville> getByDepartementIdAndNbHabitantsBetween(String id, int min, int max);
	List<Ville> findByDepartementIdOrderByNbHabitantsDesc(String id, Pageable pageable);

}
