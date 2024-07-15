package fr.diginamic.hello.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import fr.diginamic.hello.entities.UserAccount;
import fr.diginamic.hello.repository.UserRepository;
import jakarta.annotation.PostConstruct;

/**
 * Classe de service pour les méthodes liées à la classe UserAccount
 * 
 */

@Service
public class UserAccountService {
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PostConstruct
	public void init() {
		create(new UserAccount("user", passwordEncoder.encode("userP"), "ROLE_USER"));
		create(new UserAccount("admin", passwordEncoder.encode("adminP"), "ROLE_ADMIN"));
		
	}
	
	private void create(UserAccount newUserAC) {
		repository.save(newUserAC);
	}

	public UserAccount getUser(long id){
		return repository.getById(id);
	}
	
	public UserAccount getUserByUsername(String name) {
		return repository.getUserByUsername(name);
	}
	
	public List<GrantedAuthority> getAuthorities(String name){
		List<GrantedAuthority> listA = repository.getUserByUsername(name).getAuthorities();
		return listA;
	}
}
