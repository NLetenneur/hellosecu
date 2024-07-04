package fr.diginamic.hello.entities;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "departement")
public class Departement {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected int id;
	@NotNull
	@Size(min = 2)
	protected String nom;
	@OneToMany(mappedBy = "departement")
	private Set<Ville> villes = new HashSet<>();

	/**
	 * Constructor
	 * 
	 * @param nom
	 */
	public Departement(@NotNull @Size(min = 2) String nom) {
		this.nom = nom;
	}

	/** Constructor jpa **/
	public Departement() {
	}

	/**
	 * Getter pour nom
	 * 
	 * @return nom
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * Setter pour nom
	 * 
	 * @param nom nom
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * Getter pour id
	 * 
	 * @return id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Getter pour villes
	 * 
	 * @return villes
	 */
	public Set<Ville> getVilles() {
		return villes;
	}

	/**
	 * Setter pour villes
	 * 
	 * @param villes villes
	 */
	public void setVilles(Set<Ville> villes) {
		this.villes = villes;
	}

}
