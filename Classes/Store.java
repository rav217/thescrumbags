//Store class: singleton class containing ProductCatalog and Register attributes
import java.util.ArrayList;

public class Store{
 
 private static Store uniqueInst;
 private ProductCatalog catalog;
 private Register register;
 private ArrayList<Employee> employees;
 
 //singleton getInstance() method
 public static synchronized Store getInstance(){
  if (uniqueInst == null)
   uniqueInst = new Store();
  return uniqueInst;
 }
 //constructor
 public Store()
 {
  this.catalog = new ProductCatalog();
  this.register = new Register(catalog);
  this.employees = new ArrayList<Employee>();
 }
 
 //get catalog
 public ProductCatalog getCatalog(){
  return this.catalog;
 }
 
 //get register
 public Register getRegister(){
  return this.register;
 }
 
 //get employee list
 public ArrayList<Employee> getEmployees(){
  return this.employees;
 }
}