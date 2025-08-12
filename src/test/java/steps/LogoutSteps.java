package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import page.LogOut;

public class LogoutSteps {
	WebDriver driver = Hooks.getDriver();
	LogOut logout = new LogOut(driver);

	@Given("el usuario espera unos momentos para cerrar sesion")
	public void verificarBoton() {
		logout.verificarBotonSalir();
	}


	@When("realiza el cierre de sesion")
	public void clickBotonSalir() {
		logout.clickSalir(10);
	}

	@Then("deberia ver el texto de cierre exitoso")
	public void verificarTextoLogin() {
		//logout.verificarCierre();
	}
}
