@cargarHaberesProveedores
Feature: Flujo de carga de haberes y proveedores

  Scenario Outline: Realiza la carga de archivo de pago a terceros, siguiendo los pasos
    Given el usuario está en la página de login
    When ingresa "<usuario>" y "<password>"
    Then debería ver la pantalla principal

    Given en el menu principal ingresar "Haberes, proveedores y otros" en la seccion de pagos
    When seleccionamos el servicio "<servicio>", el convenio "<convenio>" y confirmamos con el boton continuar
    And seleccionamos la cuenta disponible "<cuentaDisponible>" y confirmamos con el boton continuar
    And ingresamos los datos del pago, importe total "<importe>", fecha de acreditacion "<fecha>", concepto "<concepto>"
    And adjuntamos el archivo "<archivo>" con los destinatarios y confirmamos con el boton confirmar
    Then realiza con exito la carga de haberes y servicios ingresando "<password>"

    When realiza el cierre de sesion
    Then deberia ver el login principal

    Examples:
      | usuario      | password | servicio           | convenio | cuentaDisponible        | importe | fecha                 | concepto | archivo                |
      | claudiatsoft | Bcba123- | Pago a Proveedores | 000370   | CC $ 000000230000002013 | 5000    | 28 de agosto de 2025   | Testing  | C:\prueba\clientes.txt |