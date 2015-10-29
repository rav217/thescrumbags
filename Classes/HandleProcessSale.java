package thescrumbags.Classes;

import java.util.Scanner;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author TJ
 */

//handles the Process Sale use case
public class HandleProcessSale {
 
  public static void doProcessSale()
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
    
    store.getRegister().makeNewTransaction();
    Register r = store.getRegister();
    
    String response;
    int quantity;
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
      pd = store.getCatalog().getProductDescription(itemID);
      count++;
      
      r.getCurrentTransaction().makeLineItem(pd, quantity);
      //get SalesLineItem Info
      String d = pd.getDescription(); //only dependent on product
      double p = pd.getPrice().getAmount(); //only dependent on product
      double sT = pd.getPrice().getAmount() * quantity;
      rT = r.getCurrentTransaction().getTotal().getAmount(); //depends on previous products
      
      //display SalesLineItem info
      System.out.println("ProductDescription: "+d+"\tPrice: "+p+"\tQuantity: "+quantity+"\tSubtotal: "+sT+"\tTotal: "+rT);
      
      
      System.out.println("Does the customer have more items? (Y/N)");
      response = s.next();
      if(response.equals("N") || response.equals("n"))
      {
        r.endTransaction();
      }
      else if(!response.equals("Y") && !response.equals("y"))
      {
        System.out.println("Please enter Y or N");
      }
      
    }while(r.getCurrentTransaction().isComplete() == false);
    
    System.out.println("enter type of payment ('CR' for credit, 'C' for cash): ");
    String pmtType = s.next();
    if (pmtType.equals("CR")){
      System.out.println("enter card number: ");
      String cardNum = s.next();
    }
  }
}