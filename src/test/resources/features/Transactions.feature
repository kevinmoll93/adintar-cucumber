@Transacciones
Feature: operaciones dentro de la seccion transacciones-transferencias

#  Background: Me encuentro en la página de login deslogueado
#    Given El usuario navega a "https://ciudad.bee3.h.redlink.com.ar/ciudad/bee/auth/login"
#    When El usuario completa el campo usuario "claudiatsoft" y clave "Bcba123-"
#    Then El usuario accede al dashboard

  #requerimiento: se necesita tener previamente a un destinatario

  #nota: no es posible agendar un nuevo destinatario porque pide token, y no es posible deshabilitar SFA en esta funcionalidad
#  @Transferencias
#  Scenario Outline: Desde la pestaña Transacciones-Transferencias, se agenda un nuevo destinatario.
#    Given el usuario está en la página de login
#    When ingresa "<user>" y "<password>"
#    Then debería ver la pantalla principal
#
#    Given El usuario navega a: transacciones-transferencias
#    And Se hace click en el botón "Agendar ahora"
#    And Se ingresa el nro de "<type>": "<number>"
#    And Se hace click en el botón de la lupa para buscar y se hace click en el botón "Continuar"
#    When Se ingresa la referencia: "<reference>"
#    And Se ingresa el mail: "<email>"
#    And Se hace click en el botón "Continuar" y click en el botón "Agendar destinatario"
#    Then Se muestra una ventana de "Terminos y condiciones". Se hace click en el botón "Continuar"
#    And Se muestra el mensaje de confirmación: "<expectedSuccessMessage>"
#    And Click en el botón "Transferir" que te redirige a la página "<transferPage>" para hacer una transferencia de ese "<tipo>" que agregaste.
#    Examples:
#      | user      | password | type | number                 | reference   | email               | expectedSuccessMessage                                                        | transferPage                       |
#      | josetsoft | Bcba123- | CBU  | 0290023011000000005676 | refTest      | josetsoft@gmail.com | El destinatario fue agendado y aprobado con éxito. ¿Querés transferirle ahora?|/ciudad/bee/app/transferencias/carga|

  # Se debe agregar manualmente este cbu a la agenda de transferencias antes de correr este caso:0290023000000000020132
  @Transferencias
  Scenario Outline: Desde la pestaña Transacciones-Transferencias, se elimina un destinatario de la agenda.
    Given el usuario está en la página de login
    When ingresa "<user>" y "<password>"
    Then debería ver la pantalla principal

    Given El usuario navega a: transacciones-transferencias
    And Se busca el "<type>" con numero "<number>" y se hace click en los 3 puntitos para abrir las opciones.
    When Se hace click en "Eliminar"
    And Se muestra una ventana con un mensaje de confirmación antes de eliminar: "<confirmMessage>"
    Then Se muestra el mensaje de confirmación exitosa: "<expectedSuccessMessage>"
    And Se hace click en el botón "Aceptar" y el numero de cuenta: "<number>" eliminado, ya no se muestra en la tabla de agenda de destinatarios.

    Examples:
      | user      | password | type | number                 |confirmMessage                                         | expectedSuccessMessage                  |
      | josetsoft | Bcba123- | CBU  | 0290023000000000020132 |¿Estás seguro de que querés eliminar este destinatario?| El destinatario fue eliminado con éxito.|

  @Transferencias
  Scenario Outline: Desde la pestaña Transacciones-Transferencias, se hace una transferencia inmediata a un destinatario agendado con firma y envio
    Given el usuario está en la página de login
    When ingresa "<user>" y "<password>"
    Then debería ver la pantalla principal

    Given El usuario navega a: transacciones-transferencias
    And Hace click en el boton transferir en agenda de destinatarios de la cuenta "<cbuDestino>"
    When Se elije la cuenta origen desde donde se extrae el dinero: "<cbuOrigen>"
    And Se completan los campos de transferencia inmediata. pass:"<password>" importe:"<importe>", motivo "<motivo>", concepto "<concepto>", referencia "<referencia>", email "<email>"
    And Click en firmar y enviar
    Then Se muestra un mensaje de confirmación: "<successMessage>"

    When realiza el cierre de sesion
    Then deberia ver el login principal
    Examples:
      | user       | password | importe | motivo | concepto | referencia | email          | cbuDestino           | cbuOrigen        | successMessage      | banco/tipoDeCuenta|
      | josetsoft  | Bcba123- | 50001   | varios | concep1  | ref1       | jose@gmail.com |0290000110000012110761|000000180000050623|Transferencia enviada| cbu Banco Ciudad-Especial Persona Jurídica-pesos |
      | josetsoft  | Bcba123- | 50001   | varios | concep1  | ref1       | jose@gmail.com |0290023011000000005676|000211100051577632|Transferencia enviada| cbu Banco Ciudad-Especial Persona Jurídica-dólares |
