package fr.diginamic.hello.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import fr.diginamic.hello.entities.Ville;

@Component
public interface VilleRepository extends JpaRepository<Ville, Integer>{

	boolean existsByNomAndDepartementId(String string, String string2);
	List<Ville> getByNomIsStartingWith(String string);
	List<Ville> getByNbHabitantsGreaterThan(int min);
	List<Ville> getByNbHabitantsBetween(int min, int max);
	List<Ville> getByDepartementIdAndNbHabitantsGreaterThan(String id, int min);
	List<Ville> getByDepartementIdAndNbHabitantsBetween(String id, int min, int max);
	List<Ville> findByDepartementIdOrderByNbHabitantsDesc(String id, Pageable pageable);
	Ville getById(int id);

}
