package es.payvision.tests;

import static com.jayway.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.jayway.restassured.response.Response;

import es.payvision.common.Constants;

/**
 * Test successful calling GET method with basic authentication
 *
 */
public class TestAuthorizationRequestSuccessful {
	/**
	 * GET with valid user and password
	 * Expected code 200
	 */
	@Test
	public void TestGetBasicAuthValidUserPassword() {
		
	    Response response =
	    		given().
            		auth().preemptive().
            		basic(Constants.AUTHENTICATION.USERNAME, Constants.AUTHENTICATION.PASSWORD).
            	when().
                    get(Constants.URI.ENDPOINT).
                then().
                    statusCode(200).
                    extract().response();
        Assert.assertEquals(response.getStatusCode(), Constants.RESPONSE_CODES.OK);
    }
}
