Feature: Inicios de sesion
  As un usuario registrado del sistema
  necesito validar las operaciones de logueo y disponibilidad del sitio web
  para poder tener seguridad en el perfil de los usuarios

  Scenario: login exitoso
    Given el usuario esta en la pagina de inicio de sesion con el correo de ususario "eve.holt@reqres.in" y la contraseña "cityslicka"
    When cuando el usuario hace una peticion de inicio
    Then el usuario debera ver un codigo de respuesta exitoso y un token de respuesta