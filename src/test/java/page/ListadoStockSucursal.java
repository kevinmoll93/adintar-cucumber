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
		try {
			cambiarFocoNuevaPestania();
			existe(rbtFormatoPDF);
			clickear(rbtFormatoPDF);
			System.out.println("Formato de salida PDF seleccionado correctamente.");
		} catch (Exception e) {
			manejarErrorYCerrarNavegador("Error al seleccionar formato PDF: " + e.getMessage());
		}
	}

	public void clickFormatoExcel() {
		try {
			cambiarFocoNuevaPestania();
			existe(rbtFormatoExcel);
			clickear(rbtFormatoExcel);
			System.out.println("Formato de salida Excel seleccionado correctamente.");
		} catch (Exception e) {
			manejarErrorYCerrarNavegador("Error al seleccionar formato Excel: " + e.getMessage());
		}
	}

	public void seleccionarTipolistado(String pTipo) {
		try {
			clickear(lnkTipoListado);
			String lstTipoListado = String.format("//div[@id='ctl00_ContentFiltros_cboListado_DropDown']//li[contains(text(),'%s')]", pTipo);
			seleccionarOpcionConTexto(lstTipoListado, pTipo);
			System.out.println("Tipo de listado seleccionado: " + pTipo);
		} catch (Exception e) {
			manejarErrorYCerrarNavegador("Error al seleccionar tipo de listado: " + e.getMessage());
		}
	}

	public void seleccionarSucursal(String pSucursal) {
		try {
			clickear(lnkSucursal);
			String lstSucursal = String.format("//div[@id='ctl00_ContentFiltros_cboSucursal_DropDown']//li[contains(text(),'%s')]", pSucursal);
			seleccionarOpcionConTexto(lstSucursal, pSucursal);
			System.out.println("Sucursal seleccionada: " + pSucursal);
		} catch (Exception e) {
			manejarErrorYCerrarNavegador("Error al seleccionar sucursal: " + e.getMessage());
		}
	}

	public void seleccionarAdministradora(String pAdministradora) {
		try {
			clickear(lnkAdministradora);
			String lstAdministradora = String.format("//div[@id='ctl00_ContentFiltros_cboAdmininstradora_DropDown']//li[contains(text(),'%s')]", pAdministradora);
			seleccionarOpcionConTexto(lstAdministradora, pAdministradora);
			System.out.println("Administradora seleccionada: " + pAdministradora);
		} catch (Exception e) {
			manejarErrorYCerrarNavegador("Error al seleccionar administradora: " + e.getMessage());
		}
	}

	public void seleccionarTipoAccion(String pAccion) {
		try {
			WebElement combo = driver.findElement(lnkTipoAccion);
			String disabledAttr = combo.getAttribute("disabled");

			if (disabledAttr == null) {
				clickear(lnkTipoAccion);
				String lstTipoAccion = String.format("//div[@id='ctl00_ContentFiltros_cboAccion_DropDown']//li[contains(text(),'%s')]", pAccion);
				seleccionarOpcionConTexto(lstTipoAccion, pAccion);
				System.out.println("Tipo de acción seleccionado: " + pAccion);
			} else {
				System.out.println("El select de Tipo de Acción está deshabilitado. Se omite selección.");
			}
		} catch (Exception e) {
			manejarErrorYCerrarNavegador("Error al seleccionar tipo de acción: " + e.getMessage());
		}
	}

	public void seleccionarOrigen(String pOrigen) {
		try {
			clickear(lnkOrigen);
			String lstOrigen = String.format("//div[@id='ctl00_ContentFiltros_cboOrigen_DropDown']//li[contains(text(),'%s')]", pOrigen);
			seleccionarOpcionConTexto(lstOrigen, pOrigen);
			System.out.println("Origen seleccionado: " + pOrigen);
		} catch (Exception e) {
			manejarErrorYCerrarNavegador("Error al seleccionar origen: " + e.getMessage());
		}
	}

	public void seleccionarEstado(String pEstado) {
		try {
			clickear(lnkEstado);
			String lstEstado = String.format("//div[@id='ctl00_ContentFiltros_cboEstado_DropDown']//li[contains(text(),'%s')]", pEstado);
			seleccionarOpcionConTexto(lstEstado, pEstado);
			System.out.println("Estado seleccionado: " + pEstado);
		} catch (Exception e) {
			manejarErrorYCerrarNavegador("Error al seleccionar estado: " + e.getMessage());
		}
	}

	public void ingresarFechaEstado(String pFechaDesde, String pFechaHasta) {
		try {
			escribir(clnFechaEstadoDesde, pFechaDesde);
			escribir(clnFechaEstadoHasta, pFechaHasta);
			System.out.println("Fechas de estado ingresadas: Desde " + pFechaDesde + " Hasta " + pFechaHasta);
		} catch (Exception e) {
			manejarErrorYCerrarNavegador("Error al ingresar fechas de estado: " + e.getMessage());
		}
	}

	public void ingresarFechaRecepcion(String pFechaDesde, String pFechaHasta) {
		try {
			escribir(clnFechaRecepcionDesde, pFechaDesde);
			escribir(clnFechaRecepcionHasta, pFechaHasta);
			System.out.println("Fechas de recepción ingresadas: Desde " + pFechaDesde + " Hasta " + pFechaHasta);
		} catch (Exception e) {
			manejarErrorYCerrarNavegador("Error al ingresar fechas de recepción: " + e.getMessage());
		}
	}

	public void seleccionarArchivoOrigen(String pArchivo) {
		try {
			WebElement combo = driver.findElement(lnkArchivoOrigen);
			String disabledAttr = combo.getAttribute("disabled");

			if (disabledAttr == null) {
				clickear(lnkArchivoOrigen);
				String lstArchivoOrigen = String.format("//div[@id='ctl00_ContentFiltros_cboArchivo_DropDown']//li[contains(text(),'%s')]", pArchivo);
				seleccionarOpcionConTexto(lstArchivoOrigen, pArchivo);
				System.out.println("Archivo origen seleccionado: " + pArchivo);
			} else {
				System.out.println("El select de Archivo Origen está deshabilitado. Se omite selección.");
			}
		} catch (Exception e) {
			manejarErrorYCerrarNavegador("Error al seleccionar archivo origen: " + e.getMessage());
		}
	}

	public void seleccionarSucursalOrigen(String pOrigen) {
		try {
			WebElement combo = driver.findElement(lnkSucursalOrigen);
			String disabledAttr = combo.getAttribute("disabled");

			if (disabledAttr == null) {
				clickear(lnkSucursalOrigen);
				String lstSucursalOrigen = String.format("//div[@id='ctl00_ContentFiltros_cboSucOrigen_DropDown']//li[contains(text(),'%s')]", pOrigen);
				seleccionarOpcionConTexto(lstSucursalOrigen, pOrigen);
				System.out.println("Sucursal origen seleccionada: " + pOrigen);
			} else {
				System.out.println("El select de Sucursal Origen está deshabilitado. Se omite selección.");
			}
		} catch (Exception e) {
			manejarErrorYCerrarNavegador("Error al seleccionar sucursal origen: " + e.getMessage());
		}
	}

	public void clickGenerarReporte() {
		try {
			clickear(btnGenerarReporte);
			System.out.println("Botón 'Generar Reporte' clickeado correctamente.");
		} catch (Exception e) {
			manejarErrorYCerrarNavegador("Error al hacer click en Generar Reporte: " + e.getMessage());
		}
	}
}
