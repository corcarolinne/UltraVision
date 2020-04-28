package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DashboardModel {
	
	// properties
    String dbServer = "jdbc:mysql://localhost:3306/ultravision";// type of database/port/database name
    String user = "root";
    String password = "13 Hatnephfcfati_";
    

    // method to test database
    public String showName () {
        
        String name = "";
       try{
           Connection connection = DriverManager.getConnection(dbServer, user, password);

           // get a statement from the connection
           Statement stmt = connection.createStatement();
           // building the queries
           String query = "SELECT * FROM ultravision.titles WHERE TitleID = 1";

           // sending the query to the database       
           ResultSet result = stmt.executeQuery(query) ;
           
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
           stmt.close();
           connection.close();     
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
       return name;
   }
    	

}
