package page;

import base.SeleniumBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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

    public SolicitudesPage(WebDriver driver) {
        super(driver);
    }

    public void clickLabel(By locator){
        cambiarFocoNuevaPestania();
        existe(locator);
        clickear(locator);
    }

    public void clickLastNameAndFirstNameLabel() {
        clickLabel(fullNameIdLabel);
    }

    public void clickCuitCuilLabel(){
        clickLabel(cuitCuilLabel);
    }

    public void clickDocumentTypeNumberLabel() {
        clickLabel(documentTypeNumberLabel);
    }

    public void clickTypeAndKeyNumber(){
        clickLabel(typeAndKeyNumberLabel);
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
        }else{
            System.out.println("La tabla está visible");
        }
    }

    public void isSearchResultsDisplayed(){
        clickear(searchButton);
        WebElement element = find(selectedRecordtext);
        if (element == null) {
            fail("No se encontraron resultados.");
        }else{
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
