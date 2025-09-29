package page;

import base.SeleniumBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.Assert.fail;

public class SolicitudesPage extends SeleniumBase {
    private final By fullNameIdLabel = By.xpath("//a[@id='ctl00_ContentFiltros_BusquedaCliente1_optApellido']");
    private final By fullNameInput = By.xpath("//input[@id='ctl00_ContentFiltros_BusquedaCliente1_txtBusqApellidoNombre_text']");
    private final By cuitCuilLabel = By.xpath("//a[@id='ctl00_ContentFiltros_BusquedaCliente1_optCuit']");
    private final By searchButton = By.xpath("//input[@value='Buscar']");
    private final By searchResultTable = By.xpath("//div[@id='ctl00_ContentFiltros_BusquedaCliente1_grdClientes']");
    private final By documentTypeNumberLabel = By.xpath("//a[@id='ctl00_ContentFiltros_BusquedaCliente1_optDocumento']");
    private final By documentTypeNumberSelect = By.xpath("//input[@id='ctl00_ContentFiltros_BusquedaCliente1_cboBusqTipoDoc_Input']");
    private final By documentTypeNumberInput = By.xpath("//input[@id='ctl00_ContentFiltros_BusquedaCliente1_txtBusqNroDoc_text']");
    private final By cuitCuilInput = By.xpath("//input[@id='ctl00_ContentFiltros_BusquedaCliente1_txtNroCuit_text']");
    private final By typeAndKeyNumberLabel = By.xpath("//a[@id='ctl00_ContentFiltros_BusquedaCliente1_optClave']");
    private final By keyNumberInput = By.xpath("//input[@id='ctl00_ContentFiltros_BusquedaCliente1_txtBusqNroClave_text']");
    private final By keyNumberSelect = By.xpath("//input[@id='ctl00_ContentFiltros_BusquedaCliente1_cboBusqTipoClave_Input']");
    private final By selectedRecordtext = By.xpath("//textarea[@id='ctl00_lblFormulario_text']");
    private final By stateLabel = By.xpath("//input[@id='ctl00_ContentFiltros_BusquedaSolicitud1_cboBusqEstado_Input']");
    private final By searchButtonInIndividualRequest = By.xpath("//input[@id='ctl00_ContentFiltros_BusquedaSolicitud1_cmdVerSol']");
    private final By accountNumberInput = By.xpath("//input[@id='ctl00_ContentFiltros_BusquedaSolicitud1_txtNroCta_text']");
    private final By rangeOfRequestFromInput = By.xpath("//input[@id='ctl00_ContentFiltros_BusquedaSolicitud1_txtSolicitudDesde_text']");
    private final By rangeOfRequestToInput = By.xpath("//input[@id='ctl00_ContentFiltros_BusquedaSolicitud1_txtSolicitudHasta_text']");
    private final By requestNumberInput = By.xpath("//input[@id='ctl00_ContentFiltros_BusquedaSolicitud1_txtNroSolicitud_text']");
    private final By statusDateInput = By.xpath("//input[@id='ctl00_ContentFiltros_BusquedaSolicitud1_CalendarFEstado_dateInput_text']");
    private final By registrationSectionInput = By.xpath("//input[@id='ctl00_ContentFiltros_BusquedaSolicitud1_cboBusqSectorAlta_Input']");
    private final By typeDocumentInput = By.xpath("//input[@id='ctl00_ContentFiltros_BusquedaSolicitud1_cboBusqCodTipDoc_Input']");
    private final By numberDocumentInput = By.xpath("//input[@id='ctl00_ContentFiltros_BusquedaSolicitud1_txtNrodDoc_text']");
    private final By fullNameInputInIndividualRequestPage = By.xpath("//input[@id='ctl00_ContentFiltros_BusquedaSolicitud1_txtBusqApellidoNombre_text']");


    public SolicitudesPage(WebDriver driver) {
        super(driver);
    }

