package page;

import base.SeleniumBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ConsultaCuenta extends SeleniumBase {
	private final By rbtApellidoNombre = By.xpath("//a[@id='ctl00_ContentFiltros_BusquedaCuenta1_optApellido']");
	private final By fldApellidoNombre = By.xpath("//input[@id='ctl00_ContentFiltros_BusquedaCuenta1_txtBusqApellidoNombre_text']");
	private final By btnBuscar = By.xpath("//input[@value='Buscar']");

	/**
	 * Constructor que inicializa el driver de Selenium.
	 *
	 * @param driver instancia de WebDriver
	 */
	public ConsultaCuenta(WebDriver driver) {
		super(driver);
	}

	public void clickApellidoNombre() {
		cambiarFocoNuevaPestania();
		// Espera hasta que el enlace esté visible y luego haz clic
		existe(rbtApellidoNombre);
		clickear(rbtApellidoNombre);

		System.out.println("Se hizo clic en el enlace en la nueva pestaña.");
	}

	public void ingresarApellidoNombre(String pApellidoNombre) {
		escribir(fldApellidoNombre, pApellidoNombre);
	}

	public void clickBuscar() {
		clickear(btnBuscar);
	}
}