#errorAgregadoAlExcel      | josetsoft  | Bcba123- | 50001   | varios | concep1  | ref1       | jose@gmail.com |0000001700300000000313|000000180000050623|Transferencia enviada|cvu|
      | josetsoft  | Bcba123- | 50001   | varios | concep1  | ref1       | jose@gmail.com |0290068100000000001478|000000180000050623|Transferencia enviada| cbu Banco Ciudad-cc|
      | josetsoft  | Bcba123- | 50001   | varios | concep1  | ref1       | jose@gmail.com |2470002510000000010824|000000180000050623|Transferencia enviada| cbu Banco Roela-cc|

  @Transferencias
  Scenario Outline: Desde la pestaña Transacciones-Transferencias, se hace una transferencia inmediata desde el botón "transferir ahora"-"Otras cuentas"-"Agendadas", con firma y envio
    Given el usuario está en la página de login
    When ingresa "<user>" y "<password>"
    Then debería ver la pantalla principal

    Given El usuario navega a: transacciones-transferencias
    And Se hace click en el botón "Transferir ahora"
    When Se selecciona "Agendadas", elegimos el destinatario: "<name>" y click en continuar
    And Seleccionar cuenta débito: "<cbuOrigen>"
    And Se completan los campos de transferencia inmediata. pass:"<password>" importe:"<importe>", motivo "<motivo>", concepto "<concepto>", referencia "<referencia>", email "<email>"
    And Click en firmar y enviar
    Then Se muestra un mensaje de confirmación: "<successMessage>"

    When realiza el cierre de sesion
    Then deberia ver el login principal
    Examples:
      | user       | password | importe | motivo | concepto | referencia |name                | email          | cbuDestino           | cbuOrigen        | successMessage      | banco/tipoDeCuenta|
      | josetsoft  | Bcba123- | 50001   | varios | concep1  |            |OSDE                | jose@gmail.com |0290000110000012110761|000000180000050623|Transferencia enviada| cbu Banco Ciudad-Especial Persona Jurídica-pesos |
      | josetsoft  | Bcba123- | 50001   | varios | concep1  |            |O S D E             | jose@gmail.com |0290023000000000020132|000000180000050623|Transferencia enviada| cbu Banco Ciudad-cc |
      | josetsoft  | Bcba123- | 100   | varios | concep1  |            |arevalo             | jose@gmail.com |0290000111000000734194|000211100051577632|Transferencia enviada| cbu Banco Ciudad-ca en dólares |
      #Tiene un comportamiento erratico con este name| josetsoft  | Bcba123- | 50001   | varios | concep1  |            |INSTITUTO JUAN XXIII| jose@gmail.com |2470002510000000010824|000000180000050623|Transferencia enviada| cbu Banco Roela-cc |



    # Las transferencias programadas no tienen envío. Solo firma
  @Transferencias
  Scenario Outline: Desde la pestaña Transacciones-Transferencias, se hace una transferencia programada a un destinatario agendado
    Given el usuario está en la página de login
    When ingresa "<user>" y "<password>"
    Then debería ver la pantalla principal

    Given El usuario navega a: transacciones-transferencias
    And Hace click en el boton transferir en agenda de destinatarios de la cuenta "<cbuDestino>"
    When Se elije la cuenta origen desde donde se extrae el dinero: "<cbuOrigen>"
    And Se completan los campos de transferencia programada. pass:"<password>" importe:"<importe>", motivo "<motivo>", concepto "<concepto>", referencia "<referencia>", fecha "<fecha>", email "<email>"
    And Click en firmar
    Then Se muestra un mensaje de confirmación de firma: "<successMessage>"

    When realiza el cierre de sesion
    Then deberia ver el login principal
    Examples:
      | user          | password | importe   | motivo     | concepto | referencia | email          | cbuDestino           | cbuOrigen        |fecha     | successMessage      |
      | josetsoft     | Bcba123- | 50001     | alquileres | concep2  | ref2       | jose@gmail.com |0290000110000012110761|000000180000050623|26/06/2025|Transferencia firmada|
      | josetsoft     | Bcba123- | 50001     | alquileres | concep2  | ref2       | jose@gmail.com |0290023011000000005676|000211100051577632|26/06/2025|Transferencia firmada|
      | josetsoft     | Bcba123- | 50001     | alquileres | concep2  | ref2       | jose@gmail.com |0290068100000000001478|000000180000050623|26/06/2025|Transferencia firmada|
      | josetsoft     | Bcba123- | 50001     | alquileres | concep2  | ref2       | jose@gmail.com |2470002510000000010824|000000180000050623|26/06/2025|Transferencia firmada|

  @Transferencias
  Scenario Outline: Desde la pestaña Transacciones-Transferencias, se hace una transferencia programada desde el botón "transferir ahora"-"Otras cuentas"-"Agendadas"
    Given el usuario está en la página de login
    When ingresa "<user>" y "<password>"
    Then debería ver la pantalla principal

    Given El usuario navega a: transacciones-transferencias
    And Se hace click en el botón "Transferir ahora"
    When Se selecciona "Agendadas", elegimos el destinatario: "<name>" y click en continuar
    And Seleccionar cuenta débito: "<cbuOrigen>"
    And Se completan los campos de transferencia programada. pass:"<password>" importe:"<importe>", motivo "<motivo>", concepto "<concepto>", referencia "<referencia>", fecha "<fecha>", email "<email>"
    And Click en firmar
    Then Se muestra un mensaje de confirmación de firma: "<successMessage>"

    When realiza el cierre de sesion
    Then deberia ver el login principal
    Examples:
      | user       | password | importe | motivo | concepto | referencia |name  | email          | cbuDestino           | cbuOrigen        |fecha     | successMessage      | banco/tipoDeCuenta|
      | josetsoft  | Bcba123- | 50001   | varios | concep1  |    ref1    |OSDE| jose@gmail.com |0290000110000012110761|000000180000050623|26/06/2025|Transferencia firmada| cbu Banco Ciudad-Especial Persona Jurídica-pesos |
      | josetsoft  | Bcba123- | 50001   | varios | concep1  |    ref2    |O S D E| jose@gmail.com |0290023000000000020132|000000180000050623|26/06/2025|Transferencia firmada| cbu Banco Ciudad-cc |
      | josetsoft  | Bcba123- | 50001   | varios | concep1  |    ref2    |arevalo| jose@gmail.com |0290000111000000734194|000211100051577632|26/06/2025|Transferencia firmada| cbu Banco Ciudad-ca en dólares |



  @Transferencias
  Scenario Outline: Desde la pestaña Transacciones-Transferencias, se hace una transferencia programada a un destinatario agendado, dentro de un esquema de dos firmantes.
    Given el usuario está en la página de login
    When ingresa "<user>" y "<password>"
    Then debería ver la pantalla principal

    Given El usuario navega a: transacciones-transferencias
    And Hace click en el boton transferir en agenda de destinatarios de la cuenta "<cbuDestino>"
    When Se elije la cuenta origen desde donde se extrae el dinero: "<cbuOrigen>"
    And Se completan los campos de transferencia programada. pass:"<password>" importe:"<importe>", motivo "<motivo>", concepto "<concepto>", referencia "<referencia>", fecha "<fecha>", email "<email>"
    And Click en firmar
    Then Se muestra un mensaje de confirmación de firma: "<successMessage>"

    When realiza el cierre de sesion
    Then deberia ver el login principal

    Given el usuario está en la página de login
    When ingresa "<user2>" y "<password2>"
    Then debería ver la pantalla principal

    Given El usuario navega a: transacciones-transferencias
    And Click en el botón "Consultar"
    And Click en el botón "Programadas"
    And Selecciono la cuenta origen de la transacción: "<cbuOrigen>"
    When Hago click en los tres puntitos y hago click en firmar
    And Me muestra los datos de la transferencia, junto con los firmantes y hago click en el botón firmar
    Then El mensaje de confirmación de la transferencia programada completado con sus 2 firmas, debe ser: "<sucessMessageProgrammedTransfer>"

    When realiza el cierre de sesion
    Then deberia ver el login principal

    Examples:
      | user         | password | importe | motivo     | concepto | referencia | email          | cbuDestino           | cbuOrigen        |fecha      |user2  |password2| successMessage      |sucessMessageProgrammedTransfer                                                  |banco/tipoDeCuenta|
      | josetsoft    | Bcba123- | 100     | alquileres | concep2  | ref2       | jose@gmail.com |0290000110000012110761|000000180000050623|26/06/2025 |cedip2 |Bcba123- |Transferencia firmada|La transferencia se firmó correctamente y será acreditada en la fecha programada.|cbu Banco Ciudad-Especial Persona Jurídica-pesos |
      | josetsoft    | Bcba123- | 100     | alquileres | concep2  | ref2       | jose@gmail.com |0290023011000000005676|000211100051577632|26/06/2025 |cedip2 |Bcba123- |Transferencia firmada|La transferencia se firmó correctamente y será acreditada en la fecha programada.|cbu Banco Ciudad-Especial Persona Jurídica-dólares|
      | josetsoft    | Bcba123- | 100     | alquileres | concep2  | ref2       | jose@gmail.com |0290068100000000001478|000000180000050623|26/06/2025 |cedip2 |Bcba123- |Transferencia firmada|La transferencia se firmó correctamente y será acreditada en la fecha programada.|cbu Banco Ciudad-cc                               |
      | josetsoft    | Bcba123- | 100     | alquileres | concep2  | ref2       | jose@gmail.com |2470002510000000010824|000000180000050623|26/06/2025 |cedip2 |Bcba123- |Transferencia firmada|La transferencia se firmó correctamente y será acreditada en la fecha programada.|cbu Banco Roela-cc                                |


  @Transferencias @test
  Scenario Outline: Desde la pestaña Transacciones-Transferencias, se hace una transferencia inmediata desde el botón "transferir ahora"-"Mis cuentas del banco"
    Given el usuario está en la página de login
    When ingresa "<user>" y "<password>"
    Then debería ver la pantalla principal

    Given El usuario navega a: transacciones-transferencias
    And Se hace click en el botón "Transferir ahora"
    And Se hace click en el botón "Mis cuentas del banco"
    When Se selecciona la cuenta destino: "<cbuDestino>" y hacemos click en continuar
    And Seleccionar cuenta débito: "<cbuOrigen>"
    And Se completan los campos de transferencia inmediata. pass:"<password>" importe:"<importe>", motivo "<motivo>", concepto "<concepto>", referencia "<referencia>", email "<email>"
    And Click en firmar y enviar
    Then Se muestra un mensaje de confirmación: "<successMessage>"

    When realiza el cierre de sesion
    Then deberia ver el login principal
    Examples:
      | user       | password | importe | motivo | concepto | referencia |name                | email          | cbuDestino           | cbuOrigen        | successMessage      | banco/tipoDeCuenta|
      | josetsoft  | Bcba123- | 50001   | varios | concep1  |            |OSDE                | jose@gmail.com |000000180000050623|000411100051577625|Transferencia enviada| cbu Banco Ciudad-Especial Persona Jurídica-pesos |


  @PlazoFijo
  Scenario Outline: Desde la pestaña Transferir, se hacen diferentes tipos de plazos fijos
    Given el usuario está en la página de login
    When ingresa "<user>" y "<password>"
    Then debería ver la pantalla principal

    Given El usuario navega a: transacciones-plazo fijo 2.5
    And Selecciona el tipo de plazo fijo "<typeFixedTerm>"
    And Selecciona la clase de renovación "<typeOfRenovation>" a "<days>" dias
    And Se ingresa el importe "<amount>"
    And Se selecciona la cuenta "<accountNumber>" de la tabla
    When Se hace click en el botón aceptar
    Then Se muestra la pantalla de confirmación de datos y se hace click en el botón aceptar
    And Para confirmar la operación, se ingresa la clave de usuario "<password>"
    When Se hace click en el botón firmar
    And Se selecciona la operación para firmarla y se hace click en firmar "<password>"
    And Se hace el envío de la operación
    And Se selecciona la operacion con el numero de cuenta "<accountNumber>" para hacer el envío
    Then Se muestra la confirmación de datos y aceptamos ingresando nuestra password "<password>"
    And Se muestra el número de operación y se muestra el mensaje "<expectedSuccessMessage>"
    Examples:
      | user         | password | importe | motivo     | concepto | referencia | email          | typeFixedTerm               |typeOfRenovation                 |days|amount|accountNumber      |expectedSuccessMessage   |
      | josetsoft    | Bcba123- | 100     | alquileres | concep2  | pft sin ren| jose@gmail.com |Plazo Fijo Tradicional       |Sin Renovación (Acred. al Vto.)  |30  |1000  |000000180000050623 |El envío ha sido exitoso.|
      | josetsoft    | Bcba123- | 100     | alquileres | concep2  | pft con ren| jose@gmail.com |Plazo Fijo Tradicional       |Renovación Total (Capital + Int.)|30  |1000  |000000180000050623 |El envío ha sido exitoso.|
      | josetsoft    | Bcba123- | 100     | alquileres | concep2  |pftd sin ren| jose@gmail.com |Plazo Fijo Tradicional en U$S|Sin Renovación (Acred. al Vto.)  |30  |1000  |000211100051577632 |El envío ha sido exitoso.|
      | josetsoft    | Bcba123- | 100     | alquileres | concep2  |pftd con ren| jose@gmail.com |Plazo Fijo Tradicional en U$S|Renovación Total (Capital + Int.)|30  |1000  |000211100051577632 |El envío ha sido exitoso.|
      # | josetsoft    | Bcba123- | 100     | alquileres | concep2  | pfu sin ren| jose@gmail.com |Plazo Fijo UVA               |Sin Renovación (Acred. al Vto.)  |30  |1000  |000000180000050623 |El envío ha sido exitoso.|
      # | josetsoft    | Bcba123- | 100     | alquileres | concep2  | pfu con ren| jose@gmail.com |Plazo Fijo UVA               |Renovación Total (Capital + Int.)|30  |1000  |000000180000050623 |El envío ha sido exitoso.|


  @PlazoFijo
  Scenario Outline: Desde la pestaña Transferir, se hace un plazo fijo tradicional sin renovacion con 2 firmas con la cuenta "<accountNumber>"
    Given el usuario está en la página de login
    When ingresa "<user1>" y "<password1>"
    Then debería ver la pantalla principal

    Given El usuario navega a: transacciones-plazo fijo 2.5
    And Selecciona el tipo de plazo fijo "<typeFixedTerm>"
    And Selecciona la clase de renovación "<typeOfRenovation>" a "<days>" dias
    And Se ingresa el importe "<amount>"
    And Se selecciona la cuenta "<accountNumber>" de la tabla
    When Se hace click en el botón aceptar
    Then Se muestra la pantalla de confirmación de datos y se hace click en el botón aceptar
    And Para confirmar la operación, se ingresa la clave de usuario "<password>"
    When Se hace click en el botón firmar
    And Se selecciona la operación para firmarla y se hace click en firmar "<password>"
    And Se hace el envío de la operación
    And Se selecciona la operacion con el numero de cuenta "<accountNumber>" para hacer el envío
    Then Se muestra la confirmación de datos y aceptamos ingresando nuestra password "<password>"
    And Se muestra el número de operación y se muestra el mensaje "<expectedSuccessMessage>"

    Given el usuario espera unos momentos para cerrar sesion
    When realiza el cierre de sesion
    Then deberia ver el login principal

    Given el usuario está en la página de login
    When ingresa "<user2>" y "<password2>"
    Then debería ver la pantalla principal


    Examples:
      | user1      | password1  | typeFixedTerm               |typeOfRenovation                 |days|amount |accountNumber      |expectedSuccessMessage   |user2 |password2|
      | josetsoft  | Bcba123-   |Plazo Fijo Tradicional       |Sin Renovación (Acred. al Vto.)  |30  |50001  |000000180000050623 |El envío ha sido exitoso.|cedip2|Bcba123- |