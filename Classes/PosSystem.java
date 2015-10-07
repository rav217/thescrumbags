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
    
    int count = 0;
    do{
      
      /*System.out.println("enter item id:");
      int rawID = in.nextInt(); //enter 1 for demo purposes
      ItemID itemID = new ItemID(rawID);*/
      
      //go search for itemID in DB and assign the ProductDescription to pd
      
      System.out.println("enter quantity:");
      int qty = in.nextInt();
      r.getCurrentSale().makeLineItem(pd, qty);
                  System.out.println(pd.getPrice().getAmount());
      //get SalesLineItem Info
      String d = pd.getDescription();
      double p = pd.getPrice().getAmount();            
      double sT = r.getCurrentSale().lineItems.get(count).getSubtotal().getAmount();
      
      //display SalesLineItem info
      System.out.println("ProductDescription: "+d+"\tPrice: "+p+"\tQuantity: "+qty+"\tSubtotal: "+sT);
      break;
     /* System.out.println("more items? Y or N:");
      String more = in.nextLine();
      if (more.equals("N"))
        r.endSale();*/
    } while(r.getCurrentSale().isComplete() == false);
  }
}