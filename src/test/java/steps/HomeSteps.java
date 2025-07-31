package steps;

import base.Config;
import io.cucumber.java.en.Given;
import org.openqa.selenium.WebDriver;
import page.Home;

public class HomeSteps {
	WebDriver driver = Hooks.getDriver();
	Home home = new Home(driver);

	@Given("el usuario accede al aplicativo Adintar")
	public void elUsuarioAccedeAlAplicativoAdintar() {
		driver.get(Config.urlPrincipal);
		home.clickMenuAplicativos();
		home.clickTesting();
		home.clickAdintar();
	}
}
