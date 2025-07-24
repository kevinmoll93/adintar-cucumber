@cargaVEP

Feature: permite realizar una carga VEP propia o de terceros

  Scenario Outline: seleccionar carga VEP propia
    Given el usuario está en la página de login
    When ingresa "<usuario>" y "<password>"
    Then debería ver la pantalla principal

    Given en el menu principal buscamos "ARCA - VEP" en la seccion de pagos
    When seleccionamos el tipo de VEP "<tipoVEP>" y le damos al boton Buscar
    And cargamos el numero de VEP "<numeroVEP>" especificado y el numero de cuenta "<numeroCuenta>"
    Then realizamos con exito la carga de VEP

    Given el usuario espera unos momentos para cerrar sesion
    When realiza el cierre de sesion
    Then deberia ver el login principal

    Examples:
      | usuario      | password | tipoVEP | numeroVEP    | numeroCuenta           |
      | claudiatsoft | Bcba123- | Propios | 000055354053 | CC $ 000000230000002013|

  Scenario Outline: seleccionar carga VEP de terceros
    Given el usuario está en la página de login
    When ingresa "<usuario>" y "<password>"
    Then debería ver la pantalla principal

    Given en el menu principal buscamos "ARCA - VEP" en la seccion de pagos
    When seleccionamos el tipo de VEP "<tipoVEP>"
    And ingresamos el CUIT "<CUIT>" del tecero y le damos al boton Buscar
    Then realizamos con exito la carga de VEP

    Given el usuario espera unos momentos para cerrar sesion
    When realiza el cierre de sesion
    Then deberia ver el login principal

    Examples:
      | usuario      | password | tipoVEP     | CUIT        |
      | claudiatsoft | Bcba123- | De terceros | 30709528056 |

  Scenario Outline: seleccionar carga VEP de terceros hacia terceros
    Given el usuario está en la página de login
    When ingresa "<usuario>" y "<password>"
    Then debería ver la pantalla principal

    Given en el menu principal buscamos "ARCA - VEP" en la seccion de pagos
    When seleccionamos el tipo de VEP "<tipoVEP>"
    And ingresamos el CUIT "<CUIT>" del tecero y numero de VEP "<numVEP>" a cargar y Buscamos
    Then realizamos con exito la carga de VEP

    Given el usuario espera unos momentos para cerrar sesion
    When realiza el cierre de sesion
    Then deberia ver el login principal

    Examples:
      | usuario      | password | tipoVEP                    | CUIT        | numVEP       |
      | claudiatsoft | Bcba123- | De terceros hacia terceros | 30709528056 | 000055337850 |