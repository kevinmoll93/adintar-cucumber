package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import page.Consulta.CBUAlias;
import page.MenuPrincipal;


public class ConsultaCBUSteps {
	WebDriver driver = Hooks.getDriver();
	MenuPrincipal menu = new MenuPrincipal(driver);
	CBUAlias cbuAlias = new CBUAlias(driver);


	@Given("el usuario esta en el menu principal e ingresa {string} la seccion de consultas CBUAlias")
	public void ingresarConsultaCBU(String consulta) throws InterruptedException {
		menu.clickConsulta();
		menu.ingresarBusqueda(consulta);
	}

	@When("ingresa {string} en la busqueda del CBUAlias")
	public void ingresarCuenta(String cuenta) {
		cbuAlias.ingresarCuenta(cuenta);

	}

	@Then("deberia realizar la consulta con exito")
	public void MostrarConsulta() {
		cbuAlias.mostrarTexto();
	}


}