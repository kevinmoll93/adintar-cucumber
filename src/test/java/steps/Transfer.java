package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import page.DashboardPage;
import page.transaction.TransferPage;

public class Transfer {
    DashboardPage dashboardPage = new DashboardPage();
    TransferPage transferPage = new TransferPage();

    @Given("El usuario navega a: transacciones-transferencias")
    public void navigateToTransfer() {
        dashboardPage.navigateToTransfer();
    }

    @Given("El usuario navega a: transacciones-plazo fijo 2.5")
    public void navigateToFixedTerm() {
        dashboardPage.navigateToFixedTerm();
    }

    @And("Hace click en el boton transferir en agenda de destinatarios de la cuenta {string}")
    public void clickTransferButton(String numberCbu) {
        //transferPage.clickTransferButton(nroCbu);
        String posRow = transferPage.searchUniqueAccountNumber(numberCbu);
        // Verificar si no se encontró la cuenta (es decir, posRow es "-1")
        if (posRow.equals("-1")) {
            // Mostrar mensaje y terminar el caso de prueba
            System.out.println("Número de cuenta no encontrado");
            Assert.fail("Número de cuenta no encontrado");  // Termina el caso de prueba con un fallo
            return;
        }else{
            transferPage.clickTransferButton(String.valueOf(Integer.parseInt(posRow) - 1));     // le restamos 1 porque los botones de transferir comienzan en cero
        }
    }

    @When("Se elije la cuenta origen desde donde se extrae el dinero: {string}")
    public void selectDestinationAccount(String cbuSource) {
        transferPage.selectDestinationAccount(cbuSource);
    }

    @And("Seleccionar cuenta débito: {string}")
    public void selectDestinationAccount2(String cbuSource) {
        transferPage.selectDestinationAccount2(cbuSource);
    }

    @And("Se completan los campos de transferencia inmediata. pass:{string} importe:{string}, motivo {string}, concepto {string}, referencia {string}, email {string}")
    public void completeImmediateTransferFields(String pass, String importe, String motivo, String concepto,
                                                String referencia, String email) {
        transferPage.completeImmediateTransferFields(pass, importe, motivo, concepto, referencia, email);
    }

    @Then("Se muestra un mensaje de confirmación")
    public void showConfirmationMessage() {
        Assert.assertEquals("Transferencia enviada", transferPage.showConfirmationMessage());
    }

    @And("Click en firmar y enviar")
    public void signAndSend(){
        transferPage.signAndSend();
    }

    @And("Click en firmar")
    public void sign(){
        transferPage.sign();
    }


    @Then("Se muestra un mensaje de confirmación: {string}")
    public void showConfirmationSendTransferMessage(String successTransferMessage) {
        Assert.assertEquals(successTransferMessage, transferPage.showConfirmationSendTransferMessage());
    }

    @Then("Se muestra un mensaje de confirmación de firma: {string}")
    public void showConfirmationSignTransferMessage(String successTransferMessage) {
        Assert.assertEquals(successTransferMessage, transferPage.showConfirmationSignTransferMessage());
    }

    @And("Se completan los campos de transferencia programada. pass:{string} importe:{string}, motivo {string}, concepto {string}, referencia {string}, fecha {string}, email {string}")
    public void completeProgrammedTransferFields(String pass, String importe, String motivo, String concepto,
                                                 String referencia, String fecha, String email) {
        transferPage.completeProgrammedTransferFields(pass, importe, motivo, concepto, referencia, fecha, email);
    }

    @And("Selecciona el tipo de plazo fijo {string}")
    public void selectTypeFixedTerm(String type){
        transferPage.selectTypeFixedTerm(type);
    }

    @And("Selecciona la clase de renovación {string} a {string} dias")
    public void selectTypeOfRenovation(String type, String days){
        transferPage.selectTypeOfRenovationAndAmountOfDays(type, days);
    }

    @And("Se ingresa el importe {string}")
    public void setAmount(String amount){
        transferPage.setAmount(amount);
    }

    @And("Se selecciona la cuenta {string} de la tabla")
    public void selectAccountFromTable(String numberAccount){
        transferPage.selectAccountFromTable(numberAccount);
    }

    @When("Se hace click en el botón aceptar")
    public void clickAcceptButton(){
        transferPage.clickAcceptButton();
    }

    @Then("Se muestra la pantalla de confirmación de datos y se hace click en el botón aceptar")
    public void clickAcceptDataConfirmationButton(){
        transferPage.clickAcceptDataConfirmationButton();
    }

    @And("Para confirmar la operación, se ingresa la clave de usuario {string}")
    public void setConfirmationPassword(String pass){
        transferPage.setConfirmationPassword(pass);
    }

    @When("Se hace click en el botón firmar")
    public void clickOnTheSignButton(){
        transferPage.clickOnTheSignButton();
    }

    @When("Se selecciona la operación para firmarla y se hace click en firmar {string}")
    public void selectOperationToSignItAndClickSign(String pass){
        transferPage.selectOperationToSignItAndClickSign(pass);
    }

