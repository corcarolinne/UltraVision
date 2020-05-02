package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import main.DBConnection;

public class DashboardModel {
	
    DBConnection dbConnection;

    // method to test database
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
