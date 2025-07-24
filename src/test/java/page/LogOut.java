package page;

import base.SeleniumBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LogOut extends SeleniumBase {
	private final By btnSalir = By.xpath("//button[@type='button']//svg-icon[@class='fill-white']");
	private final By btnSalir2 = By.xpath("//li[contains(.,'Salir')]");
	private final By btnSalir3 = By.xpath("//a[contains(.,'Salir')]");
	private final By btnConfirmarSAlir2 = By.xpath("//button[@id='buttonAceptarLogoutModal']");
	private final By btnConfirmarSalir = By.xpath("//button[@class='btn btn-primary px-32']");

	private final By btnIngresar = By.xpath("//button[@id='btn-login-ingresar']");

	public LogOut(WebDriver driver) {
		super(driver);
	}

	/**
	 * Hace clic en el botón de logout, intentando con diferentes localizadores.
	 */
	public void clickSalir() {
		boolean logoutExitoso = intentarClick(btnSalir) || intentarClick(btnSalir2) || intentarClick(btnSalir3);

		if (!logoutExitoso) {
			System.out.println("No se pudo encontrar el botón de salir en la página actual.");
		}

		// Confirmar logout
		boolean confirmarExitoso = intentarClick(btnConfirmarSalir) || intentarClick(btnConfirmarSAlir2);

		if (!confirmarExitoso) {
			System.out.println("No se pudo confirmar el logout.");
		}
	}

	/**
	 * Verifica si uno de los botones de salir está presente.
	 */
	public void verificarSalir() {
		if (!existe(btnSalir) && !existe(btnSalir2) && !existe(btnSalir3)) {
			System.out.println("Ningún botón de salir está disponible en la página.");
		}
	}

	public void verificarBotonLogin() {
		existe(btnIngresar);
	}

}
