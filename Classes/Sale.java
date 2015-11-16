package thescrumbags.Classes;

import java.math.BigDecimal;
import java.util.ArrayList;

//This is the Sale class which stores a list of items being sold, the current date,
// the total and customer payment. It is able to add new line items to the sale, calculate the total,
// and tender a payment. Implements the java.util.Date class
public class Sale extends Transaction {

  //creates a new sale object, date reflects current date and time, isComplete
    //is set to false at object creation
    public Sale() {
        super();
    }
    
    //need constructor w 2 args for DB purposes
    public Sale(ArrayList<LineItem> lineItems, Money total){
        this.lineItems = lineItems;
        this.total = total;
    }
    
    public void calculateDiscount(BigDecimal discount) {
        BigDecimal one=new BigDecimal(1);
        BigDecimal bd=one.subtract(discount);
        total = total.multiply(bd);
    }
    
    @Override
    public boolean accept(Payment p) {
        return p.verify(this);
    }
    
    @Override
    public void updateInventory() { //add transaction to db, update inv
        DBHandler db = DBHandler.getInstance();
        db.openConnection("sql595207", "nT1*rF4!");
        db.addTransaction("S", this.lineItems, "", 0);
        db.updateInventory("saleproducts", this.lineItems, false);
        db.closeConnection();
    }
    
    @Override
    public Receipt makeNewReceipt() { 
        receipt=new SaleReceipt();
        receipt.makeReceipt(this);
        return receipt;
    }
}
