package page;

import base.SeleniumBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ConsultaNovedades extends SeleniumBase {
	private final By rbtFormatoPDF = By.xpath("//input[@id='ctl00_optPDF']");
	private final By rbtFormatoExcel = By.xpath("//input[@id='ctl00_optExcel']");

	private final By rbtDetalle = By.xpath("//a[@id='ctl00_ContentFiltros_optTipoReporteD']");
	private final By rbtTotales = By.xpath("//a[@id='ctl00_ContentFiltros_optTipoReporteT']");
	private final By lnkAdministradora = By.xpath("//div[@id='ctl00_ContentFiltros_cmbAdm']");
	private final String lstAdministradora = ("//div[@id='ctl00_ContentFiltros_cmbAdm_DropDown']//li[contains(text(),'%s')]");
	private final By lstNovedadTipo = By.xpath("//input[@id='ctl00_ContentFiltros_cmbTipNov_Input']");
	private final By lnkNovedadTipo = By.xpath("//div[@id='ctl00_ContentFiltros_cmbTipNov_DropDown']//li");
	private final By fldFechaAltaDesde = By.xpath("//input[@id='ctl00_ContentFiltros_CalendarFDesde_dateInput_text']");

	private final By fldCuenta = By.xpath("//input[@id='ctl00_ContentFiltros_textCue_text']");
	private final By lnkOrigen = By.xpath("//div[@id='ctl00_ContentFiltros_cmbOriNov']");
	private final String lstOrigen = ("//div[@id='ctl00_ContentFiltros_cmbOriNov_DropDown']//li[contains(text(),'%s')]");
	private final By lnkEstado = By.xpath("//div[@id='ctl00_ContentFiltros_cmbEstNov']");
	private final By lstEstado = By.xpath("//div[@id='ctl00_ContentFiltros_cmbEstNov_DropDown']//li[contains(text(),'%s')]");
	private final By fldFechaAltaHasta = By.xpath("//input[@id='ctl00_ContentFiltros_CalendarFHasta_dateInput_text']");

	private final By btnGenerarReporte = By.xpath("//input[@id='ctl00_cmdGenerar']");

	/**
	 * Constructor que inicializa el driver de Selenium.
	 *
	 * @param driver instancia de WebDriver
	 */
	public ConsultaNovedades(WebDriver driver) {
		super(driver);
	}
}
