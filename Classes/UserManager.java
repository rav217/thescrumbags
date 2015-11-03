/*
 * Robert Voit
 * CSE216
 */
package thescrumbags.Classes;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Bobby
 */
//add editing of information
public class UserManager {

    private static UserManager uniqueInstance = null;
    private EmployeeList eList;
    private int userID;
    private final EmployeeList loggedOnEmployees;

    public UserManager() {
        eList = new EmployeeList();
        loggedOnEmployees = new EmployeeList();
        userID = 0;
    }

    public static synchronized UserManager getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new UserManager();
        }
        return uniqueInstance;
    }

    public EmployeeList getEmployeeList() {
        return eList;
    }

    public EmployeeList getLoggedOnList() {
        return loggedOnEmployees;
    }

    public Employee addCashier(String name, String password) {
        userID++;
        //for now I'm going to assume there is valid input for name and password
        Employee newEmployee = new Employee(userID, false, name, password);
        eList.addEmployee(newEmployee);
        return newEmployee;
    }

    public Employee addManager(String name, String password) {
        userID++;
        //for now I'm going to assume there is valid input for name and password
        Employee newEmployee = new Employee(userID, true, name, password);
        eList.addEmployee(newEmployee);
        return newEmployee;
    }
    
    public Employee setExistingEmployee(int employeeID, Boolean isManager, String employeeName, String employeePassword){
        Employee newEmployee = new Employee(employeeID, isManager, employeeName, employeePassword);
        eList.addEmployee(newEmployee);
        return newEmployee;
    }

    public void removeEmployee(int employeeID) {
        if (eList.isEmployee(employeeID) == true) {
            eList.removeEmployee(employeeID);
        } else {
            System.out.println("No valid employee ID entered.");
        }
    }

    /**
     * Log employee into system. It can be looped in a main method if the login
     * information is incorrect.
     * @param employeeId
     * @param password
     */
    public void login(int employeeId, String password) {
        try {
            if (eList.checkPassword(employeeId, password)) {
                loggedOnEmployees.addEmployee(eList.findEmployee(employeeId));
            }
        } catch (NullPointerException e) {
            System.out.println("Error: employeeID not found.");
        }
    }

    public void logout(int employeeId) {
        try {
            loggedOnEmployees.removeEmployee(employeeId);
        } catch (NullPointerException e) {
            System.out.println("Error: employeeID not currently logged on.");
        }
    }
}