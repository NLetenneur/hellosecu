package fr.diginamic.hello;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import fr.diginamic.hello.service.DepartementService;
import fr.diginamic.hello.service.VilleService;
import jakarta.transaction.Transactional;

@SpringBootApplication
public class TraitementFichiersApplication implements CommandLineRunner {

	@Autowired
	private VilleService villeService;
	@Autowired
	private DepartementService departementService;

	static Path home = Paths.get("C:\\Users\\nlete\\Documents\\Diginamic\\29. Spring Boot\\TP\\");

	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(TraitementFichiersApplication.class);
		application.setWebApplicationType(WebApplicationType.NONE);
		application.run(args);
	}

	@Transactional
	@Override
	public void run(String... args) throws Exception {
		Path fichierOFF = home.resolve("./recensementMini.csv");
		boolean exists = Files.exists(fichierOFF);
		if (exists) {
			try {
				List<String> lines = Files.readAllLines(fichierOFF, StandardCharsets.UTF_8);
				lines.remove(0);
				Iterator<String> iterator = lines.iterator();
				while (iterator.hasNext()) {
					String ligneCourante = iterator.next();
					String[] tab = ligneCourante.split(";", -1);

					// tester que la ligne fait la bonne taille
					if (tab.length == 10) {
						departementService.insertDepartementFromFile(tab);
						villeService.insertVilleFromFile(tab);
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
