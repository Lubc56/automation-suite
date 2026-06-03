@ui @login
Feature: Login en SauceDemo
  Como usuario registrado
  Quiero iniciar sesión en la plataforma
  Para acceder al catálogo de productos

  Background:
    Given el usuario navega a la página de login de SauceDemo

  Scenario: Login exitoso con credenciales válidas
    When el usuario ingresa el usuario "standard_user" y la contraseña "secret_sauce"
    And el usuario hace clic en el botón de login
    Then el usuario debería ver el inventario de productos

  Scenario: Login fallido con credenciales inválidas
    When el usuario ingresa el usuario "invalid_user" y la contraseña "wrong_pass"
    And el usuario hace clic en el botón de login
    Then el usuario debería ver un mensaje de error de credenciales