    @When("Se hace el envío de la operación")
    public void sendOperation(){
        transferPage.sendOperation();
    }

    @And("Se selecciona la operacion con el numero de cuenta {string} para hacer el envío")
    public void selectAndSendOperation(String numberAccount){
        transferPage.selectAndSendOperation(numberAccount);
    }

    @Then("Se muestra la confirmación de datos y aceptamos ingresando nuestra password {string}")
    public void showConfirmationAndAccept(String pass){
        transferPage.showConfirmationAndAccept(pass);
    }

    @And("Se muestra el número de operación y se muestra el mensaje {string}")
    public void fixedTermsDone(String expectedSuccessMessage){
        Assert.assertEquals(expectedSuccessMessage, transferPage.fixedTermsDone(expectedSuccessMessage));
    }


    @And("Se busca el {string} con numero {string} y se hace click en los 3 puntitos para abrir las opciones.")
    public void searchUniqueAccountNumber(String type, String number){
        String posRow = transferPage.searchUniqueAccountNumber(number);
        // Verificar si no se encontró la cuenta (es decir, posRow es "-1")
        if (posRow.equals("-1")) {
            // Mostrar mensaje y terminar el caso de prueba
            System.out.println("Número de cuenta no encontrado");
            Assert.fail("Número de cuenta no encontrado");  // Termina el caso de prueba con un fallo
            return;
        }else{
            transferPage.clickDropdownToggle(posRow);
        }
        //System.out.println("stop");
    }

    @When("Se hace click en \"Eliminar\"")
    public void clickDropdownToggle(){
        transferPage.deleteAccountNumber();
    }

    @And("Se muestra una ventana con un mensaje de confirmación antes de eliminar: {string}")
    public void confirmDeleteAccountNumber(String expectedConfirmMessage){
        Assert.assertEquals(expectedConfirmMessage, transferPage.confirmDeleteAccountNumber());
    }

    @Then("Se muestra el mensaje de confirmación exitosa: {string}")
    public void messageConfirmDeleteAccount(String expectedSuccessMessage){
        Assert.assertEquals(expectedSuccessMessage, transferPage.messageConfirmDeleteAccount());
    }

    @And("Se hace click en el botón \"Aceptar\" y el numero de cuenta: {string} eliminado, ya no se muestra en la tabla de agenda de destinatarios.")
    public void acceptSuccessMessageDeleteAccount(String accountUniqueNumber){
        String posRow = transferPage.acceptSuccessMessageDeleteAccount(accountUniqueNumber);
        // Si no se encuentra la cuenta, significa que fue eliminada correctamente
        if(posRow.equals("-1")) {
            // Realizar una aserción para verificar que la cuenta fue eliminada correctamente
            Assert.assertTrue(true, "Numero de cuenta eliminado correctamente.");
        } else {
            // Si la cuenta aún está presente en la tabla, podemos hacer una aserción de error o tomar otra acción
            Assert.fail("La cuenta no fue eliminada correctamente. Número de cuenta aún presente en la tabla.");
        }
    }

    @And("Click en el botón \"Consultar\"")
    public void goToConsultTab(){
        transferPage.goToConsultTab();
    }

    @And("Click en el botón \"Programadas\"")
    public void goToProgrammedTransfer(){
        transferPage.goToProgrammedTransfer();
    }

    @And("Selecciono la cuenta origen de la transacción: {string}")
    public void selectAccountFromDropdown(String numberAccount){
        transferPage.selectAccountFromDropdown(numberAccount);
    }

    @When("Hago click en los tres puntitos y hago click en firmar")
    public void clickOnTheThreeDotsAndSign() {
        transferPage.clickOnTheThreeDotsAndSign();
    }

    @And("Me muestra los datos de la transferencia, junto con los firmantes y hago click en el botón firmar")
    public void transferTeceipt(){
        transferPage.transferTeceipt();
    }

    @Then("El mensaje de confirmación de la transferencia programada completado con sus 2 firmas, debe ser: {string}")
    public void sucessMessageProgrammedTransfer(String expectedSuccessMessage){
        Assert.assertEquals(expectedSuccessMessage, transferPage.sucessMessageProgrammedTransfer());
    }

    @And("Se hace click en el botón \"Transferir ahora\"")
    public void clickOnTransferNow(){
        transferPage.clickOnTransferNow();
    }

    @When("Se selecciona \"Agendadas\", elegimos el destinatario: {string} y click en continuar")
    public void selectDestinationAndContinue(String name) {
        transferPage.selectDestinationAndContinue(name);
    }

    @And("Se hace click en el botón \"Mis cuentas del banco\"")
    public void clickOnMyBankAccounts(){
        transferPage.clickOnMyBankAccounts();
    }

    @When("Se selecciona la cuenta destino: {string} y hacemos click en continuar")
    public void selectDestinationAccountAndContinue(String numberAccount) {
        transferPage.selectDestinationAndContinue(numberAccount);
    }
}