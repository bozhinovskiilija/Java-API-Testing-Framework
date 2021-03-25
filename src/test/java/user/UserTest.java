package user;

import client.UserRestClient;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import models.*;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.CommonHelpers;

import static org.apache.http.HttpStatus.SC_NOT_FOUND;
import static org.apache.http.HttpStatus.SC_NO_CONTENT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.Assert.assertEquals;

public class UserTest {

    public UserRestClient userRestClient;
    public CommonHelpers commonHelpers;


    @BeforeClass
    public void setUp() {
        RestAssured.useRelaxedHTTPSValidation();
        userRestClient = new UserRestClient();
        commonHelpers = new CommonHelpers();
    }


    @Test
    public void canSearchUserWithFullName() {
        Users user = userRestClient.getUserById("2");
        assertEquals(user.getData().getFirst_name(), "Janet");
        assertThat(user.getData().getLast_name()).isEqualTo("Weaver");

    }

    @Test
    public void canNotSearchUserByInvalidId() {
        Response response = userRestClient.getUserByUUID("123456");
        assertThat(response.getStatusCode()).isEqualTo(SC_NOT_FOUND);
    }

    @Test
    public void getListOfUsers() {
        ListUsersGetResponseBody listUsersGetResponseBody = userRestClient.getListUsers("1");

        Assert.assertEquals(listUsersGetResponseBody.getPage(), "1");

        assertThat(listUsersGetResponseBody.getData()).hasSize(6)
                .extracting(Data::getFirst_name)
                .contains("George", "Janet", "Emma","Eve","Charles","Tracey");
    }

    @Test(dataProvider = "getData")
    public void createUser(String name, String job) {
        UserPostResponseBody userPostResponseBody = userRestClient.postUser(name, job);
        assertEquals(userPostResponseBody.getName(), name);

    }


    @Test
    public void shouldBeAbleToUpdateUser() {
        String updatedPosition = CommonHelpers.generateRandomString();

        String id = userRestClient.createUserAndReturnId("Jack", "PO");

        UserPutResponseBody body = userRestClient.updateUser1(id, new UserPutRequestBody()
                .withName("Jack")
                .withJob(updatedPosition));
        assertEquals(body.getJob(), updatedPosition);


    }

    @Test
    public void shouldBeAbleToDeleteUser() {
        String name = CommonHelpers.generateRandomString();
        String id = userRestClient.createUserAndReturnId(name, "Dev");

        System.out.println(id);
        Response response = userRestClient.deleteUser(id);
        assertEquals(response.getStatusCode(), SC_NO_CONTENT);
    }


    // TestNg data provider in order to create 3 users
    @DataProvider
    public Object[][] getData() {
        Object[][] data = new Object[3][2];

        data[0][0] = CommonHelpers.generateRandomString();
        data[0][1] = "dev";

        data[1][0] = CommonHelpers.generateRandomString();
        data[1][1] = "qa";

        data[2][0] = CommonHelpers.generateRandomString();
        data[2][1] = "PM";

        return data;


    }

}
