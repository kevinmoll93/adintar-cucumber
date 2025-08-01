@ConsultaCuenta
Feature: Permite realizar una consulta de cuenta con diferentes filtros

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
