package steps;

import base.Config;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import page.ConsultaCuenta;

public class ConsultaCuentaSteps {
	WebDriver driver = Hooks.getDriver();
	ConsultaCuenta consulta = new ConsultaCuenta(driver);

	@When("selecciona el filtro por Apellido y Nombre")
	public void seleccionaElFiltroPorApellidoYNombre() {
		consulta.clickFiltroApellidoNombre();
	}

	@And("ingresa el Apellido y Nombre {string}")
	public void ingresaElApellidoYNombre(String ApellidoNombre) {
		consulta.ingresarApellidoNombre(ApellidoNombre);
	}

	@Then("realiza la busqueda de la consulta con exito")
	public void realizaLaBusquedaDeLaConsultaConExito() {
		consulta.clickBuscar();
	}

	@When("selecciona el filtro por tipo {string} y numero de documento")
	public void seleccionaElFiltroPorTipoYNumeroDeDocumento(String tipo) {
		consulta.clickFiltroTipoNumeroDoc();
		consulta.seleccionarFiltroTipoDoc(tipo);
	}

	@And("ingresa el documento solicitado {string}")
	public void ingresaLosCaracteresSolicitados(String documento) {
		consulta.ingresarDocumento(documento);
	}

	@When("selecciona el filtro por tipo {string} y numero de clave interna")
	public void seleccionaElFiltroPorTipoYNumeroDeClaveInterna(String tipo) {
		consulta.clickFiltroTipoClave();
		consulta.seleccionarFiltroTipoClave(tipo);
	}

	@And("ingresa la clave interna solicitada {string}")
	public void ingresaLaClaveInternaSolicitada(String clave) {
		consulta.ingresarClaveInterna(clave);
	}

	@When("selecciona el filtro por administradora {string} y numero de cuenta")
	public void seleccionaElFiltroPorAdministradoraYNumeroDeCuenta(String tipo) {
		consulta.clickFiltroAdminNumeroCuenta();
		consulta.seleccionarTipoAdminNumeroCuenta(tipo);
	}

	@And("ingresa el numero de cuenta solicitado {string}")
	public void ingresaElNumeroDeCuentaSolicitado(String numero) {
		consulta.ingresarAdminNumeroCuenta(numero);
	}

	@When("selecciona el filtro por administradora {string} y numero de tarjeta")
	public void seleccionaElFiltroPorAdministradoraYNumeroDeTarjeta(String tipo) {
		consulta.clickFiltroAdminNumeroTarjeta();
		consulta.seleccionarAdminNumeroTarjeta(tipo);
	}

	@And("ingresa el numero de tarjeta solicitado {string}")
	public void ingresaElNumeroDeTarjetaSolicitado(String tarjeta) {
		consulta.ingresarAdminNumeroTarjeta(tarjeta);
	}

	@When("selecciona el filtro por numero de cuenta Nexos")
	public void seleccionaElFiltroPorNumeroDeCuentaNexos() {
		consulta.clickFiltroNumeroCuentaNexo();
	}

	@And("ingresa el numero de cuenta nexos solicitado {string}")
	public void ingresaElNumeroDeCuentaNexosSolicitado(String nexos) {
		consulta.ingresarNumeroCuentaNexo(nexos);
	}

	@When("selecciona el filtro por numero de CUIT")
	public void seleccionaElFiltroPorNumeroDeCUIT() {
		consulta.clickFiltroNumeroCUIT();
	}

	@And("ingresa el numero de CUIT solicitado {string}")
	public void ingresaElNumeroDeCUITSolicitado(String cuit) {
		consulta.ingresarNumeroCUIT(cuit);
	}
}
