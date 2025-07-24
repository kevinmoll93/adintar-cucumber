package steps;

import base.Config;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import page.Login;
import page.MenuPrincipal;

public class LoginSteps {
	WebDriver driver = Hooks.getDriver();
	Login loginPage = new Login(driver);
	MenuPrincipal menu = new MenuPrincipal(driver);

	@Given("el usuario está en la página de login")
	public void usuarioEnPaginaLogin() {
		driver.get(Config.urlPrincipal);

	}

	@When("ingresa {string} y {string}")
	public void ingresaCredenciales(String usuario, String password) {
		loginPage.ingresarUsuario(usuario);
		loginPage.ingresarContrasenia(password);
		loginPage.clickIngresar();

	}

	@Then("debería ver la pantalla principal")
	public void verificarIngreso() {
		System.out.println("Dentro de DashboardPage");
		// menu.verificarPantalla();
	}
}
