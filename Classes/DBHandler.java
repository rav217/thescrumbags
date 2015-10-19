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
        //following code loads the conn driver
        try{
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception ex){
            //error establishing connection
            System.out.println("Error loading the connection manager");
            System.exit(0);
        }
        
        //following code fetches connection from driver
        Connection conn = null;
        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost/ScrumbagsDB", "ChrisByam", "tolland17");
        } catch (SQLException ex){
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        
    }
}   
