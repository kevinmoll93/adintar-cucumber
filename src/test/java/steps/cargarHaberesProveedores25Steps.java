package steps;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import page.pagos.HaberesProveedores25;

public class cargarHaberesProveedores25Steps {
	WebDriver driver = Hooks.getDriver();
	HaberesProveedores25 cargar = new HaberesProveedores25(driver);


	@When("seleccionamos el servicio {string}, si es Otro entonces seleccionamos {string} y damos click Aceptar")
	public void seleccionServicio(String servicio, String otros) {
		cargar.seleccionarServicios(servicio, otros);
		cargar.clickAceptar();
	}

	@And("seleccionamos la cuenta disponible {string}")
	public void seleccionCuentaDisponible(String cuenta) {
		cargar.seleccionarCuenta(cuenta);
	}

	@And("ingresamos los datos de la transferencia, convenio {string}, importe {string}, fecha de acreditacion {string}, concepto {string}")
	public void ingresamosDatosTransferencia(String convenio, String importe, String fecha, String concepto) {
		cargar.seleccionarConvenio(convenio);
		cargar.ingresarImporteTotal(importe);
		cargar.ingresarFechaAcreditacion(fecha);
		cargar.ingresarConcepto(concepto);
	}

	@And("buscamos el archivo adjunto {string} con los destinatarios")
	public void buscamosArchivoAdjunto(String adjunto) {
		cargar.seleccionarAdjunto(adjunto);
	}

	@Then("realiza con exito la carga de haberes, otros destinos o servicios")
	public void realizarExitoCargaServicio() {
		cargar.clickCargar();
	}

}
