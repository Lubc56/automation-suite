@api @petstore
Feature: Gestión de Mascotas - Petstore API
  Como consumidor de la API de Petstore
  Quiero gestionar mascotas en el sistema
  Para validar las operaciones CRUD del servicio

  Scenario: Crear una nueva mascota
    Given el cliente API está configurado con la URL base de Petstore
    When el usuario crea una mascota con nombre "Firulais" y estado "available"
    Then la respuesta debería tener el código de estado 200
    And la respuesta debería contener el nombre "Firulais"
    And la respuesta debería contener el estado "available"

  Scenario: Buscar mascota por ID
    Given el cliente API está configurado con la URL base de Petstore
    And existe una mascota registrada con nombre "Rex" y estado "available"
    When el usuario busca la mascota por su ID
    Then la respuesta debería tener el código de estado 200
    And la respuesta debería contener el nombre "Rex"

  Scenario: Buscar mascotas por estado disponible
    Given el cliente API está configurado con la URL base de Petstore
    When el usuario busca mascotas con estado "available"
    Then la respuesta debería tener el código de estado 200
    And la lista de mascotas no debería estar vacía

  Scenario: Actualizar una mascota existente
    Given el cliente API está configurado con la URL base de Petstore
    And existe una mascota registrada con nombre "Bobby" y estado "available"
    When el usuario actualiza el estado de la mascota a "sold"
    Then la respuesta debería tener el código de estado 200
    And la respuesta debería contener el estado "sold"

  Scenario: Eliminar una mascota
    Given el cliente API está configurado con la URL base de Petstore
    And existe una mascota registrada con nombre "ToDelete" y estado "available"
    When el usuario elimina la mascota por su ID
    Then la respuesta debería tener el código de estado 200
