package steps;

import base.Config;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import page.ConsultaCuenta;

public class ConsultaCuentaSteps {
	WebDriver driver = Hooks.getDriver();
	ConsultaCuenta consulta = new ConsultaCuenta(driver);

	@When("selecciona el filtro por Apellido y Nombre")
	public void seleccionaElFiltroPorApellidoYNombre() {
		consulta.clickApellidoNombre();
	}

	@And("ingresa el Apellido y Nombre {string}")
	public void ingresaElApellidoYNombre(String ApellidoNombre) {
		consulta.ingresarApellidoNombre(ApellidoNombre);
	}

	@Then("realiza la busqueda de la consulta con exito")
	public void realizaLaBusquedaDeLaConsultaConExito() {
		consulta.clickBuscar();
	}
}
