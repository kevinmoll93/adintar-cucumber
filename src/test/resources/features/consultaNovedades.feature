@ConsultaNovedades
Feature: Permite realizar una consulta de novedades con generacion de reporte en formato PDF y en ExCel

  Scenario Outline: Consulta de novedades en formato PDF con tipo de reporte Detalle
	Given el usuario accede a la consulta de Novedades
	When selecciona el formato salida PDF y el filtro tipo de reporte Detalle
	And selecciona filtro Administradora "<Administradora>", Novedad tipo "<Tipo>" y Fecha de alta desde "<FechaAltaDesde>"
	And ingresa Cuenta "<Cuenta>", Origen "<Origen>", Estado "<Estado>", y Fecha Alta hasta "<FechaAltaHasta>"
	Then Genera y guarda "<NombreArchivo>" el reporte PDF de manera exitosa

	Given el usuario espera unos momentos para cerrar sesion
	When realiza el cierre de sesion
	Then deberia ver el texto de cierre exitoso

	Examples:
	  | Administradora | Tipo               | FechaAltaDesde | Cuenta | Origen                    | Estado      | FechaAltaHasta | NombreArchivo |
	  | VISA           | 0700 - CANAL VENTA | 05/05/2025     |        | 00018-PORTAL - WEBSERVICE | 6-Cancelada | 07/08/2025     | NovedadVisa   |