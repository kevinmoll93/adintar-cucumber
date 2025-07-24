package page.pagos;

import base.SeleniumBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ImpuestoServicios extends SeleniumBase {
	private final By btnNuevoPago = By.xpath("//button[contains(.,'Nuevo pago')]");
	private final By fldBuscar = By.xpath("//input[@placeholder='Buscar por impuesto, servicio o empresa']");
	private final String lstBuscar = ("//mat-option[1]");
	private final By fldCodigoPago = By.xpath("//input[@formcontrolname='inputCodigoPago']");
	private final By btnEditar = By.xpath("//*[name()='svg-icon' and contains(@class, 'icon lapiz')]");
	private final By fldDescripcion = By.xpath("//input[@formcontrolname='inputDescripcion']");
	private final By btnTilde = By.xpath("//*[name()='svg-icon' and contains(@class, 'iconTilde')]");
	private final By btnContinuar = By.xpath("//button[contains(.,'Continuar')]");
	private final By mensajeError = By.xpath("//*[contains(text(),'El dato ingresado es incorrecto.')]");
	private final By lstConceptoo = By.xpath("//app-form-validator//div[1]//mat-form-field[@appearance='outline']/following::app-form-validator[3]");
	private final By lstConcepto = By.xpath("//input[@data-placeholder='Seleccionar un concepto']");
	private final String listaConcepto = ("//mat-option");
	private final By fldImporte = By.xpath("//input[@formcontrolname='inputImporte']");
	private final By fldOperacion = By.xpath("//input[@formcontrolname='inputTextoReferencia']");
	private final By btnConfirmar = By.xpath("//button[contains(.,'Confirmar')]");
	private final By btnFinalizarCargar = By.xpath("//button[contains(.,'Finalizar carga')]");
	private final By btnAceptarPagoCargado = By.xpath("//button[.='Aceptar']");
	private final By cbxAdherir = By.xpath("//span[@class='mat-checkbox-inner-container']");
	private final By txtAdherido = By.xpath("//p[contains(text(),'El impuesto o servicio ya se encuentra adherido.')]");

	private final By btnAdheridos = By.xpath("//div[@role='tab']//div[.='Adheridos']");
	private final By txtNoServicio = By.xpath("//bee30-typography[contains(.,'Todavía no tenés servicios adheridos')]");
	private final By btnEliminar = By.xpath("//tr[contains(.,'%s')]/td[3]//button[contains(.,'Eliminar')]");
	private final By btnSiEliminar = By.xpath("//button[contains(.,'No')]");

	/**
	 * Constructor que inicializa el driver de Selenium.
	 *
	 * @param driver instancia de WebDriver
	 */
	public ImpuestoServicios(WebDriver driver) {
		super(driver);
	}

	public void clickNuevoPago() {
		clickear(btnNuevoPago);
	}

	public void ingresarBusqueda(String pBuscar) {
		esperar(3000);
		escribir(fldBuscar, pBuscar);
		seleccionarOpcionConTextoCompleto(pBuscar, lstBuscar);
	}

	public void ingresarCodigoPago(String pCodigo) {
		escribir(fldCodigoPago, pCodigo);
	}

	public void ingresarDescripcion(String pDescripcion) {
		// Verifica si el botón SVG con el icono de "lapiz" existe antes de intentar hacer clic
		if (elementoExiste("//*[name()='svg-icon' and contains(@class, 'icon lapiz')]")) {
			esperarYClickSvg("//*[name()='svg-icon' and contains(@class, 'icon lapiz')]", null, 10);
		} else {
			System.out.println("El botón SVG con el icono de 'lapiz' no está presente.");
		}

		// Limpia y escribe la descripción
		limpiar(fldDescripcion);
		escribir(fldDescripcion, pDescripcion);

		// Verifica si el botón SVG con el icono de "tilde" existe antes de intentar hacer clic
		if (elementoExiste("//*[name()='svg-icon' and contains(@class, 'iconTilde')]")) {
			esperarYClickSvg("//*[name()='svg-icon' and contains(@class, 'iconTilde')]", null, 10);
		} else {
			System.out.println("El botón SVG con el icono de 'tilde' no está presente.");
		}
	}


	private void esperar(int milisegundos) {
		try {
			Thread.sleep(milisegundos);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			System.out.println("Error durante la espera: " + e.getMessage());
		}
	}


	public void clickContinuar() {
		clickear(btnContinuar);
	}

	public void seleccionarConcepto(String pConcepto) {
		escribir(lstConcepto, pConcepto);
		seleccionarOpcionConTextoCompleto(pConcepto, listaConcepto);
	}

	public void ingresarImporte(String pImporte) {
		escribir(fldImporte, pImporte);
	}

	public void ingresarOperacion(String pOperacion) {
		escribir(fldOperacion, pOperacion);
	}

	public void clickConfirmar() {
		clickear(btnConfirmar);
		clickear(btnFinalizarCargar);
		esperarElemento(btnAceptarPagoCargado);
		clickear(btnAceptarPagoCargado);
	}

	public void clickConfirmarAdhesion(String pAdhesion) {
		clickear(btnConfirmar);

		if (existe(txtAdherido)) {
			clickear(txtAdherido); // Ya está adherido, se reconoce el mensaje
		} else {
			if ("SI".equalsIgnoreCase(pAdhesion)) {
				clickear(cbxAdherir);
			} else if (!"NO".equalsIgnoreCase(pAdhesion)) {
				manejarErrorYCerrarNavegador("Valor de pAdhesion inválido: " + pAdhesion);
			}
		}

		clickear(btnFinalizarCargar);
		esperarElemento(btnAceptarPagoCargado);
		clickear(btnAceptarPagoCargado);
	}

	public void clickAdheridos(){
		clickear(btnAdheridos);
		scrollHastaElFinal();
	}

	public void clickEliminarAdhesion(String pCodigo) {
		if (existe(txtNoServicio)) {
			manejarErrorYCerrarNavegador("No existen servicios/impuestos adheridos");
			return;
		}

		boolean eliminado = clickBotonEliminarPorCodigo(pCodigo);
		if (!eliminado) {
			manejarErrorYCerrarNavegador("No existe código de pago ingresado: " + pCodigo);
		}
	}

	public void clickConfirmarEliminarAdhesion(){
		clickear(btnSiEliminar);
	}

	public boolean clickBotonEliminarPorCodigo(String pCodigo) {
		try {
			By localizador = By.xpath(String.format("//tr[td[normalize-space(text())='%s']]/td[3]//button[contains(.,'Eliminar')]", pCodigo));
			WebElement btnEliminar = esperarElementoClickeable(localizador);
			btnEliminar.click();
			System.out.println("Se hizo clic en el botón Eliminar para el código: " + pCodigo);
			return true;
		} catch (Exception e) {
			System.out.println("No se encontró el botón Eliminar para el código: " + pCodigo);
			return false;
		}
	}

	public String obtenerMensajeError() {
		try {
			System.out.println("Obteniendo mensaje de error");
			WebElement elementoError = esperarElemento(mensajeError);
			return elementoError.getText();
		} catch (Exception e) {
			System.out.println("No se encontró mensaje de error");
			return "";
		}
	}


}
