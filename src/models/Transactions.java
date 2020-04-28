package models;

import java.util.Date;

public class Transactions {
	
	// properties
	private Date dateRented;
	private Date dateReturned;

	// constructor
	public Transactions(Date dateRented, Date dateReturned) {
		this.dateRented = dateRented;
		this.dateReturned = dateReturned;
		
	}
}
