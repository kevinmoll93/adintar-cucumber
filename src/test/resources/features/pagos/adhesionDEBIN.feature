@AdhesionDEBIN
Feature: Flujo Completo de Adhesión DEBIN

  Scenario Outline: realiza adhesiones por numero de cuenta
    Given el usuario está en la página de login
    When ingresa "<usuario>" y "<password>"
    Then debería ver la pantalla principal

    Given el usuario esta en el menu principal e ingresa "DEBIN" la seccion de pagos
    When selecciona la cuenta "<numeroCuenta>", ingresa el nombre fantasia "<nombreFantasia>" y selecciona su rubro "<rubro>"
    Then realiza con exito la adhesion de la cuenta confirmando con la contrasenia "<password>"

    When realiza el cierre de sesion
    Then deberia ver el login principal

    Examples:
      | usuario      | password | numeroCuenta       | nombreFantasia | rubro  |
      | claudiatsoft | Bcba123- | 000211100051571757 | Testing        | Varios |
      | claudiatsoft | Bcba123- | 000302300050004045 | Prueba         | Varios |

  Scenario Outline: realiza adhesiones por CBU de cuenta
    Given el usuario está en la página de login
    When ingresa "<usuario>" y "<password>"
    Then debería ver la pantalla principal

    Given el usuario esta en el menu principal e ingresa "DEBIN" la seccion de pagos
    When selecciona el CBU "<numeroCBU>", ingresa el nombre fantasia "<nombreFantasia>" y selecciona su rubro "<rubro>"
    Then realiza con exito la adhesion de la cuenta confirmando con la contrasenia "<password>"

    When realiza el cierre de sesion
    Then deberia ver el login principal

    Examples:
      | usuario      | password | numeroCBU              | nombreFantasia | rubro  |
      | claudiatsoft | Bcba123- | 0290000100000500199596 | Testing        | Varios |
