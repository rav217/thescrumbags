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
      
      System.out.println("enter item id:");
      int rawID = in.nextInt(); //enter 1 for demo purposes
      ItemID itemID = new ItemID(rawID);
      
      //go search for itemID in DB and assign the ProductDescription to pd
      
      System.out.println("enter quantity:");
      int qty = in.nextInt();
      r.getCurrentSale().makeLineItem(pd, qty);
      
      //get SalesLineItem Info
      String d = pd.getDescription(); //only dependent on product
      double p = pd.getPrice().getAmount(); //only dependent on product
      double sT = pd.getPrice().getAmount() * qty;
     // double sT = r.getCurrentSale().lineItems.get(count).getSubtotal().getAmount();
      double rT = r.getCurrentSale().getTotal().getAmount(); //depends on previous products
      
      //display SalesLineItem info
      System.out.println("ProductDescription: "+d+"\tPrice: "+p+"\tQuantity: "+qty+"\tSubtotal: "+sT+"\tTotal: "+rT);
      
      //ask for more items
      System.out.println("more items? ('Y' or 'N'):");
      String more = in.next();
      if (more.equals("N"))
        r.endSale();
      
    } while(r.getCurrentSale().isComplete() == false);
    
    //make payment object
    
    System.out.println("enter type of payment ('CR' for credit, 'C' for cash): ");
    String pmtType = in.next();
    if (pmtType.equals("CR")){
      System.out.println("enter card number: ");
      String cardNum = in.next();
      r.getCurrentSale().makePayment(r.getCurrentSale().getTotal(), true, cardNum);
      System.out.println("Total amount: "+r.getCurrentSale().getPayment().getAmt().getAmount());
      System.out.println("Total amount plus tax: "+r.getCurrentSale().getPayment().calculateAmtPlusTax().getAmount());
      if (r.getCurrentSale().getPayment().doCreditCheck() == false)
        System.out.println("credit check failed");
        //exit or break back to some point
      else
        System.out.println("credit check successful");
    }
    else{
      r.getCurrentSale().makePayment(r.getCurrentSale().getTotal(), false, null);
      
    }
  }
}