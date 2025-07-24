@Consultas

Feature: permite consultar los ultimos movimientos y saldos

  Scenario Outline: consultar ultimos movimientos y saldos
    Given el usuario está en la página de login
    When ingresa "<usuario>" y "<password>"
    Then debería ver la pantalla principal

    Given Escribimos en la barra de busqueda
    When  ingresa el tipo de movimiento "<texto>" que desea consultar
    Then  realizamos la consulta con exito

    When realiza el cierre de sesion
    Then deberia ver el login principal

    Examples:
      | usuario     | password | texto                |
      | hernantsoft | Bcba123. | Movimientos y Saldos |



  Scenario Outline: consultar ultimos movimientos y saldos por fecha
    Given el usuario está en la página de login
    When ingresa "<usuario>" y "<password>"
    Then debería ver la pantalla principal

    Given Escribimos en la barra de busqueda
    When  ingresa el tipo de movimiento "<texto>" que desea consultar
    When   buscar movimientos por fecha inicial "<fecha1>" y fecha actual "<fecha2>"
    Then  realizamos la consulta con exito

    When realiza el cierre de sesion
    Then deberia ver el login principal

    Examples:
      | usuario     | password | texto                | fecha1                 | fecha2                 |
      | hernantsoft | Bcba123. | Movimientos y Saldos |1 de noviembre de 2024  |1 de enero de 2025      |


  Scenario Outline: consultar ultimos movimientos y saldos buscando cuentas disponibles
    Given el usuario está en la página de login
    When ingresa "<usuario>" y "<password>"
    Then debería ver la pantalla principal
    Given Escribimos en la barra de busqueda

    When  ingresa el tipo de movimiento "<texto>" que desea consultar
    And   Buscar cuentas disponibles "<nCuenta>"
    Then  realizamos la consulta con exito

    When realiza el cierre de sesion
    Then deberia ver el login principal

    Examples:
      | usuario     | password | texto                | nCuenta                 |
      | hernantsoft | Bcba123. | Movimientos y Saldos |000201800051571405       |

  Scenario Outline: Adherir Extractos
    Given el usuario está en la página de login
    When ingresa "<usuario>" y "<password>"
    Then debería ver la pantalla principal

    Given Escribimos en la barra de busqueda
    When  ingresa el tipo de movimiento "<texto>" que desea consultar

    And   Adherir a extractos "<cuenta>" y "<mail>"
    Then  realizamos la consulta con exito

    When realiza el cierre de sesion
    Then deberia ver el login principal

    Examples:
      | usuario     | password | texto                | cuenta                | mail                           |
      | hernantsoft | Bcba123. | Extractos            | 000201800051571405    |claudia.salgan@tsoftglobal.com  |



  Scenario Outline: CBU Alias Agregar Referencia
    Given el usuario está en la página de login
    When ingresa "<usuario>" y "<password>"
    Then debería ver la pantalla principal

    Given Escribimos en la barra de busqueda
    When  ingresa el tipo de movimiento "<texto>" que desea consultar

    And   Buscar Cuenta a referenciar "<numCuenta>"  y escribir  "<referencia>"
    Then  realizamos la consulta con exito

    When realiza el cierre de sesion
    Then deberia ver el login principal

    Examples:
      | usuario     | password | texto                | referencia                | numCuenta          |
      | hernantsoft | Bcba123. | CBU Alias            | referencia                |000201800051571405  |


