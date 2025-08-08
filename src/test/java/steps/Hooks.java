package steps;

import base.Config;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class Hooks {
	private static WebDriver driver;

	@Before(order = 0)
	public synchronized void inicializarNavegador() {
		if (driver != null) {
			driver.quit();
			System.out.println("Cerrando navegador residual antes de iniciar uno nuevo.");
		}

		String carpetaDescargas = "C:\\Temp";

		Map<String, Object> prefs = new HashMap<>();
		prefs.put("download.default_directory", carpetaDescargas);
		prefs.put("download.prompt_for_download", false);
		prefs.put("download.directory_upgrade", true);
		prefs.put("safebrowsing.enabled", true); // evita bloqueo por "descarga insegura"
		prefs.put("plugins.always_open_pdf_externally", true); // evita visor PDF interno

		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", prefs);
		options.addArguments("--incognito");
		options.addArguments("--safebrowsing-disable-download-protection");
		options.addArguments("--test-type"); // evita bloqueos por certificados
		options.addArguments("--start-maximized");
		options.addArguments("--disable-extensions");
		options.addArguments("--no-sandbox");
		options.addArguments("--disable-gpu");

		driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Config.tiempoEsperaImplicita));

		System.out.println("Navegador inicializado con descarga automática de PDF en: " + carpetaDescargas);
	}

	@After(order = 1)
	public synchronized void cerrarNavegador() {
		System.out.println("Estado del escenario: ");
		if (driver != null) {
			driver.quit();
			driver = null;
		}
	}

	public static WebDriver getDriver() {
		return driver;
	}
}
