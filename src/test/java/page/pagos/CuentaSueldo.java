package page.pagos;

import base.SeleniumBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CuentaSueldo extends SeleniumBase {
	private final By txtAltaManual = By.xpath("//a[@id='CTAS_SUELDO_ALTA_MANUAL']");
	private final By fldDependencia = By.xpath("//label[@class='dependecia_cta_class']//input[@tabindex='1']");
	private final By fldNombre = By.xpath("//label[@class='ape_nom_titular_class']//input");
	private final By lstCodigoEmpresa = By.xpath("//label[@class='codigo_empresa_class']//select[1]");
	private final By lstTipoDocumento = By.xpath("//label[@class='tipo_documento_class']//select[1]");
	private final By fldNumDocumento = By.xpath("//label[@class='num_doc_class']//input[1]");
	private final By fldCalle = By.xpath("//label[@class='calle_class']//input[1]");
	private final By fldNumeroCalle = By.xpath("//label[@class='numero_class']//input[1]");
	private final By fldPisoCalle = By.xpath("//label[@class='piso_class']//input[1]");
	private final By fldDepartamentoCalle = By.xpath("//label[@class='departamento_class']//input[1]");
	private final By fldCodigoPostal = By.xpath("//label[@class='cod_postal_class']//input[1]");
	private final By lstLocalidad = By.xpath("//label[@class='localidad_class']//select[1]");
	private final By fldNumeroTelefono = By.xpath("//label[@class='nro_tel_class']//input[1]");
	private final By clnFechaNacimiento = By.xpath("//input[@class='fechas hasDatepicker']");
	private final By lstSexo = By.xpath("//label[@class='sexo_class']//select[1]");
	private final By fldCUIL = By.xpath("//label[@class='cuil_class']//input[1]");
	private final By fldNumeroLegajo = By.xpath("//label[@class='nro_legajo_class']//input[1]");
	private final By fldHaberAgente = By.xpath("//label[@class='haber_agente_class']//input[1]");
	private final By btnAgregarCuenta = By.xpath("//a[@id='botonAgregarElemento']");

	/**
	 * Constructor que inicializa el driver de Selenium.
	 *
	 * @param driver instancia de WebDriver
	 */
	public CuentaSueldo(WebDriver driver) {
		super(driver);
	}

	public void clickAltaManual() {
		clickear(txtAltaManual);
	}

	public void ingresarDependencia(String pDependencia) {
		escribir(fldDependencia, pDependencia);
	}


	public void ingresarNombre(String pNombre) {
		escribir(fldNombre, pNombre);
	}

	public void seleccionarCodigoEmpresa(String pCodigo) {
		seleccionarOpcionPorTexto(lstCodigoEmpresa, pCodigo);
	}

	public void seleccionarTipoDocumento(String pTipoDNI) {
		seleccionarOpcionPorTexto(lstTipoDocumento, pTipoDNI);
	}

	public void ingresarNumeroDocumento(String pNumDocumento) {
		escribir(fldNumDocumento, pNumDocumento);
	}

	public void ingresarCalle(String pCalle) {
		escribir(fldCalle, pCalle);
	}

	public void ingresarNumeroCalle(String pNumeroCalle) {
		escribir(fldNumeroCalle, pNumeroCalle);
	}

	public void ingresarPisoCalle(String pPisoCalle) {
		escribir(fldPisoCalle, pPisoCalle);
	}

	public void ingresarDepartamentoCalle(String pDepartamento) {
		escribir(fldDepartamentoCalle, pDepartamento);
	}

	public void ingresarCodigoPostal(String pCodigoPostal) {
		escribir(fldCodigoPostal, pCodigoPostal);
	}

	public void seleccionarLocalidad(String pLocalidad) {
		seleccionarOpcionPorTexto(lstLocalidad, pLocalidad);
	}

	public void ingresarNumeroTelefono(String pNumTelefono) {
		escribir(fldNumeroTelefono, pNumTelefono);
	}

	public void ingresarFechaNacimiento(String pFecha) {
		clickear(clnFechaNacimiento);
		seleccionarFechaJS(clnFechaNacimiento, pFecha);
	}

	public void seleccionarSexo(String pSexo) {
		seleccionarOpcionPorTexto(lstSexo, pSexo);
	}

	public void ingresarCUIL(String pCuil) {
		escribir(fldCUIL, pCuil);
	}

	public void ingresarNumeroLegajo(String pLegajo) {
		escribir(fldNumeroLegajo, pLegajo);
	}

	public void ingresarHaberAgente(String pHaberAgente) {
		escribir(fldHaberAgente, pHaberAgente);
	}

	public void clickAgregarCuenta() {
		existe(btnAgregarCuenta);
	}
}
