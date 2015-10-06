//main method to initialize store and run the system

import java.util.Scanner;

public class PosSystem{
  
  public static void main(String[] args){
    
    Scanner in = new Scanner(System.in);
 
    //creating Store object also creates ProductCatalog, Register,  and ArrayList<Employee>
    Store s = Store.getInstance();
    
    //create ProductDescription object
    Money price = new Money(4.5);
    String descr = "a product description";
    ItemID id = new ItemID(1);
    ProductDescription pd = new ProductDescription(id, price, descr);
    
    //add ProductDescription to ProductCatalog
    s.getCatalog().add(pd, id);
    
    //start a new sale
    Register r = s.getRegister();
    r.makeNewSale();
    
    //while sale is not complete
    do{
      r.getCurrentSale().makeLineItem(pd, 1); //qty is 1 for demo
      System.out.println("More items? Y or N");
      String more = in.nextLine();
      if (more.equals("N"))
        r.endSale();
    } while(r.getCurrentSale().isComplete() == false);
  }
}