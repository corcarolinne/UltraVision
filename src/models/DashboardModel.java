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
    public String showName () {
        
        String name = "";
       try {
        	dbConnection = new DBConnection();
        	
           // building the queries
           String query = "SELECT * FROM ultravision.titles WHERE TitleID = 1";

           // sending the query to the database       
           ResultSet result = dbConnection.getStmt().executeQuery(query) ;
           
           // if there's a result in the query
           if(result.next()) {
               // select name and print it
        	   name = result.getString("Title");
        	   System.out.println(name);
           } else {
        	   System.out.println("There's no result");
           }
          
           // close the result set, statement and connection
           result.close();
           dbConnection.getStmt().close();
           dbConnection.getConnection().close();
       } catch(Exception e) {
    	   System.out.println( e ) ;
       }
       return name;
   }
    	

}
