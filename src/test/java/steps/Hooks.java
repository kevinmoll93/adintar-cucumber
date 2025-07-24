package steps;

import base.Config;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class Hooks {
	private static WebDriver driver;

	@Before(order = 0)
	public synchronized void inicializarNavegador() {
		if (driver != null) {
			driver.quit(); // Cierra cualquier instancia existente antes de iniciar un nuevo navegador.
			System.out.println("Cerrando navegador residual antes de iniciar uno nuevo.");
		}

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--incognito");

		driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Config.tiempoEsperaImplicita));
		driver.manage().window().maximize();

		System.out.println("Navegador inicializado para el escenario: ");
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
