@pruebafuncional
Feature: Pruebas para el servicio usuario
  @metodoget @regresion
  Scenario: Listar todos los usuarios
    Given listar usuarios
    When mostrar el listado de usuarios
    And validar código de respuesta "200"
    Then validar cantidad de registro
  @metodoget
  Scenario: Mostrar Usuario
    Given listar usuario con id "3"
    When mostrar información del usuario
    Then validar información de la consulta
      | email               | nombre | apellido |
      | emma.wong@reqres.in | Emma   | Wong     |
  @metodoget
  Scenario: Usuario no registrado
    Given listar usuario con id "23"
    Then validar código de respuesta "404"
  @metodopost @regresion
  Scenario: Crear Usuario
    Given que no existe usuario registrado
    Then registrar datos del usuario
      | nombre    | puesto | codigo |
      | Alexander | QA     | 201    |
      | Pedro     | QA     | 201    |
      | Bryan     | QA     | 201    |
    Then validar código de respuesta "201"
    And mostar los datos del registro
  @metodoput @regresion
  Scenario: Actualizar usuario put
    Given que el usuario está registrado
    When actualizar datos del usuario
      | id  | nombre      | puesto | codigo |
      | 370 | Alex Emilio | Dev    | 200    |
    Then validar código de respuesta "200"
    And mostar los datos del registro
  @metodopatch
  Scenario: Actualizar usuario patch
    Given que el usuario está registrado
    When actualizar datos del usuario (patch)
      | id  | nombre | puesto | codigo |
      | 370 | Emilio   | Dev Android   | 200    |
    Then validar código de respuesta "200"
    And mostar los datos del registro