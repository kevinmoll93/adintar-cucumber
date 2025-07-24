package page.Consulta;

import base.SeleniumBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;


public class Consulta extends SeleniumBase {
    private final By btnConsulta = By.xpath("//button[contains(.,'Consultas')]");
    private final By lstBusqueda = By.xpath("//input[@id='mat-input-4']");
    private final By btnDescargargrilla = By.xpath("//download-button[@class='ng-tns-c311-10 ng-star-inserted']//button[@class='btn btn-outline-primary btn-sm dropdown-toggle']");
    private final By opPDF = By.xpath("//div[@class='dropdown-menu mt-2 show']//span[.='.PDF (Archivo PDF)']");
    private final By btnBuscarMov = By.xpath("//button[@class='btn text-size-4 text-secondary pl-20 float-right pr-12 ng-tns-c311-78 chromexPathFinder1']");
    private final By btnFecha = By.xpath("//div[@class='cdk-overlay-backdrop mat-overlay-transparent-backdrop mat-datepicker-0-backdrop cdk-overlay-backdrop-showing']");
    private final By btnCalendar = By.xpath("//button[@class='mat-focus-indicator mat-icon-button mat-button-base']");
    private final By btnFlecha = By.xpath("//button[@class='example-double-arrow pr-0']");
    private final By ltsBusquedaCuentasDisponibles = By.xpath("//input[@class='mat-input-element mat-form-field-autofill-control mat-autocomplete-trigger cdk-text-field-autofill-monitored ng-touched ng-dirty ng-valid']");
    private final By divAdherir = By.xpath("//div[@class='mat-ripple mat-tab-label mat-focus-indicator ng-star-inserted']/div[@class='mat-tab-label-content']");
    private final By ltsbuscarCuentaAdherir = By.xpath("//input[@class='mat-autocomplete-trigger search-input pl-4 pl-sm-3 pl-lg-4 ng-tns-c127-445 ng-valid ng-dirty ng-touched']");
    private final By btnAdherirExtracto = By.xpath("//td[@class='actionsNew p-0']//button[@class='btn btn-sm btn-outline-primary']");
    private final By inputMailExtracto= By.xpath("//input[@class='mat-input-element mat-form-field-autofill-control ng-tns-c57-67 ng-pristine ng-invalid cdk-text-field-autofill-monitored ng-touched']");
    private final By btnAceptar= By.xpath("//button[@class='btn btn-primary py-2']");
    private final By btnTresPuntos= By.xpath("//tbody[@class='ng-tns-c318-41']/tr[1]//svg-icon[@id='tres_puntos']");
    private final By opAgregarReferencia= By.xpath("//div[@class='dropdown-menu mt-2 ng-tns-c318-41 show']/a[2]/span[@class='ng-tns-c318-41']");
    private final By txtReferencia= By.xpath("//input[@class='mat-input-element mat-form-field-autofill-control ng-tns-c57-46 ng-pristine ng-invalid cdk-text-field-autofill-monitored ng-touched']");
    private final By btnAceptarReferencia= By.xpath("//button[@class='btn btn-primary mt-32']");
    private final By buscarNumCuentaReferencia= By.xpath("//input[@class='mat-autocomplete-trigger search-input pl-4 pl-sm-3 pl-lg-4 ng-tns-c126-42 ng-valid ng-pristine ng-touched']");


    /**Constructor que inicializa el driver de Selenium*/
    public Consulta(WebDriver driver) {
        super(driver);
    }


    public void clickConsulta() {
        clickear(btnConsulta);
    }

    /**Ingresa un texto en el campo de búsqueda y selecciona el elemento encontrado*/
    public void ingresarBusqueda(String ptexto) {
        // Intentar escribir en el primer campo de búsqueda
        if (escribirSiExiste(lstBusqueda, ptexto)) {
            clickearSiExiste(lstBusqueda);

        }
    }
    public void ingresarBusquedaCuentaDisponible(String nCuenta) {
        // Intentar escribir en el primer campo de búsqueda
        if (clickearSiExiste(ltsBusquedaCuentasDisponibles)) {
            escribirSiExiste(ltsBusquedaCuentasDisponibles, nCuenta);

        }
    }


    public void BuscarMovSeleccionarFecha(String fecha1, String fecha2) {
        clickearSiExiste(btnBuscarMov);
        try {
            // Seleccionar la primera fecha
            seleccionarFecha(fecha1);
            // Esperar un breve momento antes de seleccionar la segunda fecha (opcional)
            Thread.sleep(1000);

            // Seleccionar la segunda fecha
            seleccionarFecha(fecha2);
        } catch (Exception e) {
            manejarErrorYCerrarNavegador("Error al seleccionar las fechas de acreditación");
        }
    }

    // Método auxiliar para seleccionar una única fecha
    private void seleccionarFecha(String pFecha) {
        try {
            // Abrir el calendario
            clickear(btnCalendar);
            scrollHastaElFinal();

            // Selector dinámico basado en el atributo aria-label
            String cssSelectorFecha = String.format("[aria-label='%s']", pFecha);

            boolean fechaVisible = false;

            for (int i = 0; i < 12; i++) { // Máximo 12 iteraciones para evitar bucles infinitos
                try {
                    WebElement fecha = driver.findElement(By.cssSelector(cssSelectorFecha));
                    if (fecha.isDisplayed()) {
                        fechaVisible = true;
                        fecha.click();
                        System.out.println("Fecha seleccionada correctamente: " + pFecha);
                        break;
                    }
                } catch (NoSuchElementException e) {
                    System.out.println("Fecha no visible, navegando al siguiente mes...");
                    clickear(btnFlecha);
                }
            }

            if (!fechaVisible) {
                throw new RuntimeException("No se encontró la fecha deseada: " + pFecha);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al seleccionar la fecha: " + pFecha, e);
        }
    }

    public void clickAdherir(String cuenta, String mail) {
        // Intentar escribir en el primer campo de búsqueda
        clickearSiExiste(divAdherir);
        clickearSiExiste(ltsbuscarCuentaAdherir);
        escribirSiExiste(ltsbuscarCuentaAdherir,cuenta);
        clickearSiExiste(btnAdherirExtracto);
        escribirSiExiste(inputMailExtracto,mail);
        clickearSiExiste(btnAceptar);
        //colocar el token de link
    }

    public void clickConsultarExtracto(String cuenta) {
        //hacer funcion de consultar extracto
    }

    public void CBUAliasAgregarReferencia(String referencia,String numCuenta){
        escribirSiExiste(buscarNumCuentaReferencia,numCuenta);
        clickearSiExiste(btnTresPuntos);
        clickearSiExiste(opAgregarReferencia);
        escribirSiExiste(txtReferencia,referencia);
        clickearSiExiste(btnAceptarReferencia);
    }






    private boolean escribirSiExiste(By locator, String texto) {
        if (existe(locator)) {
            escribir(locator, texto);
            return true;
        }
        return false;
    }

    private boolean clickearSiExiste(By locator) {
        if (existe(locator)) {
            clickear(locator);
        }
        return false;
    }



}

