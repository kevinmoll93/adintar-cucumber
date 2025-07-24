package base;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.time.Duration;

public class TestBase {
	protected WebDriver driver;

	@Before
	public void setupBrowser() {
		driver = Config.getDriver("chrome");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Config.tiempoEsperaImplicita));
		driver.manage().window().maximize();
	}

	@After
	public void cerrarNavegador() throws IOException, InterruptedException {
		Thread.sleep(1000);
		if (driver != null) {
			driver.quit();
		}
	}
}
