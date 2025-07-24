package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import page.pagos.HaberesProveedores;
import page.MenuPrincipal;

public class cargarHaberesProveedoreSteps {
	WebDriver driver = Hooks.getDriver();
	MenuPrincipal menu = new MenuPrincipal(driver);
	HaberesProveedores cargar = new HaberesProveedores(driver);

	@Given("en el menu principal ingresar {string} en la seccion de pagos")
	public void seleccionarHaberesProveedores(String haberes) {
		menu.clickPagos();
		menu.ingresarBusquedaPrecisa(haberes);
	}

	@When("seleccionamos el servicio {string}, el convenio {string} y confirmamos con el boton continuar")
	public void seleccionamosServicio(String servicio, String convenio) {
		cargar.clickServicio();
		cargar.seleccionarServicio(servicio);
		cargar.clickConvenio();
		cargar.selecionarConvenio(convenio);
		cargar.clickContinuar();
	}

	@And("seleccionamos la cuenta disponible {string} y confirmamos con el boton continuar")
	public void seleccionamosCuentaDebito(String cuenta) {
		cargar.clickCuentaDisponible();
		cargar.seleccionarCuentaDisponible(cuenta);
		cargar.clickContinuar();
	}

	@And("ingresamos los datos del pago, importe total {string}, fecha de acreditacion {string}, concepto {string}")
	public void ingresamosDatosDelPago(String importe, String fecha, String concepto) {
		cargar.ingresarImporteTotal(importe);
		cargar.seleccionarFechaAcreditacion(fecha);
		cargar.ingresarConcepto(concepto);
	}

	@And("adjuntamos el archivo {string} con los destinatarios y confirmamos con el boton confirmar")
	public void seleccionarArchivoAdjunto(String adjunto) {
		cargar.seleccionarArchivoAdjunto(adjunto);

	}

	@Then("realiza con exito la carga de haberes y servicios ingresando {string}")
	public void realizaConExitoLaCargaDeHaberesYServicios(String contrasenia) {
		cargar.clickConfirmar();
		cargar.clickFinalizarCarga(contrasenia);
	}
}
