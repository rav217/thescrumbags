package thescrumbags.Classes;

/*
 CSE 216
 Robert Voit
 Employee Classes
 */

public class Employee{
  
  //datafields
  private int employeeID;
  private String employeeName;
  private String employeePassword;
  private Boolean isManager;
  
  public Employee(){
    this.employeeID = 0; //we will have to make a way of checking to see if an employee already exists
    this.employeeName = "unknown name";
    this.employeePassword = "defaultpassword"; //setting the default password
    isManager = false;
  }
  
  public Employee(int employeeID){
    this.employeeID = employeeID;
    this.employeeName = "unknown name";
    this.employeePassword = "defaultpassword"; //setting the default password
    isManager = false;
  }
  
   public Employee(int employeeID,String employeeName, String employeePassword){
    this.employeeID = employeeID;
    this.employeeName = employeeName;
    this.employeePassword = employeePassword;
    this.isManager = false;
  }
  
  /**This is the constructor for the Employee object.*/
  public Employee(int employeeID, Boolean isManager, String employeeName, String employeePassword){
    this.employeeID = employeeID;
    this.employeeName = employeeName;
    this.employeePassword = employeePassword;
    this.isManager = isManager;
  }
  
  /**Get statements for the datafields*/
  public String getEmployeeName(){
    return employeeName;
  }
  /*
  //I may remove this method for privacy sake
  public String getEmployeePassword(){
    return employeePassword;
  }
  */
  public int getEmployeeID(){
    return employeeID;
  }
  
  /**Returns if the entered password is equal to the employee's recorded password.*/
  public Boolean correctPassword(String password){
    return employeePassword.equals(password);
  }
  
  public void printEmployee(){
      if(isManager == true){
        System.out.println("[ID:" + employeeID + " , Position: Manager , Name: " + employeeName + " ]");
      }
      else{
          System.out.println("[ID:" + employeeID + " , Position: Cashier , Name: " + employeeName + " ]");
      }
  }
  
  /**If the employee is a cashier it will return the char true, else return false.*/
  public Boolean isManager(){
      return isManager;
  }
}
