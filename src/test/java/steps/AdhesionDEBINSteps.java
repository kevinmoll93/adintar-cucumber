package steps;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import page.pagos.DEBIN;
import page.MenuPrincipal;

public class AdhesionDEBINSteps {
	WebDriver driver = Hooks.getDriver();
	MenuPrincipal menu = new MenuPrincipal(driver);
	DEBIN debin = new DEBIN(driver);


	@Given("el usuario esta en el menu principal e ingresa {string} la seccion de pagos")
	public void seleccionarDEBIN(String debin) {
		menu.clickPagos();
		menu.ingresarBusquedaPrecisa(debin);
	}


	@When("selecciona la cuenta {string}, ingresa el nombre fantasia {string} y selecciona su rubro {string}")
	public void ingresarNombreSeleccionarRubro(String cuenta, String nombre, String rubro) {
		debin.esperarActualizacionTabla();
		debin.seleccionarCuenta(cuenta);
		debin.ingresarNombreFantasia(cuenta, nombre);
		debin.seleccionarRubro(cuenta, rubro);
	}

	@When("selecciona el CBU {string}, ingresa el nombre fantasia {string} y selecciona su rubro {string}")
	public void seleccionarMedianteCBU(String cbu, String nombre, String rubro) {
		debin.esperarActualizacionTabla();
		debin.seleccionarCBU(cbu);
		debin.ingresarNombreFantasia(cbu, nombre);
		debin.seleccionarRubro(cbu, rubro);
	}

	@Then("realiza con exito la adhesion de la cuenta confirmando con la contrasenia {string}")
	public void adhesionConExito(String contrasenia) {
		debin.confirmarAdherir(contrasenia);
	}

}
