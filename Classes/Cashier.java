package thescrumbags.Classes;

/*
 CSE 216
 Robert Voit
 Cashier Class
 */

public class Cashier extends Employee{
  
  public Cashier(){
    super();
    isManager = false;
  }
  
  public Cashier(int employeeID){
    super(employeeID);
    isManager = false;
  }
  
  //Constructor of Cashier. It does not use any datafields that are unqiue to this class.*/
  public Cashier(int employeeID, String employeeName, String employeePassword){
    super(employeeID, employeeName,employeePassword);
    isManager = false;
  }
  
  /**Get method for the description of this object*/
  public Boolean isManager(){
    return isManager;
   }
  }