@EliminarAdhesionImpuestoServicio

Feature: permite Eliminar una adhesion de impuestos/servicios

  Scenario Outline: ingresar su codigo de pago para identificarlo
    Given el usuario está en la página de login
    When ingresa "<usuario>" y "<password>"
    Then debería ver la pantalla principal

    Given en el menu principal buscamos "Impuestos y servicios" la seccion de pagos adheridos
    When buscamos el codigo de pago "<codigo>" y elimina la adhesion
    Then realizamos con exito la quita de adhesion

    When realiza el cierre de sesion
    Then deberia ver el login principal

    Examples:
      | usuario     | password  | codigo      |
      | claudiatsoft | Bcba123- | 10001059111 |