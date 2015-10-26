package thescrumbags.Classes;

/*
 * Robert Voit
 * CSE216
 * Employee List Class
 * */

import java.util.ArrayList;

/**This class is where the employee objects will be stored. It has various methods for checking/ modifying
  * the Employee data. I can make more methods if neccesary. It will be primarily used by the UserManagement
  * class. I haven't written that class yet (10/22/15), but I will get on that next week. I haven't run 
  * many tests on it yet, but it is on the to do list.*/
public class EmployeeList{
  
  private ArrayList<Employee> employeeList;
  
  /**Constructor.*/
  public EmployeeList(){
    employeeList = new ArrayList<Employee>();
  }
  
  /**This mehtod is used to populate the arraylist*/
  public void addEmployee(Employee newEmployee){
    employeeList.add(newEmployee);
  }
  
  /**If an Employee is fired, this method will remove them.*/
  public void removeEmployee(int employeeID){
    Employee e = this.findEmployee(employeeID);
    if(e == null){
      throw new NullPointerException("employeeID " +employeeID+ " is not a part of employeeList.(RE)");
    }
    employeeList.remove(e);
  }
  
  /**Checks to see if a given password is correct for the employee in question.*/
  public Boolean checkPassword(int employeeID, String password){
    Employee e = this.findEmployee(employeeID);
    if(e == null){
      throw new NullPointerException("employeeID " +employeeID+ " is not a part of employeeList.(CP)");
    }
    return e.correctPassword(password);
  }
  
  /**Most likely not needed, but I have it just in case.*/
  public int getEmployeeCount(){
    return employeeList.size();
  }
  
  /*Returns if the employeeID is in the arraylist or not*/
  public Boolean isEmployee(int employeeID){ //make loop to find employee using ID
    Boolean result = false;
    Employee e = this.findEmployee(employeeID);
    if(e != null){
      result = true;
    }
    return result;
  }
  
  /*Helper function for this class*/
  private Employee findEmployee(int employeeID){
    Employee result = null;
    for(int i = 0; i < employeeList.size(); i++){
      if(employeeList.get(i).getEmployeeID() == employeeID){
        result = employeeList.get(i);
      }
    }
    return result;
  }
}