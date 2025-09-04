package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import page.ListadoStockSucursal;

public class ListadoStockSucursalSteps {
	WebDriver driver = Hooks.getDriver();
	ListadoStockSucursal listadostock = new ListadoStockSucursal(driver);

	@And("selecciona el Tipo Listado {string}, Sucursal {string} y Administradora {string}")
	public void seleccionaElTipoListadoSucursalYAdministradora(String tipo, String sucursal, String administradora) {
		listadostock.seleccionarTipolistado(tipo);
		listadostock.seleccionarSucursal(sucursal);
		listadostock.seleccionarAdministradora(administradora);
	}

	@And("selecciona Estado {string}")
	public void seleccionaEstado(String estado) {
		listadostock.seleccionarEstado(estado);
	}

	@And("ingresa las Fechas Estado desde {string} y hasta {string}")
	public void ingresaLasFechasEstadoDesdeYHasta(String desde, String hasta) {
		listadostock.ingresarFechaEstado(desde, hasta);
	}

	@And("ingresa las Fechas Recepcion desde {string} y hasta {string}")
	public void ingresaLasFechasRecepcionDesdeYHasta(String desde, String hasta) {
		listadostock.ingresarFechaRecepcion(desde, hasta);
	}

	@And("selecciona el Origen {string}")
	public void seleccionaElOrigen(String origen) {
		listadostock.seleccionarOrigen(origen);
	}

	@And("selecciona el archivo origen {string}")
	public void seleccionaElArchivoOrigen(String archivo) {
		listadostock.seleccionarArchivoOrigen(archivo);
	}

	@And("selecciona la sucursal origen {string}")
	public void seleccionaLaSucursalOrigen(String sucursal) {
		listadostock.seleccionarSucursalOrigen(sucursal);
	}
}
