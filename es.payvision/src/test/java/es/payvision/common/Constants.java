package es.payvision.common;
/**
 * Contains all constants used in the test plan
 *
 */
public class Constants {

	public static class URI {
		public static final String ENDPOINT = "https://jovs5zmau3.execute-api.eu-west-1.amazonaws.com/prod/transactions";
	}
	
	public static class AUTHENTICATION {
		public static final String USERNAME = "code-challenge";
		public static final String PASSWORD = "payvisioner";
	}
	public static class FILTER {
		public static final String ACTION = "action";
		public static final String CURRENCY = "currencyCode";
		public static final String ORDERBY = "orderBy";
	}
	public static class RESPONSE_CODES {
		public static final int OK = 200;
		public static final int INVALID_CREDENTIALS = 400;
		public static final int UNAUTHORIZED = 401;
		
	}
	public static class RESPONSE_MESSAGES{
		public static final String INVALID_FILTER = "{\"message\":\"INVALID_FILTER\"}";
	}
}
