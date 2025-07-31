package page;

import base.SeleniumBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Home extends SeleniumBase {
	private final By btnMenu = By.xpath("//a[@id='bc-mastermenubutton']");
	private final By btnAplicativos = By.xpath("//li//a[contains(text(),'Aplicativos')]");
	private final By btnOtrosAplicativos = By.xpath("//li//span[contains(text(),'Otros aplicativos')]");
	private final By lnkTesting = By.xpath("//span//a[contains(text(),'Testing')]");
	private final By fldBuscador = By.xpath("//input[@id='inplaceSearchDiv_WPQ3_lsinput']");
	private final By txtAdintar = By.xpath("//a[contains(text(),'Testing - Adintar Web 2008')]");
	private final By lnkAdintar = By.xpath("//a[contains(text(),'Testing Adintar Web 2008')]");

	/**
	 * Constructor que inicializa el driver de Selenium.
	 *
	 * @param driver instancia de WebDriver
	 */
	public Home(WebDriver driver) {
		super(driver);
	}

	public void clickMenuAplicativos() {
		clickear(btnMenu);
		clickear(btnAplicativos);
		//clickear(btnOtrosAplicativos);
	}

	public void clickTesting() {
		clickear(lnkTesting);
	}

	public void ingresarBusqueda(String pBuscar) {
		//no borrar por las dudas
		escribir(fldBuscador, pBuscar);
	}

	public void clickAdintar() {
		clickear(txtAdintar);
		clickear(lnkAdintar);
	}
}