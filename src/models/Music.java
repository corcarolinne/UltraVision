package models;

import main.DBConnection;

public class Music extends Title {

	// properties
	protected String band;
	
	// constructor
	public Music(String titleName, int yearOfRelease, String format, int price, boolean isAvailable, DBConnection dbConnection) {
		super(titleName, yearOfRelease, format, price, isAvailable, dbConnection);
		this.band = band;
		
	}

}
