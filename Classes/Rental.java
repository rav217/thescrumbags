package thescrumbags.Classes;

import java.math.BigDecimal;
import java.util.*;

/**
 * Represents a Rental in a POS system
 * Extends Transaction
 * @author The Scrumbags
 */
public class Rental extends Transaction {
    
    private GregorianCalendar returnDate;
    private boolean returned;
    private int rentalPeriod;
    
    /**
     * No-arg constructor for Rental
     * Calls Transaction's default constructor
     */
    private Rental() { super(); }
    
    /**
     * Default constructor for Rental
     * Sets field values.
     * @param rentalPeriod 
     */
    public Rental(int rentalPeriod) {
        super();
        this.rentalPeriod = rentalPeriod;
        this.returnDate = new GregorianCalendar();
        this.returnDate.add(Calendar.DAY_OF_YEAR, rentalPeriod);
    }
    
    //need 2 arg constructor for DB purposes
    public Rental(ArrayList<LineItem> lineItems, Money total, int rentalPeriod, GregorianCalendar date){
        this.lineItems = lineItems;
        this.total = total;
        this.tax = total.multiply(new BigDecimal(Transaction.tax_rate));
        this.subtotal = total.subtract(tax);
        this.rentalPeriod = rentalPeriod; //added
        this.date = date;
        this.returnDate = date;
        this.returnDate.add(GregorianCalendar.DAY_OF_YEAR, rentalPeriod);
    }
    
    /**
     * Determines whether the rental has been returned
     * @return if rental has been returned
     */
    public boolean isReturned() { return returned; } 
    
    /**
     * Sets returned to true
     */
    public void completeReturn() { returned=true; }
    
    /**
     * Sets the rental period to numDays.
     * Creates new Calendar date to return items on
     * @param numDays the new rental period
     */
    public void setReturnDate(int numDays) { 
        rentalPeriod = numDays;
        GregorianCalendar returnItemsOn = new GregorianCalendar();
        returnItemsOn.add(Calendar.DAY_OF_YEAR, numDays);
        returnDate = returnItemsOn;
    }
    
    /**
     * Get method for return date
     * @return the return date
     */
    @Override
    public GregorianCalendar getReturnDate()  {
        return returnDate; 
    }
    
    /**
     * Creates a LineItem for given values
     * Overrides Transaction's makeLineItem to incorporate extra rental information 
     * @param desc entered product description
     * @param qty entered quantity
     * @return newly-created LineItem object
     */
    @Override
    public LineItem makeLineItem(ProductDescription desc, int qty) {
        LineItem lineItem = new LineItem(desc, qty);
        lineItems.add(lineItem);
        BigDecimal rentPeriodBD = new BigDecimal(rentalPeriod);
        
        // add item subtotal to subtotal
        Money tempSub = lineItem.getSubtotal().multiply(rentPeriodBD);
        this.subtotal = this.subtotal.add(tempSub);
        
        // add the subtotal * tax_rate to the tax
        Money tempTax = tempSub.multiply(new BigDecimal(tax_rate));
        this.tax = this.tax.add(tempTax);
        
        // add the subtotal and tax to the total
        Money tempTot = tempSub.add(tempTax);
        this.total = this.total.add(tempTot);
        
        return lineItem;
    }
    
    /**
     * Removes a LineItem at given index
     * @param index index of line item to remove
     */
    @Override
    public void removeLineItem(int index) {
        LineItem lineItem = lineItems.get(index);
        BigDecimal rentPeriodBD = new BigDecimal(rentalPeriod);
        
        // get lineItem subtotal and subtract from transaction subtotal
        Money tempSub = lineItem.getSubtotal().multiply(rentPeriodBD);
        this.subtotal = this.subtotal.subtract(tempSub);
        
        // get lineItem tax and subtract from transactions tax total
        Money tempTax = tempSub.multiply(new BigDecimal(tax_rate));
        this.tax = this.tax.subtract(tempTax);
        
        // get the sum of tempSub and tempTax and subtract from transaction total
        Money tempTot = tempSub.add(tempTax);
        this.total = this.total.subtract(tempTot);
        
        // remove the line item from the lineItems ArrayList
        this.lineItems.remove(index);
    }
    
    /**
     * Updates inventory in DB
     */
    @Override
    public void updateInventory() { //update inv
        DBHandler db = DBHandler.getInstance();
        db.openConnection("sql595207", "nT1*rF4!");
        this.id = db.addTransaction("R", this.lineItems, "", 0, this.date, this.rentalPeriod); //add rental period
        db.updateInventory("rentalproducts", this.lineItems, false);
        db.closeConnection();
        
    }
    
    @Override
    public Receipt makeNewReceipt() {
        this.receipt=new RentalReceipt();
        this.receipt.makeReceiptBody(this);
        return receipt;
    }
}
