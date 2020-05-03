package models;

import java.sql.ResultSet;

import main.DBConnection;

public class Title {
	
	// properties
	protected String titleName;
	protected int yearOfRelease;
	protected String format;
	protected int price;
	protected boolean isAvailable;
	protected DBConnection dbConnection;
	
	// constructors
	public Title(String titleName, int yearOfRelease, String format, int price, boolean isAvailable, DBConnection dbConnection) {
		this.titleName = titleName;
		this.yearOfRelease = yearOfRelease;
		this.format = format;
		this.price = price;
		this.isAvailable = isAvailable;
		this.dbConnection = dbConnection;
	}
	public Title() {
		// empty constructor
	}

	// getters and setters 
	public String getTitleName() {
		return this.titleName;
	}

	public int getYearOfRelease() {
		return this.yearOfRelease;
	}

	public String getFormat() {
		return this.format;
	}

	public int getPrice() {
		return this.price;
	}

	public boolean isAvailable() {
		return this.isAvailable;
	}

	public DBConnection getDbConnection() {
		return dbConnection;
	}

	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}

	public void setYearOfRelease(int yearOfRelease) {
		this.yearOfRelease = yearOfRelease;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}
	
	public void setDbConnection(DBConnection dbConnection) {
		this.dbConnection = dbConnection;
	}
	
	// method to display available titles
    public String[][] showAvailableTitles (String searchInput, String selectedFilter) {
        
    	// declaring result 2d array with data
        String[][] titlesData = null;
        
       try {
        	dbConnection = new DBConnection();
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
        	dbConnection = new DBConnection();
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
}
