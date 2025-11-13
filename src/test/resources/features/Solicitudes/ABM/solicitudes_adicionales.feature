@abm @solicitudes_adicionales
Feature: Casos de prueba: Solicitudes-ABM-Solicitudes adicionales

  @filtro_busqueda
  Scenario Outline: Filtro y busqueda poniendo usando el campo "nro de cuenta"
    Given el usuario accede al ABM Solicitudes Adicionales
    When El usuario selecciona el campo "Nro de cuenta" e ingresa: "<account_number>".
    And Se hace click en el botón buscar.
    Then Se obtiene el o los resultados de la busqueda.
    Examples:
      | account_number |
      | 370097902      |


  @filtro_busqueda
  Scenario Outline: Filtro y busqueda poniendo usando el campo "estado"
    Given el usuario accede al ABM Solicitudes Adicionales
    When El usuario selecciona el estado: "<state>".
    And Se hace click en el botón buscar.
    Then Se obtiene el o los resultados de la busqueda.
    Examples:
      | state                     |
      | 001-EN SUCURSAL           |
      | 003-EN SCORING            |
      | 004-EN OTORGAMIENTO       |
      | 005-APROBADA              |
      | 010-CONFIRMADA            |
      | 011-ENVIADA               |
      | 012-ALTA CONFIRMADA       |
      | 013-RECHAZADA POR ADM     |
      | 014-CANCELADA             |
      | 015-PARCIALMENTE ACEPTADA |
      | 017-DE BAJA               |


  @filtro_busqueda
  Scenario Outline: Filtro y busqueda poniendo usando el campo "Tipo y nro de documento titular"
    Given el usuario accede al ABM Solicitudes Adicionales
    When El usuario selecciona en el campo "Tipo y nro de documento titular" "<document_type>" e ingresa: "<document_number>".
    And Se hace click en el botón buscar.
    Then Se obtiene el o los resultados de la busqueda.
    Examples:
      | document_type                      | document_number |
      | DNI - DOCUMENTO NACIONAL IDENTIDAD | 12088803        |
      | EXT - DNI EXTRANJERO               | 93460360        |
      | LC  - LIBRETA CIVICA               | 669261          |
      | LE  - LIBRETA DE ENROLAMIENTO      | 8437965         |
      | PA  - PASAPORTE                    | 0               |

  @filtro_busqueda
  Scenario Outline: Filtro y busqueda poniendo usando el campo "Tipo y nro de documento adicional"
    Given el usuario accede al ABM Solicitudes Adicionales
    When El usuario selecciona en el campo "Tipo y nro de documento adicional" "<document_type>" e ingresa: "<document_number>".
    And Se hace click en el botón buscar.
    Then Se obtiene el o los resultados de la busqueda.
    Examples:
      | document_type                      | document_number |
      | DNI - DOCUMENTO NACIONAL IDENTIDAD | 24265633        |
      | EXT - DNI EXTRANJERO               | 94211616        |
      | LC  - LIBRETA CIVICA               | 2611132         |
      | LE  - LIBRETA DE ENROLAMIENTO      | 6248363         |
      | PA  - PASAPORTE                    | 35193940        |


  @filtro_busqueda
  Scenario Outline: Filtro y busqueda poniendo usando el campo "Apellido y nombre"
    Given el usuario accede al ABM Solicitudes Adicionales
    When El usuario selecciona el campo "Apellido y nombre" e ingresa: "<full_name>".
    And Se hace click en el botón buscar.
    Then Se obtiene el o los resultados de la busqueda.
    Examples:
      | full_name             |
      | FASULINO MARIA TERESA |

  @filtro_busqueda
  Scenario Outline: Filtro y busqueda poniendo usando el campo "Nro de cuenta"
    Given el usuario accede al ABM Solicitudes Adicionales
    When El usuario selecciona el campo "Nro de cuenta" e ingresa: "<account_number>".
    And Se hace click en el botón buscar.
    Then Se obtiene el o los resultados de la busqueda.
    Examples:
      | account_number |
      | 1191587060     |

  @filtro_busqueda
  Scenario Outline: Filtro y busqueda poniendo usando el campo "Rango solicitudes"
    Given el usuario accede al ABM Solicitudes Adicionales
    When El usuario selecciona el campo "Rango solicitudes" e ingresa: desde "<from>" y hasta "<to>".
    And Se hace click en el botón buscar.
    Then Se obtiene el o los resultados de la busqueda.
    Examples:
      | from   | to     |
      | 998654 | 999977 |

  @filtro_busqueda
  Scenario Outline: Filtro y busqueda poniendo usando el campo "Fecha estado"
    Given el usuario accede al ABM Solicitudes Adicionales
    When El usuario selecciona el campo "Fecha estado" e ingresa: "<status_date>".
    And Se hace click en el botón buscar.
    Then Se obtiene el o los resultados de la busqueda.
    Examples:
      | status_date |
      | 28/06/2023  |


  @filtro_busqueda
  Scenario Outline: Filtro y busqueda poniendo usando el campo "Sucursal bancaria"
    Given el usuario accede al ABM Solicitudes Adicionales
    When El usuario selecciona el campo "Sucursal bancaria" e ingresa: "<bank_branch>".
    And Se hace click en el botón buscar.
    Then Se obtiene el o los resultados de la busqueda.
    Examples:
      | bank_branch           |
      | 001 - SUCURSAL CENTRO |
      | 003 - SUCURSAL 3      |
      | 004 - SUCURSAL 04     |
      | 005 - TRIBUNALES      |
      | 006 - SUCURSAL 6      |
      | 008 - NU#EZ           |
      | 009 - BALVANERA       |
      | 012 - OBELISCO        |
      | 013 - LINIERS         |
      | 014 - BARRACAS        |



