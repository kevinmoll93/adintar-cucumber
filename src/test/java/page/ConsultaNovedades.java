package page;

import base.SeleniumBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ConsultaNovedades extends SeleniumBase {
	private final By rbtFormatoPDF = By.xpath("//input[@id='ctl00_optPDF']");
	private final By rbtFormatoExcel = By.xpath("//input[@id='ctl00_optExcel']");

	private final By rbtDetalle = By.xpath("//a[@id='ctl00_ContentFiltros_optTipoReporteD']");
	private final By rbtTotales = By.xpath("//a[@id='ctl00_ContentFiltros_optTipoReporteT']");
	private final By lnkAdministradora = By.xpath("//div[@id='ctl00_ContentFiltros_cmbAdm']");
	private final By lnkNovedadTipo = By.xpath("//input[@id='ctl00_ContentFiltros_cmbTipNov_Input']");
	private final By fldFechaAltaDesde = By.xpath("//input[@id='ctl00_ContentFiltros_CalendarFDesde_dateInput_text']");

	private final By fldCuenta = By.xpath("//input[@id='ctl00_ContentFiltros_textCue_text']");
	private final By lnkOrigen = By.xpath("//div[@id='ctl00_ContentFiltros_cmbOriNov']");
	private final By lnkEstado = By.xpath("//div[@id='ctl00_ContentFiltros_cmbEstNov']");
	private final By fldFechaAltaHasta = By.xpath("//input[@id='ctl00_ContentFiltros_CalendarFHasta_dateInput_text']");

	private final By btnGenerarReporte = By.xpath("//input[@id='ctl00_cmdGenerar']");

	private final By lnkDescarga = By.xpath("/html");

	/**
	 * Constructor que inicializa el driver de Selenium.
	 *
	 * @param driver instancia de WebDriver
	 */
	public ConsultaNovedades(WebDriver driver) {
		super(driver);
	}

	public void clickFormatoSalidaPDF() {
		cambiarFocoNuevaPestania();
		existe(rbtFormatoPDF);
		clickear(rbtFormatoPDF);
	}

	public void clickFormatoSalidaExcel() {
		cambiarFocoNuevaPestania();
		existe(rbtFormatoExcel);
		clickear(rbtFormatoExcel);
	}

	public void clickTipoReporteDetalles() {
		clickear(rbtDetalle);
	}

	public void clickTipoReporteTotales() {
		clickear(rbtTotales);
	}

	public void seleccionarAdministradora(String pAdmin) {
		clickear(lnkAdministradora);
		String lstAdministradora = ("//div[@id='ctl00_ContentFiltros_cmbAdm_DropDown']//li[contains(text(),'%s')]");
		seleccionarOpcionConTexto(lstAdministradora, pAdmin);
	}

	public void seleccionarNovedadTipo(String pTipo) {
		escribir(lnkNovedadTipo, pTipo);
	}

	public void ingresarFechaAltaDesde(String pFechaDesde) {
		escribir(fldFechaAltaDesde, pFechaDesde);
	}

	public void ingresarCuenta(String pCuenta) {
		escribir(fldCuenta, pCuenta);
	}

	public void seleccionarOrigen(String pOrigen) {
		clickear(lnkOrigen);
		String lstOrigen = ("//div[@id='ctl00_ContentFiltros_cmbOriNov_DropDown']//li[contains(text(),'%s')]");
		seleccionarOpcionConTexto(lstOrigen, pOrigen);
	}

	public void seleccionarEstado(String pEstado) {
		clickear(lnkEstado);
		String lstEstado = ("//div[@id='ctl00_ContentFiltros_cmbEstNov_DropDown']//li[contains(text(),'%s')]");
		seleccionarOpcionConTexto(lstEstado, pEstado);
	}

	public void ingresarFechaAltaHasta(String pFechaHasta) {
		escribir(fldFechaAltaHasta, pFechaHasta);
	}

	public void clickGenerarReporte() {
		clickear(btnGenerarReporte);
	}

	public void clickBotonAbrirDentroIframe() {
		try {
			// Cambiar al iframe
			driver.switchTo().frame("ctl00_framePDF");

			// Ahora sí buscar y hacer click
			WebElement botonAbrir = driver.findElement(By.id("open-button"));
			botonAbrir.click();

			System.out.println("Botón 'Abrir' clickeado correctamente dentro del iframe.");

			// Volver al contenido principal
			driver.switchTo().defaultContent();

		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Error al hacer click en 'Abrir': " + e.getMessage());
		}
	}

	public void guardarPDF(String pPDF) {
		descargarArchivoNoSeguro(120, "C:\\Temp", ".pdf");
		renombrarArchivo(pPDF, "C:\\Temp", ".pdf", 50);
	}

	public void guardarExcel(String pExcel) {
		descargarArchivoNoSeguro(120, "C:\\Temp", ".xls");
		renombrarArchivo(pExcel, "C:\\Temp", ".xls", 50);
	}
}
