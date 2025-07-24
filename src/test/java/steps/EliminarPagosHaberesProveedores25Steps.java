package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import page.pagos.HaberesProveedores25;

public class EliminarPagosHaberesProveedores25Steps {
	WebDriver driver = Hooks.getDriver();
	HaberesProveedores25 eliminar = new HaberesProveedores25(driver);

	@When("seleccionamos el servicio {string}, si es Otro entonces seleccionamos {string} y aceptamos")
	public void seleccionarServicio(String servicio, String otros) {
		eliminar.clickEliminarPagos();
		eliminar.seleccionarServicios(servicio, otros);
		eliminar.clickAceptar();
	}

	@And("seleccionamos el numero de transaccion {string}")
	public void seleccionarNumeroDeTransaccion(String numero) {
		eliminar.seleccionarTransaccion(numero);
	}

	@Then("realiza con exito la eliminacion de pago haberes, otros destinos o servicios ingresando la contrasenia {string}")
	public void realizarConExitoEliminacionPorTransaccion(String contrasenia) {
		eliminar.clickConfirmarEliminarPago(contrasenia);
	}

	@And("seleccionamos la cuenta debito {string} que contengan todas transacciones a eliminar")
	public void seleccionamosLaCuentaDebitoQueContenganTodasTransaccionesAEliminar(String cuenta) {
		eliminar.seleccionarCuentasDebitoIguales(cuenta);
	}

}
