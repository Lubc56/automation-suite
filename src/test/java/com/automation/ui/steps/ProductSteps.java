package com.automation.ui.steps;

import com.automation.ui.pages.InventoryPage;
import com.automation.ui.pages.LoginPage;
import com.automation.ui.pages.ProductDetailPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.assertj.core.api.Assertions;

public class ProductSteps {

    // Page Objects: inyección automática por Serenity (sin @Steps)
    private LoginPage loginPage;

    private InventoryPage inventoryPage;

    private ProductDetailPage productDetailPage;

    @Given("el usuario está autenticado en SauceDemo")
    public void elUsuarioEstaAutenticadoEnSauceDemo() {
        loginPage.open();
        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.clickLogin();
        Assertions.assertThat(inventoryPage.isInventoryDisplayed())
                .as("El usuario debería estar autenticado y ver el inventario")
                .isTrue();
    }

    @When("el usuario selecciona el producto {string}")
    public void elUsuarioSeleccionaElProducto(String productName) {
        inventoryPage.clickAddToCartForProduct(productName);
    }

    @And("el usuario hace clic en {string}")
    public void elUsuarioHaceClicEn(String buttonLabel) {
        // El botón ya fue presionado en el paso anterior para "Add to cart"
        // Este paso es un alias semántico para claridad en el feature
    }

    @Then("el contador del carrito debería mostrar {string}")
    public void elContadorDelCarritoDeberiaMostrar(String expectedCount) {
        Assertions.assertThat(inventoryPage.isCartBadgeVisible())
                .as("El badge del carrito debería ser visible")
                .isTrue();
        Assertions.assertThat(inventoryPage.getCartCount())
                .as("El contador del carrito debería ser " + expectedCount)
                .isEqualTo(expectedCount);
    }

    @When("el usuario hace clic en el producto {string}")
    public void elUsuarioHaceClicEnElProducto(String productName) {
        inventoryPage.clickOnProduct(productName);
    }

    @Then("el usuario debería ver el detalle del producto con el nombre {string}")
    public void elUsuarioDeberiaVerElDetalleDelProducto(String expectedName) {
        Assertions.assertThat(productDetailPage.getProductName())
                .as("El nombre del producto en el detalle debería ser: " + expectedName)
                .isEqualTo(expectedName);
    }

    @And("el usuario debería ver el precio del producto")
    public void elUsuarioDeberiaVerElPrecioDelProducto() {
        Assertions.assertThat(productDetailPage.isPriceDisplayed())
                .as("El precio del producto debería estar visible")
                .isTrue();
    }
}
