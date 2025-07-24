package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import page.pagos.DEBIN;
import page.MenuPrincipal;

public class OrdenDEBINSteps {
	WebDriver driver = Hooks.getDriver();
	MenuPrincipal menu = new MenuPrincipal(driver);
	DEBIN debin = new DEBIN(driver);

	@Given("en el menu principal ingresar {string} la seccion de pagos y seleccionar Orden")
	public void seleccionarOrdenDebin(String orden) {
		menu.clickPagos();
		menu.ingresarBusquedaPrecisa(orden);
		debin.seleccionarOrdenDEBIN();
	}


	@When("selecciona la cuenta credito {string}, agregar destinatario, ingresa los datos CBU {string}, su referencia {string} y su tipo {string}")
	public void ingresarDatosDestinatario(String credito, String CBU, String referencia, String tipoCuenta) {
		debin.clickCuentaCredito();
		debin.seleccionarCuentaCredito(credito);
		debin.seleccionarDebitoAutomatico();
		debin.ingresarDatosDestinatario(CBU, referencia, tipoCuenta);
	}

	@And("agregamos la cuenta, si la cuenta ya existe {string} entonces buscamos su CBU {string}, agregamos importe {string} y comprobante {string}")
	public void agregamosCuenta(String existe, String CBU, String importe, String comprobante) {
		debin.clickAgregarDestinatario(existe, CBU, importe, comprobante);
	}

	@And("seleccionamos datos debin para ingresar una descripcion {string} y concepto {string}")
	public void ingresarDatosDebin(String descripcion, String concepto) {
		debin.seleccionarDatosDebin();
		debin.ingresarDatosDebin(descripcion, concepto);
	}

	@Then("realiza con exito la orden DEBIN con los datos ingresados")
	public void realizaConExitoLaOrdenDEBINConLosDatosIngresados() {
		//debin.clickGenerarDEBIN();
	}

}