    public String searchState() {

//        // Caso 1: No encontró resultados
////            WebElement noRecords = wait.until(ExpectedConditions.presenceOfElementLocated(
////                    By.xpath("//*[contains(text(),'No records to display.')]")));
//        WebElement noRecords = esperarElemento(By.xpath("//*[contains(text(),'No records to display.')]"));
//        if (noRecords.isDisplayed()) {
//            System.out.println("No se encontraron resultados.");
//            //return EstadoBusqueda.SIN_RESULTADOS;
//            return "No se encontraron resultados.";
//        }
//
//        // Caso 2: Exactamente un resultado
////            WebElement unicoRegistro = wait.until(ExpectedConditions.presenceOfElementLocated(
////                    By.id("ctl00_lblFormulario_text")));
//        WebElement unicoRegistro = esperarElemento(By.id("ctl00_lblFormulario_text"));
//        if (unicoRegistro.isDisplayed() && unicoRegistro.getText().contains("Registro Seleccionado")) {
//            System.out.println("Se encontró 1 solo resultado.");
//            //return EstadoBusqueda.UNO;
//            return "Se encontró 1 solo resultado.";
//        }
//
////        try {
////            // Caso 3: Múltiples resultados
////            WebElement tabla = wait.until(ExpectedConditions.presenceOfElementLocated(
////                    By.id("ctl00_ContentFiltros_BusquedaSolicitud1_grdSolicitudes_ctl00")));
////            List<WebElement> filas = tabla.findElements(By.xpath(".//tbody/tr[not(contains(@class,'rgNoRecords'))]"));
////            if (!filas.isEmpty()) {
////                System.out.println("Se encontraron múltiples resultados: " + filas.size() + " filas.");
////                return EstadoBusqueda.MULTIPLES;
////            }
////        } catch (TimeoutException e) {
////            // no apareció la tabla
////        }
////        return EstadoBusqueda.DESCONOCIDO;
//        return "Estado desconocido. Revisar caso de prueba.";


        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // Esperamos a que aparezca al menos uno de los posibles elementos
        wait.until(ExpectedConditions.or(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()='No records to display.']")),
                ExpectedConditions.presenceOfElementLocated(By.id("ctl00_lblFormulario")),
                ExpectedConditions.presenceOfElementLocated(By.xpath("//table[@id='ctl00_ContentFiltros_BusquedaSolicitud1_grdSolicitudes_ctl00']"))
        ));

        // Caso 1: sin registros
        List<WebElement> sinRegistros = driver.findElements(By.xpath("//*[text()='No records to display.']"));
        if (!sinRegistros.isEmpty()) {
            return "No se encontraron resultados.";
        }

        // Caso 2: un único resultado
        List<WebElement> unicoResultado = driver.findElements(By.id("ctl00_lblFormulario"));
        //String text = unicoResultado.get(0).getAttribute("value");
        //Boolean result = unicoResultado.get(0).getAttribute("value").contains("Registro Seleccionado para Consultar o Modificar");
        if (!unicoResultado.isEmpty() && unicoResultado.get(0).getAttribute("value").contains("Registro Seleccionado para Consultar o Modificar")) {
            return "Se encontró 1 solo resultado.";
        }


        // Caso 3: múltiples resultados
        List<WebElement> filas = driver.findElements(
                By.xpath("//table[@id='ctl00_ContentFiltros_BusquedaSolicitud1_grdSolicitudes_ctl00']/tbody/tr[not(contains(@class,'rgNoRecords'))]")
        );
        if (!filas.isEmpty()) {
            System.out.println("Se encontraron múltiples resultados: " + filas.size());
            return "Se encontraron múltiples resultados";
        }

