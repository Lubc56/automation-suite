package com.automation.ui.steps;

import com.automation.ui.pages.InventoryPage;
import com.automation.ui.pages.LoginPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.annotations.DefaultUrl;
import net.serenitybdd.annotations.Steps;
import org.assertj.core.api.Assertions;

public class LoginSteps {

    @Steps
    private LoginPage loginPage;

    @Steps
    private InventoryPage inventoryPage;

    @Given("el usuario navega a la página de login de SauceDemo")
    public void elUsuarioNavegaALaPaginaDeLogin() {
        loginPage.open();
    }

    @When("el usuario ingresa el usuario {string} y la contraseña {string}")
    public void elUsuarioIngresaCredenciales(String username, String password) {
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
    }

    @And("el usuario hace clic en el botón de login")
    public void elUsuarioHaceClicEnElBotonDeLogin() {
        loginPage.clickLogin();
    }

    @Then("el usuario debería ver el inventario de productos")
    public void elUsuarioDeberiaVerElInventarioDeProductos() {
        Assertions.assertThat(inventoryPage.isInventoryDisplayed())
                .as("El inventario de productos debería estar visible")
                .isTrue();
    }

    @Then("el usuario debería ver un mensaje de error de credenciales")
    public void elUsuarioDeberiaVerUnMensajeDeError() {
        Assertions.assertThat(loginPage.isErrorMessageDisplayed())
                .as("Debería mostrarse un mensaje de error")
                .isTrue();
        Assertions.assertThat(loginPage.getErrorMessage())
                .contains("Username and password do not match");
    }
}
