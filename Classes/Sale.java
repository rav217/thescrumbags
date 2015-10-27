package thescrumbags.Classes;

import java.util.ArrayList;
import java.util.Date;

//This is the Sale class which stores a list of items being sold, the current date,
// the total and customer payment. It is able to add new line items to the sale, calculate the total,
// and tender a payment. Implements the java.util.Date class


public class Sale {
  private ArrayList<SalesLineItem> lineItems = new ArrayList<>();
  private Date time;
  private boolean isComplete;
  private Payment payment;
  private Money total;

  //creates a new sale object, date reflects current date and time, isComplete
  //is set to false at object creation
  public Sale() {
    this.time = new Date();
    this.isComplete = false;
    this.payment = null;
    this.total = new Money(0);
  }

  //sets isComplete to true, called at time of sale completion
  public void becomeComplete() {
    this.isComplete = true;
  }

  //returns objects isComplete
  public boolean isComplete() {
    return this.isComplete;
  }

  //creates a new SalesLineItem for the sale given a description and quantity,
  //adds new SalesLineItem to the lineItems ArrayList, total updated with new subtotal
  public void makeLineItem(ProductDescription desc, int qty) {
      SalesLineItem lineItem = new SalesLineItem(desc, qty);
      lineItems.add(lineItem);
      total = total.add(lineItem.getSubtotal());
  }
  
  public SalesLineItem getLineItem(int index) {
      return this.lineItems.get(index);
  }
  
  public SalesLineItem getLastLineItem() {
      return this.lineItems.get(this.lineItems.size() - 1);
  }

  public SalesLineItem removeLineItem(int i) throws NullPointerException{
      SalesLineItem removed = this.lineItems.remove(i);
      System.out.println("---------- REMOVED ----------");
      removed.print();
      System.out.println("-----------------------------");
      return removed;
  }
  //returns the current total for the Sale object
  public Money getTotal() {
    return this.total;
  }

  //subtracts payment from the total for the Sale object
  public void makePayment(Money amt, boolean credit, String cardNum) {
    //TODO: need to figure out this method
    this.payment = new Payment(amt, credit, cardNum);
    if (payment.isCredit()) {
      //verify credit payment through 3rd party
      this.becomeComplete();
    }
    else {
      Money change = payment.getAmt().subtract(this.getTotal());
      if (change.getAmount() > 0) {
        //tender change to customer
      }
      else if (change.getAmount() < 0) {
        //throw error, they did not have enough money pay for goods
      }
      this.becomeComplete();
    }
  }

  public Payment getPayment(){
    return this.payment;
  }
  
  //calculates discount and adds it into the total, discount is a decimal describing the percentage discount
  public void calculateDiscount(float discount) {
    //where does this discount come from? is it user input?

    //compute total with discount
    total = total.times(1 - discount);
  }
  
  public void printCurrentLineItems() {
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
     this.printCurrentLineItems();
  }
}
