package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import page.pagos.ImpuestoServicios;
import page.MenuPrincipal;

public class PagarImpuestoServicioSteps {
	WebDriver driver = Hooks.getDriver();
	MenuPrincipal menu = new MenuPrincipal(driver);
	ImpuestoServicios pagar = new ImpuestoServicios(driver);

	@Given("en el menu principal buscamos {string} la seccion de pagos")
	public void seleccionarImpuestoServicios(String pago) {
		menu.clickPagos();
		menu.ingresarBusqueda(pago);
		pagar.clickNuevoPago();
	}

	@When("ingresa el nombre {string} ,luego ingresamos su codigo {string} y por ultimo una descripcion {string}")
	public void ingresarDatosPagar(String nombre, String codigo, String descripcion) {
		pagar.ingresarBusqueda(nombre);
		pagar.ingresarCodigoPago(codigo);
		//pagar.ingresarDescripcion(descripcion);
		pagar.clickContinuar();
	}

	@Then("realizamos con exito el pago")
	public void realizamosConExitoElPago() {

		pagar.clickConfirmar();
	}

	@Then("deberíamos ver el mensaje de error {string}")
	public void deberiamosVerElMensajeDeError(String mensajeEsperado) {
		String mensajeActual = pagar.obtenerMensajeError();
		assert mensajeActual.contains(mensajeEsperado) :
				"Mensaje esperado: '" + mensajeEsperado + "', pero fue: '" + mensajeActual + "'";
	}

	@And("seleccionamos concepto {string}, ingresamos importe {string}, ingresamos operacion {string}")
	public void seleccionamosDatosDelPago(String concepto, String importe, String operacion) {
		pagar.seleccionarConcepto(concepto);
		pagar.ingresarImporte(importe);
		pagar.ingresarOperacion(operacion);
	}

	@Then("realizamos con exito el pago y la adhesion {string}")
	public void realizamosConExitoElPagoYLaAdhesion(String adherir) {
		pagar.clickConfirmarAdhesion(adherir);
	}
}
