/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thescrumbags.Classes;

import java.util.*;

//This is the Sale class which stores a list of items being sold, the current date,
// the total and customer payment. It is able to add new line items to the sale, calculate the total,
// and tender a payment. Implements the java.util.Date class
public class Transaction {

    protected ArrayList<LineItem> lineItems = new ArrayList<>();
    //date also functions as transaction ID
    protected Date date;
    protected boolean isComplete;
    protected Money total;
    protected Employee cashier;

  //creates a new transaction object, date reflects current date and time, isComplete
    //is set to false at object creation
    public Transaction() {
        this.date = new Date();
        this.isComplete = false;
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

  //creates a new LineItem for the sale given a description and quantity,
    //adds new LineItem to the lineItems ArrayList, total updated with new subtotal
    public void makeLineItem(ProductDescription desc, int qty) {
        LineItem lineItem = new LineItem(desc, qty);
        lineItems.add(lineItem);
        total = total.add(lineItem.getSubtotal());
    }

    public void removeLineItem(int index) {
        this.lineItems.remove(index);
    }
    
    public LineItem getLineItem(int index) {
        return this.lineItems.get(index);
    }

    public LineItem getLastLineItem() {
        return this.lineItems.get(this.lineItems.size() - 1);
    }

    //returns the current total for the Sale object
    public Money getTotal() {
        return this.total;
    }
    
    public void accept(Payment p) {}
    
    public void updateInventory() {}
}
