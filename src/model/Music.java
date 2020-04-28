package model;

public class Music extends Title {

	// properties
	protected String band;
	
	// constructor
	public Music(String titleName, int yearOfRelease, String format, int price, boolean isAvailable) {
		super(titleName, yearOfRelease, format, price, isAvailable);
		this.band = band;
		
	}

}
