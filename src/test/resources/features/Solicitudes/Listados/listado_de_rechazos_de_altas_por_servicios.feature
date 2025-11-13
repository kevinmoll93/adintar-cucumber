@listados @listado_de_rechazos_de_altas_por_servicios
Feature: Casos de prueba: Solicitudes-listados-Listado de Rechazos de Altas por servicios

  @test
  Scenario Outline: generación de reporte
    Given el usuario accede al Listado de Rechazos de Altas por servicios
    And Se abre una pestaña nueva.
    And Selecciona el formato "<format_type>"
    Then Genera el reporte de formato "<format_type>" con nombre "<file_name>".

    Examples:
      | format_type | file_name                                  |
      | pdf         | Listado de Rechazos de Altas por servicios |
      | excel       | Listado de Rechazos de Altas por servicios |