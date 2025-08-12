package base;

import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;


/**
 * Clase base para manejar acciones comunes de Selenium.
 * Incluye métodos para interactuar con elementos web y esperar su disponibilidad antes de continuar.
 */
public class SeleniumBase {

	protected static WebDriver driver;
	private static final Logger logger = LoggerFactory.getLogger(SeleniumBase.class);
	private static final int TIEMPO_ESPERA = 20; // Tiempo máximo de espera en segundos

	WebDriverWait waitShort = new WebDriverWait(driver, Duration.ofSeconds(10));

	/**
	 * Constructor que inicializa el driver de Selenium.
	 *
	 * @param driver instancia de WebDriver
	 */
	public SeleniumBase(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * Muestra una alerta de error y permite al usuario continuar o detener la ejecución.
	 *
	 * @param e excepción capturada
	 */
	private void alertaError(Exception e) {
		String errorMessage = e.getMessage().split("Build info")[0];
		int option = JOptionPane.showConfirmDialog(null, errorMessage + "\n¿Desea continuar? Asegúrese de realizar la acción manualmente antes.", "ERROR", JOptionPane.OK_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE);
		if (option == JOptionPane.CANCEL_OPTION) {
			logger.error("Error: {}", e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

	/**
	 * Espera explícitamente hasta que un elemento sea visible.
	 *
	 * @param localizador By del elemento a esperar
	 * @return WebElement encontrado
	 */
	public WebElement esperarElemento(By localizador) {
		logger.info("Esperando hasta que el elemento esté visible: {}", localizador);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIEMPO_ESPERA));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(localizador));
	}

	protected WebElement esperarElementoClickeable(By localizador) {
		logger.info("Esperando hasta que el elemento esté clickeable: {}", localizador);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIEMPO_ESPERA));
		return wait.until(ExpectedConditions.elementToBeClickable(localizador));
	}

	public List<WebElement> esperarElementos(By localizador) {
		logger.info("Esperando hasta que al menos un elemento esté visible: {}", localizador);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIEMPO_ESPERA));
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(localizador));
	}

	protected boolean esperarHastaElementoVisible(By localizador, int tiempoMaximoSeg) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(tiempoMaximoSeg));
			wait.until(ExpectedConditions.visibilityOfElementLocated(localizador));
			return true;
		} catch (TimeoutException e) {
			return false;
		}
	}

	private void desplazarAlElemento(WebElement elemento) {
		try {
			logger.info("Intentando desplazar al elemento: {}", elemento);

			// Verifica si el elemento está presente y habilitado antes de desplazarse
			if (elemento.isDisplayed()) {
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'nearest'});", elemento);
				logger.info("Desplazamiento al elemento completado.");
			} else {
				logger.warn("El elemento no está visible, no se puede desplazar hasta él.");
			}
		} catch (Exception e) {
			logger.error("Error al intentar desplazar al elemento: {}", e.getMessage());
			alertaError(e);
		}
	}

	protected void desplazarHastaHacerVisible(By locator) {
		try {
			logger.info("Intentando desplazar dinámicamente hasta que el elemento sea visible: {}", locator);

			// Máximo número de intentos de desplazamiento
			int maxScrollAttempts = 10;
			int attempt = 0;

			while (attempt < maxScrollAttempts) {
				try {
					// Intenta encontrar el elemento visible
					WebElement elemento = driver.findElement(locator);
					if (elemento.isDisplayed()) {
						logger.info("El elemento ya es visible en el DOM.");
						return; // Sal del método si el elemento ya es visible
					}
				} catch (Exception e) {
					// Ignora la excepción ya que puede no estar en el DOM aún
				}

				// Desplázate hacia abajo un poco
				((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 300);");
				Thread.sleep(500); // Breve pausa para cargar el contenido dinámico

				attempt++;
			}

			throw new RuntimeException("El elemento no se hizo visible después de " + maxScrollAttempts + " intentos de desplazamiento.");
		} catch (Exception e) {
			logger.error("Error durante el desplazamiento dinámico: {}", e.getMessage());
			alertaError(e);
		}
	}

	/**
	 * Realiza un desplazamiento hacia arriba o abajo.
	 *
	 * @param locator del elemento a buscar
	 */
	protected void desplazarHastaHacerVisibleBidireccional(By locator) {
		try {
			logger.info("Desplazándose dinámicamente en ambas direcciones hasta que el elemento sea visible: {}", locator);

			int maxScrollAttempts = 20;
			int attempt = 0;
			int scrollStep = 300;

			while (attempt < maxScrollAttempts) {
				try {
					WebElement elemento = driver.findElement(locator);
					if (elemento.isDisplayed()) {
						logger.info("Elemento visible después de {} intentos.", attempt);
						return;
					}
				} catch (Exception e) {
					// Ignorar la excepción
				}

				// Alternar entre desplazamiento hacia abajo y hacia arriba
				int direction = (attempt % 2 == 0) ? scrollStep : -scrollStep;
				((JavascriptExecutor) driver).executeScript("window.scrollBy(0, arguments[0]);", direction);

				Thread.sleep(500); // Pausa para contenido dinámico
				attempt++;
			}

			throw new RuntimeException("El elemento no se hizo visible tras el desplazamiento bidireccional.");
		} catch (Exception e) {
			logger.error("Error al desplazar dinámicamente: {}", e.getMessage());
			alertaError(e);
		}
	}

	/**
	 * Navega a la URL especificada.
	 *
	 * @param URL dirección web a visitar
	 */
	protected void irUrl(String URL) {
		logger.info("Navegando a la URL: {}", URL);
		driver.get(URL);
	}

	/**
	 * Encuentra un elemento en la página web.
	 *
	 * @param localizador By para identificar el elemento
	 * @return WebElement encontrado
	 */
	public WebElement encontrarElemento(By localizador) {
		logger.debug("Buscando elemento con localizador: {}", localizador);
		return driver.findElement(localizador);
	}

	/**
	 * Encuentra múltiples elementos en la página web.
	 *
	 * @param localizador By para identificar los elementos
	 * @return Lista de WebElements encontrados
	 */
	public List<WebElement> encontrarElementos(By localizador) {
		logger.debug("Buscando elementos con localizador: {}", localizador);
		return driver.findElements(localizador);
	}


	/**
	 * Hace clic en un elemento identificado por un localizador, esperando que sea clickeable.
	 *
	 * @param localizador By para identificar el elemento
	 */
	protected void clickear(By localizador) {
		try {
			logger.info("Haciendo clic en el elemento: {}", localizador);
			WebElement elemento = esperarElementoClickeable(localizador);
			//desplazarAlElemento(elemento);
			elemento.click();
		} catch (Exception e) {
			alertaError(e);
		}
	}

	protected void clickearConJavaScript(By localizador) {
		WebElement elemento = encontrarElemento(localizador);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", elemento);
	}

	protected void clickearBotonAbrirPDF() {
		esperar(5000);
		try {

			JavascriptExecutor js = (JavascriptExecutor) driver;

			WebElement botonAbrir = (WebElement) js.executeScript("return document.querySelector('pdf-viewer')" + ".shadowRoot.querySelector('#open-button');");

			if (botonAbrir != null) {
				botonAbrir.click();
				System.out.println("Botón 'Abrir' clickeado correctamente.");
			} else {
				System.err.println("No se encontró el botón 'Abrir' dentro del shadow DOM.");
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Error al intentar hacer click en 'Abrir': " + e.getMessage());
		}
	}

	protected void clickearWebElement(WebElement elemento) {
		elemento.click();
	}

	public void clickearPrimeraOcurrencia(By xpathDinamico) {
		try {
			// Obtén todas las ocurrencias del XPath
			List<WebElement> elementos = driver.findElements(xpathDinamico);

			// Valida si existen ocurrencias
			if (elementos.size() > 0) {
				// Haz clic en la primera ocurrencia
				elementos.get(0).click();
				logger.info("Se hizo clic en la primera ocurrencia del elemento.");
			} else {
				logger.warn("No se encontraron elementos que coincidan con el XPath proporcionado.");
			}
		} catch (Exception e) {
			logger.error("Error al intentar hacer clic en la primera ocurrencia: {}", e.getMessage());
			alertaError(e);
		}
	}

	/**
	 * Realiza clic en un elemento SVG utilizando JavaScript Executor.
	 *
	 * @param xpathSvg XPath para localizar el elemento SVG
	 */
	protected void esperarYClickSvg(String xpathSvg, String claseEsperada, int maxIntentos) {
		boolean estadoCambiado = false;
		int intentos = 0;

		while (!estadoCambiado && intentos < maxIntentos) {
			try {
				// Buscar el elemento SVG
				WebElement svgElement = driver.findElement(By.xpath(xpathSvg));

				// Esperar a que sea visible e interactuable
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
				wait.until(ExpectedConditions.elementToBeClickable(svgElement));

				// Intentar hacer clic con Actions para mayor precisión
				Actions actions = new Actions(driver);
				actions.moveToElement(svgElement).click().perform();

				// Verificar si el estado cambió (por ejemplo, clase o propiedad CSS)
				String claseActual = svgElement.getAttribute("class");
				if (claseEsperada == null || claseActual.contains(claseEsperada)) {
					estadoCambiado = true;
				} else {
					System.out.println("Estado no cambió: clase actual = " + claseActual);
				}

			} catch (Exception e) {
				System.out.println("Intento " + (intentos + 1) + ": Error al hacer clic - " + e.getMessage());
			}
			intentos++;
		}

		if (!estadoCambiado) {
			throw new RuntimeException("El estado del elemento SVG no cambió después de " + maxIntentos + " intentos.");
		}
	}

	/**
	 * Intenta hacer clic en un elemento si existe.
	 *
	 * @param localizador Localizador del elemento.
	 * @return true si se logró hacer clic, false si no.
	 */
	protected boolean intentarClick(By localizador) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			WebElement elemento = wait.until(ExpectedConditions.elementToBeClickable(localizador));

			// Desplazar al elemento para evitar problemas de visibilidad
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", elemento);
			Thread.sleep(200); // pequeña pausa para estabilizar el scroll

			try {
				elemento.click();
			} catch (Exception e) {
				// Si el click normal falla, intentamos con JavaScript
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", elemento);
			}

			return true;
		} catch (TimeoutException e) {
			logger.warn("No se encontró elemento clickeable: {}", localizador);
			return false;
		} catch (Exception e) {
			logger.error("Error al intentar click en {}: {}", localizador, e.getMessage());
			return false;
		}
	}

	/**
	 * Reintenta hacer clic en un elemento varias veces antes de fallar.
	 *
	 * @param localizador   Localizador del elemento.
	 * @param maxReintentos Número máximo de reintentos.
	 */
	protected void reintentarClick(By localizador, int maxReintentos) {
		int intentos = 0;
		while (intentos < maxReintentos) {
			try {
				// Esperar que el elemento sea clickeable
				WebElement elemento = esperarElementoClickeable(localizador);

				// Desplazar hacia el elemento (si está fuera del viewport)
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", elemento);

				// Intentar clic normal
				elemento.click();
				logger.info("Clic realizado exitosamente en: {}", localizador);
				return; // Salir del bucle si el clic es exitoso
			} catch (Exception e) {
				intentos++;
				logger.warn("Intento {} de {} fallido para hacer clic en: {}", intentos, maxReintentos, localizador, e);
				try {
					Thread.sleep(500); // Esperar un breve momento antes de reintentar
				} catch (InterruptedException ignored) {
				}
			}
		}
		throw new RuntimeException("No se pudo hacer clic en el elemento después de " + maxReintentos + " intentos: " + localizador);
	}

	/**
	 * Hace click a un boton que le podemos pasar un parametro
	 *
	 * @param value           valor del parametro
	 * @param xpathConFormato xpath
	 */
	protected void clickPorValue(String xpathConFormato, String value) {
		try {
			String xpathFinal = String.format(xpathConFormato, value);
			By localizador = By.xpath(xpathFinal);
			logger.info("Click en el botón con texto '{}': {}", value, xpathFinal);

			WebElement clickElement = esperarElementoClickeable(localizador);
			scrollHastaElemento(clickElement);
			clickElement.click();
		} catch (Exception e) {
			alertaError(e);
		}
	}

	/**
	 * Escribe texto en un campo de entrada, esperando que esté visible.
	 *
	 * @param localizador By para identificar el campo
	 * @param texto       texto a ingresar
	 */
	protected void escribir(By localizador, String texto) {
		try {
			logger.info("Escribiendo texto '{}' en el elemento: {}", texto, localizador);
			WebElement elemento = esperarElemento(localizador);

			// Usa JavaScript si sendKeys no funciona
			try {
				elemento.clear();
				elemento.sendKeys(texto);
			} catch (Exception e) {
				logger.warn("sendKeys falló, intentando con JavaScript.");
				((JavascriptExecutor) driver).executeScript("arguments[0].value = arguments[1];", elemento, texto);
			}

			logger.info("Texto '{}' escrito correctamente en el elemento.", texto);
		} catch (Exception e) {
			logger.error("Error al escribir texto en el elemento: {}", e.getMessage());
			alertaError(e);
		}
	}

	protected void escribirWebElement(WebElement elemento, String texto) {
		try {

			// Usa JavaScript si sendKeys no funciona
			try {
				elemento.clear();
				elemento.sendKeys(texto);
			} catch (Exception e) {
				logger.warn("sendKeys fallo, intentando con JavaScript.");
				((JavascriptExecutor) driver).executeScript("arguments[0].value = arguments[1];", elemento, texto);
			}

			logger.info("Texto '{}' escrito correctamente en  elemento.", texto);
		} catch (Exception e) {
			logger.error("Error escribir texto en el elemento: {}", e.getMessage());
			alertaError(e);
		}
	}

	protected void escribirConEventos(By localizador, String texto) {
		try {
			logger.info("Escribiendo (con eventos) '{}' en: {}", texto, localizador);
			WebElement elemento = esperarElemento(localizador);
			((JavascriptExecutor) driver).executeScript("const el = arguments[0];" + "const val = arguments[1];" + "el.focus();" +
					// si el control tiene un input interno con value distinto, lo seteamos también
					"if (el.tagName.toLowerCase() !== 'input') {" + "  const inner = el.querySelector('input'); if(inner) inner.value = val;" + "}" + "el.value = val;" + "const evTypes = ['keydown','keyup','keypress','input','change','blur'];" + "evTypes.forEach(t => {" + "  const ev = new Event(t, { bubbles: true });" + "  el.dispatchEvent(ev);" + "  if (el.querySelector) { const inner = el.querySelector('input'); if(inner) inner.dispatchEvent(ev); }" + "});" + "el.blur();", elemento, texto);
			logger.info("Escritura (con eventos) realizada.");
		} catch (Exception e) {
			logger.error("Error en escribirConEventos: {}", e.getMessage());
			alertaError(e);
		}
	}

	protected void escribirConActions(By localizador, String texto) {
		try {
			logger.info("Escribiendo (Actions) '{}' en: {}", texto, localizador);
			WebElement elemento = esperarElemento(localizador);
			// Aseguramos focus y borrado
			elemento.click();
			elemento.clear();
			Actions actions = new Actions(driver);
			// Enviar char por char con pequeña pausa
			for (char c : texto.toCharArray()) {
				actions.sendKeys(String.valueOf(c)).pause(Duration.ofMillis(50));
			}
			actions.build().perform();
			logger.info("Escritura (Actions) realizada.");
		} catch (Exception e) {
			logger.error("Error en escribirConActions: {}", e.getMessage());
			alertaError(e);
		}
	}

	protected void escribirRobusto(By localizador, String texto) {
		try {
			logger.info("Escribiendo (robusto) '{}' en: {}", texto, localizador);
			WebElement elemento = esperarElemento(localizador);

			// 1) Intento por JS + eventos
			escribirConEventos(localizador, texto);

			// 2) Verifico
			String valorActual = elemento.getAttribute("value");
			if (valorActual == null) valorActual = "";
			logger.info("Valor actual tras JS: '{}'", valorActual);

			// Si no coincide, intentamos Actions (char a char)
			if (!valorActual.trim().contains(texto.trim())) {
				logger.info("Valor no coincide, reintentando con Actions...");
				escribirConActions(localizador, texto);
			} else {
				logger.info("Valor coincidió tras JS + eventos.");
			}
		} catch (Exception e) {
			logger.error("Error en escribirRobusto: {}", e.getMessage());
			alertaError(e);
		}
	}

	protected boolean escribirTelerikRobusto(By localizador, String texto, boolean textoConGuiones) {
		try {
			logger.info("Escribir (TelerikRobusto) '{}' en {}", texto, localizador);
			WebElement elemento = esperarElemento(localizador);
			String id = elemento.getAttribute("id");
			if (id == null || id.isEmpty()) {
				logger.warn("El elemento no tiene id.");
				return false;
			}
			String clientId = id;
			if (clientId.endsWith("_text")) clientId = clientId.substring(0, clientId.length() - 5);

			// Preparar valor para la API (sin guiones si indicas false)
			String textoParaApi = textoConGuiones ? texto : texto.replaceAll("[^0-9A-Za-z]", "");

			String script = "var cid=arguments[0]; var val=arguments[1];" + "if(typeof $find!=='function') { return {ok:false,msg:'$find_no_existe'}; }" + "var c=$find(cid); if(!c) { return {ok:false,msg:'control_no_encontrado'}; }" + "if(typeof c.set_value==='function') {" + "  c.set_value(val);" + "  if(typeof c.focus==='function') c.focus();" + "  if(typeof c.blur==='function') c.blur();" + "  var read=null;" + "  if(typeof c.get_valueWithPromptAndLiterals==='function') read=c.get_valueWithPromptAndLiterals();" + "  else if(typeof c.get_value==='function') read=c.get_value();" + "  else if(c.get_element) { var el=c.get_element(); read = el && el.value ? el.value : null; }" + "  return {ok:true, value: read};" + "} else {" + "  var el = c.get_element ? c.get_element() : null;" + "  if(el) { el.value = val; el.dispatchEvent(new Event('input',{bubbles:true})); el.dispatchEvent(new Event('change',{bubbles:true})); return {ok:true, value: el.value}; }" + "  return {ok:false, msg:'no_set_value_no_element'};" + "}";

			Object rawResult = ((JavascriptExecutor) driver).executeScript(script, clientId, textoParaApi);
			logger.info("Resultado JS (escribirTelerik) rawResult={}", rawResult);

			// Procesar resultado
			String valorVisible = elemento.getAttribute("value");
			String valorApi = null;
			if (rawResult instanceof java.util.Map) {
				java.util.Map map = (java.util.Map) rawResult;
				Object v = map.get("value");
				Object msg = map.get("msg");
				logger.info("JS result value={}, msg={}", v, msg);
				if (v != null) valorApi = v.toString();
			} else if (rawResult != null) {
				valorApi = rawResult.toString();
			}

			logger.info("Valor visible tras JS: '{}', valor API: '{}'", valorVisible, valorApi);

			String expectedComparable = textoParaApi.replaceAll("[^0-9A-Za-z]", "").trim();
			boolean okApi = (valorApi != null && valorApi.replaceAll("[^0-9A-Za-z]", "").contains(expectedComparable));
			boolean okVisible = (valorVisible != null && valorVisible.replaceAll("[^0-9A-Za-z]", "").contains(expectedComparable));
			if (okApi || okVisible) {
				logger.info("Seteo exitoso vía API/visible.");
				return true;
			}

			// Fallback Actions
			logger.warn("No coincidió el valor tras set_value. Intentando fallback con Actions (tecla a tecla).");
			try {
				elemento.click();
				elemento.clear();
				Actions actions = new Actions(driver);
				for (char c : texto.toCharArray()) {
					actions.sendKeys(String.valueOf(c)).pause(Duration.ofMillis(40));
				}
				actions.perform();
				String afterVisible = elemento.getAttribute("value");
				logger.info("Valor visible tras fallback Actions: '{}'", afterVisible);
				if (afterVisible != null && afterVisible.replaceAll("[^0-9A-Za-z]", "").contains(expectedComparable)) {
					logger.info("Seteo exitoso via Actions.");
					return true;
				} else {
					logger.error("Fallback con Actions no logró establecer el valor esperado.");
				}
			} catch (Exception actEx) {
				logger.error("Error en fallback Actions: {}", actEx.getMessage());
			}

			logger.error("No fue posible setear el valor en el control Telerik.");
			return false;

		} catch (JavascriptException jse) {
			logger.error("JavascriptException al ejecutar script: {}", jse.getMessage());
			alertaError(jse);
			return false;
		} catch (Exception e) {
			logger.error("Excepción en escribirTelerikRobusto: {}", e.getMessage());
			alertaError(e);
			return false;
		}
	}

	/**
	 * Limpia el contenido de un campo de texto, esperando que esté visible.
	 *
	 * @param localizador By para identificar el campo
	 */
	protected void limpiar(By localizador) {
		try {
			logger.info("Limpiando contenido del elemento: {}", localizador);
			WebElement elemento = esperarElemento(localizador);
			elemento.clear();
		} catch (Exception e) {
			alertaError(e);
		}
	}

	/**
	 * Selecciona una opción en un desplegable por el texto visible, esperando que esté presente.
	 *
	 * @param localizador By para identificar el desplegable
	 * @param texto       texto visible de la opción
	 */
	protected void seleccionarOpcionPorTexto(By localizador, String texto) {
		try {
			logger.info("Seleccionando opción '{}' en el elemento: {}", texto, localizador);
			WebElement selectElement = esperarElemento(localizador);
			Select select = new Select(selectElement);
			select.selectByVisibleText(texto);
		} catch (Exception e) {
			alertaError(e);
		}
	}

	/**
	 * Selecciona una opción en un desplegable por el valor en el HTML, esperando que esté presente.
	 *
	 * @param localizador By para identificar el desplegable
	 * @param value       valor del atributo 'value' en el HTML
	 */
	protected void seleccionarOpcionPorValue(By localizador, String value) {
		try {
			logger.info("Seleccionando opción por value '{}' en el elemento: {}", value, localizador);
			WebElement selectElement = esperarElemento(localizador);
			Select select = new Select(selectElement);
			select.selectByValue(value);
		} catch (Exception e) {
			alertaError(e);
		}
	}

	/**
	 * Selecciona una opción en un desplegable utilizando un texto dinámico y seleccionando la primera coincidencia.
	 *
	 * @param texto texto dinámico que se buscará en las opciones
	 */
	protected void seleccionarOpcionConTexto(String xpathTemplate, String texto) {
		try {
			// Construye el XPath dinámico con el texto parametrizado
			String xpath = String.format(xpathTemplate, texto.trim());

			logger.info("Buscando la opción con XPath: {}", xpath);

			// Espera que el elemento esté visible
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			WebElement opcion = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));

			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", opcion);

			Thread.sleep(500);

			// Haz clic en la opción encontrada
			logger.info("Seleccionando la opción: {}", opcion.getText().trim());
			opcion.click();
		} catch (Exception e) {
			logger.error("Error al seleccionar la opción con XPath '{}' y texto '{}': {}", xpathTemplate, texto, e.getMessage());
			alertaError(e);
		}
	}

	protected void seleccionarOpcionConTextoCompleto(String texto, String xpath) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

			// Esperar que al menos una opción esté presente y visible
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));

			int intentos = 3; // Reintentar hasta 3 veces si el elemento se vuelve obsoleto

			while (intentos > 0) {
				try {
					// Obtener todas las opciones disponibles
					List<WebElement> opciones = driver.findElements(By.xpath(xpath));

					for (int i = 0; i < opciones.size(); i++) {
						WebElement opcion = opciones.get(i);

						// Extraer los textos de todos los <b> dentro de la opción
						List<WebElement> partes = opcion.findElements(By.xpath(".//b"));
						String textoCompleto = partes.stream().map(WebElement::getText).reduce("", (a, b) -> a + " " + b).trim();

						logger.info("Texto combinado de la opción: '{}'", textoCompleto);

						// Verificar si el texto combinado contiene el texto buscado
						if (textoCompleto.equalsIgnoreCase(texto) || textoCompleto.contains(texto)) {
							logger.info("Opción encontrada: '{}'. Intentando hacer clic...", textoCompleto);

							// Esperar que la opción sea clickeable antes de hacer clic
							WebElement opcionActualizada = wait.until(ExpectedConditions.elementToBeClickable(opcion));
							opcionActualizada.click();

							logger.info("Opción seleccionada correctamente: '{}'", textoCompleto);
							return;
						}
					}

					logger.warn("No se encontró la opción con texto parcial: {}", texto);
					return;
				} catch (StaleElementReferenceException e) {
					logger.warn("Elemento quedó obsoleto, reintentando...");
					intentos--;
				}
			}

			logger.error("No se pudo seleccionar la opción después de varios intentos.");
		} catch (Exception e) {
			logger.error("Error al seleccionar la opción con texto '{}': {}", texto, e.getMessage());
			alertaError(e);
		}
	}

	protected void seleccionarPorWebElement(WebElement elemento, String texto) {
		try {
			logger.info("Seleccionando opcion '{}' en el elemento: {}", texto, elemento);
			Select select = new Select(elemento);
			select.selectByVisibleText(texto);
		} catch (Exception e) {
			alertaError(e);
		}
	}

	/**
	 * Verifica si un elemento existe y está visible en la página.
	 *
	 * @param localizador By para identificar el elemento
	 * @return true si existe, false si no
	 */
	protected boolean existe(By localizador) {
		try {
			return !driver.findElements(localizador).isEmpty();
		} catch (Exception e) {
			System.out.println("Error al buscar elemento: " + localizador);
			return false;
		}
	}

	protected boolean existeDirecto(By elemento) {
		return !driver.findElements(elemento).isEmpty();
	}

	protected boolean existeClickeable(By localizador) {
		try {
			esperarElementoClickeable(localizador);
			return true;
		} catch (TimeoutException e) {
			logger.warn("El elemento no existe o no es visible: {}", localizador);
			return false;
		}
	}

	/**
	 * Verifica si un elemento existe y está visible en la página.
	 *
	 * @param xpath para identificar el elemento directo.
	 * @return true si existe, false si no
	 */
	protected boolean elementoExiste(String xpath) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
			return true; // El elemento está presente
		} catch (Exception e) {
			return false; // El elemento no está presente
		}
	}

	/**
	 * Verifica si el elemento esta habilitado y está visible en la página.
	 *
	 * @param locator para identificar el elemento directo.
	 */
	protected boolean elementoHabilitado(By locator) {
		try {
			WebElement elemento = driver.findElement(locator);
			return elemento.isDisplayed();
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	/**
	 * Realiza un manejo de errores y detiene la ejecucion.
	 *
	 * @param mensajeError aqui indicamos que mensaje nos gustaria dar.
	 */
	protected void manejarErrorYCerrarNavegador(String mensajeError) {
		try {
			// Lanza el error al log/reporte

			// Cierra el navegador actual
			if (driver != null) {
				driver.quit();
				System.out.println("Navegador cerrado debido al error: " + mensajeError);
			}
		} catch (Exception e) {
			System.err.println("Error al cerrar el navegador: " + e.getMessage());
		} finally {
			// Lanza una excepción para detener la iteración actual
			throw new RuntimeException(mensajeError);
		}
	}

	protected void scrollHastaElFinal() {
		try {
			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
			logger.info("Desplazándose hasta el final del contenido.");

			jsExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight);");
			Thread.sleep(1000); // Pausa para permitir que se cargue el contenido dinámico

			logger.info("Desplazamiento hasta el final completado.");
		} catch (Exception e) {
			logger.error("Error al desplazarse hasta el final del contenido: {}", e.getMessage());
			alertaError(e);
		}
	}

	protected void scrollHastaElemento(WebElement elemento) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", elemento);
	}

	/**
	 * Realiza un tab ya sea a un elemento o no.
	 *
	 * @param elemento indicamos el elemento al hacer tab.
	 */
	protected void hacerTab(WebElement elemento) {
		try {
			if (elemento != null) {
				logger.info("Haciendo 'Tab' en un elemento específico.");
				elemento.sendKeys(Keys.TAB);
			} else {
				logger.info("Haciendo 'Tab' de manera global (sin un elemento específico).");
				Actions actions = new Actions(driver);
				actions.sendKeys(Keys.TAB).perform();
			}
			logger.info("Acción 'Tab' realizada con éxito.");
		} catch (Exception e) {
			logger.error("Error al realizar la acción 'Tab': {}", e.getMessage());
			alertaError(e);
		}
	}

	protected void esperar(int milisegundos) {
		try {
			Thread.sleep(milisegundos);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			System.out.println("Error durante la espera: " + e.getMessage());
		}
	}

	/**
	 * Realizar el cambio de foco a la nueva pestaña.
	 */
	protected void cambiarFocoNuevaPestania() {
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
	}


	private WebElement Find(String locator) {
		WebElement element = null;

		try {
			// Esperamos hasta que el elemento esté presente y visible
			element = waitShort.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
			if (element.isDisplayed() && element.isEnabled()) {
				return element;
			}
		} catch (TimeoutException e) {
			System.out.println("Tiempo de espera agotado: No se encontró el elemento.");
		} catch (NoSuchElementException e) {
			System.out.println("No se encontró el elemento: " + locator);
		}

		throw new RuntimeException("No se pudo encontrar el elemento: " + locator);
	}

	public void clickElement(String locator) {
		Find(locator).click();
	}

	public void tryCloseModalsIfPresent(WebDriver driver) {
		try {
			// Buscar botón Aceptar
			List<WebElement> aceptarBtns = driver.findElements(By.xpath("//button[contains(text(), 'Aceptar')]"));

			if (!aceptarBtns.isEmpty()) {
				aceptarBtns.get(0).click();
				System.out.println("Botón 'Aceptar' detectado y clickeado.");
				return;
			}

			// Buscar botón Omitir
			List<WebElement> omitirBtns = driver.findElements(By.xpath("//button[contains(text(), 'Omitir')]"));

			if (!omitirBtns.isEmpty()) {
				omitirBtns.get(0).click();
				System.out.println("Botón 'Omitir' detectado y clickeado.");
				return;
			}

			// Podés seguir agregando más modales si necesitás
		} catch (Exception e) {
			System.out.println("No se detectó modal o ocurrió un error al cerrarlo: " + e.getMessage());
		}
	}

	// selector de dropdown para el menu de bepe según el optionText pasado como
	// parámetro
	public void selectFromCustomDropdown(String dropdownLocator, String optionText) {
		// Hacer clic en el dropdown para abrir las opciones
		clickElement(dropdownLocator);

		// Construir el XPath dinámicamente dependiendo de si el texto está en un <span>
		// o <div>
		String optionXpath = String.format("//mat-option[.//span[contains(text(), '%s')] or .//div[contains(text(), '%s')]]", optionText, optionText);

		clickElement(optionXpath);
	}

	// algunos elementos web, no aparecen hasta que "hagas click" en otro elemento
	// web en particular.
	public void clickAndWrite(String locator1, String locator2, String textToWrite) {
		clickElement(locator1);
		Find(locator2).clear();
		Find(locator2).sendKeys(textToWrite);
	}

	public void write(String locator, String textToWrite) {
		Find(locator).clear();
		Find(locator).sendKeys(textToWrite);
	}

	public String textFromElement(String locator) {
		try {
			return Find(locator).getText();
		} catch (Exception e) {
			throw new RuntimeException("Error al obtener el texto del elemento: " + locator, e);
		}
	}

	// scrollea 1 vez hacia abajo
	public void scrollDownOnce() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0, window.innerHeight);");
	}

	// scrollea x veces hacia abajo
	public void scrollDown(int times) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		for (int i = 0; i < times; i++) {
			js.executeScript("window.scrollBy(0, window.innerHeight);");
		}
	}

	public void findRowAndClickRadio(String filaXpath) {

		// Encontrar la fila correspondiente
		WebElement fila = driver.findElement(By.xpath(filaXpath));
		// Buscar el checkbox dentro de esa fila
		WebElement checkbox = fila.findElement(By.xpath(".//input[@type='radio']"));

		// Hacer clic en el checkbox
		checkbox.click();
	}

	public void findRowAndClickCheckbox(String filaXpath) {
		// Encontrar la fila correspondiente
		WebElement fila = driver.findElement(By.xpath(filaXpath));
		// Buscar el checkbox dentro de esa fila
		WebElement checkbox = fila.findElement(By.xpath(".//input[@type='checkbox']"));

		// Hacer clic en el checkbox
		checkbox.click();
	}

	public String findRowAndReturnData(String filaXpath, String columnNumber) {
		WebElement fila = driver.findElement(By.xpath(filaXpath));
		// Obtener el mensaje de la x columna de la fila dada
		WebElement text = fila.findElement(By.xpath("./td[" + columnNumber + "]"));
		return text.getText();
	}

	// devuelve la posición de la fila de la tabla. Recordar que el theader cuenta como fila.
	public String findPosRowInTable(String xpathTabla, String cbuBuscado) {
		try {
			// Esperar a que la tabla esté presente y visible antes de ejecutar el código
			WebElement tabla = waitShort.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathTabla)));
			// Esperar a que todas las filas de la tabla sean cargadas (al menos una fila debe estar presente)
			waitShort.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpathTabla + "/tbody/tr")));


			// Ejecutar JavaScript en el navegador para encontrar la fila del CBU
			JavascriptExecutor js = (JavascriptExecutor) driver;
			String fila = (String) js.executeScript("function encontrarFilaCBU(xpathTabla, cbuBuscado) {" + "    let tabla = document.evaluate(xpathTabla, document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue;" + "    if (!tabla) return '-1';" +  // Si no se encuentra la tabla, devolver '-1'" +
					"    let filas = tabla.getElementsByTagName('tr');" + "    for (let i = 0; i < filas.length; i++) {" + "        if (filas[i].innerText.includes(cbuBuscado)) return (i + 1).toString();" + // Devolver el índice de la fila como string
					"    }" + "    return '-1';" + // Si no se encuentra el CBU, devolver '-1'
					"}" + "return encontrarFilaCBU(arguments[0], arguments[1]);", xpathTabla, cbuBuscado);

			// Retornar el número de fila donde se encontró el CBU, o "-1" si no se encuentra
			if (!fila.equals("-1")) {       // le resto 1 porque cuenta el theader como fila
				int filaInt = Integer.parseInt(fila);  // Convertir el string a int
				filaInt = filaInt - 1;  // Restar 1
				fila = String.valueOf(filaInt);  // Convertir de nuevo a string
			}
			return fila;

		} catch (Exception e) {
			// Manejo de excepciones si algo sale mal
			e.printStackTrace();
			return "-1";
		}
	}

	public boolean isElementPresent(String xpath) {
		try {
			return driver.findElement(By.xpath(xpath)).isDisplayed();
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public boolean isOnDashboardPage() {
		String currentUrl = driver.getCurrentUrl();
		return currentUrl.contains("dashboard");
	}

	public List listAccountNumber() {
		List<String> listaCBUs = new ArrayList<>();
		// Obtener todos los elementos que contienen el CBU
		List<WebElement> elementosCBU = driver.findElements(By.xpath("//div[contains(text(), 'CBU')]"));
		// Recorrer los elementos y extraer el texto
		for (WebElement elemento : elementosCBU) {
			String texto = elemento.getText();
			// Extraer solo el número del CBU
			String cbu = texto.replace("CBU ", "").trim();
			listaCBUs.add(cbu);
		}
		return listaCBUs; // Retornar la lista de CBUs
	}

	public WebElement obtenerLista(String contenedorXpath) {
		// Esperar a que el contenedor esté visible
		WebElement dropdown = waitShort.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(contenedorXpath)));

		// Busca dentro del dropdown la opción que contiene el texto deseado
		return dropdown.findElement(By.xpath(contenedorXpath));
	}

	public static int[] getCurrentDate() {
		LocalDate today = LocalDate.now();
		return new int[]{today.getDayOfMonth(), today.getMonthValue(), today.getYear()};
	}

	public void waitSeconds(int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			System.out.println("Wait was interrupted: " + e.getMessage());
		}
	}


	/**
	 * Realiza una accion por fuera de la web para adjuntar.
	 *
	 * @param rutaArchivo indicamos el archivo para adjuntar.
	 */
	protected void seleccionarArchivoWindows(String rutaArchivo) {
		try {

			// Esperar brevemente para asegurarte de que la ventana esté visible
			Thread.sleep(1000);

			// Copiar la ruta del archivo al portapapeles
			StringSelection seleccion = new StringSelection(rutaArchivo);
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(seleccion, null);

			// Usar Robot Class para pegar la ruta y presionar Enter
			Robot robot = new Robot();

			// Pegar la ruta del archivo (Ctrl + V)
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);

			// Presionar Enter para adjuntar el archivo
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);

			System.out.println("Archivo adjuntado exitosamente.");
		} catch (AWTException | InterruptedException e) {
			e.printStackTrace();
			System.err.println("Error al adjuntar archivo: " + e.getMessage());
		}
	}

	/**
	 * Guarda un archivo PDF en una ubicación especificada con nombre personalizado.
	 * Espera dinámicamente a que aparezca la ventana de "Guardar como".
	 *
	 * @param nombreBase El nombre base del archivo (ej: "consultaMastercard").
	 *                   Se le agrega fecha y hora automáticamente.
	 */
	protected void guardarArchivoWindows(String nombreBase) {
		try {
			// Ruta fija + nombre dinámico con fecha y hora
			String timestamp = new java.text.SimpleDateFormat("yyyyMMdd_HHmmss").format(new java.util.Date());
			String rutaCompleta = "C:\\Temp\\" + nombreBase + "_" + timestamp + ".pdf";

			// Espera dinámica (hasta 30 segundos)
			boolean ventanaEncontrada = false;
			int maxWaitSeconds = 30;
			int waitedSeconds = 0;

			while (!ventanaEncontrada && waitedSeconds < maxWaitSeconds) {
				for (java.awt.Window window : java.awt.Window.getWindows()) {
					if (window.isActive() && window.isVisible()) {
						ventanaEncontrada = true;
						break;
					}
				}
				if (!ventanaEncontrada) {
					Thread.sleep(1000);
					waitedSeconds++;
				}
			}

			if (!ventanaEncontrada) {
				System.err.println("No se detectó la ventana de 'Guardar como'.");
				return;
			}

			// Copiar ruta al portapapeles
			StringSelection seleccion = new StringSelection(rutaCompleta);
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(seleccion, null);

			// Usar Robot para pegar la ruta y guardar
			Robot robot = new Robot();

			// Pegar (Ctrl + V)
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);

			Thread.sleep(300); // pequeña pausa antes de Enter

			// Presionar Enter para guardar
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);

			System.out.println("Archivo guardado como: " + rutaCompleta);

		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Error al guardar archivo: " + e.getMessage());
		}
	}

	/**
	 * Descarga un archivo marcado como "no seguro" desde el gestor de descargas de Chrome.
	 * Utiliza Robot para abrir el historial y Selenium para interactuar con el Shadow DOM.
	 */
	protected void descargarArchivoNoSeguro(int tiempoMaximoSeg, String carpetaDescargas, String tipoArchivoEsperado) {
		String handleOriginal = driver.getWindowHandle();
		Set<String> handlesAntes = new HashSet<>(driver.getWindowHandles());

		try {
			Robot robot = new Robot();
			robot.setAutoDelay(400);

			// ---- Abrir historial de descargas ----
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_J);
			robot.keyRelease(KeyEvent.VK_J);
			robot.keyRelease(KeyEvent.VK_CONTROL);

			// ---- Esperar nuevo handle (pestaña del gestor) ----
			String handleGestor = esperarHastaObtenerHandleNuevo(handlesAntes, tiempoMaximoSeg);
			if (handleGestor == null) throw new RuntimeException("No se abrió la pestaña del gestor de descargas.");

			// Cambiar a la pestaña del gestor
			driver.switchTo().window(handleGestor);

			// ---- Esperar que el downloads-manager esté presente ----
			boolean gestorVisible = esperarHasta(() -> driver.findElements(By.tagName("downloads-manager")).size() > 0, tiempoMaximoSeg);
			if (!gestorVisible) throw new RuntimeException("No se cargó el gestor de descargas.");

			// Acceder al shadow root del downloads-manager
			WebElement downloadsManager = driver.findElement(By.tagName("downloads-manager"));
			SearchContext shadowRoot1 = (SearchContext) ((JavascriptExecutor) driver).executeScript("return arguments[0].shadowRoot", downloadsManager);

			// ---- Esperar a que aparezca al menos un downloads-item ----
			boolean itemVisible = esperarHasta(() -> shadowRoot1.findElements(By.cssSelector("downloads-item")).size() > 0, tiempoMaximoSeg);
			if (!itemVisible) throw new RuntimeException("No apareció ningún elemento de descarga en el gestor.");

			// Capturamos listado previo de archivos para detectar el nuevo
			File carpeta = new File(carpetaDescargas);
			Set<String> archivosAntes = carpeta.exists() && carpeta.isDirectory() ? Arrays.stream(Optional.ofNullable(carpeta.listFiles()).orElse(new File[0])).map(File::getName).collect(Collectors.toSet()) : Collections.emptySet();

			long tStart = System.currentTimeMillis();

			// Obtener primer downloads-item y su shadow root
			WebElement firstItem = shadowRoot1.findElement(By.cssSelector("downloads-item"));
			SearchContext shadowRoot2 = (SearchContext) ((JavascriptExecutor) driver).executeScript("return arguments[0].shadowRoot", firstItem);

			// ---- Abrir menú de acciones (3 puntitos) ----
			WebElement menuButton = shadowRoot2.findElement(By.cssSelector("#more-actions"));
			menuButton.click();

			// ---- Click en "save dangerous" (Descargar archivo no seguro) ----
			boolean botonVisible = esperarHasta(() -> shadowRoot2.findElements(By.cssSelector("#save-dangerous")).size() > 0, Math.min(tiempoMaximoSeg, 10));
			if (!botonVisible) throw new RuntimeException("No apareció el botón para 'Descargar archivo no seguro'.");

			WebElement btnDescargar = shadowRoot2.findElement(By.cssSelector("#save-dangerous"));
			btnDescargar.click();

			esperar(2000);

			System.out.println("Acción de descarga (archivo no seguro) enviada. Esperando que el archivo aparezca y finalice...");

			// ---- Esperar que aparezca en carpeta un archivo nuevo y que NO esté en .crdownload ----
			boolean descargado = esperarHasta(() -> {
				File[] lista = Optional.ofNullable(carpeta.listFiles()).orElse(new File[0]);
				for (File f : lista) {
					String name = f.getName();
					if (archivosAntes.contains(name)) continue;            // no es nuevo
					if (name.endsWith(".crdownload")) return false;       // todavía en progreso
					if (tipoArchivoEsperado == null || tipoArchivoEsperado.isEmpty()) {
						// cualquier archivo nuevo sirve
						return f.lastModified() >= tStart - 5000;
					} else {
						if (name.toLowerCase().endsWith(tipoArchivoEsperado.toLowerCase()) && f.lastModified() >= tStart - 5000) {
							return true;
						}
					}
				}
				return false;
			}, tiempoMaximoSeg);

			if (!descargado) {
				System.err.println("Advertencia: no se detectó el archivo finalizado dentro del timeout.");
			} else {
				System.out.println("Archivo detectado y finalizado en la carpeta de descargas.");
			}

			// ---- Cerrar pestaña del gestor y volver a la original ----
			try {
				driver.close(); // cierra la pestaña del gestor
			} catch (Exception e) {
				System.err.println("Warning: no se pudo cerrar la pestaña del gestor: " + e.getMessage());
			}

			// Si la original sigue presente, volver a ella; sino, cambiar a una pestaña disponible
			Set<String> handlesNow = driver.getWindowHandles();
			if (handlesNow.contains(handleOriginal)) {
				driver.switchTo().window(handleOriginal);
			} else if (!handlesNow.isEmpty()) {
				driver.switchTo().window(handlesNow.iterator().next());
			} else {
				System.err.println("No hay ventanas abiertas para volver.");
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Error en descargarArchivoNoSeguroConVerificacion: " + e.getMessage());
			// Intentamos volver a la original en cualquier caso
			try {
				if (driver != null && handleOriginal != null && driver.getWindowHandles().contains(handleOriginal)) {
					driver.switchTo().window(handleOriginal);
				} else if (driver != null && !driver.getWindowHandles().isEmpty()) {
					driver.switchTo().window(driver.getWindowHandles().iterator().next());
				}
			} catch (Exception ignored) {
			}
		}
	}

	protected String esperarHastaObtenerHandleNuevo(Set<String> handlesAntes, int tiempoMaximoSeg) {
		long inicio = System.currentTimeMillis();
		while (System.currentTimeMillis() - inicio < tiempoMaximoSeg * 1000) {
			Set<String> ahora = driver.getWindowHandles();
			// calcular diferencia
			Set<String> copia = new HashSet<>(ahora);
			copia.removeAll(handlesAntes);
			if (!copia.isEmpty()) {
				// devolvemos el handle nuevo (si hay varios, devolvemos el primero)
				return copia.iterator().next();
			}
			try {
				Thread.sleep(300);
			} catch (InterruptedException ignored) {
			}
		}
		return null;
	}

	private boolean esperarHasta(Supplier<Boolean> condicion, int tiempoMaximoSeg) {
		long inicio = System.currentTimeMillis();
		while (System.currentTimeMillis() - inicio < tiempoMaximoSeg * 1000) {
			try {
				if (condicion.get()) return true;
			} catch (Exception ignored) {
			}
			try {
				Thread.sleep(300);
			} catch (InterruptedException ignored) {
			}
		}
		return false;
	}

	/**
	 * Espera dinámicamente a que se descargue un archivo (PDF, Excel, etc.) y lo renombra con un nombre base + timestamp.
	 *
	 * @param nombreBase       Nombre base del archivo sin extensión (ej: "consultaMastercard")
	 * @param carpetaDescargas Ruta donde se descarga el archivo (ej: "C:\\Temp")
	 * @param tipoArchivo      Extensión del archivo a buscar (ej: ".pdf", ".xls", ".xlsx")
	 * @param tiempoMaximoSeg  Tiempo máximo de espera en segundos
	 */
	public void renombrarArchivo(String nombreBase, String carpetaDescargas, String tipoArchivo, int tiempoMaximoSeg) {
		File carpeta = new File(carpetaDescargas);

		long tiempoInicio = System.currentTimeMillis();
		File archivoFinal = null;

		while (System.currentTimeMillis() - tiempoInicio < tiempoMaximoSeg * 1000) {
			// Buscar cualquier archivo del tipo especificado que no esté en descarga
			Optional<File> archivoOpt = Arrays.stream(carpeta.listFiles()).filter(f -> f.isFile() && f.getName().toLowerCase().endsWith(tipoArchivo.toLowerCase()) && !f.getName().endsWith(".crdownload")).max(Comparator.comparingLong(File::lastModified));

			if (archivoOpt.isPresent()) {
				archivoFinal = archivoOpt.get();
				break;
			}

			try {
				Thread.sleep(500); // Espera medio segundo antes de volver a revisar
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		if (archivoFinal != null) {
			String timestamp = new java.text.SimpleDateFormat("yyyyMMdd_HHmmss").format(new java.util.Date());
			File nuevoArchivo = new File(carpetaDescargas + "\\" + nombreBase + "_" + timestamp + tipoArchivo);
			if (archivoFinal.renameTo(nuevoArchivo)) {
				System.out.println("Archivo renombrado a: " + nuevoArchivo.getName());
			} else {
				System.out.println("No se pudo renombrar el archivo.");
			}
		} else {
			System.out.println("No se encontró un archivo " + tipoArchivo + " descargado en el tiempo límite.");
		}
	}

	/**
	 * Permite escribir una fecha con JavaScript.
	 *
	 * @param fechaEsperada indicamos la fecha por parametro.
	 */
	protected void seleccionarFechaJS(By selector, String fechaEsperada) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			WebElement campoFecha = wait.until(ExpectedConditions.elementToBeClickable(selector));

			// **Opción 1: Intentar con JavaScript**
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].value = arguments[1]; arguments[0].dispatchEvent(new Event('change'));", campoFecha, fechaEsperada);

			// Verificar si la fecha se estableció correctamente
			String fechaActual = campoFecha.getAttribute("value");
			if (fechaActual.equals(fechaEsperada)) {
				logger.info("Fecha '{}' establecida con JavaScript.", fechaEsperada);
				return;
			}

			// **Opción 2: Si JavaScript falla, usar flechas del teclado**
			campoFecha.click(); // Abrir el calendario
			Thread.sleep(500);

			int intentos = 0;
			while (!fechaActual.equals(fechaEsperada) && intentos < 50) {
				if (fechaActual.compareTo(fechaEsperada) > 0) {
					campoFecha.sendKeys(Keys.ARROW_DOWN); // Retroceder fechas
				} else {
					campoFecha.sendKeys(Keys.ARROW_UP); // Avanzar fechas
				}

				Thread.sleep(100);
				fechaActual = campoFecha.getAttribute("value"); // Re-obtener la fecha
				intentos++;
			}

			if (!fechaActual.equals(fechaEsperada)) {
				throw new RuntimeException("No se pudo establecer la fecha después de " + intentos + " intentos.");
			}

			logger.info("Fecha '{}' seleccionada correctamente.", fechaEsperada);
		} catch (Exception e) {
			logger.error("Error al seleccionar la fecha '{}': {}", fechaEsperada, e.getMessage());
		}
	}

}
