package models;

import java.sql.ResultSet;
import java.sql.SQLException;

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
	private int membership;
	private int score;
	private DashboardController controller;
	
	// constructor
	public Customer(String firstName, String lastName, String address, String email, String phoneNumber, String cardNumber, int membership, int score, DashboardController controller) {
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

	public Customer(String firstName, String lastName, String address, String email, String phoneNumber, String cardNumber, int membership, DashboardController controller) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.cardNumber = cardNumber;
		this.membership = membership;
		this.controller = controller;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public String getAddress() {
		return this.address;
	}

	public String getEmail() {
		return this.email;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public String getCardNumber() {
		return this.cardNumber;
	}

	public int getScore() {
		return this.score;
	}

	public DashboardController getController() {
		return this.controller;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public void setController(DashboardController controller) {
		this.controller = controller;
	}
	
	// method to get membership from controller
	public int getMembership() {
		return this.controller.getMembershipID();
	}

	// method to query database for customer data
	public String[][] showCustomers(String searchInput, String selectedFilter) {
	        
    	// declaring result 2d array with data
        String[][] customersData = null;
        
       try {
        	DBConnection dbConnection = new DBConnection();
        	if(searchInput.isEmpty()) {
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
        	} else {
        		// here lays the logic for when we have a search input
        		// if the filter selected is Customers
                if(selectedFilter == "Customers") {
                
                	// variable to save customer ID
                	String customerID = "";
                	
                    // query the titles table looking for any customer information that matches user input
                    String checkSize = "SELECT * FROM ultravision.customers WHERE FirstName LIKE '%"+searchInput+"%' OR LastName LIKE '%"+searchInput+"%' OR Address LIKE '%"+searchInput+"%' OR Email LIKE '%"+searchInput+"%' OR Phone LIKE '%"+searchInput+"%' OR CardNumber LIKE '%"+searchInput+"%';";
                 
                    int counter = 0;      
                    // sending the query to the database
                    ResultSet resultToCheckSize = dbConnection.getStmt().executeQuery(checkSize);
                    int numOfRows = 0;
                    
                    // while we have rows, or while next() returns true
                    while(resultToCheckSize.next()) {
                        // save the ID of the customer, add one to the rows size
                    	customerID = resultToCheckSize.getString("CustomerID"); 
                        counter++;
                        numOfRows++;
                    }
               
                    // query customer table with the ID of the customer found
                    String searchedData = "SELECT customers.FirstName, customers.LastName, customers.Address, customers.Email, customers.Phone, customers.CardNumber, memberships.Type, customers.Score FROM ultravision.customers INNER JOIN ultravision.memberships ON customers.MembershipID = memberships.MembershipID WHERE customers.CustomerID='"+customerID+"' ORDER BY customers.CustomerID";
                    
                   // sending the query to the database
                   ResultSet searchCustomers = dbConnection.getStmt().executeQuery(searchedData);
                   
                   // set artData number of rows and number of columns
                   customersData= new String[numOfRows][8];
                   
                    int row = 0;
                    // while there's a result 
                    while(searchCustomers.next()) {
                        
                        // set artData array to receive each value from each row, for each corresponding column
                    	customersData[row][0] = searchCustomers.getString("FirstName");
                    	customersData[row][1] = searchCustomers.getString("LastName");
                    	customersData[row][2] = searchCustomers.getString("Address");
                    	customersData[row][3] = searchCustomers.getString("Email");
                    	customersData[row][4] = searchCustomers.getString("Phone");
                    	customersData[row][5] = searchCustomers.getString("CardNumber");
                    	customersData[row][6] = searchCustomers.getString("Type");
                    	customersData[row][7] = searchCustomers.getString("Score");
                    	
                    	row++;
                    }   
                }
        	}
           // close statement and connection
           dbConnection.getStmt().close();
           dbConnection.getConnection().close();
       } catch(Exception e) {
    	   System.out.println( e ) ;
       }
       return customersData;
   }
	
	// method to create a customer, receives a customer object
    public void createCustomer(Customer newCustomer){
        try{
        	DBConnection dbConnection = new DBConnection();
            
            // building the query
            String query = "INSERT INTO ultravision.customers (FirstName, LastName, Address, Email, Phone, CardNumber, MembershipID) VALUES ('"+newCustomer.getFirstName()+"','"+newCustomer.getLastName()+"','"+newCustomer.getAddress()+"','"+newCustomer.getEmail()+"','"+newCustomer.getPhoneNumber()+"', '"+newCustomer.getCardNumber()+"', '"+newCustomer.getMembership()+"');";
            
            // execute query
	        dbConnection.getStmt().execute(query);

            // closing statement and connections
            dbConnection.getStmt().close();
            dbConnection.getConnection().close();
            
        } catch( SQLException se ){
            System.out.println( "SQL Exception:" ) ;

            // Loop through the SQL Exceptions
            while( se != null ){
                System.out.println( "State  : " + se.getSQLState()  ) ;
                System.out.println( "Message: " + se.getMessage()   ) ;
                System.out.println( "Error  : " + se.getErrorCode() ) ;

                se = se.getNextException() ;
            }
        }
        catch( Exception e ){
                System.out.println( e ) ;
        }
    }
	
		
}

	
