//This is the Sale class which stores a list of items being sold, the current date,
// the total and customer payment. It is able to add new line items to the sale, calculate the total,
// and tender a payment.

public class Sale {
  ArrayList<SalesLineItem> lineItems = new ArrayList<SalesLineItem>;
  Date time;
  boolean isComplete;
  Payment payment;
  Money total;

  //creates a new sale object, date reflects current date and time, isComplete
  //is set to false at object creation
  public Sale() {
    this.time = java.util.Date("yyyy-MM-dd'T'HH:mm:ss");
    this.isComplete = false;
  }

  //sets isComplete to true, called at time of sale completion
  public void becomeCompleted() {
    this.isComplete = true;
  }

  //returns objects isCompleted
  public boolean isCompleted() {
    return this.isComplete;
  }

  //creates a new SalesLineItem for the sale given a description and quantity,
  //adds new SalesLineItem to the lineItems ArrayList, total updated with new subtotal
  public void makeLineItem(ProductDescription desc, int qty) {
    lineItem = new SalesLineItem(desc, qty);
    lineItems.add(lineItem);
    total += lineItem.getSubtotal();
  }

  //returns the current total for the Sale object
  public Money getTotal() {
    return this.total;
  }

  //subtracts payment from the total for the Sale object
  public void makePayment(Payment payment) {
    //TODO: need to figure out this method
  }

  //calculates discount and adds it into the total, discount is a decimal describing the percentage discount
  public void calculateDiscount() {
    float discount;
    //where does this discount come from? is it user input?

    //compute total with discount
    total *= 1 - discount);
  }
}
