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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

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

/*not essential to POS_system functionality at this time...
    
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
*/
    public void closeConnection() {
        try {
            conn.close();
        } catch (SQLException ex) {
            System.out.println("Error closing the connection.");
            return;
        }
        System.out.println("Connection closed.");
    }

    //initializes SaleProductCatalog
    public ProductCatalog initSPC() {
        ProductCatalog spc = new ProductCatalog();
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
                //add the product description to SaleProductCatalog
                spc.add(pd);
            }
        } catch (SQLException ex) {
            System.out.println("Error viewing sale products in the system.");
            closeConnection();
        }
        return spc;
    }
    
    //initialize RentalProductCatalog
    public ProductCatalog initRPC(){
        ProductCatalog rpc = new ProductCatalog();       
        int id = 0;
        double price = 0;
        String descr = "";
        String query = "select * from rentalproducts";
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
                //add the product description to RentalProductCatalog
                rpc.add(pd);
            }
        } catch (SQLException ex) {
            System.out.println("Error viewing rental products in the system.");
            closeConnection();
        }
        return rpc;
    }
    
    //add transaction to transaction history in db (transtype, transid, itemid, price, descr, quantity, subtotal)
    //TODO: transid ends up being 0 every time
    public void addTransaction(String type, ArrayList<LineItem> lineItems){
        //query into db, select greatest transid, make transid that +1
        int highestID = 1; //if 1st element, transid will be 1
        String subQuery = "select max(transid) as transid from transactionhistory";
        try{
            stmt = conn.createStatement();
            rs = stmt.executeQuery(subQuery);
            while (rs.next()) {
                highestID = rs.getInt("transid");
            }
        } catch (SQLException ex) {
            System.out.println("Error viewing transaction history");
            closeConnection();
        }
        highestID += 1;
        Iterator<LineItem> i = lineItems.iterator();
	while (i.hasNext()){
            LineItem li = i.next();
            int itemid = li.getProductDescription().getItemID();
            BigDecimal priceBD = li.getProductDescription().getPrice().getAmount();
            double price = priceBD.doubleValue();
            BigDecimal subtotalBD = li.getSubtotal().getAmount();
            double subtotal = subtotalBD.doubleValue();
            String descr = li.getProductDescription().getDescription();
            int quantity = li.getQuantity();
            String query = "insert into transactionhistory values ('"+type+"', "+highestID+", "+itemid+", "+price+", '"
                    +descr+"', "+quantity+", "+subtotal+")";

            try {
                stmt = conn.createStatement();
                stmt.executeUpdate(query);
            } catch (SQLException ex) {
                System.out.println("Error adding transaction to transaction history.");
                closeConnection();
            }
        }
    }
    
    //iterate through product catalog on db and update QOH
    public void updateInventory(String table, ArrayList<LineItem> lineItems){
        Iterator<LineItem> i = lineItems.iterator();
	while (i.hasNext()){
            LineItem li = i.next();
            //retrieve itemid and quantity from from lineitem
            int id = li.getProductDescription().getItemID();
            int q = li.getQuantity();
            int curQ = 0;
            //get current qoh for that item id in appropriate table in db
            String query = "select qoh from "+table+" where id = "+id;
            try{
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()){
                    curQ = rs.getInt("qoh");
                }
            } catch (SQLException ex) {
                System.out.println("Error viewing product");
                closeConnection();
            }
            //calculate new qoh and execute an update on db
            int newQ = curQ-q;
            if (newQ < 0){
                System.out.println("Quantity requested exceeds QOH");
                return;
            }
            String exQuery = "update "+table+" saleproducts set qoh = "+newQ+" where id = "+id;
            try{
                stmt = conn.createStatement();
                stmt.executeUpdate(exQuery);
            } catch (SQLException ex) {
                System.out.println("Error updating QOH");
                closeConnection();
            }
        }
    }

    public int getNextUserID(){
        int highestID = 1; //if 1st element, empid will be 1
        String query = "select max(id) as id from employees";
        try{
        stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                highestID = rs.getInt("id"); 
            }
        } catch (SQLException ex) {
            System.out.println("Error viewing employees table");
            closeConnection();
        }
     return highestID + 1;       
    }
    
    public void addEmployee(Employee employee){
        int id = employee.getEmployeeID();
        String name = employee.getEmployeeName();
        String password = employee.getEmployeePassword();
        boolean isManager = employee.isManager();
        int man = 0;
        if (isManager == true) man = 1;
        String query = "insert into employees values ("+id+", '"+name+"', '"+password+"', "+man+")";
        try{
            stmt = conn.createStatement();
            stmt.executeUpdate(query);
        } catch(SQLException ex){
            System.out.println("Error inserting employee into database");
            closeConnection();
        }
    }
    
    public void removeEmployee(int id){
        String query = "delete from employees where id = "+id;
        try{
            stmt = conn.createStatement();
            stmt.executeUpdate(query);
        } catch(SQLException ex){
            System.out.println("Error removing employee into database");
            closeConnection();
        }
    }
    
    //initialize EmployeeList
    public EmployeeList initializeEmployees(){
        EmployeeList list = new EmployeeList();
        int id = 0;
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
                        man = false;
                        break;
                    case 1:
                        man = true;
                        break;
                }
                //create new Employee object based on this info
                //add the employee to employee list
                Employee e = new Employee(id, man, name, password);
                list.addEmployee(e);
            }
        } catch (SQLException ex) {
            System.out.println("Error viewing employees in system.");
            closeConnection();
        }
        return list;
    }
    
    //find transaction from transaction history (will work for sale or rental) maybe
    public Transaction findTransaction(String type, int id){
        //need array list of sli, total price
        String query = "select * from transactionhistory where transtype = '"+type+"' and transid = "+id;
        ArrayList<LineItem> items = new ArrayList<>();
        double totalPrice = 0;
        try{
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
            while(rs.next()){
                //info required for ProductDescription object
                String descr = rs.getString("descr");
                int itemid = rs.getInt("itemid");
                double price = rs.getDouble("price");
                BigDecimal priceBD = BigDecimal.valueOf(price);
                Money p = new Money(priceBD);
                ProductDescription pd = new ProductDescription(itemid, p, descr);
                //info required for LineItem object
                int quantity = rs.getInt("quantity");
                double subtotal = rs.getDouble("subtotal");
                //add to totalPrice
                totalPrice += subtotal;
                BigDecimal subtotalBD = BigDecimal.valueOf(subtotal);
                Money s = new Money(subtotalBD);
                LineItem li = new LineItem(pd, quantity, s);
                //add this LineItem to ArrayList
                items.add(li);
            }
        } catch(SQLException ex){
            System.out.println("Error retrieving transaction from history");
            closeConnection();
        }
        BigDecimal totalPriceBD = BigDecimal.valueOf(totalPrice);
        Money tp = new Money(totalPriceBD);
        if (type.equals("S")){
            //create sale object
        }
        else{
            //create rental object
        }
        return new Transaction(items, tp);
    }
}
