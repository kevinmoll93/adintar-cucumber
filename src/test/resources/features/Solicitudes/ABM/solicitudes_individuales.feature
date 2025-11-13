@abm @solicitudes_individuales
Feature: Casos de prueba: Solicitudes-ABM-Solicitudes individuales

  @filtro_busqueda
  Scenario Outline: Filtro y busqueda poniendo usando el campo "estado"
    Given el usuario accede al ABM Solicitudes Individuales
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
  Scenario Outline: Filtro y busqueda poniendo usando el campo "Nro de cuenta"
    Given el usuario accede al ABM Solicitudes Individuales
    When El usuario selecciona el campo "Nro de cuenta" e ingresa: "<account_number>".
    And Se hace click en el botón buscar.
    Then Se obtiene el o los resultados de la busqueda.
    Examples:
      | account_number |
      | 1191587060     |


  @filtro_busqueda
  Scenario Outline: Filtro y busqueda poniendo usando el campo "Rango solicitudes"
    Given el usuario accede al ABM Solicitudes Individuales
    When El usuario selecciona el campo "Rango solicitudes" e ingresa: desde "<from>" y hasta "<to>".
    And Se hace click en el botón buscar.
    Then Se obtiene el o los resultados de la busqueda.
    Examples:
      | from   | to     |
      | 998654 | 999977 |

  @filtro_busqueda
  Scenario Outline: Filtro y busqueda poniendo usando el campo "Nro de solicitud"
    Given el usuario accede al ABM Solicitudes Individuales
    When El usuario selecciona el campo "Nro de solicitud" e ingresa: "<nro_solicitud>".
    And Se hace click en el botón buscar.
    Then Se obtiene el o los resultados de la busqueda.
    Examples:
      | nro_solicitud |
      | 999977        |

  @filtro_busqueda
  Scenario Outline: Filtro y busqueda poniendo usando el campo "Fecha estado"
    Given el usuario accede al ABM Solicitudes Individuales
    When El usuario selecciona el campo "Fecha estado" e ingresa: "<status_date>".
    And Se hace click en el botón buscar.
    Then Se obtiene el o los resultados de la busqueda.
    Examples:
      | status_date |
      | 28/06/2023  |


  @filtro_busqueda
  Scenario Outline: Filtro y busqueda poniendo usando el campo "Sector de alta"
    Given el usuario accede al ABM Solicitudes Individuales
    When El usuario selecciona el campo "Sector de alta" e ingresa: "<registration_section>".
    And Se hace click en el botón buscar.
    Then Se obtiene el o los resultados de la busqueda.
    Examples:
      | registration_section              |
      | 2   - SUCURSAL 02  MONSERRAT      |
      | 3   - SUCURSAL 03                 |
      | 5   - SUCURSAL 05 COM. TRIBUNALES |
      | 6   - SUCURSAL 06 PIGNORATICIO    |
      | 7   - SUCURSAL 07 PALERMO VIEJO   |
      | 8   - SUCURSAL 08 NUÑEZ           |
      | 9   - SUCURSAL 09 BALVANERA       |
      | 12  - SUCURSAL 12 OBELISCO        |
      | 13  - SUCURSAL 13  LINIERS        |
      | 14  - SUCURSAL 14  BARRACAS       |


  @filtro_busqueda
  Scenario Outline: Filtro y busqueda poniendo usando el campo "Tipo y nro de documento"
    Given el usuario accede al ABM Solicitudes Individuales
    When El usuario selecciona en el campo "Tipo y nro de documento" "<document_type>" e ingresa: "<document_number>".
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
  Scenario Outline: Filtro y busqueda poniendo usando el campo "Apellido y nombre"
    Given el usuario accede al ABM Solicitudes Individuales
    When El usuario selecciona el campo "Apellido y nombre" e ingresa: "<full_name>".
    And Se hace click en el botón buscar.
    Then Se obtiene el o los resultados de la busqueda.
    Examples:
      | full_name            |
      | CAMELS DANIEL HECTOR |
