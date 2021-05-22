package StepDefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import models.qaMind.PostEmployeeRequestModel;

import static io.restassured.RestAssured.given;
import static models.qaMind.QaMindRestClient.status201Created;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class CreateEmployeesSteps {

    private RequestSpecification requestSpecification;
    private ValidatableResponse getEmployeeResponse;
    private PostEmployeeRequestModel postEmployeeRequestModel;
    
    @Given("The POST endpoint and the request payload")
    public void thePOSTEndpointAndTheRequestPayload() {

        requestSpecification = given();
        postEmployeeRequestModel = PostEmployeeRequestModel.builder()
                .age("55")
                .salary("400000")
                .name("Vlad")
                .build();
    }

    @When("I send a POST request for creating an employee")
    public void iSendAPOSTRequestForCreatingAnEmployee() {

        //getEmployeeResponse = requestSpecification
          //      .contentType(ContentType.JSON)
               // .body(objectToString(postEmployeeRequestModel))
                //.when().post(CREATE_EMPLOYEE)
               // .then().assertThat().spec(status201Created());
        
    }

    @Then("The employee is successfully created")
    public void theEmployeeIsSuccessfullyCreated() {
        getEmployeeResponse
                .body("status", is(equalTo("success")))
                .body("message", is(equalTo("Successfully! Record has been added.")))
                .body("data", is(notNullValue()));
    }
}
