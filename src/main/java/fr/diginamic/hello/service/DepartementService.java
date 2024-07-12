package fr.diginamic.hello.service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

import fr.diginamic.hello.DTO.DepartementDTO;
import fr.diginamic.hello.dao.DepartementDAO;
import fr.diginamic.hello.entities.Departement;
import fr.diginamic.hello.entities.Ville;
import fr.diginamic.hello.exceptions.FonctionnalException;
import fr.diginamic.hello.repository.DepartementRepository;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**Classe de service pour les méthodes liées à la classe Departement
* 
*/
@Service
public class DepartementService {
	@Autowired
	private DepartementDAO depDAO;
	
	@Autowired
	private DepartementRepository repository;
	
	@Autowired
	private DepartementDTO depDTO;

	/**Ressort tous les départements
	 * 
	 */
	public List<Departement> extractDepartements() {
		return depDAO.extractDepartements();
	}

	/**Ressort un département
	 * @param id L'ID du département à trouver
	 */
	public Departement extractDepartement(String id) {
		return depDAO.extractDepartement(id);
	}
	
	/**Insere un département dans la base de donnée
	 * @param departement Le département à inserer
	 * @throws FonctionnalException 
	 */
	public void insertDepartement(@Valid Departement nvDepartement) throws FonctionnalException {
		if(((3<nvDepartement.getCodeDep().length())&&(nvDepartement.getCodeDep().length()<2))
				||(nvDepartement.getNom().length()<3)
				) {
			throw new FonctionnalException("Le format du département est erroné");
		}
		depDAO.insertDepartement(nvDepartement);

	}

	/**Modifie un département donné
	 * @param id L'id du département à modifier
	 * @param departement Les nouvelles données
	 * @throws FonctionnalException 
	 */
	public void updateDepartement(String id, @Valid Departement departement) throws FonctionnalException {
		if(((3<departement.getCodeDep().length())&&(departement.getCodeDep().length()<2))
				||(departement.getNom().length()<3)
				) {
			throw new FonctionnalException("Le format du département est erroné");
		}
		depDAO.updateDepartement(id, departement);
	}

	/**Supprime un département donné
	 * @param id L'id du département à supprimer
	 */
	public ResponseEntity<String> deleteDepartement(String id) {
		Departement departement = extractDepartement(id);
		if (departement != null) {
			depDAO.deleteDepartement(id);
			return ResponseEntity.ok("Departement supprimé");
		}
		return ResponseEntity.badRequest().body("Ce departement n'existe pas");
	}

	/**Ressort les nb villes les plus peuplées d'un département
	 * @param id L'id du département en question
	 * @param nb Le nombre maximum de villes à sortir
	 */
	public List<Ville> topVillesByNbHabitants(String id, int nb) {
		return depDAO.topVillesByNbHabitants(id, nb);
	}

	/**Ressort toutes les villes d'un département dont le nombre d'habitants est compris entre min et max
	 * @param id L'id du département en question
	 * @param min le nombre minimum d'habitants
	 * @param max le nombre maximum d'habitants
	 */
	public List<Ville> extractVillesbetweenMinMaxNbHabitants(int id, int min, int max) {
		if (min<max) {
			return depDAO.extractVillesbetweenMinMaxNbHabitants(id,min, max);
		}
		return null;
	}

	/** Insere un département depuis un fichier
	 * 
	 * @param tab Tableau contenant les données à enregistrer
	 */
	public void insertDepartementFromFile(String[] tab) {
		String CodeDep = tab[2];
		if (tab[2].length()==1) {
			CodeDep="0"+CodeDep;
		}
		if(!repository.existsByCodeDep(CodeDep)) {
			Departement dep = new Departement(CodeDep, trouverNomParCodeDep(CodeDep));
			repository.save(dep);
		}		
	}
	

	/** Permet de retrouver le nom d'un département dans un fichier à partir de son code/id
	 * 
	 * @param string L'id du département à trouver
	 */
	public static @NotNull @Size(min = 2) String trouverNomParCodeDep(String string) {
		Path home = Paths.get("C:\\Users\\nlete\\Documents\\Diginamic\\29. Spring Boot\\TP\\");
		Path fichierDep = home.resolve("./departementsFrance.csv");
		boolean exists = Files.exists(fichierDep);
		String nomDep = null;
		if (exists) {
			try {
				List<String> lines = Files.readAllLines(fichierDep, StandardCharsets.UTF_8);
				lines.remove(0);
				Iterator<String> iterator = lines.iterator();
				string=string.replaceAll("\"", "");
				while (iterator.hasNext()&&nomDep==null) {
					String ligneCourante = iterator.next();
					String[] tab = ligneCourante.split(",", -1);

					// tester que la ligne fait la bonne taille
					if (tab.length == 4) {
						if (tab[0].equals(string)) {
							nomDep = tab[1];
						}
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return nomDep;
	}
	
	/**Transforme un département en departementDTO
	 * 
	 * @param departement Le département à transformer
	 */
	public DepartementDTO departementToDepartementDTO(Departement dep) {
		DepartementDTO dto = new DepartementDTO();
		dto.setCodeDepartement(dep.getCodeDep());
		dto.setNomDepartement(dep.getNom());
		int nbHab = 0;
		for (Ville item : dep.getVilles()) {
			nbHab += item.getNbHabitants();
		}
		dto.setNbHabitants(nbHab);
		return dto;
	}
	
	/**Extraits les déârtements de la base et les met au format DTO
	 * 
	 */
	public List<DepartementDTO> extractDepartementsDTO() {
		List<Departement> deps= depDAO.extractDepartements();
		List<DepartementDTO> depsDTO = new ArrayList<>();
		for(Departement item : deps) {
			depsDTO.add(departementToDepartementDTO(item));
		}
		return depsDTO;
	}

	public void idDepartementToPDF(String id, HttpServletResponse response) throws DocumentException, IOException {
		String titre = depDTO.getNomFromAPI(id);
		response.setHeader("Content-Disposition", "attachment; filename=\"fichier.pdf\"");
		Document document = new Document(PageSize.A4);
		PdfWriter.getInstance(document, response.getOutputStream());
		
		document.open();
		document.addTitle(titre);
		
		document.newPage();
		BaseFont baseFont = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.WINANSI, BaseFont.EMBEDDED);
		Phrase p1 = new Phrase(id+" "+titre, new Font(baseFont, 32.0f, 1, new BaseColor(0, 51, 80)));
		document.add(p1);
        document.add( Chunk.NEWLINE );
		Departement dep = extractDepartement(id);
		for(Ville item : dep.getVilles()) {
			Phrase p2 = new Phrase(item.getNom()+" - "+item.getNbHabitants()+" habitants.", new Font(baseFont, 12.0f, 0, new BaseColor(0, 51, 80)));
			document.add(p2);
	        document.add( Chunk.NEWLINE );
		}
		document.close();
		
		response.flushBuffer();
		
	}
	
}
