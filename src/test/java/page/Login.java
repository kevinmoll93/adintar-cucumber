package page;

import base.SeleniumBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Login extends SeleniumBase {
	private final By fldUsuario = By.xpath("//input[@id='defaultLoginFormEmail']");
	private final By fldContrasenia = By.xpath("//input[@id='defaultLoginFormPassword']");
	private final By btnIngresar = By.xpath("//button[@id='btn-login-ingresar']");
	private final By btnCierreForzado = By.xpath("//button[@id='modal-cerrar-sesion-activa-btn-si']");
	private final By paginaPrincipal = By.xpath("//b[@class='font-weight-bold']");

	public Login(WebDriver driver) {
		super(driver);
	}

	public void ingresarUsuario(String pUsuario) {
		escribir(fldUsuario, pUsuario);
	}

	public void ingresarContrasenia(String pContrasenia) {
		escribir(fldContrasenia, pContrasenia);
	}


	public void clickIngresar() {
		int maxReintentos = 5; // Número máximo de reintentos
		int intentos = 0;
		boolean paginaCargada = false;
		clickear(btnIngresar);
		while (intentos < maxReintentos && !paginaCargada) {


			// Si aparece btnCierreForzado, haz clic
			 if (existe(btnCierreForzado)) {
			 	clickear(btnCierreForzado);
			 }

			// Intentar cerrar modales si aparecen
			tryCloseModalsIfPresent(driver);

			// Verifica si la página principal está presente
			// paginaCargada = existe(paginaPrincipal);
			paginaCargada = isOnDashboardPage();

			// if (!paginaCargada) {
			// 	System.out.println("Intento " + (intentos + 1) + ": No se encontró paginaPrincipal. Reintentando...");
			// }
			intentos++;
		}


		// if (!paginaCargada) {
		// 	throw new RuntimeException("No se pudo cargar paginaPrincipal después de " + maxReintentos + " intentos.");
		// }

		// System.out.println("paginaPrincipal encontrada después de " + intentos + " intentos.");
	}

	public void esperarLogin() {
		// existe(fldUsuario);
	}


}
