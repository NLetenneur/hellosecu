package fr.diginamic.hello.repository;

import org.springframework.data.repository.CrudRepository;

import fr.diginamic.hello.entities.UserAccount;


public interface UserRepository extends CrudRepository<UserAccount, Long> {

	public UserAccount getById(long id);

	public UserAccount getUserByUsername(String name);

}
