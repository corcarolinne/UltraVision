package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

// db connection
public class DBConnection {

	// properties
    String dbServer = "jdbc:mysql://localhost:3306/ultravision?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    String user = "root";
    String password = "13 Hatnephfcfati_";
    Statement stmt;
    Connection connection;
    
	// constructor
    public DBConnection() {
    	try {
            connection = DriverManager.getConnection(dbServer, user, password);
            // get a statement from the connection
            stmt = connection.createStatement();     
        } catch( SQLException se ) {
            System.out.println( "SQL Exception:" ) ;

            // Loop through the SQL Exceptions
            while( se != null ){
                System.out.println( "State  : " + se.getSQLState()  ) ;
                System.out.println( "Message: " + se.getMessage()   ) ;
                System.out.println( "Error  : " + se.getErrorCode() ) ;
                
                se = se.getNextException() ;
            }
        } catch( Exception e ){
        	System.out.println( e ) ;
        }
    }
    
    // getter and setters
    public String getDbServer() {
		return this.dbServer;
	}

	public String getUser() {
		return this.user;
	}

	public String getPassword() {
		return this.password;
	}

	public Statement getStmt() {
		return this.stmt;
	}

	public Connection getConnection() {
		return connection;
	}

	public void setDbServer(String dbServer) {
		this.dbServer = dbServer;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setStmt(Statement stmt) {
		this.stmt = stmt;
	}
	
	public void setConnection(Connection connection) {
		this.connection = connection;
	}
}
    