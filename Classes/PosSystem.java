package thescrumbags.Classes;

//main method to initialize store and run the system

import java.util.Scanner;


public class PosSystem{
  
  public static void main(String[] args)
  {
    Store store = Store.getInstance();
    
    //load in three products (temporary)
    Money m1 = new Money(100.0);
    Money m2 = new Money(50.0);
    Money m3 = new Money(25.0);
    int prodID1 = 1;
    int prodID2 = 2;
    int prodID3 = 3;
    ProductDescription pd1 = new ProductDescription(prodID1, m1, "shirt");
    ProductDescription pd2 = new ProductDescription(prodID2, m2, "shorts");
    ProductDescription pd3 = new ProductDescription(prodID3, m3, "pants");
    
    store.getCatalog().add(pd1);
    store.getCatalog().add(pd2);
    store.getCatalog().add(pd3);
    
    store.getRegister().makeNewSale();
    Register r = store.getRegister();
    
    boolean moreItems = true;
    String response;
    int quantity, rawID;
    int itemID;
    ProductDescription pd;
    Scanner s = new Scanner(System.in);
    int count = 0;
    double rT;
    
    do
    {  
      //get itemid and quantity for each sales line item
      System.out.println("Enter item ID");
      itemID = s.nextInt(); 
      if(!r.getCatalog().getCatalog().containsKey(itemID))
      {
          System.out.println("Please enter a valid item id");
          continue;
      }
      System.out.println("Enter quantity");
      quantity = s.nextInt();
      
      //get product description for the given item id
      try {
        pd = store.getCatalog().getProductDescription(itemID);
        count++;

        r.getCurrentSale().makeLineItem(pd, quantity);
        //get SalesLineItem Info
        String d = pd.getDescription(); //only dependent on product
        double p = pd.getPrice().getAmount(); //only dependent on product
        double sT = pd.getPrice().getAmount() * quantity;
        rT = r.getCurrentSale().getTotal().getAmount(); //depends on previous products

        //display SalesLineItem info
        //System.out.println("ProductDescription: "+d+"\tPrice: "+p+"\tQuantity: "+quantity+"\tSubtotal: "+sT+"\tTotal: "+rT);
        r.getCurrentSale().printCurrentLineItems();

        System.out.println("Press \"Y\" to continue shopping, \"R\" to remove an item, or \"N\" to checkout? (Y/N)");
        response = s.next();
        if(response.equals("N") || response.equals("n"))
        {
          r.endSale();
        }
        else if(response.equals("R") || response.equals("r")) {
            System.out.println("Enter the line number to remove");
            int toRemove = s.nextInt();
            r.getCurrentSale().removeLineItem(toRemove);
        }
        else if(!response.equals("Y") && !response.equals("y"))
        {
          System.out.println("Please enter Y or N");
        }
      }
      catch (NullPointerException npe){
        System.out.println("The item id you entered is invalid, please try again");
      }
    } while(r.getCurrentSale().isComplete() == false);
    
    r.getCurrentSale().printReceipt();
    
    System.out.println("enter type of payment ('CR' for credit, 'C' for cash): ");
    String pmtType = s.next();
    if (pmtType.equals("CR") || pmtType.equals("cr")){
      System.out.println("enter card number: ");
      String cardNum = s.next();
    }
  }
}
