Feature: Actualizar un usuario
  As un usuario registrado en el sistema
  necesito actualizar mis credenciales
  para ajustarlas al entorno actual


  Scenario: Actualizacion Exitosa
    Given Que el usuario se encuentra en la pagina de actualizacion de datos y desea cambiar su nombre a "morpheus" y su trabajo a "zion resident"
    When el usuario realiza la peticion de actualizacion
    Then El usuario recibe un codigo de respuesta exitoso y una estructura con sus datos de usuario