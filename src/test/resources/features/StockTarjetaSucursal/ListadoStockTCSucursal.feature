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

  Scenario Outline: Consulta de Stock con Origen TODOS y con generacion de reporte Excel
	Given el usuario accede a la listado de stock TC sucursal
	When selecciona el formato salida Excel
	And selecciona el Tipo Listado "<TipoListado>", Sucursal "<Sucursal>" y Administradora "<Administradora>"
	And selecciona Estado "<Estado>"
	And ingresa las Fechas Estado desde "<FechaEstadoD>" y hasta "<FechaEstadoH>"
	And ingresa las Fechas Recepcion desde "<FechaRecepcionD>" y hasta "<FechaRecepcionH>"
	Then Genera y guarda el reporte Excel "<NombreArchivo>" de manera exitosa

	Given el usuario espera unos momentos para cerrar sesion
	When realiza el cierre de sesion
	Then deberia ver el texto de cierre exitoso

	Examples:
	  | TipoListado       | Sucursal   | Administradora | Estado      | FechaEstadoD | FechaEstadoH | FechaRecepcionD | FechaRecepcionH | NombreArchivo   |
	  | Consulta de Stock | 022 - ONCE | 01-MASTERCARD  | En Sucursal |              |              |                 |                 | StockMastercard |

  Scenario Outline: Consulta de Stock con Origen ESPECIFICO y con generacion de reporte PDF
	Given el usuario accede a la listado de stock TC sucursal
	When selecciona el formato salida PDF
	And selecciona el Tipo Listado "<TipoListado>", Sucursal "<Sucursal>" y Administradora "<Administradora>"
	And selecciona el Origen "<Origen>"
	And selecciona Estado "<Estado>"
	And ingresa las Fechas Estado desde "<FechaEstadoD>" y hasta "<FechaEstadoH>"
	And ingresa las Fechas Recepcion desde "<FechaRecepcionD>" y hasta "<FechaRecepcionH>"
	And selecciona el archivo origen "<ArchivoOrigen>"
	And selecciona la sucursal origen "<SucursalOrigen>"
	Then Genera y guarda el reporte PDF "<NombreArchivo>" de manera exitosa

	Given el usuario espera unos momentos para cerrar sesion
	When realiza el cierre de sesion
	Then deberia ver el texto de cierre exitoso

	Examples:
	  | TipoListado       | Sucursal   | Administradora | Origen  | Estado      | FechaEstadoD | FechaEstadoH | FechaRecepcionD | FechaRecepcionH | ArchivoOrigen                                         | SucursalOrigen    | NombreArchivo   |
	  | Consulta de Stock | 022 - ONCE | 01-MASTERCARD  | [TODOS] | En Sucursal |              |              |                 |                 | 582428 - [STOCKTC.TXT] TARJETAS RECIBIDAS DESDE OCASA | 004 - SUCURSAL 04 | StockMastercard |

  Scenario Outline: Plasticos a Destruir y con generacion de reporte PDF
	Given el usuario accede a la listado de stock TC sucursal
	When selecciona el formato salida PDF
	And selecciona el Tipo Listado "<TipoListado>", Sucursal "<Sucursal>" y Administradora "<Administradora>"
	And selecciona el tipo de Accion "<TipoAccion>"
	And selecciona Estado "<Estado>"
	And ingresa las Fechas Estado desde "<FechaEstadoD>" y hasta "<FechaEstadoH>"
	And ingresa las Fechas Recepcion desde "<FechaRecepcionD>" y hasta "<FechaRecepcionH>"
	Then Genera y guarda el reporte PDF "<NombreArchivo>" de manera exitosa

	Given el usuario espera unos momentos para cerrar sesion
	When realiza el cierre de sesion
	Then deberia ver el texto de cierre exitoso

	Examples:
	  | TipoListado          | Sucursal   | Administradora | TipoAccion                                   | Estado                  | FechaEstadoD | FechaEstadoH | FechaRecepcionD | FechaRecepcionH | NombreArchivo         |
	  | Plasticos a Destruir | 022 - ONCE | 03-VISA        | 44   - Boletinamiento Tarjeta decision Banco | Baja Solicitada Cliente |              |              |                 |                 | PlasticosDestruirVISA |

  Scenario Outline: Plasticos a Destruir y con generacion de reporte Excel
	Given el usuario accede a la listado de stock TC sucursal
	When selecciona el formato salida Excel
	And selecciona el Tipo Listado "<TipoListado>", Sucursal "<Sucursal>" y Administradora "<Administradora>"
	And selecciona el tipo de Accion "<TipoAccion>"
	And selecciona Estado "<Estado>"
	And ingresa las Fechas Estado desde "<FechaEstadoD>" y hasta "<FechaEstadoH>"
	And ingresa las Fechas Recepcion desde "<FechaRecepcionD>" y hasta "<FechaRecepcionH>"
	Then Genera y guarda el reporte Excel "<NombreArchivo>" de manera exitosa

	Given el usuario espera unos momentos para cerrar sesion
	When realiza el cierre de sesion
	Then deberia ver el texto de cierre exitoso

	Examples:
	  | TipoListado          | Sucursal   | Administradora | TipoAccion             | Estado         | FechaEstadoD | FechaEstadoH | FechaRecepcionD | FechaRecepcionH | NombreArchivo          |
	  | Plasticos a Destruir | 022 - ONCE | 02-CABAL       | 14   - Baja/No Emision | Baja o Boletin |              |              |                 |                 | PlasticosDestruirCABAL |

