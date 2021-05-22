package StepDefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import models.qaMind.GetEmployeeResponseModel;
import models.qaMind.PostEmployeeRequestModel;

import static io.restassured.RestAssured.given;
import static models.qaMind.QaMindRestClient.status200Ok;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasProperty;

public class EmployeesSteps {


    RequestSpecification requestSpecification;
    GetEmployeeResponseModel getEmployeeResponse;


    /*GET REQUEST*/

    @Given("There are employees")
    public void thereAreEmployees() {
        requestSpecification = given();
    }

    @When("I fetch all employees")
    public void iFetchAllEmployees() {

        getEmployeeResponse = requestSpecification.when().get("http://dummy.restapiexample.com/api/v1/employees") .then().assertThat().spec(status200Ok()).extract().response().as(GetEmployeeResponseModel.class);
    }

    @Then("The employees are listed")
    public void theEmployeesAreListed() {

        assertThat(getEmployeeResponse.getStatus(), is(equalTo("success")));
        assertThat(getEmployeeResponse.getData(), hasItem(allOf(
                hasProperty("id", is(equalTo(1))),
                hasProperty("employee_name", is(equalTo("Tiger Nixon"))),
                hasProperty("employee_salary", is(equalTo(320800))),
                hasProperty("employee_age", is(equalTo(61)))
        )));
    }



}
