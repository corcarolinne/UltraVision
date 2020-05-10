package models;

import java.sql.ResultSet;
import java.sql.SQLException;

import controllers.DashboardController;
import main.DBConnection;
import views.DashboardView;

public class Customer {
	
	// properties
	private int ID;
	private String firstName;
	private String lastName;
	private String address;
	private String email;
	private String phoneNumber;
	private String cardNumber;
	private int membership;
	private int score;
	private DashboardController controller;
	
	// constructors
	public Customer(int ID, String firstName, String lastName, String address, String email, String phoneNumber, String cardNumber, int membership, int score, DashboardController controller) {
		this.ID = ID;
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
	public Customer(DashboardController controller) {
		this.controller = controller;
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
	public Customer() {
		// empty constructor 
	}

	// getters and setters
	public int getID() {
		return this.ID;
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

	public void setID(int ID) {
		this.ID = ID;
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

	public void setMembership(int membership) {
		this.membership = membership;
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

	// method to query database for customer data, receives 2 strings corresponding to the search and returns an array with the data
	public String[][] showCustomers(String searchInput, String selectedFilter) {
	        
    	// declaring result 2d array with data
        String[][] customersData = null;
        
       try {
        	DBConnection dbConnection = new DBConnection();
        	if(searchInput.isEmpty()) {
        		
		        // building the queries
		        String numOfRowsQuery = "SELECT * FROM ultravision.customers";
		        String customersQuery = "SELECT customers.CustomerID, customers.FirstName, customers.LastName, customers.Address, customers.Email, customers.Phone, customers.CardNumber, memberships.Type, customers.Score FROM ultravision.customers INNER JOIN ultravision.memberships ON customers.MembershipID = memberships.MembershipID ORDER BY customers.CustomerID;"; 
		        
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
		        customersData= new String[numOfRows][9];
		        
		        int row = 0;
		        // loop through result, while it's returns true (while there's lines in titles table)
		        while(result.next()) {
		            // set titlesData array to receive each value from each row, for each corresponding column
		        	customersData[row][0] = result.getString("CustomerID");
		        	customersData[row][1] = result.getString("FirstName");
		        	customersData[row][2] = result.getString("LastName");
		        	customersData[row][3] = result.getString("Address");
		        	customersData[row][4] = result.getString("Email");
		        	customersData[row][5] = result.getString("Phone");
		        	customersData[row][6] = result.getString("CardNumber");
		        	customersData[row][7] = result.getString("Type");
		        	customersData[row][8] = result.getString("Score");
		
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
                    String searchedData = "SELECT customers.CustomerID, customers.FirstName, customers.LastName, customers.Address, customers.Email, customers.Phone, customers.CardNumber, memberships.Type, customers.Score FROM ultravision.customers INNER JOIN ultravision.memberships ON customers.MembershipID = memberships.MembershipID WHERE customers.CustomerID='"+customerID+"' ORDER BY customers.CustomerID";
                    
                   // sending the query to the database
                   ResultSet searchCustomers = dbConnection.getStmt().executeQuery(searchedData);
                   
                   // set customerData number of rows and number of columns
                   customersData= new String[numOfRows][9];
                   
                    int row = 0;
                    // while there's a result 
                    while(searchCustomers.next()) {
                        
                        // set customerData array to receive each value from each row, for each corresponding column
                    	customersData[row][0] = searchCustomers.getString("CustomerID");
                    	customersData[row][1] = searchCustomers.getString("FirstName");
                    	customersData[row][2] = searchCustomers.getString("LastName");
                    	customersData[row][3] = searchCustomers.getString("Address");
                    	customersData[row][4] = searchCustomers.getString("Email");
                    	customersData[row][5] = searchCustomers.getString("Phone");
                    	customersData[row][6] = searchCustomers.getString("CardNumber");
                    	customersData[row][7] = searchCustomers.getString("Type");
                    	customersData[row][8] = searchCustomers.getString("Score");
                    	
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
	
    // method to update customer details, receives 2 instances of Customer object
    public void updateCustomer(Customer customerToUpdate, Customer customerSelected ){
        try{
        	DBConnection dbConnection = new DBConnection();

            // building the query
            String updateQuery = "UPDATE ultravision.customers SET FirstName= '"+customerToUpdate.getFirstName()+"', LastName= '"+customerToUpdate.getLastName()+"', Address= '"+customerToUpdate.getAddress()+"', Email= '"+customerToUpdate.getEmail()+"', Phone= '"+customerToUpdate.getPhoneNumber()+"', CardNumber= '"+customerToUpdate.getCardNumber()+"', MembershipID= '"+customerToUpdate.getMembership()+"' WHERE CustomerID = '"+customerSelected.getID()+"';"; 
            
            // execute query
	        dbConnection.getStmt().execute(updateQuery);
	        
	        // closing statement and connections
            dbConnection.getStmt().close();
            dbConnection.getConnection().close();
            
            // setting customer object to have the values from the other customer that now has new values
            customerSelected.setFirstName(customerToUpdate.getFirstName());
            customerSelected.setLastName(customerToUpdate.getLastName());
            customerSelected.setAddress(customerToUpdate.getAddress());
            customerSelected.setEmail(customerToUpdate.getEmail());
            customerSelected.setPhoneNumber(customerToUpdate.getPhoneNumber());
            customerSelected.setCardNumber(customerToUpdate.getCardNumber());
            customerSelected.setMembership(customerToUpdate.getMembership());
            
        }
        catch( SQLException se ){
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
    
    // method to validate customer email, it receives a string with the email input, returns a boolean with the result of validation
    public boolean validateEmail(String email){
    	
    	boolean isEmailInUse = true;
        try{
        	DBConnection dbConnection = new DBConnection();
            
            // search for customer with this email on database
            String searchQuery = "SELECT * FROM ultravision.customers WHERE Email = '"+email+"';";
            
            // sending the query to the database
            ResultSet searchResult = dbConnection.getStmt().executeQuery(searchQuery);

            // if email is in use, set the boolean to true
            if(searchResult.next()) {
            	isEmailInUse = true;
            } else {
            	// otherwise, set it to false
            	isEmailInUse = false;
            }
            
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
		return isEmailInUse;
    }
		
}