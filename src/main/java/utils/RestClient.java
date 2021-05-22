package utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.restassured.filter.log.RequestLoggingFilter;
import com.jayway.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.EncoderConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.Filter;
import io.restassured.specification.RequestSpecification;

public class RestClient {

    private RestAssuredConfig mConfig = RestAssured.config().encoderConfig(EncoderConfig.encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false));
    private RequestSpecification requestSpecification;

    public RestClient() {

        RestAssured.useRelaxedHTTPSValidation();

        this.requestSpecification = new RequestSpecBuilder()
                .setBaseUri("https://reqres.in/api/") //https://reqres.in/
                //.addFilter((Filter) new ResponseLoggingFilter())//log request and response for better debugging. You can also only log if a requests fails.
                //.addFilter((Filter) new RequestLoggingFilter())
                .build();
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }

    public RequestSpecification requestSpec() {
        return RestAssured.given()
                .spec(requestSpecification)
                .config(mConfig);


    }

}
