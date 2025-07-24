package page.Consulta;

import base.SeleniumBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CBUAlias extends SeleniumBase {
	private final By fldBuscarCuenta = By.xpath("//input[@placeholder='Buscar por N° de cuenta o referencia']");
	private final By txtCuentaEncontrada = By.xpath("//div[@class='ng-tns-c235-114 ng-trigger ng-trigger-accordion ng-star-inserted']//p['Mostrando resultados de Nº de cuenta o referencia:']");



	public CBUAlias(WebDriver driver) {
		super(driver);
	}

	public void ingresarCuenta(String pCuenta) {
		clickear(fldBuscarCuenta);
		escribir(fldBuscarCuenta, pCuenta);
		clickear(fldBuscarCuenta);
	}


	public void mostrarTexto() {
		existe(txtCuentaEncontrada);
	}
}

