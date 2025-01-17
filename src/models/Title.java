package models;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import controllers.DashboardController;
import interfaces.TitleInterface;
import main.DBConnection;
import views.DashboardView;
import views.SearchResultsView;

public class Title implements TitleInterface {
	
	// properties
	private int ID;
	protected String titleName;
	private String type;
	protected String yearOfRelease;
	protected String format;
	protected double price;
	protected String artist;
	protected String genre;
	protected String director;
	protected boolean isAvailable;
	private DashboardController controller;
	
	// constructors
	public Title(int ID, String titleName, String type, String yearOfRelease, String format, double price, boolean isAvailable, String artist, String genre, String director, DashboardController controller) {
		this.ID = ID;
		this.titleName = titleName;
		this.type = type;
		this.yearOfRelease = yearOfRelease;
		this.format = format;
		this.price = price;
		this.isAvailable = isAvailable;
		this.artist = artist;
		this.genre = genre;
		this.director = director;
		this.controller = controller;
	}
	public Title() {
		// empty constructor
	}
	public Title(DashboardController controller) {
		this.controller = controller;
	}
	public Title(String titleName, String type, String yearOfRelease, String format, double price, boolean isAvailable, String artist, String genre, String director, DashboardController controller) {
		this.titleName = titleName;
		this.type = type;
		this.yearOfRelease = yearOfRelease;
		this.format = format;
		this.price = price;
		this.isAvailable = isAvailable;
		this.artist = artist;
		this.genre = genre;
		this.director = director;
		this.controller = controller;
	}
	
