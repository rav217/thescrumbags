/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thescrumbags.Classes;


import java.util.ArrayList;
import java.util.Date;

//This is the Sale class which stores a list of items being sold, the current date,
// the total and customer payment. It is able to add new line items to the sale, calculate the total,
// and tender a payment. Implements the java.util.Date class


public class Transaction {
  ArrayList<SalesLineItem> lineItems = new ArrayList<>();
  Date time;
  boolean isComplete;
  Payment payment;
  Money total;

  //creates a new transaction object, date reflects current date and time, isComplete
  //is set to false at object creation
  public Transaction() {
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
}
