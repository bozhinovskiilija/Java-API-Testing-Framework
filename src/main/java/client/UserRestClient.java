package client;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import utils.RestClient;
import models.*;

import static org.apache.http.HttpStatus.SC_CREATED;
import static org.apache.http.HttpStatus.SC_OK;

public class UserRestClient extends RestClient {

    public Response getUserByUUID(String uuid) {
        return requestSpec()
                .contentType(ContentType.JSON)
                .get("/users/" + uuid);
    }

    public Users getUserById(String userId) {
        return getUserByUUID(userId)
                .then()
                .statusCode(SC_OK)
                .and()
                .extract()
                .body()
                .as(Users.class);
    }

    public Response getListOfUsers(String page) {
        return requestSpec()
                .contentType(ContentType.JSON)
                .get("users?page=" + page);
    }

    public ListUsersGetResponseBody getListUsers(String page) {
        return getListOfUsers(page)
                .then()
                .statusCode(SC_OK)
                .and()
                .extract()
                .body()
                .as(ListUsersGetResponseBody.class);
    }

    public Response createUser(String name, String job) {
        return requestSpec()
                .contentType(ContentType.JSON)
                .body(new UserPostRequestBody()
                        .withName(name)
                        .withJob(job))
                .post("/users");
    }

    public UserPostResponseBody postUser(String name, String job) {
        return createUser(name, job)
                .then()
                .statusCode(SC_CREATED)
                .and()
                .extract()
                .body()
                .as(UserPostResponseBody.class);
    }

    public String createUserAndReturnId(String name, String job) {
        return createUser(name, job)
                .then()
                .statusCode(SC_CREATED)
                .and()
                .extract()
                .path("id");
    }

    public Response updateUser(String userId, UserPutRequestBody body) {
        return requestSpec()
                .contentType(ContentType.JSON)
                .body(body)
                .put("/users/" + userId);
    }

    public UserPutResponseBody updateUser1(String userId, UserPutRequestBody body) {
        return updateUser(userId, body)
                .then()
                .statusCode(SC_OK)
                .and()
                .extract()
                .as(UserPutResponseBody.class);

    }

    public Response deleteUser(String userId) {
        return requestSpec()
                .when()
                .delete("/users/" + userId);
    }
}
