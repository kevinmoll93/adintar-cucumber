@ConsultaCuenta
Feature: Permite realizar una consulta de cuenta con diferentes filtros

  Scenario Outline: Consultar cuenta ingresada por numero de CUIT
	Given el usuario accede a la consulta de Cuentas
	When selecciona el filtro por numero de CUIT
	And ingresa el numero de CUIT solicitado "<NumeroCUIT>"
	Then realiza la busqueda de la consulta con exito

	Given el usuario espera unos momentos para cerrar sesion
	When realiza el cierre de sesion
	Then deberia ver el texto de cierre exitoso
	Examples:
	  | NumeroCUIT    |
	  | 20-20606955-6 |

  Scenario Outline: Consultar cuenta ingresada por numero de cuenta Nexos
	Given el usuario accede a la consulta de Cuentas
	When selecciona el filtro por numero de cuenta Nexos
	And ingresa el numero de cuenta nexos solicitado "<cuentaNexos>"
	Then realiza la busqueda de la consulta con exito

	Given el usuario espera unos momentos para cerrar sesion
	When realiza el cierre de sesion
	Then deberia ver el texto de cierre exitoso
	Examples:
	  | cuentaNexos    |
	  | 11100479303717 |

  Scenario Outline: Consultar cuenta ingresada por Admin y numero de tarjeta
	Given el usuario accede a la consulta de Cuentas
	When selecciona el filtro por administradora "<administradora>" y numero de tarjeta
	And ingresa el numero de tarjeta solicitado "<tarjeta>"
	Then realiza la busqueda de la consulta con exito

	Given el usuario espera unos momentos para cerrar sesion
	When realiza el cierre de sesion
	Then deberia ver el texto de cierre exitoso
	Examples:
	  | administradora | tarjeta          |
	  | 03-VISA        | 4508152004718407 |

  Scenario Outline: Consultar cuenta ingresada por Admin y numero de cuenta
	Given el usuario accede a la consulta de Cuentas
	When selecciona el filtro por administradora "<administradora>" y numero de cuenta
	And ingresa el numero de cuenta solicitado "<cuenta>"
	Then realiza la busqueda de la consulta con exito

	Given el usuario espera unos momentos para cerrar sesion
	When realiza el cierre de sesion
	Then deberia ver el texto de cierre exitoso
	Examples:
	  | administradora | cuenta   |
	  | 01-MASTERCARD  | 49544801 |

  Scenario Outline: Consultar cuenta ingresada por tipo de Clave interna
	Given el usuario accede a la consulta de Cuentas
	When selecciona el filtro por tipo "<tipo>" y numero de clave interna
	And ingresa la clave interna solicitada "<clave>"
	Then realiza la busqueda de la consulta con exito

	Given el usuario espera unos momentos para cerrar sesion
	When realiza el cierre de sesion
	Then deberia ver el texto de cierre exitoso
	Examples:
	  | tipo      | clave          |
	  | 4  - CUIC | 60000035324185 |

  Scenario Outline: Consultar cuenta ingresada por tipo de Documento
	Given el usuario accede a la consulta de Cuentas
	When selecciona el filtro por tipo "<tipo>" y numero de documento
	And ingresa el documento solicitado "<documento>"
	Then realiza la busqueda de la consulta con exito

	Given el usuario espera unos momentos para cerrar sesion
	When realiza el cierre de sesion
	Then deberia ver el texto de cierre exitoso
	Examples:
	  | tipo                 | documento |
	  | LC  - LIBRETA CIVICA | 11858730  |

  Scenario Outline: Consultar cuenta ingresada con filtro de Apellido y Nombre
	Given el usuario accede a la consulta de Cuentas
	When selecciona el filtro por Apellido y Nombre
	And ingresa el Apellido y Nombre "<ApellidoNombre>"
	Then realiza la busqueda de la consulta con exito

	Given el usuario espera unos momentos para cerrar sesion
	When realiza el cierre de sesion
	Then deberia ver el texto de cierre exitoso
	Examples:
	  | ApellidoNombre |
	  | Rojas Carlos   |
