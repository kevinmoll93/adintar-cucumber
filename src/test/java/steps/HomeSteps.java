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
}
