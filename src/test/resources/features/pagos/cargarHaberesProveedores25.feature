@cargarHaberesProveedores25
Feature: Flujo de carga de haberes y proveedores

  Scenario Outline: Realiza la carga de archivo de pago a terceros, siguiendo los pasos
    Given el usuario está en la página de login
    When ingresa "<usuario>" y "<password>"
    Then debería ver la pantalla principal

    Given en el menu principal ingresar "Haberes, proveedores y otros 2.5" en la seccion de pagos
    When seleccionamos el servicio "<servicio>", si es Otro entonces seleccionamos "<otrosDestinos>" y damos click Aceptar
    And seleccionamos la cuenta disponible "<cuentaDisponible>"
    And ingresamos los datos de la transferencia, convenio "<convenio>", importe "<importe>", fecha de acreditacion "<fecha>", concepto "<concepto>"
    And buscamos el archivo adjunto "<archivo>" con los destinatarios
    Then realiza con exito la carga de haberes, otros destinos o servicios

    When realiza el cierre de sesion
    Then deberia ver el login principal

    Examples:
      | usuario      | password | servicio    | otrosDestinos | cuentaDisponible   | convenio | importe | fecha      | concepto | archivo                |
      | claudiatsoft | Bcba123- | PAGOPROV    | Reintegros    | 000000230000002013 | 000370   | 5000    | 28/08/2025 | Testing  | C:\prueba\clientes.txt |
      | claudiatsoft | Bcba123- | tieneSelect | Reintegros    | 000000230000002013 | 033004   | 5000    | 28/08/2025 | Testing  | C:\prueba\clientes.txt |