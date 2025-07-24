package page.pagos;

import base.SeleniumBase;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HaberesProveedores extends SeleniumBase {
	private final By txtServicio = By.xpath("//app-form-validator//div[1]//mat-form-field[@appearance='outline']");
	private final By listaServicio = By.xpath("//div[@role='listbox']//mat-option");
	private final String lstServicio = ("//div[@role='listbox']//mat-option//span[normalize-space(text())='%s']");
	private final By txtConvenio = By.xpath("//app-form-validator//div[1]//mat-form-field[@appearance='outline']/following::app-form-validator[1]");
	private final By listaConvenio = By.xpath("//div[@role='listbox']//mat-option");
	private final String lstConvenio = ("//div[@role='listbox']//mat-option//span[normalize-space(text())='%s']");
	private final By btnContinuar = By.xpath("//card-step-footer-active[contains(.,'Continuar')]//button");
	private final By txtCuentasDisponibles = By.xpath("//app-form-validator//div[1]//mat-form-field[@appearance='outline']/following::app-form-validator[2]");
	private final By listaCuentasDisponibles = By.xpath("//div[@role='listbox']//mat-option");
	private final String lstCuentasDisponibles = ("//div[@role='listbox']//mat-option//span[.='%s']");
	private final By btnContinuarDebito = By.xpath("//card-step-footer-active[.='Continuar']//button");
	private final By btnContinuarDebitos = By.xpath("//div[contains(@style, 'clear: both;')]//button[contains(text(),'Continuar') and contains(@type, 'button')]");
	private final By fldImporteTotal = By.xpath("//app-form-validator//input[contains(@class, 'form-control-optional')]");
	private final By btnCalendar = By.xpath("//button[@class='mat-focus-indicator mat-icon-button mat-button-base']");
	private final By btnFlecha = By.xpath("//button[@class='example-double-arrow pr-0']");
	private final By clnFecha = By.cssSelector("[aria-label='%s']"); //27 de enero de 2025
	private final By fldConcepto = By.xpath("//input[@formcontrolname='concepto']");
	private final By btnClip = By.xpath("//*[name()='svg-icon' and contains(@class, 'clip')]");
	private final By btnConfirmar = By.xpath("//button[contains(text(),'Confirmar')]");
	private final By btnFinalizarCarga = By.xpath("//button[.='Finalizar carga']");
	private final By txtExiste = By.xpath("//h3[contains(text(),'Ya existe el archivo')]");
	private final By btnConfirmarCarga = By.xpath("//button[contains(text(),'Sí')]");
	private final By txtVerificar = By.xpath("//h3[contains(text(),'Verificar datos del archivo')]");
	private final By fldContrasenia = By.xpath("//input[@id='clave']");
	private final By btnAceptarContrasenia = By.xpath("//button[contains(text(),'Aceptar')]");
	private final By txtError = By.xpath("//p[contains(text(),'un problema al cargar el pago')]");


	/**
	 * Constructor que inicializa el driver de Selenium.
	 *
	 * @param driver instancia de WebDriver
	 */
	public HaberesProveedores(WebDriver driver) {
		super(driver);
	}

	public void clickServicio() {
		scrollHastaElFinal();
		//desplazarHastaHacerVisible(txtServicio);
		if (!existe(txtServicio)) {
			throw new NoSuchElementException("El elemento no se encuentra en la página.");
		}

		boolean exito = false;
		int intentos = 0;
		int maxIntentos = 20; // Máximo número de intentos

		while (!exito && intentos < maxIntentos) {
			try {
				clickearPrimeraOcurrencia(txtServicio); // Intenta hacer clic
				if (existe(listaServicio)) { // Verifica si el elemento esperado aparece
					exito = true;
					break;
				}
			} catch (Exception e) {
				System.out.println("Intento " + (intentos + 1) + ": Error al hacer clic. Reintentando...");
			}
			intentos++;
		}

		if (!exito) {
			throw new RuntimeException("No se pudo hacer clic o no apareció tipoVEPList después de " + maxIntentos + " intentos.");
		}
	}

	public void seleccionarServicio(String pServicio) {
		seleccionarOpcionConTexto(lstServicio, pServicio);
	}

	public void clickConvenio() {
		//desplazarHastaHacerVisible(txtConvenio);
		scrollHastaElFinal();
		if (!existe(txtConvenio)) {
			throw new NoSuchElementException("El elemento no se encuentra en la página.");
		}

		boolean exito = false;
		int intentos = 0;
		int maxIntentos = 20; // Máximo número de intentos

		while (!exito && intentos < maxIntentos) {
			try {
				clickearPrimeraOcurrencia(txtConvenio); // Intenta hacer clic
				if (existe(listaConvenio)) { // Verifica si el elemento esperado aparece
					exito = true;
					break;
				}
			} catch (Exception e) {
				System.out.println("Intento " + (intentos + 1) + ": Error al hacer clic en lstTipoVEP. Reintentando...");
			}
			intentos++;
		}

		if (!exito) {
			throw new RuntimeException("No se pudo hacer clic en lstTipoVEP o no apareció tipoVEPList después de " + maxIntentos + " intentos.");
		}
	}

	public void selecionarConvenio(String pConvenio) {
		seleccionarOpcionConTexto(lstConvenio, pConvenio);
	}

	public void clickContinuar() {
		boolean continuarExitoso = intentarClick(btnContinuar) || intentarClick(btnContinuarDebito)
				|| intentarClick(btnContinuarDebitos);

		if (!continuarExitoso) {
			System.out.println("No se pudo encontrar el botón continuar en la página actual.");
		}
	}

	public void clickCuentaDisponible() {
		//desplazarHastaHacerVisible(txtCuentasDisponibles);
		if (!existe(txtCuentasDisponibles)) {
			throw new NoSuchElementException("El elemento no se encuentra en la página.");
		}

		boolean exito = false;
		int intentos = 0;
		int maxIntentos = 20; // Máximo número de intentos

		while (!exito && intentos < maxIntentos) {
			try {
				clickearPrimeraOcurrencia(txtCuentasDisponibles); // Intenta hacer clic
				if (existe(listaCuentasDisponibles)) { // Verifica si el elemento esperado aparece
					exito = true;
					break;
				}
			} catch (Exception e) {
				System.out.println("Intento " + (intentos + 1) + ": Error al hacer clic en lstTipoVEP. Reintentando...");
			}
			intentos++;
		}

		if (!exito) {
			throw new RuntimeException("No se pudo hacer clic en lstTipoVEP o no apareció tipoVEPList después de " + maxIntentos + " intentos.");
		}
	}

	public void seleccionarCuentaDisponible(String pCuenta) {
		seleccionarOpcionConTexto(lstCuentasDisponibles, pCuenta);
	}

	public void ingresarImporteTotal(String pImporte) {
		hacerTab(null);
		hacerTab(null);
		reintentarClick(fldImporteTotal, 20);
		escribir(fldImporteTotal, pImporte);
	}

	public void seleccionarFechaAcreditacion(String pFecha) {
		try {
			// Abrir el calendario
			clickear(btnCalendar);
			scrollHastaElFinal();

			// Selector dinámico basado en el atributo aria-label
			String cssSelectorFecha = String.format("[aria-label='%s']", pFecha);

			// Intentar localizar la fecha en el calendario actual
			boolean fechaVisible = false;

			for (int i = 0; i < 12; i++) { // Máximo 12 iteraciones para evitar bucles infinitos
				try {
					// Verificar si la fecha está visible
					WebElement fecha = driver.findElement(By.cssSelector(cssSelectorFecha));
					if (fecha.isDisplayed()) {
						fechaVisible = true;
						fecha.click();
						System.out.println("Fecha seleccionada correctamente");
						break;
					}
				} catch (NoSuchElementException e) {
					// Si no está visible, hacer clic en la flecha para navegar al siguiente mes
					System.out.println("Fecha no visible, navegando al siguiente mes...");
					clickear(btnFlecha);
				}
			}

			// Validar si la fecha no fue encontrada después de iterar
			if (!fechaVisible) {
				throw new RuntimeException("No se encontró la fecha deseada: " + pFecha);
			}
		} catch (Exception e) {
			manejarErrorYCerrarNavegador("Error al seleccionar la fecha de acreditación");
		}
	}

	public void ingresarConcepto(String pConcepto) {
		escribir(fldConcepto, pConcepto);
	}

	public void seleccionarArchivoAdjunto(String pAdjunto) {
		clickear(btnClip);
		seleccionarArchivoWindows(pAdjunto);
	}

	public void clickConfirmar() {
		clickear(btnConfirmar);
	}

	public void clickFinalizarCarga(String pContrasenia){
		clickear(btnFinalizarCarga);
		if (existe(txtExiste)) {
			clickear(btnConfirmarCarga);
		}
		if (existe(txtVerificar)) {
			clickear(btnConfirmarCarga);
		}
		escribir(fldContrasenia, pContrasenia);
		clickear(btnAceptarContrasenia);
		esperarElemento(txtError);
		if (existe(txtError)) {
			clickear(btnAceptarContrasenia);
			manejarErrorYCerrarNavegador("Ocurrió un problema al cargar el pago.");
		}
	}
}
