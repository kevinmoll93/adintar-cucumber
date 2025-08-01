package page;

import base.SeleniumBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LogOut extends SeleniumBase {
	private final By btnSalir = By.xpath("//input[@value='Salir']");
	private final By txtCierreSesion = By.xpath("//span[contains(text(),'Adintar Web finalizó correctamente con la sesion, ya puede cerrar la pestaña.')]");

	public LogOut(WebDriver driver) {
		super(driver);
	}

	public void clickSalir() {
		boolean existeBoton = intentarClick(btnSalir);
		if (!existeBoton) {
			System.out.println("El boton no se encuentra disponible");
		} else {
			cambiarFocoNuevaPestania();
			clickear(btnSalir);
		}
	}
	
	public void verificarBotonSalir() {
		existe(btnSalir);
	}

	public void verificarCierre() {
		//cambiarFocoNuevaPestania();
		existe(txtCierreSesion);
	}
}
