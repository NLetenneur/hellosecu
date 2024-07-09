package fr.diginamic.hello.dao;

import java.util.List;

import org.springframework.stereotype.*;
import fr.diginamic.hello.entities.Ville;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

/**Classe d'accès à la base données pour les méthodes liées à la classe Ville
 * 
 */
@Service
@Transactional
public class VilleDAO {
	
	@PersistenceContext
	private EntityManager em;

	/**Ressort toutes les villes
	 * 
	 */
	public List<Ville> extractVilles() {
		TypedQuery<Ville> query= em.createQuery("SELECT v FROM Ville v", Ville.class);
		return query.getResultList();
		
	}

	/**Ressort une ville
	 * @param id l'ID de la ville à trouver
	 */
	public Ville extractVille(int idVille) {
		TypedQuery<Ville> query= em.createQuery("SELECT v FROM Ville v WHERE v.id=:id", Ville.class);		
		query.setParameter("id", idVille);
		List<Ville>villes =query.getResultList();
		if(villes.size()>0) {
			return villes.get(0);
		}
		return null;
	}

	/**Insere une ville dans la base de donnée
	 * @param ville La ville à inserer
	 */
	public void insertVille(Ville ville) {
		if(!isVilleInDB(ville)) {
			Ville v = new Ville(ville.getNom(), ville.getNbHabitants(), ville.getDepartement());
			em.persist(v);
		}
		
	}

	/**Vérifie la présence d'une ville dans la base de données
	 * @param ville La ville à vérifier
	 */
	public boolean isVilleInDB(Ville ville) {
		TypedQuery<Ville> query= em.createQuery("SELECT v FROM Ville v WHERE v.id=:id", Ville.class);		
		query.setParameter("id", ville.getId());
		List<Ville> villes = query.getResultList();
		if(villes.size()>0) {
			return true;
		}
		return false;
	}

	/**Modifie une ville donnée
	 * @param id L'id de la ville à modifier
	 * @param ville Les nouvelles données
	 */
	public void updateVille(int id, Ville ville) {
		Ville v = extractVille(id);
		if(v!=null) {
			v.setNbHabitants(ville.getNbHabitants());
			v.setNom(ville.getNom());
		}		
	}
	
	/**Supprime une ville donnée
	 * @param id L'id de la ville à supprimer
	 */
	public void deleteVille(int idVille) {
		em.remove(extractVille(idVille));

	}

}
