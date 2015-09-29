/*
 CSE 216
 Robert Voit
 Employee Class
 */

public abstract class Employee{
  
  //datafields
  private int employeeID;
  private String employeeName;
  private String employeePassword;
  
  
  /**This is the constructor for the Employee object.*/
  public Employee(int employeeID, String employeeName, String employeePassword){
    this.employeeID = employeeID;
    this.employeeName = employeeName;
    this.employeePassword = employeePassword;
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
  public abstract char getEmployeeType();
}