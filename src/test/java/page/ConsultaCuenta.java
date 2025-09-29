package page;

import base.SeleniumBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

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
	private final By txtNotResult = By.xpath("//tr[@class='rgNoRecords']");
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
		cambiarFocoNuevaPestania();
		// Espera hasta que el enlace esté visible y luego haz clic
		existe(rbtApellidoNombre);
		clickear(rbtApellidoNombre);

		System.out.println("Se hizo clic en el enlace en la nueva pestaña.");
	}

	public void ingresarApellidoNombre(String pApellidoNombre) {
		escribir(fldApellidoNombre, pApellidoNombre);
	}

	public void clickFiltroTipoNumeroDoc() {
		cambiarFocoNuevaPestania();
		existe(rbtTipoNumeroDoc);
		clickear(rbtTipoNumeroDoc);
	}

	public void seleccionarFiltroTipoDoc(String pTipo) {
		clickear(lnkTipoNumeroDoc);
		String lstTipoNumeroDoc = ("//div[@id='ctl00_ContentFiltros_BusquedaCuenta1_cboBusqTipoDoc_DropDown']//ul[@class='rcbList']//li[contains(text(),'%s')]");
		seleccionarOpcionConTexto(lstTipoNumeroDoc, pTipo);
	}

	public void ingresarDocumento(String pDocumento) {
		escribir(fldTipoNumeroDoc, pDocumento);
	}

	public void clickFiltroTipoClave() {
		cambiarFocoNuevaPestania();
		existe(rbtTipoNumeroClave);
		clickear(rbtTipoNumeroClave);
	}

	public void seleccionarFiltroTipoClave(String pTipoClave) {
		clickear(lnkTipoNumeroClave);
		String lstTipoNumeroClave = ("//div[@id='ctl00_ContentFiltros_BusquedaCuenta1_cboBusqTipoClave_DropDown']//ul//li[contains(text(),'%s')]");
		seleccionarOpcionConTexto(lstTipoNumeroClave, pTipoClave);
	}

	public void ingresarClaveInterna(String pClaveInterna) {
		escribir(fldTipoNumeroClave, pClaveInterna);
	}

	public void clickFiltroAdminNumeroCuenta() {
		cambiarFocoNuevaPestania();
		existe(rbtAdminNumeroCuenta);
		clickear(rbtAdminNumeroCuenta);
	}

	public void seleccionarTipoAdminNumeroCuenta(String pTipo) {
		clickear(lnkAdminNumeroCuenta);
		String lstAdminNumeroCuenta = ("//div[@id='ctl00_ContentFiltros_BusquedaCuenta1_cboBusqAdminCta_DropDown']//ul//li[contains(text(),'%s')]");
		seleccionarOpcionConTexto(lstAdminNumeroCuenta, pTipo);
	}

	public void ingresarAdminNumeroCuenta(String pNumero) {
		escribir(fldAdminNumeroCuenta, pNumero);
	}

	public void clickFiltroAdminNumeroTarjeta() {
		cambiarFocoNuevaPestania();
		existe(rbtAdminNumeroTarjeta);
		clickear(rbtAdminNumeroTarjeta);
	}

	public void seleccionarAdminNumeroTarjeta(String pTipo) {
		clickear(lnkAdminNumeroTarjeta);
		String lstAdminNumeroTarjeta = ("//div[@id='ctl00_ContentFiltros_BusquedaCuenta1_cboBusqAdminTar_DropDown']//ul//li[contains(text(),'%s')]");
		seleccionarOpcionConTexto(lstAdminNumeroTarjeta, pTipo);
	}

	public void ingresarAdminNumeroTarjeta(String pTarjeta) {
		escribirTelerikRobusto(fldAdminNumeroTarjeta, pTarjeta, false);
	}

	public void clickFiltroNumeroCuentaNexo() {
		cambiarFocoNuevaPestania();
		existe(rbtNumeroCuentaNexo);
		clickear(rbtNumeroCuentaNexo);
	}

	public void ingresarNumeroCuentaNexo(String pNexo) {
		escribirTelerikRobusto(fldNumeroCuentaNexo, pNexo, false);
	}

	public void clickFiltroNumeroCUIT() {
		cambiarFocoNuevaPestania();
		existe(rbtNumeroCUIT);
		clickear(rbtNumeroCUIT);
	}

	public void ingresarNumeroCUIT(String pCUIT) {
		escribirTelerikRobusto(fldNumeroCUIT, pCUIT, false);
	}

	public void clickBuscar() {
		try {
			// Paso 1: hacer clic en el botón buscar
			clickear(btnBuscar);

			// Paso 2: esperar que aparezca o un resultado o el mensaje de "no records"
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			Boolean hayResultados = wait.until(driver -> {
				if (driver.findElements(txtNotResult).size() > 0) {
					return false; // apareció el mensaje de "no records"
				}
				if (driver.findElements(filaResultado).size() > 1) {
					return true; // apareció al menos un resultado
				}
				return null; // seguir esperando
			});

			if (Boolean.FALSE.equals(hayResultados)) {
				throw new RuntimeException("No se encontraron resultados en la búsqueda.");
			}

			System.out.println("Búsqueda ejecutada con resultados.");
		} catch (Exception e) {
			System.err.println("Error en búsqueda: " + e.getMessage());
		}
	}
}
