package fr.diginamic.hello.dao;

import java.util.List;

import org.springframework.stereotype.*;
import fr.diginamic.hello.entities.Ville;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class VilleDAO {
	
	@PersistenceContext
	private EntityManager em;

	public List<Ville> extractVilles() {
		TypedQuery<Ville> query= em.createQuery("SELECT v FROM Ville v", Ville.class);
		return query.getResultList();
		
	}

	public Ville extractVille(int idVille) {
		TypedQuery<Ville> query= em.createQuery("SELECT v FROM Ville v WHERE v.id=:id", Ville.class);		
		query.setParameter("id", idVille);
		List<Ville>villes =query.getResultList();
		if(villes.size()>0) {
			return villes.get(0);
		}
		return null;
	}

	public void insertVille(Ville ville) {
		if(!isVilleInDB(ville)) {
			Ville v = new Ville(ville.getNom(), ville.getNbHabitants());
			em.persist(v);
		}
		
	}

	private boolean isVilleInDB(Ville ville) {
		TypedQuery<Ville> query= em.createQuery("SELECT v FROM Ville v WHERE v.id=:id", Ville.class);		
		query.setParameter("id", ville.getId());
		List<Ville> villes = query.getResultList();
		if(villes.size()>0) {
			return true;
		}
		return false;
	}

	public void updateVille(int id, Ville ville) {
		Ville v = extractVille(id);
		if(v!=null) {
			v.setNbHabitants(ville.getNbHabitants());
			v.setNom(ville.getNom());
		}		
	}

	public void deleteVille(int idVille) {
		em.remove(extractVille(idVille));

	}

}
