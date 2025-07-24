package page;


import base.SeleniumBase;
import org.openqa.selenium.*;

import java.util.List;


public class MenuPrincipal extends SeleniumBase {
	private final By btnConsulta = By.xpath("//button[contains(.,'Consultas')]");
	private final By btnPagos = By.xpath("//button[contains(.,'Pagos')]");
	private final By lstBusqueda = By.xpath("//input[@id='mat-input-4']");
	private final By lstBusqueda2 = By.xpath("//input[@class='input-autocomplete']");
	private final By lstEncontrado = By.xpath("//div[@id='cdk-overlay-0']");
	private final By lstEncontrado2 = By.xpath("//datalist[@id='opciones']");
	private final By paginaPrincipal = By.xpath("//b[@class='font-weight-bold']");
	private final String txtBusqueda = ("//mat-option[%s]/span[@class='mat-option-text']");
	private final By lstMenuBusqueda = By.xpath("//input[@data-placeholder='Haberes, proveedores y otros, Haberes, proveedores y otros 2.5, C...']");
	

	public MenuPrincipal(WebDriver driver) {
		super(driver);
		
        
	}

	public void verificarPantalla() {
		// clickear(paginaPrincipal);
	}

	public void clickConsulta() {
		clickear(btnConsulta);
	}

	public void clickPagos() {
		clickear(btnPagos);
	}

	/**
	 * Ingresa un texto en el campo de búsqueda y selecciona el elemento encontrado.
	 *
	 * @param ptexto Texto a ingresar en la búsqueda.
	 */
	public void ingresarBusqueda(String ptexto) {
		// Intentar escribir en el primer campo de búsqueda
		if (escribirSiExiste(lstBusqueda, ptexto)) {
			clickearSiExiste(lstEncontrado);
		}
		// Si no está disponible, intentar con el segundo campo
		else if (escribirSiExiste(lstBusqueda2, ptexto)) {
			clickearSiExiste(lstEncontrado2);
		} else {
			System.out.println("No se encontró un campo de búsqueda válido.");
		}
	}

	public void ingresarBusquedaPrecisa(String busqueda) {
		clickear(lstMenuBusqueda);
		int filaCuenta = obtenerOpcionMenu(busqueda);

		if (filaCuenta != -1) {
			By lstMenu = By.xpath(String.format(txtBusqueda, filaCuenta));
			reintentarClick(lstMenu, 10); // Reintentar hasta 10 veces

		} else {

		}
	}

	/**
	 * Intenta escribir en un elemento si existe.
	 *
	 * @param locator Localizador del elemento.
	 * @param texto   Texto a escribir.
	 * @return True si el elemento existe y se escribió el texto, False si no.
	 */
	private boolean escribirSiExiste(By locator, String texto) {
		if (existe(locator)) {
			escribir(locator, texto);
			return true;
		}
		return false;
	}

	/**
	 * Intenta hacer clic en un elemento si existe.
	 *
	 * @param locator Localizador del elemento.
	 * @return True si el elemento existe y se hizo clic, False si no.
	 */
	private boolean clickearSiExiste(By locator) {
		if (existe(locator)) {
			clickear(locator);
			return true;
		}
		return false;
	}

	private int obtenerOpcionMenu(String opcion) {
		// Reevaluar las filas dinámicamente
		List<WebElement> filas = encontrarElementos(By.xpath("//mat-option/span[@class='mat-option-text']"));

		for (int i = 1; i <= filas.size(); i++) {
			By cuentaEnFila = By.xpath(String.format(txtBusqueda, i));

			try {
				// Validar si el elemento existe y es visible en el DOM
				WebElement elementoCuenta = esperarElemento(cuentaEnFila);
				String textoCuenta = elementoCuenta.getText();

				if (textoCuenta.equals(opcion)) {
					return i; // Retorna el índice de la fila
				}
			} catch (NoSuchElementException | TimeoutException e) {
				// Continuar si el elemento no está disponible
				continue;
			}
		}

		return -1; // No se encontró la cuenta
	}


}
