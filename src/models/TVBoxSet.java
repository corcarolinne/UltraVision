package models;

import main.DBConnection;

public class TVBoxSet extends Title {

	// properties
	protected String genre;
	
	// constructor
	public TVBoxSet(String titleName, int yearOfRelease, String format, int price, boolean isAvailable, DBConnection dbConnection) {
		super(titleName, yearOfRelease, format, price, isAvailable, dbConnection);
		this.genre = genre;
	}
	
	// other methods

}
