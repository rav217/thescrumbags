/*
 class that will interact with the DB. can be adapted as needed
 */
package thescrumbags.Classes;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

/**
 *
 * @author Chris
 */
public class DBHandler {

    private Connection conn = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    private static DBHandler uniqueInst = null;
    //put other db variables here. default constructor will make them all null

    private DBHandler() {
    }

    public static synchronized DBHandler getInstance() {
        if (uniqueInst == null) {
            uniqueInst = new DBHandler();
        }
        return uniqueInst;
    }

    //opens connection. returns false on success, true on error
    public boolean openConnection(String user, String pswd) {
        System.out.println("Loading JDBC driver...");
        try {
            Class.forName("java.sql.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("Error loading JDBC driver. ");
            System.out.println("");
            return true;
        }
        System.out.println("JDBC driver successfully loaded.");
        System.out.println("");

        System.out.println("User " + user + " connecting to system...");
        try { //jdbc:mysql://sql5.freemysqlhosting.net:3306/?user=sql595207
            conn = DriverManager.getConnection("jdbc:mysql://sql5.freemysqlhosting.net:3306/sql595207", user, pswd);
        } catch (SQLException ex) {
            System.out.println("Invalid username or password.");
            System.out.println("");
            return true;

        }
        System.out.println("User " + user + " successfully connected to system.");
        System.out.println("");
        return false;
    }

    //fetches item from db specified by the id. returns false on success, true on failure
    public boolean fetchItem(int id) {
        System.out.println("Searching...");
        String descr = null;
        double price = 0;
        String query = "select * from saleproducts where id = " + id;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                descr = rs.getString("descr");
                price = rs.getDouble("price");
            }
        } catch (SQLException ex) {
            System.out.println("Error connecting to system.");
            System.out.println("");
            return true;
        }
        if (descr == null || price == 0) {
            System.out.println("Item ID " + id + " not found.");
            System.out.println("");
            return true;
        } else {
            System.out.println("Item with ID " + id + " is " + descr + " and costs $" + price + ".");
            System.out.println("");
        }
        return false;
    }

    public boolean addItem(int id, double price, String descr) {
        String query = "insert into saleproducts values (" + id + ", " + price + ", '" + descr + "')";
        System.out.println(query);
        System.out.println("");
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException ex) {
            System.out.println("Error adding item to system.");
            System.out.println("");
            return true;
        }
        System.out.println("Item successfully added to system.");
        System.out.println("");
        return false;
    }

    public boolean removeItem(int id) {
        String query = "delete from saleproducts where id = " + id;
        System.out.println("");
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(query); //is the error here?
        } catch (SQLException ex) {
            System.out.println("Error removing item from system.");
            System.out.println("");
            return true;
        }
        System.out.println("Item successfully removed from system.");
        System.out.println("");
        return false;
    }

    public void displayItems() {
        String descr = null;
        double price = 0;
        int id = 0;
        String query = "select * from saleproducts";
        System.out.println("ItemID\tDescription\t\t\tPrice");
        System.out.println("-----------------------------------------------");
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                id = rs.getInt("id");
                price = rs.getDouble("price");
                descr = rs.getString("descr");
                if (descr.length() > 16) {
                    System.out.println(id + "\t" + descr + "\t" + price);
                } else {
                    System.out.println(id + "\t" + descr + "\t\t\t" + price);
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error viewing items in the system.");
            System.out.println("");
        }
        System.out.println("");
    }

    public void closeConnection() {
        try {
            conn.close();
        } catch (SQLException ex) {
            System.out.println("Error closing the connection.");
            return;
        }
        System.out.println("Connection closed.");
    }

    /*fetches product catalog from db. returns ProductDescription hashmap (aka ProductCatalog.catalog)*/
    public ProductCatalog init() {

       //initialize ProductCatalog
        ProductCatalog pc = new ProductCatalog();
        
        int id = 0;
        double price = 0;
        String descr = "";
        String query = "select * from saleproducts";
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
            //while the result set is not empty
            while (rs.next()) {
                id = rs.getInt("id");
                price = rs.getDouble("price");
                descr = rs.getString("descr");
                BigDecimal bd = BigDecimal.valueOf(price);
                Money p = new Money(bd);
                //create new ProductDescription object based on this info
                ProductDescription pd = new ProductDescription(id, p, descr);
                //add the product description to ProductCatalog being passed as parameter
                pc.add(pd);
            }
        } catch (SQLException ex) {
            System.out.println("Error viewing sale products in the system.");
            closeConnection();
        }
        return pc;
    }
/**
        //TODO: initialize EmployeeList
        int empID = 0;
        String name = "";
        String password = "";
        int isManager = 0;
        String query2 = "select * from employees";
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query2);
            //while the result set is not empty
            while (rs.next()) {
                id = rs.getInt("id");
                name = rs.getString("name");
                password = rs.getString("password");
                isManager = rs.getInt("ismanager");
                boolean man = true;
                //switch int to bool value for simplifying integration
                switch (isManager) {
                    case 0:
                        man = true;
                        break;
                    case 1:
                        man = false;
                        break;
                }
                //create new Employee object based on this info
                //add the employee to employee list
                r.getUserManager().setExistingEmployee(id, man, name, password);
                System.out.println(name + " added to EmployeeList");
            }
        } catch (SQLException ex) {
            System.out.println("Error viewing employees in system.");
            closeConnection();
        }
    }

    /*public Sale getSale() {}
     public Rental getRental() {}*/
}
