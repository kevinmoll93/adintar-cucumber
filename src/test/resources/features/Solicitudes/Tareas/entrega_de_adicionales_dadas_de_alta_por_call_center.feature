@EntregaAdicionalesDadasDeAltaPorCallCenter
Feature: Casos de prueba: Solicitudes-Tareas-entrega de adicionales dadas de alta por call center

  @filtro_busqueda
  Scenario Outline: Filtro y busqueda por el número de solicitud
    Given el usuario accede a la Entrega de Adicionales dadas de alta por Call Center
    And Se abre una pestaña nueva.
    When Se ingresa el número de solicitud: "<request_number>".
    And Click en el botón "Buscar".
    Then Se obtiene el o los resultados de la busqueda.

    Examples:
      | request_number |
      | 200008         |


  @filtro_busqueda
  Scenario: Filtro y busqueda por el checkbox "Solicitud entregada"
    Given el usuario accede a la Entrega de Adicionales dadas de alta por Call Center
    And Se abre una pestaña nueva.
    When Click en el checkbox "solicitud entregada"
    And Click en el botón "Buscar".
    Then Se obtiene el o los resultados de la busqueda.


  @filtro_busqueda
  Scenario Outline: Filtro y busqueda por fecha de alta desde
    Given el usuario accede a la Entrega de Adicionales dadas de alta por Call Center
    And Se abre una pestaña nueva.
    When Se ingresa la fecha de alta desde: "<registration_date_from>".
    And Click en el botón "Buscar".
    Then Se obtiene el o los resultados de la busqueda.

    Examples:
      | registration_date_from |
      | 01/01/2025             |


  @filtro_busqueda
  Scenario Outline: Filtro y busqueda por fecha de alta hasta
    Given el usuario accede a la Entrega de Adicionales dadas de alta por Call Center
    And Se abre una pestaña nueva.
    When Se ingresa la fecha de alta hasta: "<registration_date_to>".
    And Click en el botón "Buscar".
    Then Se obtiene el o los resultados de la busqueda.

    Examples:
      | registration_date_to |
      | 01/03/2025           |

  @test
  Scenario Outline: Filtro y busqueda por la administradora
    Given el usuario accede a la Entrega de Adicionales dadas de alta por Call Center
    And Se abre una pestaña nueva.
    When Seleciona la administradora: "<administrator>".
    And Click en el botón "Buscar".
    Then Se obtiene el o los resultados de la busqueda.

    Examples:
      | administrator |
      | 01-MASTERCARD |
      | 02-CABAL      |
      | 03-VISA       |