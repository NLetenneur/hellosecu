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

@Service
@Transactional
public class DepartementDAO {
	@PersistenceContext
	private EntityManager em;

	public List<Departement> extractDepartements() {
		TypedQuery<Departement> query = em.createQuery("SELECT d FROM Departement d", Departement.class);
		return query.getResultList();
	}

	public Departement extractDepartement(int id) {
		TypedQuery<Departement> query = em.createQuery("SELECT d FROM Departement d WHERE d.id=:id", Departement.class);
		query.setParameter("id", id);
		List<Departement> departements = query.getResultList();
		if (departements.size() > 0) {
			return departements.get(0);
		}
		return null;
	}

	public void insertDepartement(@Valid Departement departement) {
		if (!isDepInDB(departement)) {
			Departement d = new Departement(departement.getNom());
			em.persist(d);
		}
	}

	private boolean isDepInDB(@Valid Departement departement) {
		TypedQuery<Departement> query = em.createQuery("SELECT d FROM Departement d WHERE d.id=:id", Departement.class);
		query.setParameter("id", departement.getId());
		List<Departement> departements = query.getResultList();
		if (departements.size() > 0) {
			return true;
		}

		return false;

	}

	public void updateDepartement(int id, @Valid Departement departement) {
		Departement d = extractDepartement(id);
		if(d!=null) {
			d.setNom(departement.getNom());
		}
		
	}

	public void deleteDepartement(int id) {
		em.remove(extractDepartement(id));		
	}

	public List<Ville> topVillesByNbHabitants(int id, int nb) {
		TypedQuery<Ville> query = em.createQuery("SELECT v FROM Departement d JOIN d.villes v  WHERE d.id=:id ORDER BY v.nbHabitants desc", Ville.class).setMaxResults(nb);
		query.setParameter("id", id);
		return query.getResultList();
	}

}
