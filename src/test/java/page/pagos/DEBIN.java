package page.pagos;

import base.SeleniumBase;
import org.openqa.selenium.*;

import java.util.List;


public class DEBIN extends SeleniumBase {
	private final String txtCuenta = "//tbody[@id='idCuentasAAdherir_gridFormId_gridBodyId']//tr[%s]//td[3]";
	private final String rbtCuenta = "//tbody[@id='idCuentasAAdherir_gridFormId_gridBodyId']//tr[%s]//input[@type='radio']";
	private final String fldNombreFantasia = "//tbody[@id='idCuentasAAdherir_gridFormId_gridBodyId']//tr[%s]//input[contains(@id,'nombreFantasia')]";
	private final String lstRubro = "//tbody[@id='idCuentasAAdherir_gridFormId_gridBodyId']//tr[%s]//select";
	private final By btnAdherir = By.xpath("//a[@id='buttonAdherir']");
	private final By btnAceptarAdherir = By.xpath("//a[@id='reautenticarModalAdherir_aceptarResumen']");
	private final By btnCancelarAdherir = By.xpath("//a[@id='reautenticarModalAdherir_cancelarResumen']");
	private final By fldContrasenia = By.xpath("//input[@id='reautenticarModalAdherir_passwordInput']");
	private final By btnAceptarConfirmacion = By.xpath("//a[@id='reautenticarModalAdherir_aceptar']");
	private final By btnAceptarError = By.xpath("//a[@id='reautenticarModalAdherir_errorWarningAutenticacionAceptar']");
	private final By btnAceptarConfirmacionAdhesion = By.xpath("//a[@id='btnAceptarResultado']");
	private final By txtErrorAdhesion = By.xpath("//div[@id='divErrorAdherir']//span[@id='spError']");

	private final By lnkOrdenDEBIN = By.xpath("//label[contains(.,'Orden de DEBIN')]");
	private final By cbxOrdenDEBIN = By.xpath("//input[@id='tycModal_tycAcceptCheckbox']");
	private final By btnAceptarOrdenDEBIN = By.xpath("//a[@id='tycModal_okButton']");

	private final By lnkCuentaCredito = By.xpath("//div[.='Seleccione una cuenta de crédito']");
	private final String txtCuentaCredito = "//tbody[@id='gridCuentasCredito_gridFormId_gridBodyId']//tr//td[contains(text(),'%s')]";
	private final String rbtCuentaCredito = "//input[@name='gridCuentasCredito_gridFormId_radio']";

	private final By lnkDebitoAutomatico = By.xpath("//div[.='Ingrese los datos para generar el Débito Inmediato']");
	private final By btnAgregarDestinatario = By.xpath("//button[@id='botonNueva']");
	private final By fldCBU = By.xpath("//input[@id='cbu']");
	private final By fldReferencia = By.xpath("//input[@id='referencia']");
	private final String cbxSeleccionCuenta = "//input[@id='%s']";
	private final By btnAgregarDestinatarioCredito = By.xpath("//input[@id='boton']");
	private final By txtDestinatarioExiste = By.xpath("//span[.='El destinatario ya existe']");
	private final String txtCuentaDestinatario = "//tbody[@id='destinatariosGrid_gridBodyId']//tr//td[contains(text(),'%s')]";
	private final String cbxCuentaDestinatario = "//tbody[@id='destinatariosGrid_gridBodyId']//tr[%s]//input[@type='checkbox']";

	private final By lnkDatosDebin = By.xpath("//div[.='Ingrese los datos del Debin']");
	private final By fldDescripcion = By.xpath("//input[@id='inputDescripcion']");
	private final By lstConcepto = By.xpath("//select[@id='selectConcepto']");
	private final By rbtRecurrenciaNO = By.xpath("//input[@id='recurrenciaNo']");
	private final By btnGenerarDebin = By.xpath("//a[@id='botonGenerarDebin']");


	/**
	 * Constructor que inicializa el driver de Selenium.
	 *
	 * @param driver instancia de WebDriver
	 */
	public DEBIN(WebDriver driver) {
		super(driver);
	}

