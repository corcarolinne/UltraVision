package interfaces;

import models.Title;

public interface TitleInterface {

	// method to display available titles
	String[][] showAvailableTitles(String searchInput, String selectedFilter);

	// method to display rented titles
	String[][] showRentedTitles(String searchInput, String selectedFilter);

	// method to create a title, receives a title object
	void createTitle(Title newTitle);

	// method to make the validation of a rent, receives a title object and returns a string with the type of message that should be displayed
	String validateRent(Title titleToRent, String customerEmail);

	// method to check score of a customer, if they are able to get a free rent or not, receives an email and returns the result in boolean
	boolean checkScore(String customerEmail);

	// method to rent a title, receives a title object and a string with customer's email entered on input
	void rentTitle(Title titleToRent, String customerEmail);

	// method to return a title, receives a title object
	void returnTitle(Title titleToRent);

}