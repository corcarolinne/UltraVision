package models;

public class Movie extends Title {
	
	// properties
	protected String genre;
	protected String director;
	
	// constructor
	public Movie(String titleName, int yearOfRelease, String format, int price, boolean isAvailable, String genre, String director) {
		super(titleName, yearOfRelease, format, price, isAvailable);	
		this.genre = genre;
		this.director = director;
	}

	// other methods

}
