package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import page.SolicitudesPage;

public class Solicitudes {
    WebDriver driver = Hooks.getDriver();
    SolicitudesPage solicitudes = new SolicitudesPage(driver);

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

    @And("Ingresa la clave {string}.")
    public void enterKeyNumber(String keyNumber) {
        solicitudes.enterKeyNumber(keyNumber);
    }
}
