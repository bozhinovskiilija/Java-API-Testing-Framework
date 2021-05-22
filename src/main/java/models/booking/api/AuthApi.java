package models.booking.api;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.booking.payloads.Auth;

import static io.restassured.RestAssured.given;
import static models.booking.api.BaseApi.baseUrl;

public class AuthApi {
    private static final String apiUrl = baseUrl + "auth/";

    public static Response postAuth(Auth payload){
        return given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post(apiUrl);
    }
}
