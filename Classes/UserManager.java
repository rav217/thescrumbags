/*
 * Robert Voit
 * CSE216
 */
package thescrumbags.Classes;

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
        DBHandler db = DBHandler.getInstance();
        db.openConnection("sql595207", "nT1*rF4!");
        this.eList = db.initializeEmployees();
        db.closeConnection();
        loggedOnEmployees = new EmployeeList();
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

    //when cashier added, update db (done)
    public Employee addCashier(String name, String password) {
        DBHandler db = DBHandler.getInstance();
        db.openConnection("sql595207", "nT1*rF4!");
        this.userID = db.getNextUserID(); //add to dbhandler
        //for now I'm going to assume there is valid input for name and password
        Employee newEmployee = new Employee(this.userID, false, name, password);
        db.addEmployee(newEmployee);
        db.closeConnection();
        eList.addEmployee(newEmployee);
        return newEmployee;
    }

    //when manager added, update db
    public Employee addManager(String name, String password) {
        DBHandler db = DBHandler.getInstance();
        db.openConnection("sql595207", "nT1*rF4!");
        this.userID = db.getNextUserID();
        //for now I'm going to assume there is valid input for name and password
        Employee newEmployee = new Employee(userID, true, name, password);
        db.addEmployee(newEmployee);
        db.closeConnection();
        eList.addEmployee(newEmployee);
        return newEmployee;
    }
    
    public Employee setExistingEmployee(int employeeID, Boolean isManager, String employeeName, String employeePassword){
        Employee newEmployee = new Employee(employeeID, isManager, employeeName, employeePassword);
        eList.addEmployee(newEmployee);
        return newEmployee;
    }

    //upon removal, update db
    public void removeEmployee(int employeeID) {
        if (eList.isEmployee(employeeID) == true) {
            eList.removeEmployee(employeeID);
            DBHandler db = DBHandler.getInstance();
            db.openConnection("sql595207", "nT1*rF4!");
            db.removeEmployee(employeeID);
            db.closeConnection();
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
    public boolean login(int employeeId, String password) {
        boolean successful = false;
        try {
            if (eList.checkPassword(employeeId, password)) {
                loggedOnEmployees.addEmployee(eList.findEmployee(employeeId));
                successful = true;
            }
        } catch (NullPointerException e) {
            System.out.println("Error: employeeID not found.");
        }
        return successful;
    }

    public boolean managerLogin(int employeeId, String password) {
        Employee e = this.eList.findEmployee(employeeId);
        boolean successful = false;
        
        try {
            if (e.isManager()) {
                if (eList.checkPassword(employeeId, password)) {
                    loggedOnEmployees.addEmployee(eList.findEmployee(employeeId));
                    successful = true;
                }
            }
        } catch (NullPointerException ex) {
            System.out.println("Error: employeeID not found.");
        }
        return successful;
    }
    
    public boolean logout(int employeeId) {
        boolean successful = false;
        try {
            loggedOnEmployees.removeEmployee(employeeId);
            successful = true;
        } catch (NullPointerException e) {
            System.out.println("Error: employeeID not currently logged on.");
        }
        return successful;
    }
}