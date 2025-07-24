package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import page.pagos.ImpuestoServicios;
import page.MenuPrincipal;

public class EliminarAdhesionImpuestoServicio {
	WebDriver driver = Hooks.getDriver();
	MenuPrincipal menu = new MenuPrincipal(driver);
	ImpuestoServicios adhesion = new ImpuestoServicios(driver);

	@Given("en el menu principal buscamos {string} la seccion de pagos adheridos")
	public void ingresarImpuestoServicioAdheridos(String pago) {
		menu.clickPagos();
		menu.ingresarBusqueda(pago);
		adhesion.clickAdheridos();
	}

	@When("buscamos el codigo de pago {string} y elimina la adhesion")
	public void buscamosElCodigoDePagoYEliminaLaAdhesion(String codigo) {
		adhesion.clickEliminarAdhesion(codigo);
	}

	@Then("realizamos con exito la quita de adhesion")
	public void realizamosConExitoLaQuitaDeAdhesion() {
		adhesion.clickConfirmarEliminarAdhesion();
	}
}
