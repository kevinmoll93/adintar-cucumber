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

	private final By lnkCuentas = By.xpath("//a[@id='MenuIzquierdot0']");
	private final By lnkConsultas = By.xpath("//a[@id='MenuIzquierdot1']");
	private final By lnkConsultaCuentas = By.xpath("//a[@id='ListaProgt0']");
	private final By lnkCuentasListados = By.xpath("//a[@id='MenuIzquierdot2']");
	private final By lnkConsultaNovedades = By.xpath("//a[@id='ListaProgt1']");

	private final By lnkStockTarjetaSucursal = By.xpath("//a[@id='MenuIzquierdot17']");
	private final By lnkStockListados = By.xpath("//a[@id='MenuIzquierdot18']");
	private final By lnkListadoStockTCSucursal = By.xpath("//a[@id='ListaProgt0']");

	private final By lnkSolicitudes = By.xpath("//a[@id='MenuIzquierdot13']");
	private final By lnkABM = By.xpath("//a[@id='MenuIzquierdot14']");
	private final By lnkListados = By.xpath("//a[@id='MenuIzquierdot15']");
	private final By lnkTareas = By.xpath("//a[@id='MenuIzquierdot16']");
	private final By lnkABMClientes = By.xpath("//a[@id='ListaProgt0']");
	private final By lnkABMSolicitudesAdicionales = By.xpath("//a[@id='ListaProgt1']");
	private final By lnkABMSolicitudesIndividuales = By.xpath("//a[@id='ListaProgt2']");
	private final By lnkListadoRechazosAltasServicios = By.xpath("//a[@id='ListaProgt0']");
	private final By lnkEntregaAdicionales = By.xpath("//a[@id='ListaProgt0']");


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

	public void clickCuentas() {
		clickear(lnkCuentas);
	}

	public void clickConsultas() {
		clickear(lnkConsultas);
	}

	public void clickConsultaCuentas() {
		clickear(lnkConsultaCuentas);
	}

	public void clickCuentasListados() {
		clickear(lnkCuentasListados);
	}

	public void clickConsultaNovedades() {
		clickear(lnkConsultaNovedades);
	}

	public void clickStockTarjetasSucursal() {
		clickear(lnkStockTarjetaSucursal);
	}

	public void clickStockListados() {
		clickear(lnkStockListados);
	}

	public void clickListadoStockSucursal() {
		clickear(lnkListadoStockTCSucursal);
	}

	public void clickSolicitudes() {
		clickear(lnkSolicitudes);
	}

	public void clickABM() {
		clickear(lnkABM);
	}

	public void clickABMClientes() {
		clickear(lnkABMClientes);
	}

	public void clickABMSolicitudesAdicionales() {
		clickear(lnkABMSolicitudesAdicionales);
	}

	public void clickABMSolicitudesIndividuales() {
		clickear(lnkABMSolicitudesIndividuales);
	}

	public void clickListados() {
		clickear(lnkListados);
	}

	public void clickListadoRechazosAltaServicio() {
		clickear(lnkListadoRechazosAltasServicios);
	}

	public void clickTareas() {
		clickear(lnkTareas);
	}

	public void clickEntregaAdicionales() {
		clickear(lnkEntregaAdicionales);
	}
}