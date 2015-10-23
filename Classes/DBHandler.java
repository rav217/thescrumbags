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
            System.out.println("Error loading JDBC driver. ");
            System.out.println("");
            return true;
        }
        System.out.println("JDBC driver successfully loaded.");
        System.out.println("");
        
        System.out.println("User " + user + " connecting to system...");
        try{ //jdbc:mysql://127.0.0.1:3306/?user=ChrisByam
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test", user, pswd);
        } catch (SQLException ex){
            System.out.println("Invalid username or password.");
            System.out.println("");
            return true;
        }
        System.out.println("User " + user + " successfully connected to system.");
        System.out.println("");
        return false;
    }
    
    //fetches item from db specified by the id. returns false on success, true on failure
    public boolean fetchItem(int id){
        System.out.println("Searching...");
        String descr = null;
        double price = 0;
        String query = "select * from productdescription where id = " + id;
        try{
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()){
                descr = rs.getString("descr");
                price = rs.getDouble("price");
            }
        } catch (SQLException ex){
            System.out.println("Error connecting to system.");
            System.out.println("");
            return true;
        }
        if (descr == null || price == 0){
            System.out.println("Item ID " + id + " not found.");
            System.out.println("");
            return true;
        }
        else{
            System.out.println("Item with ID " + id + " is " + descr + " and costs $" + price + ".");
            System.out.println("");
        }
        return false;
    }
    
    public boolean addItem(int id, double price, String descr){
        String query = "insert into productdescription values (" + price + ", " + id + ", '" + descr + "')";
        System.out.println("");
        try{
            stmt = conn.createStatement();
            stmt.executeUpdate(query); //is the error here?
        } catch (SQLException ex){
            System.out.println("Error adding item to system.");
            System.out.println("");
            return true;
        }
        System.out.println("Item successfully added to system.");
        System.out.println("");
        return false;
    }
    
    public boolean removeItem(int id){
        String query = "delete from productdescription where id = " + id;
        System.out.println("");
        try{
            stmt = conn.createStatement();
            stmt.executeUpdate(query); //is the error here?
        } catch (SQLException ex){
            System.out.println("Error removing item from system.");
            System.out.println("");
            return true;
        }
        System.out.println("Item successfully removed from system.");
        System.out.println("");
        return false;
    }
    
    public void closeConnection(){
        try{
            conn.close();
        } catch(SQLException ex){
            System.out.println("Error closing the connection.");
            return;
        }
        System.out.println("Connection closed.");
    }
}   