	// getters and setters 
	public String getTitleName() {
		return this.titleName;
	}
	public int getID() {
		return this.ID;
	}
	public void setID(int iD) {
		this.ID = iD;
	}
	public String getGenre() {
		return this.genre;
	}
	public String getDirector() {
		return this.director;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public String getArtist() {
		return this.artist;
	}
	public DashboardController getController() {
		return this.controller;
	}
	public void setArtist(String artist) {
		this.artist = artist;
	}
	public void setController(DashboardController controller) {
		this.controller = controller;
	}
	public String getType() {
		return this.controller.getType();
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getYearOfRelease() {
		return this.yearOfRelease;
	}

	public String getFormat() {
		return this.controller.getFormat();
	}
	public double getPrice() {
		return this.price;
	}
	public boolean isAvailable() {
		return this.isAvailable;
	}
	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}
	public void setYearOfRelease(String yearOfRelease) {
		this.yearOfRelease = yearOfRelease;
	}
	public void setFormat(String format) {
		this.format = format;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}
	
	// method to display available titles
    @Override
	public String[][] showAvailableTitles (String searchInput, String selectedFilter) {
        
    	// declaring result 2d array with data
        String[][] titlesData = null;
        
       try {
        	DBConnection dbConnection = new DBConnection();
        	if(searchInput.isEmpty()) {
                // building the queries
                String numOfRowsQuery = "SELECT * FROM ultravision.titles";
                String availableTitlesQuery = "SELECT titles.TitleID, titles.Title, titles.Type, titles.YearOfRelease, titles.Genre, titles.Format, titles.Price, titles.Director, titles.Artist FROM ultravision.titles WHERE IsAvailable=1;";
                
                // sending the query to the database       
                ResultSet resultNumOfRows = dbConnection.getStmt().executeQuery(numOfRowsQuery) ;
                
                int numOfRows = 0; 
                // while we have rows, or while next() returns true
                while(resultNumOfRows.next()) {
                    // add one to rows
                    numOfRows++;
                }     
                // making a result set
                ResultSet result = dbConnection.getStmt().executeQuery(availableTitlesQuery);
                // set titlesData number of rows and number of columns
                titlesData= new String[numOfRows][9];
                
                int row = 0;
                // loop through result, while it's returns true (while there's lines in titles table)
                while(result.next()) {
                    // set titlesData array to receive each value from each row, for each corresponding column
                	titlesData[row][0] = result.getString("TitleID");
                	titlesData[row][1] = result.getString("Title");
                	titlesData[row][2] = result.getString("Type");
                	titlesData[row][3] = result.getString("YearOfRelease");
                	titlesData[row][4] = result.getString("Genre");
                	titlesData[row][5] = result.getString("Format");
                	titlesData[row][6] = result.getString("Price");
                	titlesData[row][7] = result.getString("Director");
                	titlesData[row][8] = result.getString("Artist");

                   // increase row to populate the next row
                    row++;
                }
        	} else {
        		// here lays the logic for when we have a search input
        		// if the filter selected is Title
                if(selectedFilter == "Titles") {
                
                    // query the titles table looking for any title information that matches user input
                    String checkSize = "SELECT * FROM ultravision.titles WHERE Title LIKE '%"+searchInput+"%' OR Type LIKE '%"+searchInput+"%' OR YearOfRelease LIKE '%"+searchInput+"%' OR Genre LIKE '%"+searchInput+"%' OR Format LIKE '%"+searchInput+"%' OR Price LIKE '%"+searchInput+"%' OR Director LIKE '%"+searchInput+"%' OR Artist LIKE '%"+searchInput+"%';";
                    String searchedData = "SELECT * FROM ultravision.titles WHERE Title LIKE '%"+searchInput+"%' OR Type LIKE '%"+searchInput+"%' OR YearOfRelease LIKE '%"+searchInput+"%' OR Genre LIKE '%"+searchInput+"%' OR Format LIKE '%"+searchInput+"%' OR Price LIKE '%"+searchInput+"%' OR Director LIKE '%"+searchInput+"%' OR Artist LIKE '%"+searchInput+"%';";
                 
                    int numOfRows = 0;      
                    // sending the query to the database
                    ResultSet resultToCheckSize = dbConnection.getStmt().executeQuery(checkSize);
                    
                    // while we have rows, or while next() returns true
                    while(resultToCheckSize.next()) {
                        // add one to rows
                        numOfRows++;
                    }
               
                    // set titlesData number of rows and number of columns
                   titlesData= new String[numOfRows][9];
                    
                   // sending the query to the database
                   ResultSet searchTitles = dbConnection.getStmt().executeQuery(searchedData);
                    
                    int row = 0;
                    // while there's a result 
                    while(searchTitles.next()) {
                        
                        // set titlesData array to receive each value from each row, for each corresponding column
                    	titlesData[row][0] = searchTitles.getString("TitleID");
                    	titlesData[row][1] = searchTitles.getString("Title");
                    	titlesData[row][2] = searchTitles.getString("Type");
                    	titlesData[row][3] = searchTitles.getString("YearOfRelease");
                    	titlesData[row][4] = searchTitles.getString("Genre");
                    	titlesData[row][5] = searchTitles.getString("Format");
                    	titlesData[row][6] = searchTitles.getString("Price");
                    	titlesData[row][7] = searchTitles.getString("Director");
                    	titlesData[row][8] = searchTitles.getString("Artist");                 	
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
       return titlesData;
   }
    
    // method to display rented titles
    @Override
	public String[][] showRentedTitles (String searchInput, String selectedFilter) {
        
    	// declaring result 2d array with data
        String[][] titlesData = null;
        
       try {
        	DBConnection dbConnection = new DBConnection();
        	if(searchInput.isEmpty()) {
                // building the queries
                String numOfRowsQuery = "SELECT * FROM ultravision.titles";
                String availableTitlesQuery = "SELECT titles.TitleID, titles.Title, titles.Type, titles.YearOfRelease, titles.Genre, titles.Format, titles.Price, titles.Director, titles.Artist FROM ultravision.titles WHERE IsAvailable=0;";
                
                // sending the query to the database       
                ResultSet resultNumOfRows = dbConnection.getStmt().executeQuery(numOfRowsQuery) ;
                
                int numOfRows = 0; 
                // while we have rows, or while next() returns true
                while(resultNumOfRows.next()) {
                    // add one to rows
                    numOfRows++;
                }
                
                ResultSet result = dbConnection.getStmt().executeQuery(availableTitlesQuery) ;
                // set titlesData number of rows and number of columns
                titlesData= new String[numOfRows][9];
                
                int row = 0;
                // loop through result, while it's returns true (while there's lines in titles table)
                while(result.next()) {
                    // set titlesData array to receive each value from each row, for each corresponding column
                	titlesData[row][0] = result.getString("TitleID");
                	titlesData[row][1] = result.getString("Title");
                	titlesData[row][2] = result.getString("Type");
                	titlesData[row][3] = result.getString("YearOfRelease");
                	titlesData[row][4] = result.getString("Genre");
                	titlesData[row][5] = result.getString("Format");
                	titlesData[row][6] = result.getString("Price");
                	titlesData[row][7] = result.getString("Director");
                	titlesData[row][8] = result.getString("Artist");
                   // increase row to populate the next row
                    row++;
                }
        	} 
           // close statement and connection
           dbConnection.getStmt().close();
           dbConnection.getConnection().close();
       } catch(Exception e) {
    	   System.out.println( e ) ;
       }
       return titlesData;
   }
    
    // method to create a title, receives a title object
    @Override
	public void createTitle(Title newTitle){
        try{
        	DBConnection dbConnection = new DBConnection();
            
            // building the query
            String query = "INSERT INTO ultravision.titles (Title, Type, YearOfRelease, Genre, Format, Price, isAvailable, Director, Artist) VALUES ('"+newTitle.getTitleName()+"','"+newTitle.getType()+"','"+newTitle.getYearOfRelease()+"','"+newTitle.getGenre()+"','"+newTitle.getFormat()+"','"+newTitle.getPrice()+"', '1','"+newTitle.getDirector()+"','"+newTitle.getArtist()+"');";
            
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
    // method to make the validation of a rent, receives a title object and returns a string with the type of message that should be displayed
    @Override
	public String validateRent(Title titleToRent, String customerEmail){
    	
    	String messageType = "";
        try{
        	String customerID = "";
        	String membershipID = "";
        	String type = titleToRent.type;
        	
        	DBConnection dbConnection = new DBConnection();
            
        	// query database for customer ID
        	String findCustomerID = "SELECT * FROM ultravision.customers WHERE Email = '"+customerEmail+"';";
        	
	        ResultSet customerDetails = dbConnection.getStmt().executeQuery(findCustomerID) ;
           
            if(customerDetails.next()) {
                // set customerID to be the ID found on DB and save their membershipID
            	customerID = customerDetails.getString("CustomerID");
            	membershipID = customerDetails.getString("MembershipID");
            	
            	// check if this customer ID is found 4 times in the transactions table
            	// if it's found, then it means that the customer is not allowed to rent more titles
            	// query database for customer ID
            	String findTransactions = "SELECT * FROM ultravision.transactions WHERE CustomerID = '"+customerID+"';";
            	ResultSet transactionsNumber = dbConnection.getStmt().executeQuery(findTransactions) ;
            	
            	// count number of transactions found
            	int counter = 0;
            	while(transactionsNumber.next()) {
            		counter++;
            	}
            	
            	// number of rents validation
            	if(counter > 3) {
            		messageType = "rentLimitError";
            	} else {
            		// if the customer can rent titles, proceed with the next part of the validation
            		// check membership to see if customer can rent that type of title
                    if(membershipID.equals("1") && (type.equals("Movie") || type.equals("Box Set"))) {
                    	messageType = "membershipPermissionError1";
                    } else if(membershipID.equals("2") && (type.equals("Music") || type.equals("Live Concert") || type.equals("Box Set"))) {
                    	messageType = "membershipPermissionError2";
                    } else if(membershipID.equals("3") && (type.equals("Music") || type.equals("Live Concert") || type.equals("Movie"))) {
                    	messageType = "membershipPermissionError3";
                    } else {
	            		// in case the customer has premium membership or its allowed to rent the selected title
	                	messageType = "successfulValidation";	
                    }
            	}	
            } else {
            	messageType = "customerNotFoundError";	
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
        return messageType;
    }
    
    // method to check score of a customer, if they are able to get a free rent or not, receives an email and returns the result in boolean
    @Override
	public boolean checkScore(String customerEmail){
    	
    	boolean isFreeRentValid = false;
    	try{
    		
        	String customerID = "";
        	String score = "";

        	DBConnection dbConnection = new DBConnection();

        	// query database for customer ID
        	String findCustomerID = "SELECT * FROM ultravision.customers WHERE Email = '"+customerEmail+"';";

	        ResultSet customerFinding = dbConnection.getStmt().executeQuery(findCustomerID) ;

            // if a result it's a found, it means there's a customer with this email
            if(customerFinding.next()) {
                // set customerID to be the ID found on DB
            	customerID = customerFinding.getString("CustomerID");
            	score = customerFinding.getString("Score");
            }

	       // if the score of this customer is 0 or 100
	       if(score.equals("0") || score.equals("100")) {
	    	   // set this method to return true
	    	   isFreeRentValid = true;
	    	   // update on database the score of this customer
	    	   String updateScore = "UPDATE ultravision.customers SET Score = '0' WHERE CustomerID = '"+customerID+"';";
	    	   // execute query
		       dbConnection.getStmt().execute(updateScore);
	    	   
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
    	return isFreeRentValid;
    }
    
    // method to rent a title, receives a title object and a string with customer's email entered on input
    @Override
	public void rentTitle(Title titleToRent, String customerEmail){
    	
    	try{
        	String customerID = "";

        	DBConnection dbConnection = new DBConnection();

        	// query database for customer ID
        	String findCustomerID = "SELECT * FROM ultravision.customers WHERE Email = '"+customerEmail+"';";

	        ResultSet result = dbConnection.getStmt().executeQuery(findCustomerID) ;

            // if a result it's a found, it means there's a customer with this email
            if(result.next()) {
                // set customerID to be the ID found on DB
            	customerID = result.getString("CustomerID");
            }

            // insert rent on transactions table on database
            String insertQuery = "INSERT INTO ultravision.transactions (CustomerID, TitleID) VALUES ('"+customerID+"','"+titleToRent.getID()+"');";

            // execute insert query
	        dbConnection.getStmt().execute(insertQuery);

	        // query to update title isAvailable column
	        String updateTitleAvailability = "UPDATE ultravision.titles SET isAvailable = 0 WHERE titleID='"+titleToRent.getID()+"';";

	        // execute update query
	        dbConnection.getStmt().execute(updateTitleAvailability);

	        // query adds 10 to customer's score
	        String updateScore = "UPDATE ultravision.customers SET Score = Score + 10 WHERE CustomerID='"+customerID+"';"; 

	        // execute update query
	       dbConnection.getStmt().execute(updateScore);

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
    
    // method to return a title, receives a title object
    @Override
	public void returnTitle(Title titleToRent){
    	
    	try{
        	String transactionID = "";

        	DBConnection dbConnection = new DBConnection();

        	// search transactions with this title ID
        	String searchQuery = "SELECT * FROM ultravision.transactions WHERE TitleID = '"+titleToRent.getID()+"';";
        	ResultSet result = dbConnection.getStmt().executeQuery(searchQuery) ;
        	
        	// if there's a transaction with this title ID
        	if(result.next()) {
        		// save transaction ID
        		transactionID = result.getString("TransactionID");
        	}
        	
        	// convert transactionID to integer
        	int transactionIDAsInt = Integer.parseInt(transactionID);  
        	
            // update column with return date on transactions table with current time
            String updateQuery = "UPDATE ultravision.transactions SET ReturnedAt = current_timestamp() WHERE TransactionID = '"+transactionIDAsInt+"';";

            // execute update query
	        dbConnection.getStmt().executeUpdate(updateQuery);

	        // query to update title isAvailable column
	        String updateTitleAvailability = "UPDATE ultravision.titles SET isAvailable = 1 WHERE titleID='"+titleToRent.getID()+"';";

	        // execute update title query
	        dbConnection.getStmt().execute(updateTitleAvailability);

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
