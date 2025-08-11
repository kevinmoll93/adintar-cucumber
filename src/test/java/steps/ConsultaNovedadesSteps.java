package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import page.ConsultaNovedades;

public class ConsultaNovedadesSteps {
	WebDriver driver = Hooks.getDriver();
	ConsultaNovedades consulta = new ConsultaNovedades(driver);

	@When("selecciona el formato salida PDF")
	public void seleccionaElFormatoSalidaPDFYElFiltroTipoDeReporteDetalle() {
		consulta.clickFormatoSalidaPDF();
	}

	@And("selecciona filtro Administradora {string}, Novedad tipo {string} y Fecha de alta desde {string}")
	public void seleccionaFiltroAdministradoraNovedadTipoYFechaDeAltaDesde(String admin, String tipo, String fecha) {
		consulta.seleccionarAdministradora(admin);
		consulta.seleccionarNovedadTipo(tipo);
		consulta.ingresarFechaAltaDesde(fecha);
	}

	@And("ingresa Cuenta {string}, Origen {string}, Estado {string}, y Fecha Alta hasta {string}")
	public void ingresaCuentaOrigenEstadoYFechaAltaHasta(String cuenta, String origen, String estado, String fecha) {
		consulta.ingresarCuenta(cuenta);
		consulta.seleccionarOrigen(origen);
		consulta.seleccionarEstado(estado);
		consulta.ingresarFechaAltaHasta(fecha);
	}

	@Then("Genera y guarda el reporte PDF {string} de manera exitosa")
	public void generaYGuardaElReportePDFDeManeraExitosa(String archivo) {
		consulta.clickGenerarReporte();
		consulta.clickBotonAbrirDentroIframe();
		consulta.guardarPDF(archivo);
	}

	@When("selecciona el formato salida Excel")
	public void seleccionaElFormatoSalidaExcelYElFiltroTipoDeReporteDetalle() {
		consulta.clickFormatoSalidaExcel();
	}

	@And("selecciona el filtro tipo de reporte Detalle")
	public void seleccionaElFiltroTipoDeReporteDetalle() {
		consulta.clickTipoReporteDetalles();
	}

	@Then("Genera y guarda el reporte Excel {string} de manera exitosa")
	public void generaYGuardaElReporteExcelDeManeraExitosa(String excel) {
		consulta.clickGenerarReporte();
		consulta.guardarExcel(excel);
	}
}
