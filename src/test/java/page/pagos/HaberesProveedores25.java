package page.pagos;

import base.SeleniumBase;
import org.openqa.selenium.*;

import java.util.List;

public class HaberesProveedores25 extends SeleniumBase {
	private final String rbtServicios = ("//input[@value='%s']");
	private final By lstOtrosDestinos = By.xpath("//select[@id='Otros_Destinos']");
	private final By btnAceptar = By.xpath("//a[@id='buttonValidar']");
	private final String txtCuenta = "//tbody[@id='gridCuentasDebito_gridFormId_gridBodyId']//tr[3]//td[.='%s']";
	private final By rbtCuenta = By.xpath("//tbody[@id='gridCuentasDebito_gridFormId_gridBodyId']//td[1]//input[@type='radio']");
	private final By lstConvenio = By.xpath("//select[@id='comboConvenios']");
	private final By fldImporte = By.xpath("//td[contains(.,'Importe total')]//input");
	private final By clnFecha = By.xpath("//input[@id='fechaImputacionID']");
	private final By fldConcepto = By.xpath("//input[@id='conceptoID']");
	private final By btnBuscar = By.xpath("//div[contains(text(),'Buscar')]");
	private final By btnCargar = By.xpath("//a[@id='buttonCargar']");
	private final By txtEliminarPagos = By.xpath("//a[@id='TM_ELIMINAR_PAGOS']");
	private final By btnEliminarPagos = By.xpath("//a[@id='buttonEliminar']");
	private final By btnAceptarEliminar = By.xpath("//a[@id='reautenticarModalEliminacion_cancelarResumen']");
	private final By btnConfirmarEliminar = By.xpath("//button[@id='reautenticarModalEliminacion_buttonAceptarModal']");
	private final By fldContrasenia = By.xpath("//input[@id='reautenticarModalEliminacion_passwordInput']");
	private final By btnAceptarContrasenia = By.xpath("//a[@id='reautenticarModalEliminacion_aceptar']");



	/**
	 * Constructor que inicializa el driver de Selenium.
	 *
	 * @param driver instancia de WebDriver
	 */
	public HaberesProveedores25(WebDriver driver) {
		super(driver);
	}

	public void seleccionarServicios(String pServicio, String pOtros) {
		// Seleccionar el radio button
		seleccionarOpcionConTexto(rbtServicios, pServicio);

		try {
			// Esperar brevemente para que el select se active si es necesario
			Thread.sleep(1000);

			// Verificar si el select de 'Otros Destinos' está habilitado
			WebElement selectElement = driver.findElement(lstOtrosDestinos);
			if (selectElement.isEnabled()) {
				System.out.println("El select 'Otros Destinos' está habilitado. Seleccionando opción...");
				seleccionarOpcionPorTexto(lstOtrosDestinos, pOtros);
			} else {
				System.out.println("El select 'Otros Destinos' está deshabilitado. No se seleccionará nada.");
			}
		} catch (Exception e) {
			manejarErrorYCerrarNavegador("Error en seleccionarServicios");
		}
	}

	public void clickAceptar() {
		clickear(btnAceptar);
	}

	public void seleccionarCuenta(String numeroCuenta) {
		scrollHastaElFinal();

		// Buscar la celda que contiene el número de cuenta exacto
		By cuentaEnTabla = By.xpath(String.format("//tbody[@id='gridCuentasDebito_gridFormId_gridBodyId']//td[.='%s']", numeroCuenta));

		try {
			WebElement celdaCuenta = esperarElemento(cuentaEnTabla);

			// Subir desde la celda al nivel de la fila
			WebElement fila = celdaCuenta.findElement(By.xpath("./ancestor::tr"));

			// Encontrar el radio button en la primera celda de la fila
			WebElement radioButton = fila.findElement(By.xpath(".//td[1]//input[@type='radio']"));

			// Hacer clic en el radio button
			clickWebElement(radioButton);

			System.out.println("Cuenta seleccionada correctamente: " + numeroCuenta);
		} catch (NoSuchElementException | TimeoutException e) {
			System.out.println("No se encontró la cuenta con número: " + numeroCuenta);
		}
	}

	public void seleccionarConvenio(String pConvenio) {
		scrollHastaElFinal();
		seleccionarOpcionPorTexto(lstConvenio, pConvenio);
	}

	public void ingresarImporteTotal(String pImporte) {
		escribir(fldImporte, pImporte);
	}

	public void ingresarFechaAcreditacion(String pFecha) {
		escribir(clnFecha, pFecha);
	}

	public void ingresarConcepto(String pConcepto) {
		escribir(fldConcepto, pConcepto);
	}

	public void seleccionarAdjunto(String pAdjunto) {
		clickear(btnBuscar);
		seleccionarArchivoWindows(pAdjunto);
	}

	public void clickCargar() {
		clickear(btnCargar);
	}

	public void clickEliminarPagos(){
		clickear(txtEliminarPagos);
		scrollHastaElFinal();
	}

	public void seleccionarTransaccion(String numeroTransaccion) {
		scrollHastaElFinal();

		// Buscar la celda que contiene el número de Transaccion exacto
		By TransaccionEnTabla = By.xpath(String.format("//tbody[@id='listadoArchivosGrid_gridBodyId']//td[contains(text(),'%s')]", numeroTransaccion));

		try {
			WebElement celdaTransaccion = esperarElemento(TransaccionEnTabla);

			// Subir desde la celda al nivel de la fila
			WebElement fila = celdaTransaccion.findElement(By.xpath("./ancestor::tr"));

			// Encontrar el check button en la primera celda de la fila
			WebElement checkButton = fila.findElement(By.xpath(".//td[1]//input[@type='checkbox']"));

			// Hacer clic en el check button
			clickWebElement(checkButton);

			System.out.println("Transaccion seleccionada correctamente: " + numeroTransaccion);
		} catch (NoSuchElementException | TimeoutException e) {
			System.out.println("No se encontró la Transaccion con número: " + numeroTransaccion);
		}
	}

	public void seleccionarCuentasDebitoIguales(String numeroCuenta) {
		scrollHastaElFinal();

		// Localizar todas las celdas que contienen el número de transacción exacto
		By transaccionesEnTabla = By.xpath(String.format("//tbody[@id='listadoArchivosGrid_gridBodyId']//td[contains(text(),'%s')]", numeroCuenta));

		try {
			// Esperar a que al menos una coincidencia esté presente
			List<WebElement> celdasTransaccion = esperarElementos(transaccionesEnTabla);

			if (celdasTransaccion.isEmpty()) {
				System.out.println("No se encontró ninguna transacción con número: " + numeroCuenta);
				return;
			}

			for (WebElement celda : celdasTransaccion) {
				// Subir desde la celda al nivel de la fila
				WebElement fila = celda.findElement(By.xpath("./ancestor::tr"));

				// Encontrar el checkbox en la primera celda de la fila
				WebElement checkButton = fila.findElement(By.xpath(".//td[1]//input[@type='checkbox']"));

				// Hacer clic en el checkbox
				clickWebElement(checkButton);
			}

			System.out.println("Seleccionadas todas las transacciones con número: " + numeroCuenta);
		} catch (NoSuchElementException | TimeoutException e) {
			System.out.println("No se encontró ninguna transacción con número: " + numeroCuenta);
		}
	}

	public void clickConfirmarEliminarPago(String pContrasenia){
		scrollHastaElFinal();
		clickear(btnEliminarPagos);
		clickear(btnAceptarEliminar);
		escribir(fldContrasenia, pContrasenia);
		clickear(btnAceptarContrasenia);
		clickear(btnConfirmarEliminar);
	}

}
