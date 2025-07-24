package page.transaction;

import base.SeleniumBase;
import org.openqa.selenium.By;

public class TransferPage extends SeleniumBase {
    private String transferButton = "//tbody/tr[1]/td[5]/div[1]/button[1]";
    private String inputAccountDropdown = "/html[1]/body[1]/app-root[1]/app-logged[1]/div[2]/app-transferencias[1]/app-transferencias-carga[1]/div[1]/div[1]/app-transferencias-carga-individual[1]/div[1]/div[2]/app-card-step[1]/div[1]/card-step-body-active[1]/div[1]/form[1]/div[1]/app-form-validator[1]/div[1]/app-select-autocomplete[1]/mat-form-field[1]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/input[1]";
    //private String continueButton = "(//button[contains(@class, 'btn-primary') and normalize-space(text())='Continuar'])[2]";
    private String amountField = "(//label[normalize-space(text())='Importe']/preceding-sibling::input)[1]";
    private String amountField2 = "(//label[normalize-space(text())='Importe']/preceding-sibling::input)[2]";
    private String reasonDropdown = "/html[1]/body[1]/app-root[1]/app-logged[1]/div[2]/app-transferencias[1]/app-transferencias-carga[1]/div[1]/div[1]/app-transferencias-carga-individual[1]/div[1]/div[3]/app-card-step[1]/div[1]/card-step-body-active[1]/div[1]/div[1]/form[1]/div[1]/app-form-validator[1]/div[1]/mat-form-field[1]/div[1]/div[1]/div[3]";
    private String conceptField = "//input[@formcontrolname='concepto' and @type='text']";
    private String referenceField = "/html[1]/body[1]/app-root[1]/app-logged[1]/div[2]/app-transferencias[1]/app-transferencias-carga[1]/div[1]/div[1]/app-transferencias-carga-individual[1]/div[1]/div[3]/app-card-step[1]/div[1]/card-step-body-active[1]/div[1]/div[1]/form[1]/div[1]/div[3]/app-form-validator[1]/div[1]/input[1]";
    private String emailField = "/html[1]/body[1]/app-root[1]/app-logged[1]/div[2]/app-transferencias[1]/app-transferencias-carga[1]/div[1]/div[1]/app-transferencias-carga-individual[1]/div[1]/div[3]/app-card-step[1]/div[1]/card-step-body-active[1]/div[1]/div[1]/form[1]/div[1]/div[5]/app-form-validator[1]/div[1]/div[1]/mat-form-field[1]/div[1]/div[1]/div[3]/input[1]";
    private String confirmButton = "//button[normalize-space()='Confirmar']";
    private String completeUploadButton = "//button[normalize-space()='Finalizar carga']";
    private String passwordField = "//input[@id='clave']";
    private String acceptConfirmationButton = "//button[@type='submit']";
    private String acceptConfirmationSignTransferButton = "//button[normalize-space()='Aceptar']";
    private String transferSuccessMessageText = "//*[contains(text(), 'Transferencia enviada')]";
    private String signTransferSuccessMessageText = "//*[contains(text(), 'Transferencia firmada')]";
    private String sendTransferSuccessMessageText = "//*[contains(text(), 'Transferencia enviada')]";
    private String transferSuccessMessageAccept = "//button[normalize-space()='Aceptar']";
    private String programmedButton = "//span[normalize-space()='Programada']";
    private String calendarButton = "//svg-icon[@id='calendario']";
    private String dayCalendar = "//div[normalize-space()='%s']";
    private String signButton = "//span[normalize-space()='Firmar']";
    private String yearCalendar = "//button[@class='example-double-arrow ng-star-inserted']";
    private String selectTypeFixedTerm = "//select[@id='tipoPlazoFijo']";
    private String typeFixedTerm = "//select[@id='tipoPlazoFijo']/option[text()='%s']";
    private String selectTypeOfRenovation = "//select[@id='claseRenovacion']";
    private String typeOfRenovation = "//select[@id='claseRenovacion']/option[contains(text(), '%s')]";
    public String fieldTermInDays = "//input[@id='plazoIngresado']";
    public String fieldAmount = "//input[@id='importeIngresado']";
    public String checkBoxAccount = "//input[@id='gridCuentasDebito_gridFormId_item_1']";       //temporalmente hasta que tenga mas cuentas en mi usuario
    public String acceptButton = "//a[@id='botonAceptar']";
    public String acceptButton2 = "//a[@id='firmaReautenticarId_aceptar']";
    public String acceptDataConfirmationButton = "//a[@id='reautenticarTagId_aceptarResumen']";
    public String confirmationPasswordField = "//input[@id='reautenticarTagId_passwordInput']";
    public String confirmationPasswordAcceptButton = "//a[@id='reautenticarTagId_aceptar']";
    public String signButton2 = "//a[@id='botonFirmar']";
    public String signButton3 = "//a[@id='btFirmar']";
    public String checkBoxOperation = "//input[@id='gridFormId_item_1']";       // temporal
    public String acceptConfirmationButton2 = "//a[@id='firmaReautenticarId_aceptarResumen']";
    public String confirmationPasswordField2 = "//input[@id='firmaReautenticarId_passwordInput']";
    public String sendOperationButton = "//a[@id='btEnviar']";
    public String checkboxAccount= "//td[contains(text(), '%s')]/..";
    public String acceptSendButton = "//a[@id='envioReautenticarId_aceptarResumen']";
    public String sendPasswordField = "//input[@id='envioReautenticarId_passwordInput']";
    public String sendConfirmationAcceptButton = "//a[@id='envioReautenticarId_aceptar']";
    public String backButton = "//a[@id='btnVolver']";
    public String tableAccountNumber = "//table[contains(@class, 'table-bordered')]";
    public String optionTableAccountNumber = "//tbody/tr['%s']/td[5]/div[1]/div[2]/a[1]";
    public String transferButtonAddressBook = "//button[@id='btnTransfDestinatario-%s']";
    public String deleteTableAccountNumber = "//div[@class='dropdown ng-star-inserted show']//span[contains(text(),'Eliminar')]";
    public String deleteTableAccountNumberSi = "//button[normalize-space()='Sí']";
    public String buttonAccept = "//button[normalize-space()='Aceptar']";
    public String confirmTextDeleteAccountNUmber = "//p[text()='¿Estás seguro de que querés eliminar este destinatario?']";
    public String confirmSuccessMessageDeleteAccount = "//p[text()='El destinatario fue eliminado con éxito.']";
    public String leftArrowMonth = "//button[@class='example-double-arrow mr-12']";
    public String rightArrowMonth = "//button[@class='example-double-arrow pr-0']";
    public String signAndSendButton = "//button[.//span[text()='Firmar y enviar']]";
    public String transferConsultTab ="//div[contains(text(),'Consultar')]";
    public String programmedTransfersButton = "//button[text()=' Programadas ']";
    public String inputDropdownAccount = "//input[@placeholder='Buscar por Nº de cuenta o referencia']";
    public String accountDropdown = "//mat-option//div[contains(normalize-space(.), '%s')]";
    public String lastTransactionOptionsInTable = "//tbody/tr[1]/td[7]/div[1]/div[1]/a[1]";
    public String signOptionDropdown = "//div[@class='dropdown ng-star-inserted show']//button[@id='accionFirmarTransfProgramada']";
    public String signIdButton = "//button[@id='btnFirmarTransf']";
    public String successTransferProgrammedText = "//p[contains(text(), 'La transferencia se firmó correctamente')]";
    public String transferNowButton = "//button[@id='btnTransferirAhora']";
    //public String selectDestinationInput = "input[placeholder='Buscar...']";
    public String optionInList = "(//mat-option[@role='option'])[%s]";
    public String continueButton = "//button[contains(text(),'Continuar')]";
    public String continueButton2 = "(//button[contains(text(),'Continuar')])[2]";
    public String selectDestinationInput = "(//input[@data-placeholder='Buscar...'])[1]";
    //public String selectDestinationInput = "input[data-placeholder='Buscar...']";
    public String myBankAccountButton = "//label[span[text()='Mis cuentas del banco']]";

