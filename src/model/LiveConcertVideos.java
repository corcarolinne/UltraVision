package model;

public class LiveConcertVideos extends Title {

	// properties
	protected String band;
	
	// constructor
	public LiveConcertVideos(String titleName, int yearOfRelease, String format, int price, boolean isAvailable) {
		super(titleName, yearOfRelease, format, price, isAvailable);
		this.band = band;
	}

	// other methods
}
