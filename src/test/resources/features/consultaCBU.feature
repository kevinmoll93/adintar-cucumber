@consultaCBU
Feature: Consulta CBUAlias

  Scenario: Realiza una consulta de manera exitosa de la cuenta indicada
    Given el usuario esta en el menu principal e ingresa "CBU/Alias" la seccion de consultas CBUAlias
    When ingresa "0290018611000515714058" en la busqueda del CBUAlias
    Then deberia realizar la consulta con exito