    public TransferPage() {
        super(driver);
    }

    public void clickTransferButton() {
        clickElement(transferButton);
    }

    public void selectDestinationAccount(String cbuSource) {
        selectFromCustomDropdown(inputAccountDropdown, cbuSource);
        clickElement(continueButton);
    }

    public void selectDestinationAccount2(String cbuSource) {
        // es igual a "selectDestinationAccount" pero se cambia el selector del botón "Continuar" ya que en este camino del escenario de prueba, tiene 2 botones "Continuar"
        // y se necesita hacer click en el 2do.
        selectFromCustomDropdown(inputAccountDropdown, cbuSource);
        clickElement(continueButton2);
    }

    public void completeImmediateTransferFields(String pass, String importe, String motivo, String concepto, String referencia, String email) {
        clickAndWrite(amountField2, amountField, importe);
        selectFromCustomDropdown(reasonDropdown, getTransferCode(motivo));
        write(conceptField, concepto);
        write(referenceField, referencia);
        write(emailField, email);
        clickElement(confirmButton);
        clickElement(completeUploadButton);
    }

    public String showConfirmationMessage() {
        String acceptText = textFromElement(transferSuccessMessageText);
        clickElement(transferSuccessMessageAccept);
        return acceptText;
    }

    public String showConfirmationSendTransferMessage() {
        String acceptText = textFromElement(sendTransferSuccessMessageText);
        clickElement(acceptConfirmationSignTransferButton);
        return acceptText;
    }

