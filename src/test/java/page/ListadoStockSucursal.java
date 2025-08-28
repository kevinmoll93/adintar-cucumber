package page;

import base.SeleniumBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ListadoStockSucursal extends SeleniumBase {
	private final By rbtFormatoPDF = By.xpath("//input[@id='ctl00_optPDF']");
	private final By rbtFormatoExcel = By.xpath("//input[@id='ctl00_optExcel']");

	private final By lstTipoListado = By.xpath("//input[@id='ctl00_ContentFiltros_cboListado_Input']");
	private final By lstSucursal = By.xpath("//input[@id='ctl00_ContentFiltros_cboSucursal_Input']");
	private final By lstAdministradora = By.xpath("//input[@id='ctl00_ContentFiltros_cboAdmininstradora_Input']");
	private final By lstTipoAccion = By.xpath("//input[@id='ctl00_ContentFiltros_cboAccion_Input']");
	private final By lstOrigen = By.xpath("//input[@id='ctl00_ContentFiltros_cboOrigen_Input']");
	private final By lstEstado = By.xpath("//input[@id='ctl00_ContentFiltros_cboEstado_Input']");

	private final By clnFechaEstadoDesde = By.xpath("//input[@id='ctl00_ContentFiltros_cFechaEstadoD_dateInput_text']");
	private final By clnFechaEstadoHasta = By.xpath("//input[@id='ctl00_ContentFiltros_cFechaEstadoH_dateInput_text']");
	private final By clnFechaRecepcionDesde = By.xpath("//input[@id='ctl00_ContentFiltros_cFechaRecepD_dateInput_text']");
	private final By clnFechaRecepcionHasta = By.xpath("//input[@id='ctl00_ContentFiltros_cFechaRecepH_dateInput_text']");

	private final By lstArchivoOrigen = By.xpath("//input[@id='ctl00_ContentFiltros_cboArchivo_Input']");
	private final By lstSucursalOrigen = By.xpath("//input[@id='ctl00_ContentFiltros_cboSucOrigen_Input']");

	private final By btnGenerarReporte = By.xpath("//input[@id='ctl00_cmdGenerar']");

	/**
	 * Constructor que inicializa el driver de Selenium.
	 *
	 * @param driver instancia de WebDriver
	 */
	public ListadoStockSucursal(WebDriver driver) {
		super(driver);
	}
}
