package fr.diginamic.hello.dao;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.diginamic.hello.entities.Departement;
import fr.diginamic.hello.entities.Ville;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

/**Classe d'accès à la base données pour les méthodes liées à la classe Departement
 * 
 */
@Service
@Transactional
public class DepartementDAO {
	@PersistenceContext
	private EntityManager em;

	/**Ressort tous les départements
	 * 
	 */
	public List<Departement> extractDepartements() {
		TypedQuery<Departement> query = em.createQuery("SELECT d FROM Departement d", Departement.class);
		return query.getResultList();
	}

	/**Ressort un département
	 * @param id L'ID du département à trouver
	 */
	public Departement extractDepartement(String id) {
		TypedQuery<Departement> query = em.createQuery("SELECT d FROM Departement d WHERE d.id=:id", Departement.class);
		query.setParameter("id", id);
		List<Departement> departements = query.getResultList();
		if (departements.size() > 0) {
			return departements.get(0);
		}
		return null;
	}
	
	/**Insere un département dans la base de donnée
	 * @param departement Le département à inserer
	 */
	public void insertDepartement(@Valid Departement departement) {
		if (!isDepInDB(departement)) {
			Departement d = new Departement(departement.getCodeDep(),departement.getNom());
			em.persist(d);
		}
	}


	/**Verifie si un département est dans la base de données
	 * @param departement Le département à vérifier
	 */
	private boolean isDepInDB(@Valid Departement departement) {
		TypedQuery<Departement> query = em.createQuery("SELECT d FROM Departement d WHERE d.id=:id", Departement.class);
		query.setParameter("id", departement.getId());
		List<Departement> departements = query.getResultList();
		if (departements.size() > 0) {
			return true;
		}

		return false;

	}

	/**Modifie un département donné
	 * @param id L'id du département à modifier
	 * @param departement Les nouvelles données
	 */
	public void updateDepartement(String id, @Valid Departement departement) {
		Departement d = extractDepartement(id);
		if(d!=null) {
			d.setNom(departement.getNom());
		}
		
	}

	/**Supprime un département donné
	 * @param id L'id du département à supprimer
	 */
	public void deleteDepartement(String id) {
		em.remove(extractDepartement(id));		
	}

	/**Ressort les nb villes les plus peuplées d'un département
	 * @param id L'id du département en question
	 * @param nb Le nombre maximum de villes à sortir
	 */
	public List<Ville> topVillesByNbHabitants(String id, int nb) {
		TypedQuery<Ville> query = em.createQuery("SELECT v FROM Departement d JOIN d.villes v  WHERE d.id=:id ORDER BY v.nbHabitants desc", Ville.class).setMaxResults(nb);
		query.setParameter("id", id);
		return query.getResultList();
	}

	/**Ressort toutes les villes d'un département dont le nombre d'habitants est compris entre min et max
	 * @param id L'id du département en question
	 * @param min le nombre minimum d'habitants
	 * @param max le nombre maximum d'habitants
	 */
	public List<Ville> extractVillesbetweenMinMaxNbHabitants(int id, int min, int max) {
		TypedQuery<Ville> query = em.createQuery("SELECT v FROM Departement d JOIN d.villes v  WHERE d.id=:id AND v.nbHabitants BETWEEN :min AND :max", Ville.class);
		query.setParameter("id", id);
		query.setParameter("min", min);
		query.setParameter("max", max);
		return query.getResultList();
	}

}
