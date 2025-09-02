@ListadoStockTCSucursal
Feature: Permite realizar la verificacion de la generacion de reportes y filtros del listado de stock

  Scenario Outline: Consulta de Stock con Origen TODOS y con generacion de reporte PDF
	Given el usuario accede a la listado de stock TC sucursal
	When selecciona el formato salida PDF
	And selecciona el Tipo Listado "<TipoListado>", Sucursal "<Sucursal>" y Administradora "<Administradora>"
	And selecciona Estado "<Estado>"
	And ingresa las Fechas Estado desde "<FechaEstadoD>" y hasta "<FechaEstadoH>"
	And ingresa las Fechas Recepcion desde "<FechaRecepcionD>" y hasta "<FechaRecepcionH>"
	Then Genera y guarda el reporte PDF "<NombreArchivo>" de manera exitosa

	Given el usuario espera unos momentos para cerrar sesion
	When realiza el cierre de sesion
	Then deberia ver el texto de cierre exitoso

	Examples:
	  | TipoListado       | Sucursal   | Administradora | Estado      | FechaEstadoD | FechaEstadoH | FechaRecepcionD | FechaRecepcionH | NombreArchivo   |
	  | Consulta de Stock | 022 - ONCE | 01-MASTERCARD  | En Sucursal |              |              |                 |                 | StockMastercard |