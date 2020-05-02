package models;

import main.DBConnection;

public class Movie extends Title {
	
	// properties
	protected String genre;
	protected String director;
	
	// constructor
	public Movie(String titleName, int yearOfRelease, String format, int price, boolean isAvailable, String genre, String director, DBConnection dbConnection) {
		super(titleName, yearOfRelease, format, price, isAvailable, dbConnection);	
		this.genre = genre;
		this.director = director;
	}

	// other methods

}
