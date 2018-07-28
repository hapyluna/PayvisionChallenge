package es.payvision.tests;



import static com.jayway.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;

import es.payvision.common.Actions;
import es.payvision.common.Constants;
import es.payvision.common.OrderBy;

/**
 * Tests using filter "orderBy"  
 *
 */
public class TestFilterOrderBy {

	/**
	 * GET with valid filter "orderBy"
	 * Expected code 200
	 *
	 */   
	@Test
	public void TestFilterOrderBySuccessfull() {
	
	    Response response =
			given().
				queryParam(Constants.FILTER.ORDERBY, OrderBy.date1.getName()).
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
	 * GET with an invalid filter "orderBy" value "date1"
	 * Expected code 400
	 *
	 */		
	@Test
	public void TestFilterOrderByUnsuccessfull() {
	
	    Response response =
			given().
				queryParam(Constants.FILTER.ORDERBY, OrderBy.date1.getName() + "1").
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
	 * GET with two strings to compare filter "orderBy" with the values "date" and "-date"
	 * Expected assertion no equal true
	 *
	 */	
	@Test
	public void TestFilterOrderByDateComparedById() throws Exception{
		int id = 0;
		String responseDate1 =
			given().
				queryParam(Constants.FILTER.ORDERBY, OrderBy.date1.getName()).
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
		
		String responseDate2 =
    		given().
    			queryParam(Constants.FILTER.ORDERBY, OrderBy.date2.getName()).
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
			Assert.assertNotEquals(responseDate1, responseDate2);
	}
	/**
	 * GET with filter "orderBy" with the value "date" and filter "action" with value "payment"
	 * @return a response object with the filter applied
	 *
	 */				
	public Response TestFilterOrderByDate1() {

	    Response response =
	   		given().
				queryParam(Constants.FILTER.ORDERBY, Actions.payment).
				queryParam(Constants.FILTER.ORDERBY, OrderBy.date1.getName()).
           		auth().preemptive().
           		basic(Constants.AUTHENTICATION.USERNAME, Constants.AUTHENTICATION.PASSWORD).          
           	when().
                get(Constants.URI.ENDPOINT).
            then().
    			assertThat().
    				statusCode(Constants.RESPONSE_CODES.OK).
    				contentType(ContentType.JSON).
    				extract().response();
	    return response;
  	    }
	/**
	 * GET with filter "orderBy" with the value "-date" and filter "action" with value "payment"
	 * @return a response object with the filter applied
	 *
	 */	
	public Response TestFilterOrderByDate2() {
	    Response response =
    		given().
				queryParam(Constants.FILTER.ORDERBY, Actions.payment).
				queryParam(Constants.FILTER.ORDERBY, OrderBy.date2.getName()).
           		auth().preemptive().
           		basic(Constants.AUTHENTICATION.USERNAME, Constants.AUTHENTICATION.PASSWORD).          
           	when().
                get(Constants.URI.ENDPOINT).
            then().
   				assertThat().
   					statusCode(Constants.RESPONSE_CODES.OK).
   					contentType(ContentType.JSON).
   					extract().response();
	    return response;
	    }
	/**
	 * 
	 * Expected assertion no equal true
	 * 
	 */			
	@Test
	public void TestFilterOrderByCompararion() {
		Response responseDate1 = TestFilterOrderByDate1();
		Response responseDate2 = TestFilterOrderByDate2();
		Assert.assertNotEquals(responseDate1, responseDate2);
  	    }
}