	/**
	 * Selecciona una cuenta específica basada en el número de cuenta.
	 *
	 * @param numeroCuenta Número de cuenta recibido desde el .feature
	 */
	public void seleccionarCuenta(String numeroCuenta) {
		// Buscar la celda que contiene el número de cuenta exacto
		By cuentaEnTabla = By.xpath(String.format("//tbody[@id='idCuentasAAdherir_gridFormId_gridBodyId']//tr//td[contains(text(),'%s')]", numeroCuenta));

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

	public void seleccionarCBU(String CBU) {

		// Buscar la celda que contiene el número de cuenta exacto
		By cbuEnTabla = By.xpath(String.format("//tbody[@id='idCuentasAAdherir_gridFormId_gridBodyId']//tr//td[contains(text(),'%s')]", CBU));

		try {
			WebElement celdaCBU = esperarElemento(cbuEnTabla);

			// Subir desde la celda al nivel de la fila
			WebElement fila = celdaCBU.findElement(By.xpath("./ancestor::tr"));

			// Encontrar el radio button en la primera celda de la fila
			WebElement radioButton = fila.findElement(By.xpath(".//td[1]//input[@type='radio']"));

			// Hacer clic en el radio button
			clickWebElement(radioButton);

			System.out.println("CBU seleccionada correctamente: " + CBU);
		} catch (NoSuchElementException | TimeoutException e) {
			System.out.println("No se encontró la cuenta CBU con número: " + CBU);
		}
	}

	public void seleccionarCuentaCredito(String numeroCuenta) {
		scrollHastaElFinal();

		// Buscar la celda que contiene el número de cuenta exacto
		By cuentaEnTabla = By.xpath(String.format("//tbody[@id='gridCuentasCredito_gridFormId_gridBodyId']//tr//td[contains(text(),'%s')]", numeroCuenta));

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

	public void seleccionarCuentaDestinatario(String numeroCuenta, String importe, String comprobante) {
		scrollHastaElFinal();

		// Buscar la celda que contiene el número de cuenta exacto
		By cuentaEnTabla = By.xpath(String.format("//tbody[@id='destinatariosGrid_gridBodyId']//tr//td[contains(text(),'%s')]", numeroCuenta));

		try {
			WebElement celdaCuenta = esperarElemento(cuentaEnTabla);

			// Subir desde la celda al nivel de la fila
			WebElement fila = celdaCuenta.findElement(By.xpath("./ancestor::tr"));

			// Encontrar el radio button en la primera celda de la fila
			WebElement radioButton = fila.findElement(By.xpath(".//td[1]//input[@type='checkbox']"));

			// Hacer clic en el radio button
			clickWebElement(radioButton);

			// Encontrar el campo importe
			WebElement fldImporte = fila.findElement(By.xpath(".//td[4]//input"));

			escribirWebElement(fldImporte, importe);

			// Encontrar el campo comprobante
			WebElement fldComprobante = fila.findElement(By.xpath(".//td[5]//input"));

			escribirWebElement(fldComprobante, comprobante);

			System.out.println("Cuenta seleccionada correctamente: " + numeroCuenta);
		} catch (NoSuchElementException | TimeoutException e) {
			System.out.println("No se encontró la cuenta con número: " + numeroCuenta);
		}
	}


	/**
	 * Ingresa el nombre de fantasía para una cuenta específica.
	 *
	 * @param numeroCuenta   Número de cuenta recibido desde el .feature
	 * @param nombreFantasia Nombre de fantasía a ingresar
	 */
	public void ingresarNombreFantasia(String numeroCuenta, String nombreFantasia) {
		// Buscar la celda que contiene el número de cuenta exacto
		By cuentaEnTabla = By.xpath(String.format("//tbody[@id='idCuentasAAdherir_gridFormId_gridBodyId']//tr//td[contains(text(),'%s')]", numeroCuenta));

		try {
			WebElement celdaCuenta = esperarElemento(cuentaEnTabla);

			// Subir desde la celda al nivel de la fila
			WebElement fila = celdaCuenta.findElement(By.xpath("./ancestor::tr"));

			// Encontrar el campo input de la fila
			WebElement inputText = fila.findElement(By.xpath(".//td[5]//input"));

			// Hacer clic en el radio button
			escribirWebElement(inputText, nombreFantasia);

			System.out.println("Cuenta seleccionada correctamente: " + numeroCuenta);
		} catch (NoSuchElementException | TimeoutException e) {
			System.out.println("No se encontró la cuenta con número: " + numeroCuenta);
		}
	}

	/**
	 * Selecciona un rubro para una cuenta específica.
	 *
	 * @param numeroCuenta Número de cuenta recibido desde el .feature
	 * @param rubro        Rubro a seleccionar
	 */
	public void seleccionarRubro(String numeroCuenta, String rubro) {
		// Buscar la celda que contiene el número de cuenta exacto
		By cuentaEnTabla = By.xpath(String.format("//tbody[@id='idCuentasAAdherir_gridFormId_gridBodyId']//tr//td[contains(text(),'%s')]", numeroCuenta));

		try {
			WebElement celdaCuenta = esperarElemento(cuentaEnTabla);

			// Subir desde la celda al nivel de la fila
			WebElement fila = celdaCuenta.findElement(By.xpath("./ancestor::tr"));

			// Encontrar el campo input de la fila
			WebElement select = fila.findElement(By.xpath(".//td[6]//select"));

			// Hacer clic en el radio button
			seleccionarPorWebElement(select, rubro);

			System.out.println("Cuenta seleccionada correctamente: " + numeroCuenta);
		} catch (NoSuchElementException | TimeoutException e) {
			System.out.println("No se encontró la cuenta con número: " + numeroCuenta);
		}
	}

	/**
	 * Encuentra la fila (tr) correspondiente al número de cuenta especificado.
	 *
	 * @param numeroCuenta Número de cuenta recibido desde el .feature
	 * @return Índice de la fila (1, 2, 3, ...) o -1 si no se encuentra
	 */
	private int obtenerFilaCuenta(String numeroCuenta) {
		// Reevaluar las filas dinámicamente
		List<WebElement> filas = encontrarElementos(By.xpath("//tbody[@id='idCuentasAAdherir_gridFormId_gridBodyId']//tr"));

		for (int i = 1; i <= filas.size(); i++) {
			By cuentaEnFila = By.xpath(String.format(txtCuenta, i));

			try {
				// Validar si el elemento existe y es visible en el DOM
				WebElement elementoCuenta = esperarElemento(cuentaEnFila);
				String textoCuenta = elementoCuenta.getText();

				if (textoCuenta.equals(numeroCuenta)) {
					return i; // Retorna el índice de la fila
				}
			} catch (NoSuchElementException | TimeoutException e) {
				// Continuar si el elemento no está disponible
				continue;
			}
		}

		return -1; // No se encontró la cuenta
	}


	public void esperarActualizacionTabla() {
		By indicadorTablaActualizada = By.xpath("//tbody[@id='idCuentasAAdherir_gridFormId_gridBodyId']//tr");

		// Esperar que la tabla sea visible nuevamente (indicador de actualización)
		esperarElemento(indicadorTablaActualizada);
	}

	public void confirmarAdherir(String pContrasenia) {
		clickear(btnAdherir);
		clickear(btnAceptarAdherir);
		escribir(fldContrasenia, pContrasenia);
		clickear(btnAceptarConfirmacion);
		if(existe(txtErrorAdhesion)){
			manejarErrorYCerrarNavegador("Existe un error interno del servicio que impide la adhesion");
		}
		clickear(btnAceptarConfirmacionAdhesion);
	}

	public void seleccionarOrdenDEBIN() {
		clickear(lnkOrdenDEBIN);
		clickear(cbxOrdenDEBIN);
		clickear(btnAceptarOrdenDEBIN);
	}

	public void clickCuentaCredito() {
		clickear(lnkCuentaCredito);
	}

	public void seleccionarDebitoAutomatico() {
		clickear(lnkDebitoAutomatico);
		clickear(btnAgregarDestinatario);
	}

	public void ingresarDatosDestinatario(String pCBU, String pReferencia, String pTipo) {
		escribir(fldCBU, pCBU);
		escribir(fldReferencia, pReferencia);
		By localizador = By.xpath(String.format(cbxSeleccionCuenta, pTipo));
		clickear(localizador);
	}

	public void clickAgregarDestinatario(String agregarExiste, String numeroCuenta, String importe, String comprobante) {
		System.out.println("Clic en 'Agregar Destinatario'");
		clickear(btnAgregarDestinatarioCredito);

		boolean destinatarioExiste = existe(txtDestinatarioExiste);
		System.out.println("¿El destinatario ya existe?: " + destinatarioExiste);
		System.out.println("Valor de 'AgregarExiste': " + agregarExiste);

		if (destinatarioExiste && agregarExiste.equalsIgnoreCase("SI")) {
			System.out.println("Ejecutando seleccionarCuentaDestinatario...");
			seleccionarCuentaDestinatario(numeroCuenta, importe, comprobante);
		} else if (destinatarioExiste && agregarExiste.equalsIgnoreCase("NO")) {
			System.out.println("Ejecutando manejarErrorYCerrarNavegador...");
			manejarErrorYCerrarNavegador("El destinatario ya existe y 'AgregarExiste' está en 'NO'.");
		} else {
			System.out.println("No se detectó el mensaje de destinatario existente. Continuando...");
		}
	}

	public void seleccionarDatosDebin() {
		clickear(lnkDatosDebin);
	}

	public void ingresarDatosDebin(String pDescripcion, String pConcepto) {
		escribir(fldDescripcion, pDescripcion);
		seleccionarOpcionPorTexto(lstConcepto, pConcepto);
		clickear(rbtRecurrenciaNO);
	}

	public void clickGenerarDEBIN() {
		clickear(btnGenerarDebin);
	}

}
