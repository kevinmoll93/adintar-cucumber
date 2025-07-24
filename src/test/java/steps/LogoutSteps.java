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
		logout.verificarSalir();
	}


	@When("realiza el cierre de sesion")
	public void clickSalir() {
		logout.clickSalir();
	}

	@Then("deberia ver el login principal")
	public void verificarTextoLogin() {
		logout.verificarBotonLogin();
	}
}
