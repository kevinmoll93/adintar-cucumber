package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.And;
import org.openqa.selenium.WebDriver;
import page.Consulta.Consulta;


public class ConsultaSteps {
    WebDriver driver = Hooks.getDriver();
    Consulta consulta = new Consulta(driver);

    @Given("Escribimos en la barra de busqueda")
    public void ClickConsulta() {
        consulta.clickConsulta();
    }

    @When("ingresa el tipo de movimiento {string} que desea consultar")
    public void ConsultaMov(String texto) {
        consulta.ingresarBusqueda(texto);
    }

    @And("buscar movimientos por fecha inicial {string} y fecha actual {string}")
    public void buscarMovimientosPorFechaInicialYFechaActual(String fecha1, String fecha2) {
    }


    @Then("realizamos la consulta con exito")
    public void realizamosConExitoLaConsulta() {
    }

    @And("Buscar cuentas disponibles {string}")
    public void buscarCuentasDisponibles(String nCuenta) {
        consulta.ingresarBusquedaCuentaDisponible(nCuenta);
    }


    @And("Adherir a extractos {string} y {string}")
    public void adherirAExtractosY(String cuenta, String mail) {
        consulta.clickAdherir( cuenta,mail);
    }


    @And("Buscar Cuenta a referenciar {string}  y escribir  {string}")
    public void buscarCuentaAReferenciarYEscribir(String referencia, String numCuenta) {
        consulta.CBUAliasAgregarReferencia(referencia,numCuenta);
    }
}
