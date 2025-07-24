package page;

import base.SeleniumBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Utils extends SeleniumBase {
	private final By txtToken = By.xpath("//b[contains(.,'Link Token')]");
	/**
	 * Constructor que inicializa el driver de Selenium.
	 *
	 * @param driver instancia de WebDriver
	 */
	public Utils(WebDriver driver) {
		super(driver);
	}

	public void verificarToken() {
		if (existe(txtToken)) {
			String mensajeError = "Se requiere un token para continuar, deteniendo ejecución.";
			System.out.println(mensajeError);
			manejarErrorYCerrarNavegador(mensajeError);
		}
	}

}
