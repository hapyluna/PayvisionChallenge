# **REST API payvision challenge**

## Environment used

1. testng Version: 6.14.3
2. rest-assured Version: 2.9.0
3. json-schema-validator Version: 2.9.0
4. json-path Version 2.4.0
5. JVM Version: 1.8.0_161

## Technology used

* Choosen rest-assured DSL java framework for the challenge which allows test REST services without third party dependecies and with a large javadoc documentation

## Test classes

* TestAuthorizationRequestSuccessful	
* TestAuthorizationRequestUnsuccessful
* TestCurrencyCode
* TestFilterActionAndCurrency
* TestFilterCreditAction
* TestFilterOrderBy
* TestFilterPaymentAction

## Common classes

* Actions
* Constants
* Currency
* OrderBy

## Plan text file

* JsonOutput Used as reference about API response output syntax

## Test plan

* Get transaction collection with basic authorization
	- Method used:
		- TestGetBasicAuthValidUserPassword
* Get transaction collection without basic authorization
	- Methods used:
		- TestAuthorizationRequestUnsuccessful
		- TestGetBasicAuthWrongUser
		- TestGetBasicAuthWrongPass
* Get transaction collection with basic authorization and CurrencyCode filter applied
	- Methods used:
		- TestCurrencyCodeSuccessfull
		- TestCurrencyCodeUnsuccessfull
		- TestCurrencyCodeById
		- TestCurrencyCodeByWrongId
* Get transaction collection with basic authorization and two filters applied
	- Methods used:
		- TestFilterActionAndCurrencySuccessfull
		- TestFilterActionAndCurrencyUnsuccessfull
		- TestFilterActionAndCurrencyById
		- TestFilterActionAndCurrencyByWrongId
* Get transaction collection with basic authorization and filter action=credit applied	
	- Methods used:
		- TestFilterCreditSuccessfull
		- TestFilterCreditUnsuccessfull
		- TestFilterCreditById
		- TestFilterCreditByWrongId
* Get transaction collection with basic authorization and filter action=payment applied	
	- Methods used:
		- TestFilterpaymentSuccessfull
		- TestFilterpaymentUnsuccessfull
		- TestFilterpaymentById
		- TestFilterpaymentByWrongId	
		
* Get transaction collection with basic authorization and filter orderBy applied	
	- Methods used:
		- TestFilterOrderBySuccessfull
		- TestFilterOrderByUnsuccessfull
		- TestFilterOrderByDateComparedById
		- TestFilterOrderByComparison

##  Report with all BUGS found

	- I found a bug using TestFilterOrderBy method because orderBy filter does not work, no difference between 'date' and '-date'. It's available at ../es.payvision/test-output/index.html
	- Also can be genetated through testng.xml file and run all tests as testNG suite