package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import page.pagos.AFIPVEP;
import page.MenuPrincipal;

public class CargaVEPSteps {
	WebDriver driver = Hooks.getDriver();
	MenuPrincipal menu = new MenuPrincipal(driver);
	AFIPVEP afipvep = new AFIPVEP(driver);

	@Given("en el menu principal buscamos {string} en la seccion de pagos")
	public void seleccionarAfipVep(String afipvep) {
		menu.clickPagos();
		menu.ingresarBusqueda(afipvep);
	}

	@When("seleccionamos el tipo de VEP {string} y le damos al boton Buscar")
	public void seleccionarTipoVEP(String tipo) {
		afipvep.clickTipoVep();
		afipvep.seleccionarTipoVEP(tipo);
		afipvep.clickBuscar();
	}

	@Then("realizamos con exito la carga de VEP")
	public void realizamosConExitoLaCargaDeVEP() {
		afipvep.comprobarNoResultado();
	}

	@When("seleccionamos el tipo de VEP {string}")
	public void seleccionamosElTipoDeVEP(String terceros) {
		afipvep.clickTipoVep();
		afipvep.seleccionarTipoVEP(terceros);
	}

	@And("ingresamos el CUIT {string} del tecero y le damos al boton Buscar")
	public void ingresarCUIT(String cuit) {
		afipvep.ingresarCUIT(cuit);
		afipvep.clickBuscar();
	}

	@And("ingresamos el CUIT {string} del tecero y numero de VEP {string} a cargar y Buscamos")
	public void ingresamosDatosCuitVEP(String cuit, String vep) {
		afipvep.ingresarCUIT(cuit);
		afipvep.ingresarNumVEP(vep);
		afipvep.clickBuscar();
	}

	@And("cargamos el numero de VEP {string} especificado y el numero de cuenta {string}")
	public void cargamosElNumeroDeVEPEspecificado(String numero, String cuenta) {
		afipvep.clickCargar(numero, cuenta);
		afipvep.comprobarNoResultado();
	}
}
