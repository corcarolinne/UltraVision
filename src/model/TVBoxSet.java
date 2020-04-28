package model;

public class TVBoxSet extends Title {

	// properties
	protected String genre;
	
	// constructor
	public TVBoxSet(String titleName, int yearOfRelease, String format, int price, boolean isAvailable) {
		super(titleName, yearOfRelease, format, price, isAvailable);
		this.genre = genre;
	}
	
	// other methods

}
