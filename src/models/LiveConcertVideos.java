package models;

import main.DBConnection;

public class LiveConcertVideos extends Title {

	// properties
	protected String band;
	
	// constructor
	public LiveConcertVideos(String titleName, int yearOfRelease, String format, int price, boolean isAvailable, DBConnection dbConnection) {
		super(titleName, yearOfRelease, format, price, isAvailable, dbConnection);
		this.band = band;
	}

	// other methods
}