    public String showConfirmationSignTransferMessage() {
        String acceptText = textFromElement(signTransferSuccessMessageText);
        clickElement(buttonAccept);
        return acceptText;
    }



    public void completeProgrammedTransferFields(String pass, String importe, String motivo, String concepto, String referencia, String fecha, String email) {
        clickElement(programmedButton);
        clickAndWrite(amountField2, amountField, importe);
        scrollDownOnce();
        selectFromCustomDropdown(reasonDropdown, getTransferCode(motivo));
        write(conceptField, concepto);
        write(referenceField, referencia);
        insertDate(fecha);
        write(emailField, email);
        clickElement(confirmButton);
        clickElement(completeUploadButton);
//        clickElement(signButton);
//        write(passwordField, pass);
//        clickElement(acceptConfirmationSignTransferButton);
    }

    // funciones auxiliares
    public String getTransferCode(String reason) {
        // Normalizamos el texto para que no haya diferencias entre mayúsculas y
        // minúsculas
        String lowerReason = reason.toLowerCase();

        // Comprobamos con las opciones definidas
        if (lowerReason.contains("varios")) {
            return " 001 - Varios ";
        } else if (lowerReason.contains("alquiler")) {
            return " 002 - Alquileres ";
        } else if (lowerReason.contains("cuota")) {
            return " 003 - Cuotas ";
        } else if (lowerReason.contains("expensa")) {
            return " 004 - Expensas ";
        } else {
            // Si no se encuentra ninguna coincidencia
            return "No matching code found";
        }
    }

    public void insertDate(String fecha) {
        clickElement(calendarButton);
        String dia = fecha.substring(0, 2);
        int mes = Integer.parseInt(fecha.substring(3, 5));
        String anio = fecha.substring(6, 10);

        int[] date = getCurrentDate();
        int currentMonth = date[1];
        int dif = 0;
        if (mes > currentMonth){
            dif = mes - currentMonth;
        }
        for (int i = 0; i < dif; i++) {
            clickElement(rightArrowMonth);
        }
        String selectDay = String.format(dayCalendar, dia);
        clickElement(selectDay);
    }

    public void selectTypeFixedTerm(String type){
        clickElement(selectTypeFixedTerm);
        String selectTypeFixedTerm = String.format(typeFixedTerm, type);
        clickElement(selectTypeFixedTerm);
    }


    public void selectTypeOfRenovationAndAmountOfDays(String type, String days){
        clickElement(selectTypeOfRenovation);
        String selectTypeOfRenovation = String.format(typeOfRenovation, type);
        clickElement(selectTypeOfRenovation);
        write(fieldTermInDays, days);
    }

    public void setAmount(String amount){
        write(fieldAmount, amount);
    }

    public void selectAccountFromTable(String numberAccount){
        scrollDown(3);
//        clickElement(checkBoxAccount);
        // XPath para encontrar la fila que contiene el número de cuenta
        String filaXpath = String.format(checkboxAccount, numberAccount);

        // Encontrar la fila correspondiente y hacerle click al checkbox correspondiente al numero de cuenta
        findRowAndClickRadio(filaXpath);
    }

    public void clickAcceptButton(){
        clickElement(acceptButton);
    }

    public void clickAcceptDataConfirmationButton(){
        clickElement(acceptDataConfirmationButton);
    }

    public void setConfirmationPassword(String pass){
        write(confirmationPasswordField,pass);
        clickElement(confirmationPasswordAcceptButton);
    }

    public void clickOnTheSignButton(){
        clickElement(signButton2);
    }

