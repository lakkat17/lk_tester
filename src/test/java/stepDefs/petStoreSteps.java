package stepDefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;

import java.util.List;
import java.util.Map;

public class petStoreSteps {
    private static final String BASE_URI = "https://petstore.swagger.io";
    private static Response response;
    List<Map<String, String>> responses;
    private static RequestSpecification request;
    private static String endpoint;

    @Given("^endpoint (.*)$")
    public void url(String exp) {
        endpoint = StringUtils.trimToEmpty(exp);
        RestAssured.baseURI = BASE_URI;
        request = RestAssured.given();
    }

    @When("^method (\\w+)$")
    public void method(String method) {
        switch (method) {
            case "get":
                response = request.get(endpoint);
                break;
            case "put":
                response = request.put(endpoint);
                break;
            case "post":
                response = request.post(endpoint);
                break;
            case "delete":
                response = request.delete(endpoint);
                break;
        }
    }

    @Then("^status (\\d+)$")
    public void status(int status) {
        Assert.assertEquals(status, response.getStatusCode());
    }

    @And("^assert (\\w+)$")
    public void assertEquals(String exp) {
        responses = JsonPath.from(response.asString()).get();
        if (exp.equals("response"))
            Assert.assertTrue(responses.size() > 0);
    }

    @When("^header ([^\\s]+) = (.+)$")
    public void header(String name, String value) {
        request.header(name, value);
    }

    @When("^request (.+)$")
    public void request(String requestBody) {
        request.body(requestBody);
    }

    @Then("^match ([^\\s]+) = (.+)$")
    public void matchEquals(String name, String expected) {
        String tag = name.substring(name.lastIndexOf(".")+1).trim();
        String val = JsonPath.from(response.asString()).get(tag).toString();
        Assert.assertEquals(val, expected);
    }

}
