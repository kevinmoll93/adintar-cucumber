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
    private final By holderTypeDocumentInput = By.xpath("//input[@id='ctl00_ContentFiltros_BusquedaSolicitud1_cboBusqCodTipDoc_Tit_Input']");
    private final By additionalTypeDocumentInput = By.xpath("//input[@id='ctl00_ContentFiltros_BusquedaSolicitud1_cboBusqCodTipDoc_Adic_Input']");
    private final By numberDocumentInput = By.xpath("//input[@id='ctl00_ContentFiltros_BusquedaSolicitud1_txtNrodDoc_text']");
    private final By holderNumberDocumentInput = By.xpath("//input[@id='ctl00_ContentFiltros_BusquedaSolicitud1_txtNrodDoc_TIT_text']");
    private final By additionalNumberDocumentInput = By.xpath("//input[@id='ctl00_ContentFiltros_BusquedaSolicitud1_txtNrodDoc_ADIC_text']");
    private final By fullNameInputInIndividualRequestPage = By.xpath("//input[@id='ctl00_ContentFiltros_BusquedaSolicitud1_txtBusqApellidoNombre_text']");
    private final By bankBranchInput = By.xpath("//input[@id='ctl00_ContentFiltros_BusquedaSolicitud1_cboBusqSucBco_Input']");
    private final By generateReportBtn = By.xpath("//input[@id='ctl00_cmdGenerar']");
    private final By formatTypePdfBtn = By.xpath("//input[@id='ctl00_optPDF']");
    private final By formatTypeExcelBtn = By.xpath("//input[@id='ctl00_optExcel']");


    //common elements
    private final By searchButton = By.xpath("//input[@value='Buscar']");

    //solicitudes-tareas-Entrega de Adicionales dadas de alta por Call Center
    private final By requestNumberInputPath = By.xpath("//input[@id='ctl00_ContentFiltros_txtNroSol_text']");
    private final By noRecordsMessage = By.xpath("//table[@id='ctl00_ContentFormulario_grdSolicitudesNue_ctl00']//div[contains(text(), 'No se encontraron registros para mostrar')]");
    private final By rowsInTable = By.xpath("//table[@id='ctl00_ContentFormulario_grdSolicitudesNue_ctl00']//tr[contains(@class, 'rgRow')]");
    private final By requestDeliveredCheckbox = By.xpath("//a[@id='ctl00_ContentFiltros_chkSolEntregada']");
    private final By registrationDateFromInput = By.xpath("//input[@id='ctl00_ContentFiltros_dtFechaAltaD_dateInput_text']");
    private final By registrationDateToInput = By.xpath("//input[@id='ctl00_ContentFiltros_dtFechaAltaH_dateInput_text']");
    private final By administratorInput = By.xpath("//input[@id='ctl00_ContentFiltros_cboAdministradora_Input']");
    String administrationPath = "//ul[@class='rcbList']/li[contains(text(),'%s')]";

    public SolicitudesPage(WebDriver driver) {
        super(driver);
    }


    public String searchState() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // Esperamos a que aparezca al menos uno de los posibles elementos
        wait.until(ExpectedConditions.or(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()='No records to display.']")),
                ExpectedConditions.presenceOfElementLocated(By.id("ctl00_lblFormulario")),
                ExpectedConditions.presenceOfElementLocated(By.xpath("//table[@id='ctl00_ContentFiltros_BusquedaSolicitud1_grdSolicitudes_ctl00']"))
        ));

        // Caso 1: sin registros
        List<WebElement> sinRegistros = driver.findElements(By.xpath("//*[text()='No records to display.']"));
        List<WebElement> rows = driver.findElements(rowsInTable);
        if (!sinRegistros.isEmpty() || !driver.findElements(noRecordsMessage).isEmpty()) {
            return "No se encontraron resultados.";
        }

        // Caso 2: un único resultado
        List<WebElement> unicoResultado = driver.findElements(By.id("ctl00_lblFormulario"));
        //String text = unicoResultado.get(0).getAttribute("value");
        //Boolean result = unicoResultado.get(0).getAttribute("value").contains("Registro Seleccionado para Consultar o Modificar");
        if (!unicoResultado.isEmpty() && (unicoResultado.get(0).getAttribute("value").contains("Registro Seleccionado para Consultar o Modificar") || rows.size() == 1)) {
            return "Se encontró 1 solo resultado.";
        }


        // Caso 3: múltiples resultados
        List<WebElement> filas = driver.findElements(
                By.xpath("//table[@id='ctl00_ContentFiltros_BusquedaSolicitud1_grdSolicitudes_ctl00']/tbody/tr[not(contains(@class,'rgNoRecords'))]")
        );
        if (!filas.isEmpty() || rows.size() > 1) {
            System.out.println("Se encontraron múltiples resultados: " + filas.size());
            return "Se encontraron múltiples resultados";
        }

        return "No se pudo determinar el estado de la búsqueda.";
    }

    public String searchStateClients() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // Esperamos a que aparezca al menos uno de los posibles elementos
        wait.until(ExpectedConditions.or(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()='No records to display.']")),      // sin resultados
                ExpectedConditions.presenceOfElementLocated(By.id("ctl00_lblFormulario")),          //1 resultado
                ExpectedConditions.presenceOfElementLocated(By.xpath("//table[@id='ctl00_ContentFiltros_BusquedaCliente1_grdClientes_ctl00']"))     //más de 1 resultado
        ));

        // Caso 1: sin registros
        List<WebElement> sinRegistros = driver.findElements(By.xpath("//*[text()='No records to display.']"));
        if (!sinRegistros.isEmpty()) {
            return "No se encontraron resultados.";
        }

        // Caso 2: un único resultado
        List<WebElement> unicoResultado = driver.findElements(By.id("ctl00_lblFormulario"));
        if (!unicoResultado.isEmpty() && unicoResultado.get(0).getAttribute("value").contains("Registro Seleccionado para Consultar o Modificar")) {
            return "Se encontró 1 solo resultado.";
        }


        // Caso 3: múltiples resultados
        List<WebElement> filas = driver.findElements(
                By.xpath("//table[@id='ctl00_ContentFiltros_BusquedaCliente1_grdClientes_ctl00']/tbody/tr[not(contains(@class,'rgNoRecords'))]")
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

    public void switchToNewTab() {
        cambiarFocoNuevaPestania();
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

    public void selectDocumentHolderTypeAndDocumentNumberAndType(String type, String number) {
        switchWindowAndWaitForElement(holderTypeDocumentInput);
        clickear(holderTypeDocumentInput);
        String documentTypeListItemXpath = "//div[@id='ctl00_ContentFiltros_BusquedaSolicitud1_cboBusqCodTipDoc_Tit_DropDown']//ul[@class='rcbList']/li[contains(text(), '%s')]";
        seleccionarOpcionConTexto(documentTypeListItemXpath, type);

        escribir(holderNumberDocumentInput, number);
    }

    public void selectDocumentAdditionalTypeAndDocumentNumberAndType(String type, String number) {
        switchWindowAndWaitForElement(additionalTypeDocumentInput);
        clickear(additionalTypeDocumentInput);
        String documentTypeListItemXpath = "//div[@id='ctl00_ContentFiltros_BusquedaSolicitud1_cboBusqCodTipDoc_Adic_DropDown']//ul[@class='rcbList']/li[contains(text(), '%s')]";
        seleccionarOpcionConTexto(documentTypeListItemXpath, type);

        escribir(additionalNumberDocumentInput, number);
    }

    public void selectFullNameAndType(String fullName) {
        switchWindowAndWaitForElement(fullNameInputInIndividualRequestPage);
        escribir(fullNameInputInIndividualRequestPage, fullName);
    }

    public void selectBankBranch(String fullName) {
        switchWindowAndWaitForElement(bankBranchInput);
        escribir(bankBranchInput, fullName);
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

    public void clickGenerateReport() {
        clickear(generateReportBtn);
    }

    public void clickBotonAbrirDentroIframe() {
        try {
            // Cambiar al iframe
            driver.switchTo().frame("ctl00_framePDF");

            // Ahora sí buscar y hacer click
            WebElement botonAbrir = driver.findElement(By.id("open-button"));
            botonAbrir.click();

            System.out.println("Botón 'Abrir' clickeado correctamente dentro del iframe.");

            // Volver al contenido principal
            driver.switchTo().defaultContent();

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error al hacer click en 'Abrir': " + e.getMessage());
        }
    }

    public void saveReportFile(String fileType, String fileName) {
        if (fileType.equalsIgnoreCase("pdf")) {
            descargarArchivoNoSeguro(720, "C:\\Temp", ".pdf");
            renombrarArchivo(fileName, "C:\\Temp", ".pdf", 50);
        } else if (fileType.equalsIgnoreCase("excel")) {
            descargarArchivoNoSeguro(720, "C:\\Temp", ".xls");
            renombrarArchivo(fileName, "C:\\Temp", ".xls", 50);
        }
    }

    public void selectFormatType(String formatType) {
        if (formatType.equalsIgnoreCase("pdf")) {
            clickear(formatTypePdfBtn);
        } else if (formatType.equalsIgnoreCase("excel")) {
            clickear(formatTypeExcelBtn);
        } else {
            throw new AssertionError("Tipo de formato de archivo inválido. Sólo se permite 'pdf' o 'excel'.");
        }
    }


    public void selectAdministrator(String administrator) {
        clickear(administratorInput);
        if (
                administrator.equalsIgnoreCase("01-MASTERCARD")
                        || administrator.equalsIgnoreCase("02-CABAL")
                        || administrator.equalsIgnoreCase("03-VISA")
        ) {
            seleccionarOpcionConTexto(administrationPath, administrator);
        } else {
            throw new AssertionError("Administrador inválido.");
        }
    }


    public void setRequestNumber(String requestNumber) {
        escribir(requestNumberInputPath, requestNumber);
    }

    public void checkRequestDelivered() {
        clickear(requestDeliveredCheckbox);
    }

    public void setRegistrationDateFrom(String date) {
        escribir(registrationDateFromInput, date);
    }

    public void setRegistrationDateTo(String date) {
        escribir(registrationDateToInput, date);
    }


}
