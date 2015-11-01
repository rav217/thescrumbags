/*
 * Robert Voit
 * CSE216
 */
package thescrumbags.Classes;

import java.util.ArrayList;

/**
 *
 * @author Bobby
 */

//add editing of information
public class UserManager {
    
    private EmployeeList eList;
    private int userID;
    private EmployeeList loggedOnEmployees;
    
    public UserManager(){
     eList = new EmployeeList();
     loggedOnEmployees = new EmployeeList();
     userID = 0;
    }
    
    public EmployeeList getEmployeeList(){
        return eList;
    }
    
    public EmployeeList getLoggedOnList(){
        return loggedOnEmployees;
    }
    
    public void addCashier(String name, String password){
    userID++;
    //for now I'm going to assume there is valid input for name and password
    Employee newEmployee = new Employee(userID, false, name, password);
    eList.addEmployee(newEmployee);
    }
    
    public void addManager(String name, String password){
    userID++;
    //for now I'm going to assume there is valid input for name and password
    Employee newEmployee = new Employee(userID, true, name, password);
    eList.addEmployee(newEmployee);
    }
    
    public void removeEmployee(int employeeID){
        if(eList.isEmployee(employeeID) == true){
            eList.removeEmployee(employeeID);
        }
        else{
            System.out.println("No valid employee ID entered.");
        }
    }
    
    /**Log employee into system. It can be looped in a main method if the
    login information is incorrect. */
    public void login(int employeeId, String password){
        try{
        if(eList.checkPassword(employeeId, password)){
            loggedOnEmployees.addEmployee(eList.findEmployee(employeeId));
        }
        }
        catch(NullPointerException e){
            System.out.println("Error: employeeID not found.");
        }
    }
    
    public void logout(int employeeId){
        try{
            loggedOnEmployees.removeEmployee(employeeId);
        }
        catch(NullPointerException e){
            System.out.println("Error: employeeID not currently logged on.");
        }
    }
    
    public static void main(String[] args){
        UserManager userManager = new UserManager();
        userManager.addManager("John", "manager123");
        userManager.addManager("Jim", "cashier123");
        userManager.addCashier("James", "cashier123");
        
        
        
        //System.out.println("Madeit");
    }
    
}
