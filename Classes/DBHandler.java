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
    
    public static void main(String[] args){
        
        System.out.println("Loading JDBC driver...");
        try{
            Class.forName("java.sql.Driver");
        } catch (ClassNotFoundException ex){
            //error establishing connection
            System.out.println("Error loading JDBC driver.");
            ex.printStackTrace();
            return;
        }
        System.out.println("JDBC driver successfully loaded.");
    
        System.out.println("Connecting to database...");
        Connection conn = null;
        try{
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/?user=ChrisByam", "ChrisByam", "tolland17");
        } catch (SQLException ex){
            System.out.println("Error connecting to database.");
            ex.printStackTrace();
            return;
        }
        System.out.println("Successfully connected to database.");
    }
}   