        return "No se pudo determinar el estado de la búsqueda.";
    }

    public void clickLabel(By locator) {
        cambiarFocoNuevaPestania();
        existe(locator);
        clickear(locator);
    }


    public void clickLastNameAndFirstNameLabel() {
        clickLabel(fullNameIdLabel);

    }

    public void clickCuitCuilLabel() {
        clickLabel(cuitCuilLabel);
    }

    public void clickDocumentTypeNumberLabel() {
        clickLabel(documentTypeNumberLabel);
    }

    public void clickTypeAndKeyNumber() {
        clickLabel(typeAndKeyNumberLabel);
    }

    public void clickAccountNumberAndType(String accountNumber) {
        clickLabel(accountNumberInput);
        escribir(accountNumberInput, accountNumber);
    }

    public void clickRangeOfRequestAndType(String from, String to) {
        switchWindowAndWaitForElement(rangeOfRequestFromInput);
        //clickear(rangeOfRequestFromInput);
        escribir(rangeOfRequestFromInput, from);
        //clickear(rangeOfRequestToInput);
        escribir(rangeOfRequestToInput, to);
    }

    public void clickRequestNumberAndType(String requestNumber) {
        switchWindowAndWaitForElement(requestNumberInput);
        escribir(requestNumberInput, requestNumber);
    }

    public void selectStatusDateAndType(String statusDate) {
        switchWindowAndWaitForElement(statusDateInput);
        escribir(statusDateInput, statusDate);
    }

    public void selectRegistrationSectionAndType(String registrationSection) {
        switchWindowAndWaitForElement(registrationSectionInput);
        escribir(registrationSectionInput, registrationSection);
    }

    public void selectDocumentTypeAndDocumentNumberAndType(String type, String number) {
        switchWindowAndWaitForElement(typeDocumentInput);
        clickear(typeDocumentInput);
        String documentTypeListItemXpath = "//div[@id='ctl00_ContentFiltros_BusquedaSolicitud1_cboBusqCodTipDoc_DropDown']//ul[@class='rcbList']/li[contains(text(), '%s')]";
        seleccionarOpcionConTexto(documentTypeListItemXpath, type);

        escribir(numberDocumentInput, number);
    }

    public void selectFullNameAndType(String fullName) {
        switchWindowAndWaitForElement(fullNameInputInIndividualRequestPage);
        escribir(fullNameInputInIndividualRequestPage, fullName);
    }


    public void selectStateLabel(String stateString) {
        clickLabel(stateLabel);
        String state = "//div[@id='ctl00_ContentFiltros_BusquedaSolicitud1_cboBusqEstado_DropDown']//ul[@class='rcbList']/li[contains(text(), '%s')]";
        seleccionarOpcionConTexto(state, stateString);
    }

    public void searchButtonInIndividualRequest() {
        clickear(searchButtonInIndividualRequest);
        System.out.println();
    }

    public void enterFullname(String fullname) {
        escribir(fullNameInput, fullname);
    }

    public void enterDocumentNumber(String documentNumber) {
        escribir(documentTypeNumberInput, documentNumber);
    }

    public void enterKeyNumber(String keyNumber) {
        escribir(keyNumberInput, keyNumber);
    }

    public void enterCuitCuil(String cuitCuil) {
        escribirTelerikRobusto(cuitCuilInput, cuitCuil, false);
    }

    public void clickSearchButton() {
        clickear(searchButton);
        System.out.println("Click en el botón Buscar");
    }

    public void isSearchResultsTableDisplayed() {
        clickear(searchButton);
        WebElement element = find(searchResultTable);
        if (element == null) {
            fail("La tabla no está visible en pantalla.");
        } else {
            System.out.println("La tabla está visible");
        }
    }

    public void isSearchResultsDisplayed() {
        clickear(searchButton);
        WebElement element = find(selectedRecordtext);
        if (element == null) {
            fail("No se encontraron resultados.");
        } else {
            System.out.println("Se ha encontrado resultados.");
        }
    }

    public void selectDocumentTypeNumber(String typeOfDocument) {
        clickear(documentTypeNumberSelect);
        String lstTipoNumeroDoc = ("//div[@id='ctl00_ContentFiltros_BusquedaCliente1_cboBusqTipoDoc_DropDown']//ul[@class='rcbList']//li[contains(text(),'%s')]");
        seleccionarOpcionConTexto(lstTipoNumeroDoc, typeOfDocument);
    }

    public void selectTypeAndKeyNumber(String typeAndKeyNumber) {
        clickear(keyNumberSelect);
        String lstTypeAndKeyNumber = ("//div[@id='ctl00_ContentFiltros_BusquedaCliente1_cboBusqTipoClave_DropDown']//ul[@class='rcbList']//li[contains(text(),'%s')]");
        //div[@id='ctl00_ContentFiltros_BusquedaCuenta1_cboBusqTipoClave_DropDown']//ul[@class='rcbList']//li[contains(text(),'4  - CUIC')]
        //ctl00_ContentFiltros_BusquedaCliente1_cboBusqTipoClave_DropDown
        seleccionarOpcionConTexto(lstTypeAndKeyNumber, typeAndKeyNumber);
    }
}
