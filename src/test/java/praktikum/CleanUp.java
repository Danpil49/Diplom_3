package praktikum;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class CleanUp {
    private static final String userDeleteAPI = "/api/auth/user";
    private static final String userLoginAPI = "/api/auth/login";
    private static final String apiBaseURI = "https://stellarburgers.nomoreparties.site";

    @Step("Получение токена пользователя")
    private static String getToken(String email, String password) {
        return given()
                .header("Content-type", "application/json")
                .body("{\"email\": \"" + email + "\", \"password\": \"" + password + "\"}")
                .post(userLoginAPI)
                .then()
                .extract()
                .body()
                .path("accessToken")
                .toString().replaceAll("Bearer ", "");
    }

    @Step("Удаление пользователя")
    public static Response delete(String email, String password) {
        RestAssured.baseURI = apiBaseURI;
        String bearerToken = getToken(email, password);
        return given()
                .header("Content-type", "application/json")
                .auth().oauth2(bearerToken)
                .delete(userDeleteAPI);
    }
}
