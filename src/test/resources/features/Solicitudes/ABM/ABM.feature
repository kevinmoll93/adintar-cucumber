@Abm
Feature: Casos de prueba: Solicitudes-ABM

  Scenario Outline: Filtro y busqueda por apellido y nombre
    Given el usuario accede al ABM Clientes
    When Selecciona el filtro por Apellido y Nombre.
    And Ingresa nombre y apellido: "<Apellido>".
    And Click en el botón "Buscar".
    Then Se muestra la tabla de resultado de la búsqueda.
    Examples:
    |Apellido|
    |RILLO MARIA ELINA|


  Scenario Outline: Filtro y busqueda por tipo y número de Clave
    Given el usuario accede al ABM Clientes
    When Selecciona el filtro por tipo "<tipo>" y numero de clave.
    And Ingresa la clave "<clave>".
    Then Se obtiene el resultado de la busqueda.
    Examples:
      | tipo      | clave          |
      | 4  - CUIC | 60000035324185 |

  Scenario Outline: Filtro y busqueda por tipo y número de documento
    Given el usuario accede al ABM Clientes
    When Selecciona el filtro por tipo "<tipo>" y numero de documento.
    And Ingresa el documento "<documento>".
    And Click en el botón "Buscar".
    Then Se muestra la tabla de resultado de la búsqueda.
    Examples:
      | tipo                             | documento |
      |DNI - DOCUMENTO NACIONAL IDENTIDAD| 11858730  |


  Scenario Outline: Filtro y busqueda por Cuit
    Given el usuario accede al ABM Clientes
    When Selecciona el filtro por número de CUIT
    And Ingresa el numero de CUIT o CUIL "<NumeroCUIT>".
    And Click en el botón "Buscar".
    Then Se muestra la tabla de resultado de la búsqueda.
    Examples:
      | NumeroCUIT    |
      | 20-20606955-5 |


