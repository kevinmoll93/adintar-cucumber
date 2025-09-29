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
		if (existe(rbtFormatoPDF)) {
			clickear(rbtFormatoPDF);
		} else {
			manejarErrorYCerrarNavegador("No se encontró el botón Formato PDF.");
		}
	}

	public void clickFormatoSalidaExcel() {
		cambiarFocoNuevaPestania();
		if (existe(rbtFormatoExcel)) {
			clickear(rbtFormatoExcel);
		} else {
			manejarErrorYCerrarNavegador("No se encontró el botón Formato Excel.");
		}
	}

	public void clickTipoReporteDetalles() {
		if (existe(rbtDetalle)) {
			clickear(rbtDetalle);
		} else {
			manejarErrorYCerrarNavegador("No se encontró el botón Detalle.");
		}
	}

	public void clickTipoReporteTotales() {
		if (existe(rbtTotales)) {
			clickear(rbtTotales);
		} else {
			manejarErrorYCerrarNavegador("No se encontró el botón Totales.");
		}
	}

	public void seleccionarAdministradora(String pAdmin) {
		try {
			clickear(lnkAdministradora);
			String lstAdministradora = "//div[@id='ctl00_ContentFiltros_cmbAdm_DropDown']//li[contains(text(),'%s')]";
			seleccionarOpcionConTexto(String.format(lstAdministradora, pAdmin), pAdmin);
		} catch (Exception e) {
			manejarErrorYCerrarNavegador("Error al seleccionar administradora: " + pAdmin);
		}
	}

	public void seleccionarNovedadTipo(String pTipo) {
		try {
			escribir(lnkNovedadTipo, pTipo);
		} catch (Exception e) {
			manejarErrorYCerrarNavegador("Error al escribir Novedad Tipo: " + pTipo);
		}
	}

	public void ingresarFechaAltaDesde(String pFechaDesde) {
		try {
			escribir(fldFechaAltaDesde, pFechaDesde);
		} catch (Exception e) {
			manejarErrorYCerrarNavegador("Error al ingresar Fecha Alta Desde: " + pFechaDesde);
		}
	}

	public void ingresarCuenta(String pCuenta) {
		try {
			escribir(fldCuenta, pCuenta);
		} catch (Exception e) {
			manejarErrorYCerrarNavegador("Error al ingresar Cuenta: " + pCuenta);
		}
	}

	public void seleccionarOrigen(String pOrigen) {
		try {
			clickear(lnkOrigen);
			String lstOrigen = "//div[@id='ctl00_ContentFiltros_cmbOriNov_DropDown']//li[contains(text(),'%s')]";
			seleccionarOpcionConTexto(String.format(lstOrigen, pOrigen), pOrigen);
		} catch (Exception e) {
			manejarErrorYCerrarNavegador("Error al seleccionar Origen: " + pOrigen);
		}
	}

	public void seleccionarEstado(String pEstado) {
		try {
			clickear(lnkEstado);
			String lstEstado = "//div[@id='ctl00_ContentFiltros_cmbEstNov_DropDown']//li[contains(text(),'%s')]";
			seleccionarOpcionConTexto(String.format(lstEstado, pEstado), pEstado);
		} catch (Exception e) {
			manejarErrorYCerrarNavegador("Error al seleccionar Estado: " + pEstado);
		}
	}

	public void ingresarFechaAltaHasta(String pFechaHasta) {
		try {
			escribir(fldFechaAltaHasta, pFechaHasta);
		} catch (Exception e) {
			manejarErrorYCerrarNavegador("Error al ingresar Fecha Alta Hasta: " + pFechaHasta);
		}
	}

	public void clickGenerarReporte() {
		if (existe(btnGenerarReporte)) {
			clickear(btnGenerarReporte);
		} else {
			manejarErrorYCerrarNavegador("No se encontró el botón Generar Reporte.");
		}
	}

	public void clickBotonAbrirDentroIframe() {
		try {
			driver.switchTo().frame("ctl00_framePDF");
			WebElement botonAbrir = driver.findElement(By.id("open-button"));
			botonAbrir.click();
			System.out.println("Botón 'Abrir' clickeado correctamente dentro del iframe.");
		} catch (Exception e) {
			manejarErrorYCerrarNavegador("Error al hacer click en 'Abrir' dentro del iframe: " + e.getMessage());
		} finally {
			driver.switchTo().defaultContent();
		}
	}

	public void guardarPDF(String pPDF) {
		guardarArchivoComun(pPDF, ".pdf");
	}

	public void guardarExcel(String pExcel) {
		guardarArchivoComun(pExcel, ".xls");
	}

	private void guardarArchivoComun(String nombreArchivo, String extension) {
		try {
			descargarArchivoNoSeguro(120, "C:\\Temp", extension);
			renombrarArchivo(nombreArchivo, "C:\\Temp", extension, 50);
		} catch (Exception e) {
			manejarErrorYCerrarNavegador("Error al guardar archivo " + extension + ": " + e.getMessage());
		}
	}
}
