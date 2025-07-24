package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import page.MenuPrincipal;
import page.pagos.CuentaSueldo;

public class AltaCuentaSueldoManualSteps {
	WebDriver driver = Hooks.getDriver();
	MenuPrincipal menu = new MenuPrincipal(driver);
	CuentaSueldo sueldo = new CuentaSueldo(driver);


	@Given("en el menu principal ingresar {string} en la seccion de pagos y luego click Alta Cuentas Manual")
	public void seleccionarCuentaSueldoManual(String cuentasueldo) {
		menu.clickPagos();
		menu.ingresarBusqueda(cuentasueldo);
		sueldo.clickAltaManual();
	}

	@When("ingresamos dependencia {string}, nombre completo {string} y seleccionamos el codigo de la empresa {string}")
	public void ingresamosDependenciaNombreCodigo(String dependencia, String nombre, String codigo) {
		sueldo.ingresarDependencia(dependencia);
		sueldo.ingresarNombre(nombre);
		sueldo.seleccionarCodigoEmpresa(codigo);
	}

	@And("seleccionamos el tipo de documento {string} e ingresamos su numero de documento {string}")
	public void seleccionIngresoDocumento(String tipo, String documento) {
		sueldo.seleccionarTipoDocumento(tipo);
		sueldo.ingresarNumeroDocumento(documento);
	}

	@And("ingresamos la calle {string}, su numero {string}, el piso {string}, depto {string}")
	public void ingresarDatosDomicilio(String calle, String numero, String piso, String depto) {
		sueldo.ingresarCalle(calle);
		sueldo.ingresarNumeroCalle(numero);
		sueldo.ingresarPisoCalle(piso);
		sueldo.ingresarDepartamentoCalle(depto);
	}

	@And("ingresamos el codigo postal {string}, seleccionamos la localidad {string} y su numero de telefono {string}")
	public void ingresarDatosContacto(String codigo, String localidad, String telefono) {
		sueldo.ingresarCodigoPostal(codigo);
		sueldo.seleccionarLocalidad(localidad);
		sueldo.ingresarNumeroTelefono(telefono);
	}

	@And("ingresamos la fecha de nacimiento {string}, seleccionamos el sexo {string} e ingresamos el CUIL {string}")
	public void ingresarDatosNacimiento(String fecha, String sexo, String cuil) {
		sueldo.ingresarFechaNacimiento(fecha);
		sueldo.seleccionarSexo(sexo);
		sueldo.ingresarCUIL(cuil);
	}

	@And("ingresamos el numero de de legajo {string} y escribimos el haber del agente {string}")
	public void ingresamosElNumeroDeDeLegajoYEscribimosElHaberDelAgente(String legajo, String haber) {
		sueldo.ingresarNumeroLegajo(legajo);
		sueldo.ingresarHaberAgente(haber);
	}

	@Then("realiza con exito el alta de cuenta sueldo manual")
	public void realizaConExitoElAltaDeCuentaSueldoManual() {
		sueldo.clickAgregarCuenta();
	}
}
