Feature: Pruebas para el servicio Unknown
  Scenario: Listar todos los Unknown
    Given listar Unknown esté operativo
    Then mostrar la lista de Unknown
    And validar el código de estado "200"

  Scenario: Mostrar un solo registro del servicio Unknown
    Given mostrando unknown con el id "2"
    Then validar el código de estado "200"
    And mostrar el registro
    Then validamos información de la consulta
      | nombre       | anio | color   | pantone |
      | fuchsia rose | 2001 | #C74375 | 17-2031 |

  Scenario: Unknown no registrado
    Given mostrando unknown con el id "23"
    Then validar el código de estado "404"
