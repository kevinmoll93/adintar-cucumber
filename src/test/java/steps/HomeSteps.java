package steps;

import base.Config;
import io.cucumber.java.en.Given;
import org.openqa.selenium.WebDriver;
import page.Home;

public class HomeSteps {
	WebDriver driver = Hooks.getDriver();
	Home home = new Home(driver);

	@Given("el usuario accede a la consulta de Cuentas")
	public void elUsuarioAccedeALaConsultaDeCuentas() {
		driver.get(Config.urlPrincipal);
		home.clickCuentas();
		home.clickConsultas();
		home.clickConsultaCuentas();
	}

	@Given("el usuario accede a la consulta de Novedades")
	public void elUsuarioAccedeALaConsultaDeNovedades() {
		driver.get(Config.urlPrincipal);
		home.clickCuentas();
		home.clickCuentasListados();
		home.clickConsultaNovedades();
	}

	@Given("el usuario accede a la listado de stock TC sucursal")
	public void elUsuarioAccedeALaListadoDeStockTCSucursal() {
		driver.get(Config.urlPrincipal);
		home.clickStockTarjetasSucursal();
		home.clickStockListados();
		home.clickListadoStockSucursal();

	}

	@Given("el usuario accede al ABM Clientes")
	public void ABMClientes() {
		driver.get(Config.urlPrincipal);
		home.clickSolicitudes();
		home.clickABM();
		home.clickABMClientes();
	}

	@Given("el usuario accede al ABM Solicitudes Adicionales")
	public void ABMSolicitudesAdicionales() {
		driver.get(Config.urlPrincipal);
		home.clickSolicitudes();
		home.clickABM();
		home.clickABMSolicitudesAdicionales();
	}

	@Given("el usuario accede al ABM Solicitudes Individuales")
	public void ABMSolicitudesIndividuales() {
		driver.get(Config.urlPrincipal);
		home.clickSolicitudes();
		home.clickABM();
		home.clickABMSolicitudesIndividuales();
	}

	@Given("el usuario accede al Listado de Rechazos de Altas por servicios")
	public void ListadosRechazosAltaServicios() {
		driver.get(Config.urlPrincipal);
		home.clickSolicitudes();
		home.clickListados();
		home.clickListadoRechazosAltaServicio();
	}

	@Given("el usuario accede a la Entrega de Adicionales dadas de alta por Call Center")
	public void TareasEntregaAdicionales() {
		driver.get(Config.urlPrincipal);
		home.clickSolicitudes();
		home.clickTareas();
		home.clickEntregaAdicionales();
	}
}
