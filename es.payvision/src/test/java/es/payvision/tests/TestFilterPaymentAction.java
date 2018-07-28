package es.payvision.tests;

import static com.jayway.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;

import es.payvision.common.Actions;
import es.payvision.common.Constants;

	/**
	 * Tests using filter action and value "payment" 
	 *
	 */
	public class TestFilterPaymentAction {

	/**
	 * GET with valid filter "action" and value "payment"
	 * Expected code 200
	 *
	 */
	@Test
	public void TestFilterPaymentSuccessfull() {

	    Response response =
	   		given().
	   			queryParam(Constants.FILTER.ACTION, Actions.payment).
           		auth().preemptive().
           		basic(Constants.AUTHENTICATION.USERNAME, Constants.AUTHENTICATION.PASSWORD).          
           	when().
                   get(Constants.URI.ENDPOINT).
               then().
               	assertThat().
                   	statusCode(Constants.RESPONSE_CODES.OK).
                   	contentType(ContentType.JSON).
                   	extract().response();
	       Assert.assertEquals(response.getStatusCode(), Constants.RESPONSE_CODES.OK);
	    }
				
	/**
	 * GET with an invalid filter value "payments"
	 * Expected code 400
	 *
	 */	
	@Test
	public void TestFilterPaymentUnsuccessfull() {

	    Response response =
	   		given().
	   		queryParam(Constants.FILTER.ACTION, Actions.payment + "s").
           		auth().preemptive().
           		basic(Constants.AUTHENTICATION.USERNAME, Constants.AUTHENTICATION.PASSWORD).          
           	when().
                get(Constants.URI.ENDPOINT).
            then().
	           	assertThat().
	            	statusCode(Constants.RESPONSE_CODES.INVALID_CREDENTIALS).
	            	contentType(ContentType.JSON).
	            	extract().response();
		    	
	    Assert.assertEquals(response.asString(), Constants.RESPONSE_MESSAGES.INVALID_FILTER);
	}
				
	/**
	 * GET with valid filter "action" and value "payment" getting the first record seeking by id
	 * Expected code 200
	 *
	 */	
	@Test
	public void TestFilterPaymentById() {
		int id = 0;
	    String response =
	   		given().
	   			queryParam(Constants.FILTER.ACTION, Actions.payment).
           		auth().preemptive().
           		basic(Constants.AUTHENTICATION.USERNAME, Constants.AUTHENTICATION.PASSWORD).          
           	when().
                get(Constants.URI.ENDPOINT).
            then().
       			assertThat().
           			statusCode(Constants.RESPONSE_CODES.OK).
           			contentType(ContentType.JSON).
           			extract().
           			path(String.format("[%s].id", id));               
	       System.out.println(String.format("id of first record which used '%s' as pay method is: %s", Actions.credit,response));
  	    }
				
	/**
	 * GET with valid filter "action" and value "payment" getting the first record seeking by wrong id
	 * Expected code 200
	 *
	 */	
	@Test
	public void TestFilterPaymentByWrongId() {
	int id = 999;
	String response =
	  		given().
	   		queryParam(Constants.FILTER.ACTION, Actions.payment).
           		auth().preemptive().
           		basic(Constants.AUTHENTICATION.USERNAME, Constants.AUTHENTICATION.PASSWORD).          
           	when().
                get(Constants.URI.ENDPOINT).
            then().
    			assertThat().
    				statusCode(Constants.RESPONSE_CODES.OK).
    				contentType(ContentType.JSON).
    				extract().
    				path(String.format("[%s]", id));
	    System.out.println(String.format("Response for invalid id '%s'", response));
  	    }
}
