package page.pagos;

import base.SeleniumBase;
import org.openqa.selenium.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


public class AFIPVEP extends SeleniumBase {
	private final By lstTipoVEP = By.xpath("//div[@class='pendienteContenido']//span//mat-label[text()='Tipo de VEP']//following::div[1]");
	private final String tipoVEP = "//div[@role='listbox']//mat-option[%s]";
	private final By tipoVEPList = By.xpath("//div[@role='listbox']//mat-option//span");
	private final By btnBuscar = By.xpath("//button[@id='buscador']");
	private final By txtNoResultado = By.xpath("//bee30-typography[@class='mgb-3 ng-star-inserted']");
	private final By fldBuscador = By.xpath("//input[@formcontrolname='cuit']");
	private final By fldNumVEP = By.xpath("//input[@formcontrolname='VEPNumber']");
	private final String btnCargarVEP = "//tr//div[contains(.,'%s')]//following::button[1]";
	private final String numeroVEP = "//tbody[1]//div[contains(.,'%s')]//mat-checkbox[@class='mat-checkbox mat-accent']";
	private final By btnCuentasDisponibles = By.xpath("//input[@placeholder='Buscar por Nº de cuenta o referencia']");
	private final String lstCuentasDisponibles = ("//div[@role='listbox']//mat-option//span[.='%s']");
	private final By btnCargar = By.xpath("//button[contains(text(),'Cargar')]/following::button[17]");
	private final By txtFirmarEnviar = By.xpath("//span[.='Firmar y enviar']");
	private final By btnAceptarVEP = By.xpath("//button[contains(text(),'Aceptar')]");


	private final By btnServiciosArca = By.xpath("//div[text()='Servicios ARCA']");
	private final By btnAceptaArca = By.xpath("//a[@href='javascript:irAfip()']");
	private final By lnkDDJJPagos = By.xpath("//p[contains(.,'Presentación de DDJJ y Pagos')]");
	private final By txtErrorDDJJPagos = By.xpath("//h3[.='Se ha producido un error']");
	private final By lnkSICAM = By.xpath("//p[contains(.,'SICAM Contribuyente')]");
	private final By txtIngresoDenegado = By.xpath("//font[contains(.,'Ingreso Denegado.')]");


	/**
	 * Constructor que inicializa el driver de Selenium.
	 *
	 * @param driver instancia de WebDriver
	 */
	public AFIPVEP(WebDriver driver) {
		super(driver);
	}

	public void clickTipoVep() {
		scrollHastaElFinal();
		if (!existe(lstTipoVEP)) {
			throw new NoSuchElementException("El elemento lstTipoVEP no se encuentra en la página.");
		}

		boolean exito = false;
		int intentos = 0;
		int maxIntentos = 20; // Máximo número de intentos

		while (!exito && intentos < maxIntentos) {
			try {
				clickear(lstTipoVEP); // Intenta hacer clic
				if (existe(tipoVEPList)) { // Verifica si el elemento esperado aparece
					exito = true;
					break;
				}
			} catch (Exception e) {
				System.out.println("Intento " + (intentos + 1) + ": Error al hacer clic en lstTipoVEP. Reintentando...");
			}
			intentos++;
			esperar(500); // Espera antes de reintentar (ajusta según tu necesidad)
		}

		if (!exito) {
			throw new RuntimeException("No se pudo hacer clic en lstTipoVEP o no apareció tipoVEPList después de " + maxIntentos + " intentos.");
		}
	}


	public void seleccionarTipoVEP(String pVEP) {
		int filaCuenta = obtenerTipoVEP(pVEP);

		if (filaCuenta != -1) {
			By lstCuenta = By.xpath(String.format(tipoVEP, filaCuenta));
			reintentarClick(lstCuenta, 10); // Reintentar hasta 10 veces

		} else {

		}
	}

	public void clickBuscar() {
		clickear(btnBuscar);
	}

	public void clickCargar(String pNumVEP, String pNumCuenta) {
		if (existe(txtNoResultado)) {
			manejarErrorYCerrarNavegador("No existe el número de VEP especificado");
		} else {
			clickPorValue(btnCargarVEP, pNumVEP);
			clickear(btnCuentasDisponibles);
			seleccionarOpcionConTexto(lstCuentasDisponibles, pNumCuenta);
			clickear(btnCargar);
			clickear(txtFirmarEnviar);
			esperar(10000);
			clickear(btnAceptarVEP);
		}
	}

