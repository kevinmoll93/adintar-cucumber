@ConsultaNovedades
Feature: Permite realizar una consulta de novedades con generacion de reporte en formato PDF y en ExCel

  Scenario Outline: Consulta de novedades en formato PDF con tipo de reporte Detalle
	Given el usuario accede a la consulta de Novedades
	When selecciona el formato salida PDF
	And selecciona el filtro tipo de reporte Detalle
	And selecciona filtro Administradora "<Administradora>", Novedad tipo "<Tipo>" y Fecha de alta desde "<FechaAltaDesde>"
	And ingresa Cuenta "<Cuenta>", Origen "<Origen>", Estado "<Estado>", y Fecha Alta hasta "<FechaAltaHasta>"
	Then Genera y guarda el reporte PDF "<NombreArchivo>" de manera exitosa

	Given el usuario espera unos momentos para cerrar sesion
	When realiza el cierre de sesion
	Then deberia ver el texto de cierre exitoso

	Examples:
	  | Administradora | Tipo                                       | FechaAltaDesde | Cuenta    | Origen | Estado       | FechaAltaHasta | NombreArchivo |
	  | VISA           | 7330 - BOLETIN: BAJA DE BLOQUEOS DE CUENTA | 05/05/2025     | 916505266 | 4-Mora | 2-Confirmada | 07/08/2025     | NovedadVisa   |

  Scenario Outline: Consulta de novedades en formato PDF con tipo de reporte Detalle
	Given el usuario accede a la consulta de Novedades
	When selecciona el formato salida PDF
	And selecciona el filtro tipo de reporte Detalle
	And selecciona filtro Administradora "<Administradora>", Novedad tipo "<Tipo>" y Fecha de alta desde "<FechaAltaDesde>"
	And ingresa Cuenta "<Cuenta>", Origen "<Origen>", Estado "<Estado>", y Fecha Alta hasta "<FechaAltaHasta>"
	Then Genera y guarda el reporte PDF "<NombreArchivo>" de manera exitosa

	Given el usuario espera unos momentos para cerrar sesion
	When realiza el cierre de sesion
	Then deberia ver el texto de cierre exitoso

	Examples:
	  | Administradora | Tipo    | FechaAltaDesde | Cuenta | Origen  | Estado  | FechaAltaHasta | NombreArchivo |
	  | CABAL          | [TODOS] | 05/05/2025     |        | [TODOS] | [TODOS] | 07/08/2025     | NovedadCabal  |

  Scenario Outline: Consulta de novedades en formato Excel con tipo de reporte Detalle
	Given el usuario accede a la consulta de Novedades
	When selecciona el formato salida Excel
	And selecciona el filtro tipo de reporte Detalle
	And selecciona filtro Administradora "<Administradora>", Novedad tipo "<Tipo>" y Fecha de alta desde "<FechaAltaDesde>"
	And ingresa Cuenta "<Cuenta>", Origen "<Origen>", Estado "<Estado>", y Fecha Alta hasta "<FechaAltaHasta>"
	Then Genera y guarda el reporte Excel "<NombreArchivo>" de manera exitosa

	Given el usuario espera unos momentos para cerrar sesion
	When realiza el cierre de sesion
	Then deberia ver el texto de cierre exitoso

	Examples:
	  | Administradora | Tipo                                  | FechaAltaDesde | Cuenta | Origen                           | Estado    | FechaAltaHasta | NombreArchivo     |
	  | MASTERCARD     | 2100-CAMBIO DOMICILIO CORRESPONDENCIA | 05/05/2025     |        | 24-ARCHIVO CONVENIOS PLAN SUELDO | 3-Enviada | 07/08/2025     | NovedadMastercard |