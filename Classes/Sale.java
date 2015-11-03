package thescrumbags.Classes;

import java.math.BigDecimal;

//This is the Sale class which stores a list of items being sold, the current date,
// the total and customer payment. It is able to add new line items to the sale, calculate the total,
// and tender a payment. Implements the java.util.Date class
public class Sale extends Transaction {

    private Payment payment;
  //creates a new sale object, date reflects current date and time, isComplete
    //is set to false at object creation
    public Sale() {
        super();
    }
    
    public Payment getPayment() {
        return this.payment;
    }
    
    public void calculateDiscount(BigDecimal discount) {
        BigDecimal one=new BigDecimal(1);
        BigDecimal bd=one.subtract(discount);
        total = total.multiply(bd);
    }
    
    @Override
    public void accept(Payment p) {
        boolean b=p.verify(this);
        if(!b) {
            System.out.println("Payment was not accepted.");
        }
    }
    
    @Override
    public void updateInventory() { //add transaction to db, update inventory
        DBHandler db = DBHandler.getInstance();
        db.openConnection("sql595207", "nT1*rF4!");
        db.addTransaction("S");
        db.closeConnection();
    }
}
