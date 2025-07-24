package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import base.SeleniumBase;

public class DashboardPage extends SeleniumBase {
    private String transactionButton = "//button[normalize-space()='Transacciones']";
    private String inputOptionFieldTransaction = "//input[@formcontrolname='submenuAutocomplete']";
    private String transferOption = "/html[1]/body[1]/div[2]/div[1]/div[1]/div[1]";
    private String logoutButton = "//*[@class='fill-white']//*[name()='svg']";
    private String logoutButtonYes = "//button[normalize-space()='Si']";
    private String subMenu = "//div[@role='listbox']";
    private String modalBewareOfScams = "//app-modal[contains(@class, 'ng-tns-c140')]//bee30-typography[contains(text(), '¡Cuidate de las estafas!')]";
    

    public DashboardPage() {
        super(driver);
        acceptModalBewareOfScams(); // Se ejecuta cada vez que se cree una instancia de DashboardPage
    }


    public void navigateToTransfer() {
        clickElement(transactionButton);
        selectFromCustomDropdown(inputOptionFieldTransaction, " Transferencias ");
    }

    public void navigateToFixedTerm() {
        clickElement(transactionButton);
        selectFromCustomDropdown(inputOptionFieldTransaction, " Plazo fijo 2.5 ");
    }

    public void logout() {
        clickElement(logoutButton);
        clickElement(logoutButtonYes);
    }

    
	public void acceptModalBewareOfScams() {
        long startTime = System.currentTimeMillis();
        long timeout = 3000; // 3 segundos
        System.out.println("Dentro del acceptModalBewareOfScams");
    
        while (System.currentTimeMillis() - startTime < timeout) {
            if (isElementPresent(modalBewareOfScams)) {
                System.out.println("Se muestra el modal: ¡Cuidado! Apareció el modal de advertencia de estafas.");
                 // Verificar si el botón "Aceptar" está presente
                WebElement acceptButton = driver.findElement(By.xpath("//button[contains(text(),'Aceptar')]"));
                acceptButton.click();
                return; // Sale del bucle si el modal aparece
            }
            try {
                Thread.sleep(500); // Espera 500ms antes de verificar nuevamente
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

}
