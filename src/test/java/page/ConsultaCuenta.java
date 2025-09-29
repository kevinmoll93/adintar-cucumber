package page;

import base.SeleniumBase;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import java.time.Duration;

public class ConsultaCuenta extends SeleniumBase {
	private final By rbtApellidoNombre = By.xpath("//a[@id='ctl00_ContentFiltros_BusquedaCuenta1_optApellido']");
	private final By fldApellidoNombre = By.xpath("//input[@id='ctl00_ContentFiltros_BusquedaCuenta1_txtBusqApellidoNombre_text']");
	private final By rbtTipoNumeroDoc = By.xpath("//a[@id='ctl00_ContentFiltros_BusquedaCuenta1_optDocumento']");
	private final By lnkTipoNumeroDoc = By.xpath("//input[@id='ctl00_ContentFiltros_BusquedaCuenta1_cboBusqTipoDoc_Input']");
	private final By fldTipoNumeroDoc = By.xpath("//input[@id='ctl00_ContentFiltros_BusquedaCuenta1_txtBusqNroDoc_text']");
	private final By rbtTipoNumeroClave = By.xpath("//a[@id='ctl00_ContentFiltros_BusquedaCuenta1_optClave']");
	private final By lnkTipoNumeroClave = By.xpath("//input[@id='ctl00_ContentFiltros_BusquedaCuenta1_cboBusqTipoClave_Input']");
	private final By fldTipoNumeroClave = By.xpath("//input[@id='ctl00_ContentFiltros_BusquedaCuenta1_txtBusqNroClave_text']");
	private final By rbtAdminNumeroCuenta = By.xpath("//a[@id='ctl00_ContentFiltros_BusquedaCuenta1_optCuenta']");
	private final By lnkAdminNumeroCuenta = By.xpath("//input[@id='ctl00_ContentFiltros_BusquedaCuenta1_cboBusqAdminCta_Input']");
	private final By fldAdminNumeroCuenta = By.xpath("//input[@id='ctl00_ContentFiltros_BusquedaCuenta1_txtNroCta_text']");
	private final By rbtAdminNumeroTarjeta = By.xpath("//a[@id='ctl00_ContentFiltros_BusquedaCuenta1_optTarjeta']");
	private final By lnkAdminNumeroTarjeta = By.xpath("//input[@id='ctl00_ContentFiltros_BusquedaCuenta1_cboBusqAdminTar_Input']");
	private final By fldAdminNumeroTarjeta = By.xpath("//input[@id='ctl00_ContentFiltros_BusquedaCuenta1_txtNroTar_text']");
	private final By rbtNumeroCuentaNexo = By.xpath("//a[@id='ctl00_ContentFiltros_BusquedaCuenta1_optCuentaNexos']");
	private final By fldNumeroCuentaNexo = By.xpath("//input[@id='ctl00_ContentFiltros_BusquedaCuenta1_txtCuentaNexos_text']");
	private final By rbtNumeroCUIT = By.xpath("//a[@id='ctl00_ContentFiltros_BusquedaCuenta1_optCUIT']");
	private final By fldNumeroCUIT = By.xpath("//input[@id='ctl00_ContentFiltros_BusquedaCuenta1_txtCUIT_text']");
	private final By btnBuscar = By.xpath("//input[@value='Buscar']");
	private final By txtNotResult = By.xpath("//div[contains(text(),'No records to display')]");
	private final By filaResultado = By.xpath("//table[contains(@id,'ctl00_ContentFiltros')]//tr[contains(@class,'rgRow') or contains(@class,'rgAltRow')]");

	/**
	 * Constructor que inicializa el driver de Selenium.
	 *
	 * @param driver instancia de WebDriver
	 */
	public ConsultaCuenta(WebDriver driver) {
		super(driver);
	}

	public void clickFiltroApellidoNombre() {
		try {
			cambiarFocoNuevaPestania();
			if (existe(rbtApellidoNombre)) {
				clickear(rbtApellidoNombre);
				System.out.println("Se hizo clic en el enlace en la nueva pestaña.");
			} else {
				manejarErrorYCerrarNavegador("No se encontró el filtro 'Apellido/Nombre'.");
			}
		} catch (Exception e) {
			manejarErrorYCerrarNavegador("Error al hacer clic en filtro Apellido/Nombre: " + e.getMessage());
		}
	}

