package fr.diginamic.hello.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.diginamic.hello.DTO.DepartementDTO;

public interface DepartementDTORepository extends JpaRepository<DepartementDTO, String>{

}
