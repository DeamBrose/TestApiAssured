Feature: Pruebas para el servicio registrar cuenta de usuario
  Scenario: Registrando una cuenta de usuario
    Given que el usuario no tiene una cuenta de usuario
    Then registrar cuenta de usuario
      | email              | password    |
      | eve.holt@reqres.in | pistol      |
    And validar que el código de respuesta sea "200"
    And mostrar la respuesta del satisfactorio.

  Scenario: Validando registro erroneo
    Given que el usuario se registre con datos faltantes
    Then validamos el caso
      | email       |
      | sydney@fife |
    And validar que el código de respuesta sea "400"