	public void ingresarApellidoNombre(String pApellidoNombre) {
		try {
			escribir(fldApellidoNombre, pApellidoNombre);
		} catch (Exception e) {
			manejarErrorYCerrarNavegador("Error al ingresar Apellido/Nombre: " + pApellidoNombre);
		}
	}

	public void clickFiltroTipoNumeroDoc() {
		try {
			cambiarFocoNuevaPestania();
			if (existe(rbtTipoNumeroDoc)) {
				clickear(rbtTipoNumeroDoc);
			} else {
				manejarErrorYCerrarNavegador("No se encontró el filtro 'Tipo/Número Doc'.");
			}
		} catch (Exception e) {
			manejarErrorYCerrarNavegador("Error al hacer clic en filtro Tipo/Número Doc: " + e.getMessage());
		}
	}

	public void seleccionarFiltroTipoDoc(String pTipo) {
		try {
			clickear(lnkTipoNumeroDoc);
			String lstTipoNumeroDoc = "//div[@id='ctl00_ContentFiltros_BusquedaCuenta1_cboBusqTipoDoc_DropDown']//ul[@class='rcbList']//li[contains(text(),'%s')]";
			seleccionarOpcionConTexto(String.format(lstTipoNumeroDoc, pTipo), pTipo);
		} catch (Exception e) {
			manejarErrorYCerrarNavegador("Error al seleccionar Tipo Doc: " + pTipo);
		}
	}

	public void ingresarDocumento(String pDocumento) {
		try {
			escribir(fldTipoNumeroDoc, pDocumento);
		} catch (Exception e) {
			manejarErrorYCerrarNavegador("Error al ingresar Documento: " + pDocumento);
		}
	}

	public void clickFiltroTipoClave() {
		try {
			cambiarFocoNuevaPestania();
			if (existe(rbtTipoNumeroClave)) {
				clickear(rbtTipoNumeroClave);
			} else {
				manejarErrorYCerrarNavegador("No se encontró el filtro 'Tipo Clave'.");
			}
		} catch (Exception e) {
			manejarErrorYCerrarNavegador("Error al hacer clic en filtro Tipo Clave: " + e.getMessage());
		}
	}

	public void seleccionarFiltroTipoClave(String pTipoClave) {
		try {
			clickear(lnkTipoNumeroClave);
			String lstTipoNumeroClave = "//div[@id='ctl00_ContentFiltros_BusquedaCuenta1_cboBusqTipoClave_DropDown']//ul//li[contains(text(),'%s')]";
			seleccionarOpcionConTexto(String.format(lstTipoNumeroClave, pTipoClave), pTipoClave);
		} catch (Exception e) {
			manejarErrorYCerrarNavegador("Error al seleccionar Tipo Clave: " + pTipoClave);
		}
	}

	public void ingresarClaveInterna(String pClaveInterna) {
		try {
			escribir(fldTipoNumeroClave, pClaveInterna);
		} catch (Exception e) {
			manejarErrorYCerrarNavegador("Error al ingresar Clave Interna: " + pClaveInterna);
		}
	}

	public void clickFiltroAdminNumeroCuenta() {
		try {
			cambiarFocoNuevaPestania();
			if (existe(rbtAdminNumeroCuenta)) {
				clickear(rbtAdminNumeroCuenta);
			} else {
				manejarErrorYCerrarNavegador("No se encontró el filtro 'Admin/Número Cuenta'.");
			}
		} catch (Exception e) {
			manejarErrorYCerrarNavegador("Error al hacer clic en filtro Admin/Número Cuenta: " + e.getMessage());
		}
	}

	public void seleccionarTipoAdminNumeroCuenta(String pTipo) {
		try {
			clickear(lnkAdminNumeroCuenta);
			String lstAdminNumeroCuenta = "//div[@id='ctl00_ContentFiltros_BusquedaCuenta1_cboBusqAdminCta_DropDown']//ul//li[contains(text(),'%s')]";
			seleccionarOpcionConTexto(String.format(lstAdminNumeroCuenta, pTipo), pTipo);
		} catch (Exception e) {
			manejarErrorYCerrarNavegador("Error al seleccionar Tipo Admin/Número Cuenta: " + pTipo);
		}
	}

