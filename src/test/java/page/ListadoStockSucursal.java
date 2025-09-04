package page;

import base.SeleniumBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ListadoStockSucursal extends SeleniumBase {
	private final By rbtFormatoPDF = By.xpath("//input[@id='ctl00_optPDF']");
	private final By rbtFormatoExcel = By.xpath("//input[@id='ctl00_optExcel']");

	private final By lnkTipoListado = By.xpath("//input[@id='ctl00_ContentFiltros_cboListado_Input']");
	private final By lnkSucursal = By.xpath("//input[@id='ctl00_ContentFiltros_cboSucursal_Input']");
	private final By lnkAdministradora = By.xpath("//input[@id='ctl00_ContentFiltros_cboAdmininstradora_Input']");
	private final By lnkTipoAccion = By.xpath("//input[@id='ctl00_ContentFiltros_cboAccion_Input']");
	private final By lnkOrigen = By.xpath("//input[@id='ctl00_ContentFiltros_cboOrigen_Input']");
	private final By lnkEstado = By.xpath("//input[@id='ctl00_ContentFiltros_cboEstado_Input']");

	private final By clnFechaEstadoDesde = By.xpath("//input[@id='ctl00_ContentFiltros_cFechaEstadoD_dateInput_text']");
	private final By clnFechaEstadoHasta = By.xpath("//input[@id='ctl00_ContentFiltros_cFechaEstadoH_dateInput_text']");
	private final By clnFechaRecepcionDesde = By.xpath("//input[@id='ctl00_ContentFiltros_cFechaRecepD_dateInput_text']");
	private final By clnFechaRecepcionHasta = By.xpath("//input[@id='ctl00_ContentFiltros_cFechaRecepH_dateInput_text']");

	private final By lnkArchivoOrigen = By.xpath("//input[@id='ctl00_ContentFiltros_cboArchivo_Input']");
	private final By lnkSucursalOrigen = By.xpath("//input[@id='ctl00_ContentFiltros_cboSucOrigen_Input']");

	private final By btnGenerarReporte = By.xpath("//input[@id='ctl00_cmdGenerar']");

	/**
	 * Constructor que inicializa el driver de Selenium.
	 *
	 * @param driver instancia de WebDriver
	 */
	public ListadoStockSucursal(WebDriver driver) {
		super(driver);
	}

	public void clickFormatoPDF() {
		cambiarFocoNuevaPestania();
		clickear(rbtFormatoPDF);
	}

	public void clickFormatoExcel() {
		cambiarFocoNuevaPestania();
		clickear(rbtFormatoExcel);
	}

	public void seleccionarTipolistado(String pTipo) {
		clickear(lnkTipoListado);
		String lstTipoListado = ("//div[@id='ctl00_ContentFiltros_cboListado_DropDown']//li[contains(text(),'%s')]");
		seleccionarOpcionConTexto(lstTipoListado, pTipo);
	}

	public void seleccionarSucursal(String pSucursal) {
		clickear(lnkSucursal);
		String lstSucursal = ("//div[@id='ctl00_ContentFiltros_cboSucursal_DropDown']//li[contains(text(),'%s')]");
		seleccionarOpcionConTexto(lstSucursal, pSucursal);
	}

	public void seleccionarAdministradora(String pAdministradora) {
		clickear(lnkAdministradora);
		String lstAdministradora = ("//div[@id='ctl00_ContentFiltros_cboAdmininstradora_DropDown']//li[contains(text(),'%s')]");
		seleccionarOpcionConTexto(lstAdministradora, pAdministradora);
	}

	public void seleccionarTipoAccion(String pAccion) {
		try {
			WebElement combo = driver.findElement(lnkTipoAccion);

			// Validar si tiene el atributo "disabled"
			String disabledAttr = combo.getAttribute("disabled");

			if (disabledAttr == null) {
				// Combo habilitado
				clickear(lnkTipoAccion);

				String lstTipoAccion = String.format("//div[@id='ctl00_ContentFiltros_cboAccion_DropDown']//li[contains(text(),'%s')]", pAccion);

				seleccionarOpcionConTexto(lstTipoAccion, pAccion);
				System.out.println("tipo de Accion seleccionada: " + pAccion);
			} else {
				// Combo deshabilitado
				System.out.println("El select de tipo de Accion está deshabilitado. Se omite selección.");
			}
		} catch (Exception e) {
			System.err.println("Error al seleccionar tipo de Accion: " + e.getMessage());
		}
	}

	public void seleccionarOrigen(String pOrigen) {
		clickear(lnkOrigen);
		String lstOrigen = ("//div[@id='ctl00_ContentFiltros_cboOrigen_DropDown']//li[contains(text(),'%s')]");
		seleccionarOpcionConTexto(lstOrigen, pOrigen);
	}

	public void seleccionarEstado(String pEstado) {
		clickear(lnkEstado);
		String lstEstado = ("//div[@id='ctl00_ContentFiltros_cboEstado_DropDown']//li[contains(text(),'%s')]");
		seleccionarOpcionConTexto(lstEstado, pEstado);
	}

	public void ingresarFechaEstado(String pFechaDesde, String pFechaHasta) {
		escribir(clnFechaEstadoDesde, pFechaDesde);
		escribir(clnFechaEstadoHasta, pFechaHasta);
	}

	public void ingresarFechaRecepcion(String pFechaDesde, String pFechaHasta) {
		escribir(clnFechaRecepcionDesde, pFechaDesde);
		escribir(clnFechaRecepcionHasta, pFechaHasta);
	}

	public void seleccionarArchivoOrigen(String pArchivo) {
		try {
			WebElement combo = driver.findElement(lnkArchivoOrigen);

			// Validar si tiene el atributo "disabled"
			String disabledAttr = combo.getAttribute("disabled");

			if (disabledAttr == null) {
				// Combo habilitado
				clickear(lnkArchivoOrigen);

				String lstArchivoOrigen = String.format("//div[@id='ctl00_ContentFiltros_cboArchivo_DropDown']//li[contains(text(),'%s')]", pArchivo);

				seleccionarOpcionConTexto(lstArchivoOrigen, pArchivo);
				System.out.println("Archivo origen seleccionada: " + pArchivo);
			} else {
				// Combo deshabilitado
				System.out.println("El select de Archivo Origen está deshabilitado. Se omite selección.");
			}
		} catch (Exception e) {
			System.err.println("Error al seleccionar Archivo origen: " + e.getMessage());
		}
	}

	public void seleccionarSucursalOrigen(String pOrigen) {
		try {
			WebElement combo = driver.findElement(lnkSucursalOrigen);

			// Validar si tiene el atributo "disabled"
			String disabledAttr = combo.getAttribute("disabled");

			if (disabledAttr == null) {
				// Combo habilitado
				clickear(lnkSucursalOrigen);

				String lstSucursalOrigen = String.format("//div[@id='ctl00_ContentFiltros_cboSucOrigen_DropDown']//li[contains(text(),'%s')]", pOrigen);

				seleccionarOpcionConTexto(lstSucursalOrigen, pOrigen);
				System.out.println("Sucursal origen seleccionada: " + pOrigen);
			} else {
				// Combo deshabilitado
				System.out.println("El select de Sucursal Origen está deshabilitado. Se omite selección.");
			}
		} catch (Exception e) {
			System.err.println("Error al seleccionar sucursal origen: " + e.getMessage());
		}
	}

	public void clickGenerarReporte() {
		clickear(btnGenerarReporte);
	}
}
