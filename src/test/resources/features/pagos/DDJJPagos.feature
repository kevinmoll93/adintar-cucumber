@DDJJPagos
@prueba
Feature: Presentaciones de declaraciones juradas y pagos por medio de transferencia

  Scenario Outline: Presentar DDJJ y pagos
    Given el usuario está en la página de login
    When ingresa "<usuario>" y "<password>"
    Then debería ver la pantalla principal

    Given en el menu principal buscamos "AFIP - VEP" y luego Servicios ARCA
    When seleccionamos DDJJ y Pagos
    Then realizamos con exito DDJJ y pagos

    Given el usuario espera unos momentos para cerrar sesion
    When realiza el cierre de sesion
    Then deberia ver el login principal

    Examples:
      | usuario     | password |
      | hernantsoft | Bcba123. |