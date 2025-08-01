package base;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Config {
	public static final int tiempoEspera = 3000; //milisegundos
	public static final int tiempoEsperaImplicita = 10; //segundos
	public static final String urlPrincipal = "http://bclt2015/adintarweb2008/default.aspx";

	public static WebDriver getDriver(String navegador) {
		WebDriver driver = null;
		if (navegador.equals("chrome")) {
			driver = new ChromeDriver();
		}
		return driver;
	}

	public static void esperar(float tiempoMilisegundos) throws InterruptedException {
		Thread.sleep(((long) tiempoMilisegundos));
	}

	public static List<String> getEscenariosEnFeature(String featureFile) {
		try {
			Path path = Paths.get(featureFile);
			return Files.lines(path).filter(line -> line.trim().startsWith("Scenario")) // Filtrar líneas con escenarios
					.map(line -> line.replace("Scenario: ", "").trim()) // Extraer el nombre
					.collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
	}

}
