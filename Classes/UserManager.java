/*
 * Robert Voit
 * CSE216
 */
package thescrumbags.Classes;

/**
 *
 * @author Bobby
 */

/**This class is the main class for handling the user management use case. It uses
 * our database to dynamically add and remove employees. It also uses two different
 * employeeLists to keep a local copy of the current users that are logged on and
 * all the other employees that are registered in the database. It is also singleton
 * and can have no more than one instance.
 * @author Bobby
 */
public class UserManager {

    private static UserManager uniqueInstance = null;
    private EmployeeList eList;
    private int userID;
    private final EmployeeList loggedOnEmployees;

    /**The constructor for this class. It initializes the two employeeLists. 
     * It loads in the eList from the database and makes a new instance of employeeList
     * for the logged on employees.
     */
    public UserManager() {
        DBHandler db = DBHandler.getInstance();
        db.openConnection("sql595207", "nT1*rF4!");
        this.eList = db.initializeEmployees();
        db.closeConnection();
        loggedOnEmployees = new EmployeeList();
    }

    /**Singleton getInstance class.*/
    public static synchronized UserManager getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new UserManager();
        }
        return uniqueInstance;
    }
    
    /** Returns the eList. */
    public EmployeeList getEmployeeList() {
        return eList;
    }
    
    /**Returns the list of currently logged on employees.*/
    public EmployeeList getLoggedOnList() {
        return loggedOnEmployees;
    }

    /**Method for adding a cashier to the database/ employeeList. Every time the
     * method is called it opens the database, makes a new employee with the given
     * arguments, adds it to the database, closes the database connection, adds it
     * to the employeeList, and also returns it.
     * @param name
     * @param password
     * @return 
     */
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

    /**Same as cashier method except with the boolean value for isManager set to
     * true.
     * @param name
     * @param password
     * @return 
     */
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
    
    /**This method is used to add an employee with already given data to the eList.
     * It is used by the database handler when loading in employees to populate the
     * local eList.
     * @param employeeID
     * @param isManager
     * @param employeeName
     * @param employeePassword
     * @return 
     */
    public Employee setExistingEmployee(int employeeID, Boolean isManager, String employeeName, String employeePassword){
        Employee newEmployee = new Employee(employeeID, isManager, employeeName, employeePassword);
        eList.addEmployee(newEmployee);
        return newEmployee;
    }

    /**This method takes in an int for employeeID and uses it to remove that
     * specific employee from the database and the eList. If the ID doesn't exist
     * a message is printed and nothing else happens.
     * @param employeeID 
     */
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
     * information is incorrect. Returns true if login is successful.
     * @param employeeId
     * @param password
     */
    public boolean login(int employeeId, String password) {
        Employee e = this.eList.findEmployee(employeeId);
        boolean successful = false;
        
        try {
            if (eList.checkPassword(employeeId, password)) {
                loggedOnEmployees.addEmployee(eList.findEmployee(employeeId));
                successful = true;
            }
        } catch (NullPointerException ex) {
            System.out.println("Error: employeeID not found.");
        }
        return successful;
    }
    /**Same as Login method except it will only take managers.
     * 
     * @param employeeId
     * @param password
     * @return 
     */
    public boolean checkManager(int employeeId, String password) {
        Employee e = this.eList.findEmployee(employeeId);
        boolean successful = false;
        
        try {
            if (e.isManager()) {
                if (eList.checkPassword(employeeId, password)) {
                    successful = true;
                }
            }
        } catch (NullPointerException ex) {
            System.out.println("Error: employeeID not found.");
        }
        return successful;
    }
    
    public boolean checkEmployee(int employeeId, String password) {
        boolean successful = false;
        
        try {
            if (eList.checkPassword(employeeId, password)) {
                successful = true;
            }
        }
        catch (NullPointerException ex) {
            System.out.println("Error: employeeID not found.");
        }
        return successful;
    }
    
    /**Takes in an employeeID and logs that employee out of the system.
     * If employee is not in the loggedOnList it catches the error and prints
     * out that no valid ID was entered.
     * @param employeeId
     * @return 
     */
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