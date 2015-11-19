//The Scrumbags: DBHandler class
//Singleton class that acts as a facade from the system to the DB

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
import java.util.GregorianCalendar;
import java.util.Date;
import java.util.Scanner;

public class DBHandler {
    
    //private variables
    private Connection conn = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    private static DBHandler uniqueInst = null;

    //private default constructor
    private DBHandler() {
    }

    //Singleton getIsntance method
    public static synchronized DBHandler getInstance() {
        if (uniqueInst == null) {
            uniqueInst = new DBHandler();
        }
        return uniqueInst;
    }
    
//following methods deal with opening and closing connection
    
    //initializes JDBC driver and opens DB connection
    //returns false on success, true on error
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

    //closes DB connection
    public void closeConnection() {
        try {
            conn.close();
        } catch (SQLException ex) {
            System.out.println("Error closing the connection.");
            return;
        }
        System.out.println("Connection closed.");
    }
    
//following methods deal with initializing catalogs

    //initializes system ProductCatalog for sale items from DB
    //returns ProductCatalog object
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
    
    //initializes system ProductCatalog for rental items from DB
    //returns ProductCatalog object
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
    
//following methods deal with transactions
    
    //finds transaction from transactionhistory table in DB
    //returns Transaction
    //TODO: add col for rental period, use 1 arg rental constructor
    public Transaction findTransaction(String type, int id){
        //need array list of sli, total price
        String query = "select * from transactionhistory where transtype = '"+type+"' and transid = "+id;
        ArrayList<LineItem> items = new ArrayList<>();
        double totalPrice = 0;
        int rPeriod = 0;
        GregorianCalendar date = null;
        try{
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
            
            int size = 0;
            if(rs != null)
            {
                rs.beforeFirst();
                rs.last();
                size = rs.getRow();
            }
            if(size == 0)
            {
                throw new NullPointerException();
            }
            rs.beforeFirst();
            
            while(rs.next()){
                //if item has already been returned, start loop over for next
                int returned = rs.getInt("returned");
                if (returned == 1)
                    continue;
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
            rPeriod = rs.getInt("rentalperiod");
            date = getGregorianCalendar(rs.getString("date"));
        } catch(SQLException ex){
            System.out.println("Error retrieving transaction from history");
            closeConnection();
        }
        BigDecimal totalPriceBD = BigDecimal.valueOf(totalPrice);
        Money tp = new Money(totalPriceBD);
        if (type.equals("S")){
            return new Sale(items, tp); //return sale
        }
        else{
            return new Rental(items, tp, rPeriod, date);
        }
    }
    
    //returns GregorianCalendar object for the date of a rental
    public GregorianCalendar getGregorianCalendar(String dateString){
        //Tue Nov 17 13:47:38 EST 2015
        Scanner s = new Scanner(dateString);
        String dayString = s.next();
        String monthString = s.next();
        String dayofMonthString = s.next();
        String timeString = s.next(); //13:47:38
        String timeZone = s.next();
        String yearString = s.next();
        char h1 = timeString.charAt(0);
        char h2 = timeString.charAt(1);
        char m1 = timeString.charAt(3);
        char m2 = timeString.charAt(4);
        char s1 = timeString.charAt(6);
        char s2 = timeString.charAt(7);
        String hourString = ""+h1+h2;
        String minuteString = ""+m1+m2;
        String secondString = ""+s1+s2;
        return new GregorianCalendar(Integer.parseInt(yearString), Integer.parseInt(monthString), Integer.parseInt(dayofMonthString),
                Integer.parseInt(hourString), Integer.parseInt(minuteString), Integer.parseInt(secondString));
        //return new GregorianCalendar(year, month, dayOfMonth, hourOfDay, minute, second);
    }
    //adds transaction to transaction table in DB (type S, R, SR, RR)
    //add field for rental period
    public void addTransaction(String type, ArrayList<LineItem> lineItems, String reason, int origTransID, GregorianCalendar date, int rPeriod){
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
        //get date String from GregorianCalendar obj
        Date dateobj = date.getTime();
        String dateString = dateobj.toString();
	while (i.hasNext()){
            LineItem li = i.next();
            int itemid = li.getProductDescription().getItemID();
            BigDecimal priceBD = li.getProductDescription().getPrice().getAmount();
            double price = priceBD.doubleValue();
            BigDecimal subtotalBD = li.getSubtotal().getAmount();
            double subtotal = subtotalBD.doubleValue();
            String descr = li.getProductDescription().getDescription();
            int quantity = li.getQuantity();
            String query = "insert into transactionhistory values ('"+type+"', "+highestID+", "+origTransID+", "+itemid+", "+price+", '"
                    +descr+"', "+quantity+", "+subtotal+", '"+reason+"', 0, '"+dateString+"', "+rPeriod+")";
            System.out.println(query);
            try {
                stmt = conn.createStatement();
                stmt.executeUpdate(query);
            } catch (SQLException ex) {
                System.out.println("Error adding transaction to transaction history.");
                closeConnection();
            }
            //set return flag for this line item on original sale or return transaction
            if (type.equals("SR") || type.equals("RR"))
                setReturnFlag(origTransID, itemid);
        }
    }
    
    //sets return flag for appropriate sale or rental line items in transactionhistory table in DB
    public void setReturnFlag(int origTransID, int itemid){
        String query = "update transactionhistory set returned = 1 where transid = "+origTransID+" and itemid = "+itemid;
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException ex) {
            System.out.println("Error setting return flag");
            closeConnection();
        }
    }
    
    //updates inv in DB
    public void updateInventory(String table, ArrayList<LineItem> lineItems, boolean neg){
        Iterator<LineItem> i = lineItems.iterator();
	while (i.hasNext()){
            LineItem li = i.next();
            //retrieve itemid and quantity from from lineitem
            int id = li.getProductDescription().getItemID();
            int q = li.getQuantity();
            //if rental return, negate q so it gets added
            if (neg)
                q = q * -1;
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
            String exQuery = "update "+table+" set qoh = "+newQ+" where id = "+id;
            try{
                stmt = conn.createStatement();
                stmt.executeUpdate(exQuery);
            } catch (SQLException ex) {
                System.out.println("Error updating QOH");
                closeConnection();
            }
        }
    }

//following methods deal with user management
    
    //gets unique user id for new employee
    //returns user id
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
    
    //adds employee to employees DB table
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
    
    //removes employee from employees DB table
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
    
    //initializes system EmployeeList from DB
    //returns EmployeeList object
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
    
    //returns the qoh of the given product id
    public int getQOH(int prodId, String prodType)
    {       
        String query = "select qoh from " + prodType + "products where id = " + prodId;
        int qoh = 0;
        
        try
        {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
            rs.next();
            qoh = rs.getInt("qoh");
        }
            
        catch(SQLException ex)
        {
            System.out.println("Error checking QOH");
            System.out.println(ex.getMessage());
        }
       
        return qoh;        
    }
}
