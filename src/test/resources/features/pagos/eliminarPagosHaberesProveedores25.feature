@eliminarPagosHaberesProveedores25
Feature: Flujo de eliminacion pagos haberes y proveedores

  Scenario Outline: Realiza eliminacion de pagos a terceros, mediante transaccion
    Given el usuario está en la página de login
    When ingresa "<usuario>" y "<password>"
    Then debería ver la pantalla principal

    Given en el menu principal ingresar "Haberes, proveedores y otros 2.5" en la seccion de pagos
    When seleccionamos el servicio "<servicio>", si es Otro entonces seleccionamos "<otrosDestinos>" y aceptamos
    And seleccionamos el numero de transaccion "<numeroTransaccion>"
    Then realiza con exito la eliminacion de pago haberes, otros destinos o servicios ingresando la contrasenia "<password>"

    When realiza el cierre de sesion
    Then deberia ver el login principal

    Examples:
      | usuario      | password | servicio    | otrosDestinos       | numeroTransaccion |
      | claudiatsoft | Bcba123- | tieneSelect | Complemento Docente | 1189941           |

  Scenario Outline: Realiza eliminacion de pagos mediante la cuenta indicada
    Given el usuario está en la página de login
    When ingresa "<usuario>" y "<password>"
    Then debería ver la pantalla principal

    Given en el menu principal ingresar "Haberes, proveedores y otros 2.5" en la seccion de pagos
    When seleccionamos el servicio "<servicio>", si es Otro entonces seleccionamos "<otrosDestinos>" y aceptamos
    And seleccionamos la cuenta debito "<cuentaDebito>" que contengan todas transacciones a eliminar
    Then realiza con exito la eliminacion de pago haberes, otros destinos o servicios ingresando la contrasenia "<password>"

    When realiza el cierre de sesion
    Then deberia ver el login principal

    Examples:
      | usuario      | password | servicio | otrosDestinos | cuentaDebito       |
      | claudiatsoft | Bcba123- | PAGOHAB  |               | 000000230000002013 |