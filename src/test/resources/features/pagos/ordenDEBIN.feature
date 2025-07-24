@ordenDEBIN
Feature: Flujo completo para generar una orden DEBIN

  Scenario Outline: Genera una orden a una cuenta adherida, ingresando una cuenta de credito y un destinatario
    Given el usuario está en la página de login
    When ingresa "<usuario>" y "<password>"
    Then debería ver la pantalla principal

    Given en el menu principal ingresar "DEBIN" la seccion de pagos y seleccionar Orden
    When selecciona la cuenta credito "<numeroCuenta>", agregar destinatario, ingresa los datos CBU "<CBU>", su referencia "<referencia>" y su tipo "<tipoCuenta>"
    And agregamos la cuenta, si la cuenta ya existe "<agregarExiste>" entonces buscamos su CBU "<CBU>", agregamos importe "<importe>" y comprobante "<comprobante>"
    And seleccionamos datos debin para ingresar una descripcion "<descripcion>" y concepto "<concepto>"
    Then realiza con exito la orden DEBIN con los datos ingresados

    When realiza el cierre de sesion
    Then deberia ver el login principal

    Examples:
      | usuario      | password | numeroCuenta       | CBU                    | referencia | tipoCuenta     | agregarExiste | descripcion | concepto     | importe | comprobante |
      | claudiatsoft | Bcba123- | 000000230000002013 | 0290024710000001455943 | Alias      | cuentaNoPropia | SI            | Testing     | VAR - Varios | 500     | 1234        |
      | claudiatsoft | Bcba123- | 000000230000002013 | 0290024710000001455943 | Alias      | cuentaNoPropia | NO            | Testing     | VAR - Varios | 500     | 1234        |
