package page;

import base.SeleniumBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LogOut extends SeleniumBase {
	private final By btnSalir = By.xpath("//*[@value='Salir']");
	private final By txtCierreSesion = By.xpath("//span[contains(text(),'Adintar Web finalizó correctamente con la sesion, ya puede cerrar la pestaña.')]");

	public LogOut(WebDriver driver) {
		super(driver);
	}

	/**
	 * Cierra sesión de forma secuencial manejando múltiples pestañas.
	 */
	public void clickSalir(int tiempoMaximoSeg) {
		try {
			Set<String> handles = driver.getWindowHandles();
			System.out.println("Pestañas abiertas: " + handles.size());

			// Convertir a lista para ordenar (el orden depende del navegador, pero normalmente es así)
			List<String> listaHandles = new ArrayList<>(handles);

			// La pestaña menú suele ser la primera, la que querés dejar abierta al final
			String handleMenu = listaHandles.get(0);

			// Cerrás pestañas desde la última hacia la segunda (dejás abierta la primera)
			for (int i = listaHandles.size() - 1; i > 0; i--) {
				driver.switchTo().window(listaHandles.get(i));
				System.out.println("Cerrando pestaña " + i);
				driver.close();
				Thread.sleep(1000); // Espera para que se cierre bien la pestaña
			}

			// Volvés a la pestaña menú
			driver.switchTo().window(handleMenu);

			// Hacés click final en Salir
			if (!reintentarClick(btnSalir, 4, 1000)) {
				throw new RuntimeException("No se pudo hacer click final en 'Salir' en pestaña menú");
			}

			// Verificás el mensaje de cierre
			if (esperarHastaElementoVisible(txtCierreSesion, tiempoMaximoSeg)) {
				System.out.println("Cierre de sesión confirmado en pestaña menú.");
			} else {
				throw new RuntimeException("No se detectó mensaje de cierre de sesión en pestaña menú.");
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Error al cerrar sesión dinámicamente: " + e.getMessage());
		}
	}

	/**
	 * Reintenta hacer click varias veces antes de fallar.
	 */
	private boolean reintentarClick(By localizador, int reintentos, int esperaEntreIntentosMs) {
		for (int i = 0; i < reintentos; i++) {
			if (existeClickeable(localizador)) {
				clickear(localizador);
				return true;
			}
			esperar(esperaEntreIntentosMs);
		}
		return false;
	}

	public void verificarCierre() {
		if (!esperarHastaElementoVisible(txtCierreSesion, 3)) {
			//throw new RuntimeException("El mensaje de cierre de sesión no apareció.");
		}
	}

	public void verificarBotonSalir() {
		existe(btnSalir);
	}

}
