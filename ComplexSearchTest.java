package org.example.Lesson3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ComplexSearchTest extends AbstractTest {


    @Test
    @DisplayName("Проверка, что статус кода равен:'200' ")
    void ComplexSerchStatusCodeTest() {
        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("query", "Pasta Margherita")
                .contentType("application/json")
                .when()
                .get(getBaseUrl() + "recipes/complexSearch")
                .then()
                .assertThat()
                .statusCode(200);
    }

    @Test
    @DisplayName("Проверка, что время ответа не меньше:'800' ")
    void timeToRespondeTest() {
        given()
                .contentType("application/json")
                .queryParam("apiKey", getApiKey())
                .queryParam("query", "Pasta Margherita")
                .when()
                .get(getBaseUrl() + "recipes/complexSearch")
                .then()
                .assertThat()
                .time(lessThan(800L));
    }

    @Test
    @DisplayName("Проверка, что значение 'title', содержит значение:'Pasta Margherita' ")
    void checkValueTitileTest() {
        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("query", "Pasta Margherita")
                .contentType("application/json")
                .expect()
                .when()
                .get(getBaseUrl() + "recipes/complexSearch")
                .then()
                .assertThat()
                .body("results[0].title", equalTo("Pasta Margherita"));
    }

    @Test
    @DisplayName("Проверка, что значение 'id', содержит значение:'511728' ")
    void checkValueIdTest() {
        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("query", "Pasta Margherita")
                .contentType("application/json")
                .expect()
                .when()
                .get(getBaseUrl() + "recipes/complexSearch")
                .then()
                .assertThat()
                .body("results[0].id", equalTo(511728));
    }

    @Test
    @DisplayName("Проверка, что значение 'image', содержит ссылку")
    void checkImageTest() {
        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("query", "Pasta Margherita")
                .contentType("application/json")
                .expect()
                .when()
                .get(getBaseUrl() + "recipes/complexSearch")
                .then()
                .assertThat()
                .body("results[0].image", containsString("https://spoonacular.com/recipeImages/511728-312x231.jpg"));
    }
}
