package models;

public class Customer {
	
	// properties
	private String firstName;
	private String lastName;
	private String address;
	private String email;
	private String phoneNumber;
	private String cardNumber;
	private String membership;
	private int score;
	
	// constructor
	public Customer(String firstName, String lastName, String address, String email, String phoneNumber, String cardNumber, String membership, int score) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.cardNumber = cardNumber;
		this.membership = membership;
		this.score = score;
	}

	// other methods
}
