package es.payvision.tests;

import static com.jayway.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.jayway.restassured.response.Response;

import es.payvision.common.Constants;
/**
 * Tests unsuccessful calling GET method
 *
 */
public class TestAuthorizationRequestUnsuccessful {
	/**
	 * GET with no authentication method given
	 * Expected code 401
	 *
	 */
	@Test
	public void TestGetNoAuth() {

		Response response = 
                given().
                		auth().
                		none().
                when().
                        get(Constants.URI.ENDPOINT).
                then().
                        statusCode(401).
                        extract().response();
        Assert.assertEquals(response.getStatusCode(), Constants.RESPONSE_CODES.UNAUTHORIZED);
    }
	
	/**
	 * GET with basic authentication but wrong username
	 * Expected code 400
	 */
	@Test
	public void TestGetBasicAuthWrongUser() {
		
	    Response response =
	    		given().
            		auth().preemptive().
            		basic(Constants.AUTHENTICATION.USERNAME.substring(1), Constants.AUTHENTICATION.PASSWORD).
            	when().
                    get(Constants.URI.ENDPOINT).
                then().
                    statusCode(400).
                    extract().response();
        Assert.assertEquals(response.getStatusCode(), Constants.RESPONSE_CODES.INVALID_CREDENTIALS);
    }
	/**
	 * GET with basic authentication but wrong password
	 * Expected code 400
	 */
	@Test
	public void TestGetBasicAuthWrongPass() {
		
	    Response response =
	    		given().
            		auth().preemptive().
            		basic(Constants.AUTHENTICATION.USERNAME, Constants.AUTHENTICATION.PASSWORD.substring(1)).
            	when().
                    get(Constants.URI.ENDPOINT).
                then().
                    statusCode(400).
                    extract().response();
        Assert.assertEquals(response.getStatusCode(), Constants.RESPONSE_CODES.INVALID_CREDENTIALS);
    }
}
