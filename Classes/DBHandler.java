/*
class that will interact with the DB
 */
package thescrumbags.Classes;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Chris
 */
public class DBHandler {
    public static void main(String[] args){
        
        System.out.println("Loading JDBC driver...");
        try{
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex){
            //error establishing connection
            System.out.println("Error loading JDBC driver.");
            ex.printStackTrace();
            return;
        }
        System.out.println("JDBC driver successfully loaded."); //test
    
        System.out.println("Connecting to database...");
        Connection conn = null;
        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ScrumbagsDB", "ChrisByam", "tolland17");
        } catch (SQLException ex){
            System.out.println("Error connecting to database.");
            ex.printStackTrace();
            return;
        }
        System.out.println("Successfully connected to database");
    }
}   
