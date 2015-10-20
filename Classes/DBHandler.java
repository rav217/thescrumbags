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
    private Statement stmt = null;
    private ResultSet rs = null;
    
    //put other db variables here. default constructor will make them all null
    
    //opens connection. returns false on success, true on error
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
    
    //fetches item from db specified by the id. returns false on success, true on failure
    public boolean fetchItem(int id){
        String query = "select * from productdescription where id = " + id;
        try{
            con.createStatement();
            rs = stmt.executeQuery(query);
        } catch (SQLException ex){
            System.out.println("Item");
        }
    }

    
    
}   
