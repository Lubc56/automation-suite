@ui @product
Feature: Selección de producto en SauceDemo
  Como usuario autenticado
  Quiero seleccionar productos del catálogo
  Para agregarlos al carrito de compras

  Background:
    Given el usuario está autenticado en SauceDemo

  Scenario: Agregar producto al carrito
    When el usuario selecciona el producto "Sauce Labs Backpack"
    And el usuario hace clic en "Add to cart"
    Then el contador del carrito debería mostrar "1"

  Scenario: Ver detalle de un producto
    When el usuario hace clic en el producto "Sauce Labs Bike Light"
    Then el usuario debería ver el detalle del producto con el nombre "Sauce Labs Bike Light"
    And el usuario debería ver el precio del producto

  Scenario Outline: Agregar múltiples productos al carrito
    When el usuario selecciona el producto "<producto>"
    And el usuario hace clic en "Add to cart"
    Then el contador del carrito debería mostrar "<cantidad>"

    Examples:
      | producto                 | cantidad |
      | Sauce Labs Fleece Jacket | 1        |
      | Sauce Labs Onesie        | 1        |
