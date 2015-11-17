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
        System.out.println(this.returnDate);
    }
    
    //need 2 arg constructor for DB purposes
    public Rental(ArrayList<LineItem> lineItems, Money total){
        this.lineItems = lineItems;
        this.total = total;
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
        total = total.add(lineItem.getSubtotal().multiply(new BigDecimal(rentalPeriod)));
        return lineItem;
    }
    
    /**
     * Removes a LineItem at given index
     * @param index index of line item to remove
     */
    @Override
    public void removeLineItem(int index) {
        LineItem lineItem = lineItems.get(index);
        total = total.subtract(lineItem.getSubtotal());
        this.lineItems.remove(index);
    }
    
    /**
     * Accepts a given Payment
     * Passes itself to Payment's verify()
     * @param p given payment
     * @return whether or not payment went through
     */
    @Override
    public boolean accept(Payment p) {
        return p.verify(this);  
    }
    
    /**
     * Updates inventory in DB
     */
    @Override
    public void updateInventory() { //update inv
        DBHandler db = DBHandler.getInstance();
        db.openConnection("sql595207", "nT1*rF4!");
        db.addTransaction("R", this.lineItems, "", 0);
        db.updateInventory("rentalproducts", this.lineItems, false);
        db.closeConnection();
        
    }
    
    @Override
    public Receipt makeNewReceipt() {
        receipt=new RentalReceipt();
        receipt.makeReceiptBody(this);
        return receipt;
    }
}
