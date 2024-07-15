package fr.diginamic.hello.entities;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class UserAccount {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String username;
	private String password;
	@ElementCollection(fetch = FetchType.EAGER)
	private List<GrantedAuthority> authorities;

	/**
	 * Constructor
	 * 
	 * @param username
	 * @param password
	 * @param authorities
	 */
	public UserAccount(String username, String password, String role) {
		GrantedAuthority roleAuthority = new SimpleGrantedAuthority(role);
		authorities = new ArrayList<>();
		authorities.add(roleAuthority);
		this.username = username;
		this.password = password;
	}

	/**
	 * Constructor jpa
	 * 
	 */
	public UserAccount() {
		super();
	}

	/**
	 * Getter pour username
	 * 
	 * @return username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Setter pour username
	 * 
	 * @param username username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Getter pour password
	 * 
	 * @return password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Setter pour password
	 * 
	 * @param password password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Getter pour authorities
	 * 
	 * @return authorities
	 */
	public List<GrantedAuthority> getAuthorities() {
		return authorities;
	}

	/**
	 * Setter pour authorities
	 * 
	 * @param authorities authorities
	 */
	public void setAuthorities(List<GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	/**
	 * Getter pour id
	 * 
	 * @return id
	 */
	public long getId() {
		return id;
	}


}