    public void selectOperationToSignItAndClickSign(String pass){
        clickElement(checkBoxOperation);
        clickElement(signButton3);
        clickElement(acceptConfirmationButton2);
        write(confirmationPasswordField2,pass);
        clickElement(acceptButton2);
    }

    public void sendOperation(){
        clickElement(sendOperationButton);
    }

    public void selectAndSendOperation(String numberAccount){
        // XPath para encontrar la fila que contiene el número de cuenta
        String filaXpath = String.format(checkboxAccount, numberAccount);

        // Encontrar la fila correspondiente y hacerle click al checkbox correspondiente al numero de cuenta
        findRowAndClickCheckbox(filaXpath);

        clickElement(sendOperationButton);
    }

    public void showConfirmationAndAccept(String pass){
        clickElement(acceptSendButton);
        write(sendPasswordField, pass);
        clickElement(sendConfirmationAcceptButton);
    }

    public String fixedTermsDone(String successfulMessage){
        String filaXpath = String.format(checkboxAccount, successfulMessage);
        String numberOperation = findRowAndReturnData(filaXpath, "1");
        System.out.println("Número de operación: " + numberOperation);
        clickElement(backButton);
        return findRowAndReturnData(filaXpath, "2");
    }

    public String searchUniqueAccountNumber(String accountUniqueNumber){
        return findPosRowInTable(tableAccountNumber, accountUniqueNumber);
    }

    public void clickDropdownToggle(String posRow){
        String dropdownToggle = String.format(optionTableAccountNumber, posRow);
        clickElement(dropdownToggle);
    }

    public void clickTransferButton(String posRow){
        String dropdownToggle = String.format(transferButtonAddressBook, posRow);
        clickElement(dropdownToggle);
    }

    public void deleteAccountNumber(){
        clickElement(deleteTableAccountNumber);
    }

    public String confirmDeleteAccountNumber(){
        clickElement(deleteTableAccountNumberSi);
        return textFromElement(confirmTextDeleteAccountNUmber);
    }

    public String messageConfirmDeleteAccount(){
        return textFromElement(confirmSuccessMessageDeleteAccount);
    }

    public String acceptSuccessMessageDeleteAccount(String accountUniqueNumber){
        clickElement(buttonAccept);
        return findPosRowInTable(tableAccountNumber, accountUniqueNumber);
    }

    public void signAndSend(){
        clickElement(signAndSendButton);
        //clickElement(signAndSendButton);
        //write(passwordField, pass);       de momento, no pide password
    }

    public void sign(){
        clickElement(signButton);
        //write(passwordField, pass);       de momento, no pide password
    }

    public void goToConsultTab(){
        clickElement(transferConsultTab);
    }

    public void goToProgrammedTransfer(){
        clickElement(programmedTransfersButton);
    }

    public void selectAccountFromDropdown(String numberAccount){
        clickElement(inputDropdownAccount);
        String selectAccount = String.format(accountDropdown, numberAccount);
        clickElement(selectAccount);
    }

    public void clickOnTheThreeDotsAndSign(){
        clickElement(lastTransactionOptionsInTable);
        clickElement(signOptionDropdown);
    }

    public void transferTeceipt(){
        clickElement(signIdButton);
    }

    public void clickOnTransferNow(){
        clickElement(transferNowButton);
    }

    public String sucessMessageProgrammedTransfer(){
        String actualText = textFromElement(successTransferProgrammedText);
        clickElement(buttonAccept);
        return actualText;
    }

    public void selectDestinationAndContinue(String name){
        //clickElement(selectDestinationInput);
        write(selectDestinationInput, name);
        waitSeconds(3);
        String selectOption = String.format(optionInList, "1");     //selecciona la 1ra opción de la lista del selector ya que el buscador no funciona correctamente
        waitSeconds(3);
        clickElement(selectOption);
        clickElement(selectOption);
        waitSeconds(3);
        clickElement(continueButton);
    }

    public void clickOnMyBankAccounts(){
        clickElement(myBankAccountButton);
    }

//    public void selectDestinationAccountAndContinue(String numberAccount){
//        write(selectDestinationInput, numberAccount);
//        waitSeconds(3);
//        String selectOption = String.format(optionInList, "1");     //selecciona la 1ra opción de la lista del selector ya que el buscador no funciona correctamente
//        waitSeconds(3);
//        clickElement(selectOption);
//        waitSeconds(3);
//        clickElement(continueButton);
//    }
}
