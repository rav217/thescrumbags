import java.util.ArrayList;
import java.util.Date;

//This is the Sale class which stores a list of items being sold, the current date,
// the total and customer payment. It is able to add new line items to the sale, calculate the total,
// and tender a payment. Implements the java.util.Date class


public class Sale {
  ArrayList<SalesLineItem> lineItems = new ArrayList<>();
  Date time;
  boolean isComplete;
  Payment payment;
  Money total;

  //creates a new sale object, date reflects current date and time, isComplete
  //is set to false at object creation
  public Sale() {
    this.time = new Date();
    this.isComplete = false;
    this.payment = null;
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
    if (!this.isComplete()) {
      SalesLineItem lineItem = new SalesLineItem(desc, qty);
      lineItems.add(lineItem);
      total = total.add(lineItem.getSubtotal());
    }
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

  //calculates discount and adds it into the total, discount is a decimal describing the percentage discount
  public void calculateDiscount(float discount) {
    //where does this discount come from? is it user input?

    //compute total with discount
    total = total.times(1 - discount);
  }
}
