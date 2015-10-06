/*
 CSE 216
 Robert Voit
 Manager Class
 */

public class Manager extends Employee{
  
  public Manager(){
    super();
    isManager = true;
  }
  
  public Manager(int employeeID){
    super(employeeID);
    isManager = true;
  }
  
  //Constructor of Manager. It does not use any datafields that are unqiue to this class.*/
  public Manager(int employeeID, String employeeName, String employeePassword){
    super(employeeID, employeeName,employeePassword);
    isManager = true;
  }
  
  /**Get method for the description of this object*/
  public Boolean isManager(){
    return isManager;
   }
  
  /**These classes don't do anything yet because I'm not sure of their intended functionality.
    * I don't think they should be void, but I wanted to keep them concurrent with the 
    * class diagram for now. */
  public void openStore(){
   return; 
  }
  
  public void closeStore(){
   return; 
  }
  
  public void overridePrice(Money newAmount){
    return;
  }
  
  }