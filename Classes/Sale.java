package thescrumbags.Classes;

import java.math.BigDecimal;
import java.util.ArrayList;

//This is the Sale class which stores a list of items being sold, the current date,
// the subtotal and customer payment. It is able to add new line items to the sale, calculate the subtotal,
// and tender a payment. Implements the java.util.Date class
public class Sale extends Transaction {
    
  //creates a new sale object, date reflects current date and time, isComplete
    //is set to false at object creation
    public Sale() {
        super();
    }
    
    //need constructor w 2 args for DB purposes
    public Sale(ArrayList<LineItem> lineItems, Money total){
        System.out.println("In Sale 2 arg constructor");
        this.lineItems = lineItems;
        this.total = total;
        this.tax = total.multiply(new BigDecimal(Transaction.tax_rate));
        this.subtotal = total.subtract(tax);
    }
    
    public void calculateDiscount(BigDecimal discount) {
        BigDecimal one=new BigDecimal(1);
        BigDecimal bd=one.subtract(discount);
        subtotal = subtotal.multiply(bd);
    }
    
    @Override
    public void updateInventory() { //add transaction to db, update inv
        DBHandler db = DBHandler.getInstance();
        db.openConnection("sql595207", "nT1*rF4!");
        db.addTransaction("S", this.lineItems, "", 0, this.date, 0);
        db.updateInventory("saleproducts", this.lineItems, false);
        db.closeConnection();
    }
    
    @Override
    public Receipt makeNewReceipt() { 
        this.receipt=new Receipt();
        this.receipt.makeReceiptBody(this);
        return receipt;
    }
}
