@altaCuentaSueldoManual
Feature: Flujo de carga altas cuentas sueldo Manual

  Scenario Outline: Realiza alta de cuenta sueldo
    Given el usuario está en la página de login
    When ingresa "<usuario>" y "<password>"
    Then debería ver la pantalla principal

    Given en el menu principal ingresar "Cuentas sueldo" en la seccion de pagos y luego click Alta Cuentas Manual
    When ingresamos dependencia "<dependencia>", nombre completo "<nombre>" y seleccionamos el codigo de la empresa "<codEmpresa>"
    And seleccionamos el tipo de documento "<tipoDocumento>" e ingresamos su numero de documento "<documento>"
    And ingresamos la calle "<calle>", su numero "<numeroCalle>", el piso "<piso>", depto "<depto>"
    And ingresamos el codigo postal "<codPostal>", seleccionamos la localidad "<localidad>" y su numero de telefono "<telefono>"
    And ingresamos la fecha de nacimiento "<fechaNacimiento>", seleccionamos el sexo "<sexo>" e ingresamos el CUIL "<CUIL>"
    And ingresamos el numero de de legajo "<legajo>" y escribimos el haber del agente "<haberAgente>"
    Then realiza con exito el alta de cuenta sueldo manual

    When realiza el cierre de sesion
    Then deberia ver el login principal

    Examples:
      | usuario      | password | dependencia | nombre           | codEmpresa | tipoDocumento | documento | calle   | numeroCalle | piso | depto | codPostal | localidad | telefono   | fechaNacimiento | sexo   | CUIL          | legajo | haberAgente |
      | claudiatsoft | Bcba123- | 123         | kevin mollecundo | 123459     | DNI           | 36854994  | testing | 243         | 1    | B     | 5300      | Capital   | 3804515146 | 1993-02-26      | Hombre | 20-35456456-1 | 1234   | 1234        |