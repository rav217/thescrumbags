package thescrumbags.Classes;

/*
 CSE 216
 Robert Voit
 Employee Classes
 */

/**This class defines data associated with an employee. A boolean value determines
 whether an employee is a cashier or a manager. */
public class Employee{
  
  //datafields
  private int employeeID;
  private String employeeName;
  private String employeePassword;
  private Boolean isManager;
  
  /**Default constructor for the Employee class.*/
  public Employee(){
    this.employeeID = 0; //we will have to make a way of checking to see if an employee already exists
    this.employeeName = "unknown name";
    this.employeePassword = "defaultpassword"; //setting the default password
    isManager = false;
  }
  
  /**Constructor takes in an int for employeeID.
   * 
   * @param employeeID 
   */
    public Employee(int employeeID){
    this.employeeID = employeeID;
    this.employeeName = "unknown name";
    this.employeePassword = "defaultpassword"; //setting the default password
    isManager = false;
  }
  
   /**Constructor takes data for ID, name, and password. Manager is assumed to
    * be false.
    * @param employeeID
    * @param employeeName
    * @param employeePassword 
    */
   public Employee(int employeeID, String employeeName, String employeePassword){
    this.employeeID = employeeID;
    this.employeeName = employeeName;
    this.employeePassword = employeePassword;
    this.isManager = false;
  }
  
  /**This is the full constructor for the Employee object.*/
  public Employee(int employeeID, Boolean isManager, String employeeName, String employeePassword){
    this.employeeID = employeeID;
    this.employeeName = employeeName;
    this.employeePassword = employeePassword;
    this.isManager = isManager;
  }
  
  /**Get statements for the employee's name
   * 
   * @return 
   */
  public String getEmployeeName(){
    return employeeName;
  }
  
  /**Get statement for password.
   * 
   * @return 
   */
  public String getEmployeePassword(){
    return employeePassword;
  }
  
  /**Get id
   * 
   * @return 
   */
  public int getEmployeeID(){
    return employeeID;
  }
  
  /**Returns if the entered password is equal to the employee's recorded password.
   * 
   * @param password
   * @return 
   */
  public Boolean correctPassword(String password){
    return employeePassword.equals(password);
  }
  /**Print's the employee's data in a formatted version.*/
  public void printEmployee(){
      if(isManager == true){
        System.out.println("[ID:" + employeeID + " , Position: Manager , Name: " + employeeName + " ]");
      }
      else{
          System.out.println("[ID:" + employeeID + " , Position: Cashier , Name: " + employeeName + " ]");
      }
  }
  
  /**If the employee is a manager it will return true, else return false.*/
  public Boolean isManager(){
      return isManager;
  }
}
