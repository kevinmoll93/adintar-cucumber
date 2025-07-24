@pagarImpuestoServicio
@prueba
Feature: permite pagar impuestos y servicios, ingresando su codigo de pago y una descripcion

  Scenario Outline: ingresar nombre de la empresa, su codigo y una descripcion del pago
    Given el usuario está en la página de login
    When ingresa "<usuario>" y "<password>"
    Then debería ver la pantalla principal

    Given en el menu principal buscamos "Impuestos y servicios" la seccion de pagos
    When ingresa el nombre "<nombre>" ,luego ingresamos su codigo "<codigo>" y por ultimo una descripcion "<descripcion>"
    And seleccionamos la cuenta disponible "<cuentaDisponible>" y confirmamos con el boton continuar
    And seleccionamos concepto "<concepto>", ingresamos importe "<importe>", ingresamos operacion "<operacion>"
    Then realizamos con exito el pago

    When realiza el cierre de sesion
    Then deberia ver el login principal

    Examples:
      | usuario     | password | nombre        | codigo      | descripcion | cuentaDisponible        | concepto     | importe | operacion  |
      | hernantsoft | Bcba123. | Mercado Libre | 10001059111 | TESTING     | CC $ 000000180000050852 | MPAGO COMPRA | 500     | 1234567890 |

  Scenario Outline: intentar pagar con datos inválidos
    Given el usuario está en la página de login
    When ingresa "<usuario>" y "<password>"
    Then debería ver la pantalla principal

    Given en el menu principal buscamos "Impuestos y servicios" la seccion de pagos
    When ingresa el nombre "<nombre>" ,luego ingresamos su codigo "<codigo>" y por ultimo una descripcion "<descripcion>"
    Then deberíamos ver el mensaje de error "<mensajeError>"

    When realiza el cierre de sesion
    Then deberia ver el login principal

    Examples:
      | usuario     | password | nombre | codigo | descripcion | mensajeError                     |
      | hernantsoft | Bcba123. | CLARO  | 123456 | PRUEBA      | El dato ingresado es incorrecto. |

  Scenario Outline: ingresar nombre de la empresa, su codigo, una descripcion del pago y adherir pago
    Given el usuario está en la página de login
    When ingresa "<usuario>" y "<password>"
    Then debería ver la pantalla principal

    Given en el menu principal buscamos "Impuestos y servicios" la seccion de pagos
    When ingresa el nombre "<nombre>" ,luego ingresamos su codigo "<codigo>" y por ultimo una descripcion "<descripcion>"
    And seleccionamos la cuenta disponible "<cuentaDisponible>" y confirmamos con el boton continuar
    And seleccionamos concepto "<concepto>", ingresamos importe "<importe>", ingresamos operacion "<operacion>"
    Then realizamos con exito el pago y la adhesion "<adherir>"

    When realiza el cierre de sesion
    Then deberia ver el login principal

    Examples:
      | usuario      | password | nombre        | codigo      | descripcion | cuentaDisponible        | concepto     | importe | operacion  | adherir |
      | claudiatsoft | Bcba123- | Mercado Libre | 10001059111 | TESTING     | CC $ 000000230000002013 | MPAGO COMPRA | 500     | 1234567890 | SI      |