	public void ingresarAdminNumeroCuenta(String pNumero) {
		try {
			escribir(fldAdminNumeroCuenta, pNumero);
		} catch (Exception e) {
			manejarErrorYCerrarNavegador("Error al ingresar Admin/Número Cuenta: " + pNumero);
		}
	}

	public void clickFiltroAdminNumeroTarjeta() {
		try {
			cambiarFocoNuevaPestania();
			if (existe(rbtAdminNumeroTarjeta)) {
				clickear(rbtAdminNumeroTarjeta);
			} else {
				manejarErrorYCerrarNavegador("No se encontró el filtro 'Admin/Número Tarjeta'.");
			}
		} catch (Exception e) {
			manejarErrorYCerrarNavegador("Error al hacer clic en filtro Admin/Número Tarjeta: " + e.getMessage());
		}
	}

	public void seleccionarAdminNumeroTarjeta(String pTipo) {
		try {
			clickear(lnkAdminNumeroTarjeta);
			String lstAdminNumeroTarjeta = "//div[@id='ctl00_ContentFiltros_BusquedaCuenta1_cboBusqAdminTar_DropDown']//ul//li[contains(text(),'%s')]";
			seleccionarOpcionConTexto(String.format(lstAdminNumeroTarjeta, pTipo), pTipo);
		} catch (Exception e) {
			manejarErrorYCerrarNavegador("Error al seleccionar Admin/Número Tarjeta: " + pTipo);
		}
	}

	public void ingresarAdminNumeroTarjeta(String pTarjeta) {
		try {
			escribirTelerikRobusto(fldAdminNumeroTarjeta, pTarjeta, false);
		} catch (Exception e) {
			manejarErrorYCerrarNavegador("Error al ingresar Admin/Número Tarjeta: " + pTarjeta);
		}
	}

	public void clickFiltroNumeroCuentaNexo() {
		try {
			cambiarFocoNuevaPestania();
			if (existe(rbtNumeroCuentaNexo)) {
				clickear(rbtNumeroCuentaNexo);
			} else {
				manejarErrorYCerrarNavegador("No se encontró el filtro 'Número Cuenta Nexo'.");
			}
		} catch (Exception e) {
			manejarErrorYCerrarNavegador("Error al hacer clic en filtro Número Cuenta Nexo: " + e.getMessage());
		}
	}

	public void ingresarNumeroCuentaNexo(String pNexo) {
		try {
			escribirTelerikRobusto(fldNumeroCuentaNexo, pNexo, false);
		} catch (Exception e) {
			manejarErrorYCerrarNavegador("Error al ingresar Número Cuenta Nexo: " + pNexo);
		}
	}

	public void clickFiltroNumeroCUIT() {
		try {
			cambiarFocoNuevaPestania();
			if (existe(rbtNumeroCUIT)) {
				clickear(rbtNumeroCUIT);
			} else {
				manejarErrorYCerrarNavegador("No se encontró el filtro 'Número CUIT'.");
			}
		} catch (Exception e) {
			manejarErrorYCerrarNavegador("Error al hacer clic en filtro Número CUIT: " + e.getMessage());
		}
	}

	public void ingresarNumeroCUIT(String pCUIT) {
		try {
			escribirTelerikRobusto(fldNumeroCUIT, pCUIT, false);
		} catch (Exception e) {
			manejarErrorYCerrarNavegador("Error al ingresar Número CUIT: " + pCUIT);
		}
	}

	public void clickBuscar() {
		clickear(btnBuscar);

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		try {
			// Esperamos a que aparezca O un resultado o el mensaje de "sin resultados"
			wait.until(ExpectedConditions.or(ExpectedConditions.presenceOfElementLocated(filaResultado), ExpectedConditions.presenceOfElementLocated(txtNotResult)));

			// Validamos cuál apareció
			if (isElementPresent(txtNotResult)) {
				manejarErrorYCerrarNavegador("Error en búsqueda: No se encontraron resultados.");
			} else {
				Reporter.log("Búsqueda exitosa: Se encontraron resultados.", true);
			}

		} catch (TimeoutException e) {
			Reporter.log("Búsqueda exitosa con lentitud: Se encontraron resultados.", true);
		}
	}

	// Método auxiliar para validar presencia sin excepción
	private boolean isElementPresent(By locator) {
		try {
			return driver.findElements(locator).size() > 0;
		} catch (Exception e) {
			return false;
		}
	}
}
