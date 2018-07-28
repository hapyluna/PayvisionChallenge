package es.payvision.common;
/**
 * Possible orderBy filter values
 * 
 */
public enum OrderBy {
	date1 ("date"),
	date2 ("-date");
	
    private final String name;       

    private OrderBy(String s) {
        name = s;
    }

	public String getName() {
		return name;
	}
}
