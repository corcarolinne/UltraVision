package models;

import java.sql.ResultSet;
import java.sql.SQLException;

import controllers.DashboardController;
import main.DBConnection;
import views.SearchResultsView;

public class Title {
	
	// properties
	protected String titleName;
	private String type;
	protected String yearOfRelease;
	protected String format;
	protected double price;
	protected String band;
	protected boolean isAvailable;
	//protected DBConnection dbConnection;
	private DashboardController controller;
	
	// constructors
	public Title(String titleName, String type, String yearOfRelease, String format, double price, boolean isAvailable, String band, DashboardController controller) {
		this.titleName = titleName;
		this.type = type;
		this.yearOfRelease = yearOfRelease;
		this.format = format;
		this.price = price;
		this.isAvailable = isAvailable;
		this.band = band;
		//this.dbConnection = dbConnection;
		this.controller = controller;
	}
	public Title() {
		// empty constructor
	}

	// getters and setters 
	public String getTitleName() {
		return this.titleName;
	}

	public String getBand() {
		return this.band;
	}
	public DashboardController getController() {
		return this.controller;
	}
	public void setBand(String band) {
		this.band = band;
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

//	public DBConnection getDbConnection() {
//		return dbConnection;
//	}

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
	
//	public void setDbConnection(DBConnection dbConnection) {
//		this.dbConnection = dbConnection;
//	}
	
	// method to display available titles
    public String[][] showAvailableTitles (String searchInput, String selectedFilter) {
        
    	// declaring result 2d array with data
        String[][] titlesData = null;
        
       try {
        	DBConnection dbConnection = new DBConnection();
        	if(searchInput.isEmpty()) {
                // building the queries
                String numOfRowsQuery = "SELECT * FROM ultravision.titles";
                String availableTitlesQuery = "SELECT titles.Title, titles.Type, titles.YearOfRelease, titles.Genre, titles.Format, titles.Price, titles.Director, titles.Band FROM ultravision.titles WHERE IsAvailable=1;";
                
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
                titlesData= new String[numOfRows][8];
                
                int row = 0;
                // loop through result, while it's returns true (while there's lines in titles table)
                while(result.next()) {
                    // set titlesData array to receive each value from each row, for each corresponding column
                	titlesData[row][0] = result.getString("Title");
                	titlesData[row][1] = result.getString("Type");
                	titlesData[row][2] = result.getString("YearOfRelease");
                	titlesData[row][3] = result.getString("Genre");
                	titlesData[row][4] = result.getString("Format");
                	titlesData[row][5] = result.getString("Price");
                	titlesData[row][6] = result.getString("Director");
                	titlesData[row][7] = result.getString("Band");

                   // increase row to populate the next row
                    row++;
                }
        	} else {
        		// here lays the logic for when we have a search input
        		// if the filter selected is Title
                if(selectedFilter == "Titles") {
                
                    // query the titles table looking for any title information that matches user input
                    String checkSize = "SELECT * FROM ultravision.titles WHERE Title LIKE '%"+searchInput+"%' OR Type LIKE '%"+searchInput+"%' OR YearOfRelease LIKE '%"+searchInput+"%' OR Genre LIKE '%"+searchInput+"%' OR Format LIKE '%"+searchInput+"%' OR Price LIKE '%"+searchInput+"%' OR Director LIKE '%"+searchInput+"%' OR Band LIKE '%"+searchInput+"%';";
                    String searchedData = "SELECT * FROM ultravision.titles WHERE Title LIKE '%"+searchInput+"%' OR Type LIKE '%"+searchInput+"%' OR YearOfRelease LIKE '%"+searchInput+"%' OR Genre LIKE '%"+searchInput+"%' OR Format LIKE '%"+searchInput+"%' OR Price LIKE '%"+searchInput+"%' OR Director LIKE '%"+searchInput+"%' OR Band LIKE '%"+searchInput+"%';";
                 
                    int numOfRows = 0;      
                    // sending the query to the database
                    ResultSet resultToCheckSize = dbConnection.getStmt().executeQuery(checkSize);
                    
                    // while we have rows, or while next() returns true
                    while(resultToCheckSize.next()) {
                        // add one to rows
                        numOfRows++;
                    }
               
                    // set artData number of rows and number of columns
                   titlesData= new String[numOfRows][8];
                    
                   // sending the query to the database
                   ResultSet searchTitles = dbConnection.getStmt().executeQuery(searchedData);
                    
                    int row = 0;
                    // while there's a result 
                    while(searchTitles.next()) {
                        
                        // set artData array to receive each value from each row, for each corresponding column
                    	titlesData[row][0] = searchTitles.getString("Title");
                    	titlesData[row][1] = searchTitles.getString("Type");
                    	titlesData[row][2] = searchTitles.getString("YearOfRelease");
                    	titlesData[row][3] = searchTitles.getString("Genre");
                    	titlesData[row][4] = searchTitles.getString("Format");
                    	titlesData[row][5] = searchTitles.getString("Price");
                    	titlesData[row][6] = searchTitles.getString("Director");
                    	titlesData[row][7] = searchTitles.getString("Band");
                    	
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
    public String[][] showRentedTitles (String searchInput, String selectedFilter) {
        
    	// declaring result 2d array with data
        String[][] titlesData = null;
        
       try {
        	DBConnection dbConnection = new DBConnection();
        	if(searchInput.isEmpty()) {
                // building the queries
                String numOfRowsQuery = "SELECT * FROM ultravision.titles";
                String availableTitlesQuery = "SELECT titles.Title, titles.Type, titles.YearOfRelease, titles.Genre, titles.Format, titles.Price, titles.Director, titles.Band FROM ultravision.titles WHERE IsAvailable=0;";
                
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
                titlesData= new String[numOfRows][8];
                
                int row = 0;
                // loop through result, while it's returns true (while there's lines in titles table)
                while(result.next()) {
                    // set titlesData array to receive each value from each row, for each corresponding column
                	titlesData[row][0] = result.getString("Title");
                	titlesData[row][1] = result.getString("Type");
                	titlesData[row][2] = result.getString("YearOfRelease");
                	titlesData[row][3] = result.getString("Genre");
                	titlesData[row][4] = result.getString("Format");
                	titlesData[row][5] = result.getString("Price");
                	titlesData[row][6] = result.getString("Director");
                	titlesData[row][7] = result.getString("Band");

                   // increase row to populate the next row
                    row++;
                }
        	} else {
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
    public void createTitle(Title newTitle){
        try{
        	DBConnection dbConnection = new DBConnection();
            
            // building the query
            String query = "INSERT INTO ultravision.titles (Title, Type, YearOfRelease, Format, Price, isAvailable, Band) VALUES ('"+newTitle.getTitleName()+"','"+newTitle.getType()+"','"+newTitle.getYearOfRelease()+"','"+newTitle.getFormat()+"','"+newTitle.getPrice()+"', '1', '"+newTitle.getBand()+"');";
            
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
