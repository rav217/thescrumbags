package thescrumbags.Classes;

/*
 CSE 216
 Robert Voit
 Employee Class
 */

public abstract class Employee{
  
  //datafields
  protected int employeeID;
  protected String employeeName;
  protected String employeePassword;
  protected Boolean isManager;
  
  public Employee(){
    this.employeeID = 0; //we will have to make a way of checking to see if an employee already exists
    this.employeeName = "unknown name";
    this.employeePassword = "defaultpassword"; //setting the default password
    isManager = null;
  }
  
  public Employee(int employeeID){
    this.employeeID = employeeID;
    this.employeeName = "unknown name";
    this.employeePassword = "defaultpassword"; //setting the default password
    isManager = null;
  }
  
  /**This is the constructor for the Employee object.*/
  public Employee(int employeeID, String employeeName, String employeePassword){
    this.employeeID = employeeID;
    this.employeeName = employeeName;
    this.employeePassword = employeePassword;
    isManager = null;
  }
  
  /**Get statements for the datafields*/
  public String getEmployeeName(){
    return employeeName;
  }
  
  //I may remove this method for privacy sake
  public String getEmployeePassword(){
    return employeePassword;
  }
  
  public int getEmployeeID(){
    return employeeID;
  }
  
  /**Returns if the entered password is equal to the employee's recorded password.*/
  public Boolean correctPassword(String password){
    return employeePassword.equals(password);
  }
  
  /**abstract method to be defined by subclass. If the employee is a cashier it will return the char 'c'.
    * It they are a manager it will return 'm'*/
  public abstract Boolean isManager();
}