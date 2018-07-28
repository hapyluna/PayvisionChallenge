package es.payvision.tests;

import static com.jayway.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;

import es.payvision.common.Constants;
import es.payvision.common.Currency;

/**
 * Tests using filter "currencyCode" 
 *
 */
public class TestCurrencyCode {
	/**
	 * GET with valid filter called "currencyCode" and valid value "EUR"
	 * Expected code 200
	 *
	 */
	@Test
	public void TestCurrencyCodeSuccessfull() {
	
	    Response response =
			given().
				queryParam(Constants.FILTER.CURRENCY, Currency.EUR).
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
	 * GET with valid filter called "currencyCode" but invalid value "PES"
	 * Expected code 400
	 *
	 */	
	@Test
	public void TestCurrencyCodeUnsuccessfull() {
	
	    Response response =
			given().
				queryParam(Constants.FILTER.CURRENCY, "PES").
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
	 * GET with valid filter called "currencyCode" and value "EUR" getting the first record seeking by id
	 * Expected code 200
	 *
	 */			
	@Test
	public void TestCurrencyCodeById() {
		int id = 0;
	    String response =
			given().
				queryParam(Constants.FILTER.CURRENCY, Currency.EUR).
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
	    System.out.println(String.format("id of first record which currency Code  is '%s': %s", Currency.EUR,response));
	  
			    }
					
	/**
	 * GET with valid filter called "currencyCode" and value "EUR" seeking by wrong id
	 * Expected code 200
	 *
	 */	
	@Test
	public void TestCurrencyCodeByWrongId() {
		int id = 999;
	    String response =
			given().
				queryParam(Constants.FILTER.CURRENCY, Currency.EUR).
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

