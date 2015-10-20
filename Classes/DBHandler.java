/*
class that will interact with the DB
 */
package thescrumbags.Classes;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Chris
 */
public class DBHandler {
    
    private Connection conn = null;
    //put other db variables here. default constructor will make them all null
    
    //opens connection. returns 0 on success, -1 on error
    public boolean openConnection(String user, String pswd){
       System.out.println("Loading JDBC driver...");
        try{
            Class.forName("java.sql.Driver");
        } catch (ClassNotFoundException ex){
            //error establishing connection
            System.out.println("Error loading JDBC driver. " + ex.getMessage() + ".");
            ex.getMessage();
            return true;
        }
        System.out.println("JDBC driver successfully loaded.");
        
        System.out.println("User " + user + " connecting to database...");
        try{ //jdbc:mysql://127.0.0.1:3306/?user=ChrisByam
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306", user, pswd);
        } catch (SQLException ex){
            System.out.println("Error connecting to database. " + ex.getMessage() + ".");
            return true;
        }
        System.out.println("User " + user + " successfully connected to database.");
        return false;
    }

    
    
}   
