package steps;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import page.SolicitudesPage;

public class Solicitudes {
    WebDriver driver = Hooks.getDriver();
    SolicitudesPage solicitudes = new SolicitudesPage(driver);

    private Scenario scenario;

    @And("Se abre una pestaña nueva.")
    public void switchToNewTab() {
        solicitudes.switchToNewTab();
    }

    @Before
    public void setScenario(Scenario scenario) {
        this.scenario = scenario;
    }

    @When("El usuario selecciona el estado: {string}.")
    public void selectState(String state) {
        solicitudes.selectStateLabel(state);
    }

    @When("Selecciona el filtro por Apellido y Nombre.")
    public void selectFilterByLastNameAndFirstName() {
        solicitudes.clickLastNameAndFirstNameLabel();
    }

    @When("Selecciona el filtro por número de CUIT")
    public void selectCuitCuilFilter() {
        solicitudes.clickCuitCuilLabel();
    }

    @And("Ingresa nombre y apellido: {string}.")
    public void enterFullname(String fullName) {
        solicitudes.enterFullname(fullName);
    }

    @And("Ingresa el numero de CUIT o CUIL {string}.")
    public void enterCuitCuil(String cuitCuil) {
        solicitudes.enterCuitCuil(cuitCuil);
    }

    @And("Click en el botón \"Buscar\".")
    public void clickSearchButton() {
        solicitudes.clickSearchButton();
    }

    @And("Selecciona el formato {string}")
    public void selectFormatType(String formatType) {
        solicitudes.selectFormatType(formatType);
    }

    @And("Seleciona la administradora: {string}.")
    public void selectAdministrator(String administrator) {
        solicitudes.selectAdministrator(administrator);
    }


    @Then("Se muestra la tabla de resultado de la búsqueda.")
    public void isSearchResultsTableDisplayed() {
        solicitudes.isSearchResultsTableDisplayed();
    }

    @Then("Se obtiene el resultado de la busqueda.")
    public void isSearchResultsDisplayed() {
        solicitudes.isSearchResultsDisplayed();
    }

    @When("Selecciona el filtro por tipo {string} y numero de documento.")
    public void selectDocumentTypeNumber(String typeOfDocument) {
        solicitudes.clickDocumentTypeNumberLabel();
        solicitudes.selectDocumentTypeNumber(typeOfDocument);
    }

    @And("Ingresa el documento {string}.")
    public void enterDocumentNumber(String documentNumber) {
        solicitudes.enterDocumentNumber(documentNumber);
    }

    @When("Selecciona el filtro por tipo {string} y numero de clave.")
    public void selectTypeAndKeyNumber(String tipo) {
        solicitudes.clickTypeAndKeyNumber();
        solicitudes.selectTypeAndKeyNumber(tipo);
    }

    @When("El usuario selecciona el campo \"Nro de cuenta\" e ingresa: {string}.")
    public void selectAccountNumber(String clickAccountNumber) {
        solicitudes.clickAccountNumberAndType(clickAccountNumber);
    }

    @When("El usuario selecciona el campo \"Rango solicitudes\" e ingresa: desde {string} y hasta {string}.")
    public void selectRangeOfRequest(String from, String to) {
        solicitudes.clickRangeOfRequestAndType(from, to);
    }

    @When("El usuario selecciona el campo \"Nro de solicitud\" e ingresa: {string}.")
    public void selectRequestNumber(String requestNumber) {
        solicitudes.clickRequestNumberAndType(requestNumber);
    }

    @When("El usuario selecciona el campo \"Fecha estado\" e ingresa: {string}.")
    public void selectStatusDateAndType(String statusDate) {
        solicitudes.selectStatusDateAndType(statusDate);
    }

    @When("El usuario selecciona el campo \"Sector de alta\" e ingresa: {string}.")
    public void selectRegistrationSectionAndType(String registrationSection) {
        solicitudes.selectRegistrationSectionAndType(registrationSection);
    }

    @When("El usuario selecciona en el campo \"Tipo y nro de documento\" {string} e ingresa: {string}.")
    public void selectDocumentTypeAndDocumentNumberAndType(String type, String number) {
        solicitudes.selectDocumentTypeAndDocumentNumberAndType(type, number);
    }

    @When("El usuario selecciona en el campo \"Tipo y nro de documento titular\" {string} e ingresa: {string}.")
    public void selectDocumentHolderTypeAndDocumentNumberAndType(String type, String number) {
        solicitudes.selectDocumentHolderTypeAndDocumentNumberAndType(type, number);
    }

    @When("El usuario selecciona en el campo \"Tipo y nro de documento adicional\" {string} e ingresa: {string}.")
    public void selectDocumentAdditionalTypeAndDocumentNumberAndType(String type, String number) {
        solicitudes.selectDocumentAdditionalTypeAndDocumentNumberAndType(type, number);
    }

    @When("El usuario selecciona el campo \"Apellido y nombre\" e ingresa: {string}.")
    public void selectFullNameAndType(String fullName) {
        solicitudes.selectFullNameAndType(fullName);
    }

    @When("El usuario selecciona el campo \"Sucursal bancaria\" e ingresa: {string}.")
    public void selectBankBranch(String fullName) {
        solicitudes.selectBankBranch(fullName);
    }

    @When("Se ingresa el número de solicitud: {string}.")
    public void setRequestNumber(String requestNumber) {
        solicitudes.setRequestNumber(requestNumber);
    }

    @When("Click en el checkbox \"solicitud entregada\"")
    public void checkRequestDelivered() {
        solicitudes.checkRequestDelivered();
    }

    @When("Se ingresa la fecha de alta desde: {string}.")
    public void setRegistrationDateFrom(String date) {
        solicitudes.setRegistrationDateFrom(date);
    }


    @When("Se ingresa la fecha de alta hasta: {string}.")
    public void setRegistrationDateTo(String date) {
        solicitudes.setRegistrationDateTo(date);
    }


    @And("Ingresa la clave {string}.")
    public void enterKeyNumber(String keyNumber) {
        solicitudes.enterKeyNumber(keyNumber);
    }

    @And("Se hace click en el botón buscar.")
    public void clickOnButtonInIndividualRequest() {
        solicitudes.searchButtonInIndividualRequest();
    }

    @Then("Se obtiene el o los resultados de la busqueda.")
    public void resultSearchInIndividualRequest() {
        String searchState = solicitudes.searchState();

        scenario.log("Resultado de la búsqueda: " + searchState);
        System.out.println("Resultado de la búsqueda: " + searchState);
    }

    @Then("Se obtiene el o los resultados de la busqueda de la tabla de Clientes.")
    public void resultSearchClientTable() {
        String searchState = solicitudes.searchStateClients();

        scenario.log("Resultado de la búsqueda: " + searchState);
        System.out.println("Resultado de la búsqueda: " + searchState);
    }

    @Then("Genera el reporte de formato {string} con nombre {string}.")
    public void generaYGuardaElReportePDFDeManeraExitosa(String formatType, String fileName) {
        solicitudes.clickGenerateReport();
        if (formatType.equalsIgnoreCase("pdf")) {
            solicitudes.clickBotonAbrirDentroIframe();
            solicitudes.saveReportFile(formatType, fileName);
        } else if (formatType.equalsIgnoreCase("excel")) {
            solicitudes.saveReportFile(formatType, fileName);
        }
    }
}