package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import page.pagos.AFIPVEP;
import page.MenuPrincipal;

public class DDJJPagoSteps {
	WebDriver driver = Hooks.getDriver();
	MenuPrincipal menu = new MenuPrincipal(driver);
	AFIPVEP afipvep = new AFIPVEP(driver);

	@Given("en el menu principal buscamos {string} y luego Servicios ARCA")
	public void seleccionamoServiciosARCA(String arca) {
		menu.clickPagos();
		menu.ingresarBusqueda(arca);
		afipvep.clickServiciosARCA();
	}

	@When("seleccionamos DDJJ y Pagos")
	public void seleccionamosDDJJYPagos() {
		afipvep.clickDDJJPagos();
	}

	@Then("realizamos con exito DDJJ y pagos")
	public void realizamosConExitoDDJJYPagos() {
		afipvep.comprobarErrorDDJJPagos();
	}
}