	public void comprobarNoResultado() {
		try {
			if (existe(txtNoResultado)) {
				// Registra el fallo en el reporte pero no detiene la ejecución

				// Lanza una excepción para detener esta iteración
				manejarErrorYCerrarNavegador("Deteniendo la iteración actual debido a un error en la carga de VEPs.");
			} else {

			}
		} catch (NoSuchElementException e) {
			// Si no se encuentra el elemento, pasa normalmente

		}
	}


	public void ingresarCUIT(String pCUIT) {
		escribir(fldBuscador, pCUIT);
	}

	public void ingresarNumVEP(String pnumVEP) {
		escribir(fldNumVEP, pnumVEP);
	}

	private int obtenerTipoVEP(String tipoCuenta) {
		// Reevaluar las filas dinámicamente
		List<WebElement> filas = encontrarElementos(By.xpath("//div[@role='listbox']//mat-option"));

		for (int i = 1; i <= filas.size(); i++) {
			By cuentaEnFila = By.xpath(String.format(tipoVEP, i));

			try {
				// Validar si el elemento existe y es visible en el DOM
				WebElement elementoCuenta = esperarElemento(cuentaEnFila);
				String textoCuenta = elementoCuenta.getText();

				if (textoCuenta.equals(tipoCuenta)) {
					return i; // Retorna el índice de la fila
				}
			} catch (NoSuchElementException | TimeoutException e) {
				// Continuar si el elemento no está disponible
				continue;
			}
		}

		return -1; // No se encontró la cuenta
	}

	private void esperar(int milisegundos) {
		try {
			Thread.sleep(milisegundos);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			System.out.println("Error durante la espera: " + e.getMessage());
		}
	}

	public void clickServiciosARCA() {
		clickear(btnServiciosArca);
		clickear(btnAceptaArca);
		esperar(3000);
	}

	public void clickDDJJPagos() {
		// Guarda el identificador de la pestaña actual
		String currentTab = driver.getWindowHandle();

		// Espera hasta que haya más pestañas abiertas
		esperar(1500);

		// Obtén todos los identificadores de las ventanas abiertas
		Set<String> allTabs = driver.getWindowHandles();

		// Cambia el foco a la nueva pestaña
		for (String tab : allTabs) {
			if (!tab.equals(currentTab)) {
				driver.switchTo().window(tab);
				System.out.println("Foco cambiado a la nueva pestaña.");
				break;
			}
		}

		// Espera hasta que el enlace lnkDDJJPagos esté visible y luego haz clic
		existe(lnkDDJJPagos);
		clickear(lnkDDJJPagos);

		System.out.println("Se hizo clic en el enlace DDJJ Pagos en la nueva pestaña.");
	}

	public void comprobarErrorDDJJPagos() {
		// Guarda el identificador de la pestaña actual
		String currentTab = driver.getWindowHandle();

		// Espera un breve tiempo para que la nueva pestaña se abra
		esperar(1500);

		// Obtén todos los identificadores de las ventanas abiertas
		Set<String> allTabs = driver.getWindowHandles();

		// Convierte el conjunto en una lista para acceder a la última pestaña
		List<String> tabsList = new ArrayList<>(allTabs);

		// Verifica si hay más de una pestaña abierta
		if (tabsList.size() > 1) {
			// Cambia el foco a la última pestaña abierta (la tercera)
			String lastTab = tabsList.get(tabsList.size() - 1);
			driver.switchTo().window(lastTab);
			System.out.println("Foco cambiado a la tercera pestaña (última abierta).");

			try {
				// Verifica si el elemento existe en la nueva ventana
				if (existe(txtErrorDDJJPagos)) {
					// Registra el fallo en el reporte

					// Cierra la pestaña actual y regresa a la pestaña principal
					driver.close();
					driver.switchTo().window(currentTab);

					// Lanza una excepción para detener esta iteración
					manejarErrorYCerrarNavegador("Deteniendo la iteración actual debido a un error en DDJJ Pagos.");
				} else {
					// Registra el éxito en el reporte

				}
			} catch (NoSuchElementException e) {
				// Si el elemento no se encuentra, pasa normalmente

			} finally {
				// Asegúrate de regresar el foco a la pestaña principal
				if (!driver.getWindowHandle().equals(currentTab)) {
					driver.switchTo().window(currentTab);
				}
			}
		}
	}

	public void clickSICAM() {
		clickear(lnkSICAM);
	}

	public void comprobarDenegadoSICAM() {
		try {
			if (existe(txtIngresoDenegado)) {
				// Registra el fallo en el reporte pero no detiene la ejecución

			} else {

			}
		} catch (NoSuchElementException e) {
			// Si no se encuentra el elemento, pasa normalmente

		}
	}


}
