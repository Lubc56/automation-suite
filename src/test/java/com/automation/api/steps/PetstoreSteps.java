package com.automation.api.steps;

import com.automation.api.model.Pet;
import com.automation.utils.ApiConfig;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import org.assertj.core.api.Assertions;

import java.util.List;

public class PetstoreSteps {

    private Response response;
    private Long petId;

    @Given("el cliente API está configurado con la URL base de Petstore")
    public void elClienteAPIEstaConfigurado() {
        ApiConfig.configureRestAssured();
    }

    @When("el usuario crea una mascota con nombre {string} y estado {string}")
    public void elUsuarioCreaUnaMascota(String name, String status) {
        Pet pet = buildPet(name, status);

        response = SerenityRest.given()
                .header("Content-Type", "application/json")
                .body(pet)
                .when()
                .post("/pet");

        if (response.statusCode() == 200) {
            petId = response.jsonPath().getLong("id");
        }
    }

    @And("existe una mascota registrada con nombre {string} y estado {string}")
    public void existeUnaMascotaRegistrada(String name, String status) {
        Pet pet = buildPet(name, status);

        Response createResponse = SerenityRest.given()
                .header("Content-Type", "application/json")
                .body(pet)
                .when()
                .post("/pet");

        Assertions.assertThat(createResponse.statusCode())
                .as("La mascota debería crearse exitosamente")
                .isEqualTo(200);

        petId = createResponse.jsonPath().getLong("id");
    }

    @When("el usuario busca la mascota por su ID")
    public void elUsuarioBuscaLaMascotaPorSuID() {
        response = SerenityRest.given()
                .when()
                .get("/pet/{petId}", petId);
    }

    @When("el usuario busca mascotas con estado {string}")
    public void elUsuarioBuscaMascotasPorEstado(String status) {
        response = SerenityRest.given()
                .queryParam("status", status)
                .when()
                .get("/pet/findByStatus");
    }

    @When("el usuario actualiza el estado de la mascota a {string}")
    public void elUsuarioActualizaElEstadoDeLaMascota(String newStatus) {
        Pet updatedPet = Pet.builder()
                .id(petId)
                .name("Updated Pet")
                .status(newStatus)
                .photoUrls(List.of("https://example.com/photo.jpg"))
                .build();

        response = SerenityRest.given()
                .header("Content-Type", "application/json")
                .body(updatedPet)
                .when()
                .put("/pet");
    }

    @When("el usuario elimina la mascota por su ID")
    public void elUsuarioEliminaLaMascota() {
        response = SerenityRest.given()
                .header("api_key", "special-key")
                .when()
                .delete("/pet/{petId}", petId);
    }

    @Then("la respuesta debería tener el código de estado {int}")
    public void laRespuestaDeberiaTenerElCodigoDeEstado(int expectedStatus) {
        Assertions.assertThat(response.statusCode())
                .as("El código de estado HTTP debería ser " + expectedStatus)
                .isEqualTo(expectedStatus);
    }

    @And("la respuesta debería contener el nombre {string}")
    public void laRespuestaDeberiaContenerElNombre(String expectedName) {
        String actualName = response.jsonPath().getString("name");
        Assertions.assertThat(actualName)
                .as("El nombre de la mascota debería ser: " + expectedName)
                .isEqualTo(expectedName);
    }

    @And("la respuesta debería contener el estado {string}")
    public void laRespuestaDeberiaContenerElEstado(String expectedStatus) {
        String actualStatus = response.jsonPath().getString("status");
        Assertions.assertThat(actualStatus)
                .as("El estado de la mascota debería ser: " + expectedStatus)
                .isEqualTo(expectedStatus);
    }

    @And("la lista de mascotas no debería estar vacía")
    public void laListaDeMascotasNoDeberiaEstarVacia() {
        List<?> pets = response.jsonPath().getList("$");
        Assertions.assertThat(pets)
                .as("La lista de mascotas no debería estar vacía")
                .isNotEmpty();
    }

    private Pet buildPet(String name, String status) {
        return Pet.builder()
                .name(name)
                .status(status)
                .photoUrls(List.of("https://example.com/photo.jpg"))
                .category(Pet.Category.builder()
                        .id(1L)
                        .name("Dogs")
                        .build())
                .tags(List.of(Pet.Tag.builder()
                        .id(1L)
                        .name("friendly")
                        .build()))
                .build();
    }
}
