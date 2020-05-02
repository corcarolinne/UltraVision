package models;

import java.sql.ResultSet;

import controllers.DashboardController;
import main.DBConnection;

public class Customer {
	
	// properties
	private String firstName;
	private String lastName;
	private String address;
	private String email;
	private String phoneNumber;
	private String cardNumber;
	private Memberships membership;
	private int score;
	private DashboardController controller;
	
	// constructor
	public Customer(String firstName, String lastName, String address, String email, String phoneNumber, String cardNumber, Memberships membership, int score, DashboardController controller) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.cardNumber = cardNumber;
		this.membership = membership;
		this.score = score;
		this.controller = controller;
	}

	public Customer() {
		// empty constructor
	}

	// method to query database for customer data
	public String[][] showCustomers(String searchInput, String selectedFilter) {
	        
    	// declaring result 2d array with data
        String[][] customersData = null;
        
       try {
        	DBConnection dbConnection = new DBConnection();
            // building the queries
            String numOfRowsQuery = "SELECT * FROM ultravision.customers";
            String customersQuery = "SELECT customers.FirstName, customers.LastName, customers.Address, customers.Email, customers.Phone, customers.CardNumber, memberships.Type, customers.Score FROM ultravision.customers INNER JOIN ultravision.memberships ON customers.MembershipID = memberships.MembershipID;"; 
            
            // sending the query to the database       
            ResultSet resultNumOfRows = dbConnection.getStmt().executeQuery(numOfRowsQuery) ;
            
            int numOfRows = 0; 
            // while we have rows, or while next() returns true
            while(resultNumOfRows.next()) {
                // add one to rows
                numOfRows++;
            }
            
            ResultSet result = dbConnection.getStmt().executeQuery(customersQuery) ;
            // set titlesData number of rows and number of columns
            customersData= new String[numOfRows][8];
            
            int row = 0;
            // loop through result, while it's returns true (while there's lines in titles table)
            while(result.next()) {
                // set titlesData array to receive each value from each row, for each corresponding column
            	customersData[row][0] = result.getString("FirstName");
            	customersData[row][1] = result.getString("LastName");
            	customersData[row][2] = result.getString("Address");
            	customersData[row][3] = result.getString("Email");
            	customersData[row][4] = result.getString("Phone");
            	customersData[row][5] = result.getString("CardNumber");
            	customersData[row][6] = result.getString("Type");
            	customersData[row][7] = result.getString("Score");

               // increase row to populate the next row
                row++;
            }
            
           // close statement and connection
           dbConnection.getStmt().close();
           dbConnection.getConnection().close();
       } catch(Exception e) {
    	   System.out.println( e ) ;
       }
       return customersData;
   }
		
}

	
