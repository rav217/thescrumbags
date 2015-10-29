package thescrumbags.Classes;

import java.util.ArrayList;
import java.util.Date;

//This is the Sale class which stores a list of items being sold, the current date,
// the total and customer payment. It is able to add new line items to the sale, calculate the total,
// and tender a payment. Implements the java.util.Date class


public class Sale extends Transaction{
  //creates a new sale object, date reflects current date and time, isComplete
  //is set to false at object creation
  public Sale() {
    this.time = new Date();
    this.isComplete = false;
    this.payment = null;
    this.total = new Money(0);
  }
  
  @Override
  public void makeLineItem(ProductDescription desc, int qty) {
      SalesLineItem lineItem = new SalesLineItem(desc, qty);
      lineItems.add(lineItem);
      total = total.add(lineItem.getSubtotal());
  }
    
  public SalesLineItem removeLineItem(int i) throws NullPointerException{
      SalesLineItem removed = lineItems.remove(i);
      return removed;
  }
  //returns the current total for the Sale object
  
  public void printItemsInCart() {
      System.out.println("----- ITEMS IN CART -----");
      for (int i = 0; i < lineItems.size(); i++) {
          System.out.print(i);
          lineItems.get(i).print();
      }
      System.out.println("-------------------------");
      System.out.printf("Total: $%4.2f\n", this.total.getAmount());
      System.out.println("-------------------------");
  }
  
  public void printReceipt() {
     System.out.println("Thank you for shopping with the Scrumbags POS system");
     System.out.println("                 Enjoy your day!                    ");
     this.printItemsInCart();
  }
}
