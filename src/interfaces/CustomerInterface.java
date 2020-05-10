package interfaces;

import models.Customer;

public interface CustomerInterface {

	// method to query database for customer data, receives 2 strings corresponding to the search and returns an array with the data
	String[][] showCustomers(String searchInput, String selectedFilter);

	// method to create a customer, receives a customer object
	void createCustomer(Customer newCustomer);

	// method to update customer details, receives 2 instances of Customer object
	void updateCustomer(Customer customerToUpdate, Customer customerSelected);

	// method to validate customer email in update, it receives a string with the email input, returns a boolean with the result of validation
	boolean validateEmail(String email, int iDAsInt);

	// method to validate customer email in create customer, it receives a string with the email input, returns a boolean with the result of validation
	boolean validateCreateEmail(String email);

}