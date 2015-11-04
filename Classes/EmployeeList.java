package thescrumbags.Classes;

/*
 * Robert Voit
 * CSE216
 * Employee ListClass
 * */

import java.util.ArrayList;

/**This class is where the employee objects will be stored. It has various methods for checking/ modifying
  * the Employee data. I can make more methods if neccesary. It will be primarily used by the UserManagement
  * class.*/
public class EmployeeList{
  
  private ArrayList<Employee> employeeList;
  
  /**Constructor for this class.*/
  public EmployeeList(){
    employeeList = new ArrayList<Employee>();
  }
  
  /**Returns the arrayList of this class.*/
  public ArrayList<Employee> getEList() {
      return employeeList;
  }
  
  /**This method is used to populate the arraylist*/
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
  
  
  /**Returns the size of the arrayList.*/
  public int getEmployeeCount(){
    return employeeList.size();
  }
  
  /**Returns if the employeeID is in the arraylist or not*/
  public Boolean isEmployee(int employeeID){ //make loop to find employee using ID
    Boolean result = false;
    Employee e = this.findEmployee(employeeID);
    if(e != null){
      result = true;
    }
    return result;
  }
  
  /**Takes in an ID and if it is in the list it returns the employee reference.
   * Else, returns null.*/
  public Employee findEmployee(int employeeID){
    Employee result = null;
    for(int i = 0; i < employeeList.size(); i++){
      if(employeeList.get(i).getEmployeeID() == employeeID){
        result = employeeList.get(i);
      }
    }
    return result;
  }
  
  /**Prints out a formatted version of the employeeList */
  public void printList(){
      if(employeeList.size() == 0){
          System.out.println("{");
          System.out.println("\t The employeeList is empty.");
          System.out.println("}");
      }
      else{
        System.out.println("{");
        for(int i = 0; i<employeeList.size(); i++){
            System.out.print("\t");
            employeeList.get(i).printEmployee();
        }
        System.out.println("}");
    }
  }
  
}