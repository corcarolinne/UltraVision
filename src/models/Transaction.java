package models;

import java.util.Date;

public class Transaction {
	
	// properties
	private Date dateRented;
	private Date dateReturned;

	// constructor
	public Transaction(Date dateRented, Date dateReturned) {
		this.dateRented = dateRented;
		this.dateReturned = dateReturned;
		
	}
}
