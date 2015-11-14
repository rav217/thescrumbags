package thescrumbags.Classes;

import java.math.BigDecimal;

/**
 * Represents a transaction to return inventory.
 * Subclass of Transaction.
 * Grabs Sale object corresponding to passed sale ID number.
 * Verifies (with receipt) that the entered items were indeed purchased.
 * If so, creates a Reimbursement object.
 */
public class SaleReturn extends Transaction {
   
    private final Transaction sale;
    private Reimbursement r;
    private final String reason;
    private final int saleID;
    private int itemsReturned=0;
    
    /**
     * Constructor for SaleReturn.
     * First calls Transaction's constructor.
     * Grabs the sale from DB.
     * saleID is the date with all symbols removed.
     * @param saleID the ID number printed on the original receipt
     * @param reason the reason for the return
     */
    public SaleReturn(int saleID, String reason) {
        super();
        this.saleID=saleID;
        this.reason=reason;
        
        //add neccessary dbhandler code
        DBHandler db = DBHandler.getInstance();
        db.openConnection("sql595207", "nT1*rF4!");
        this.sale = db.findTransaction("S", saleID);
        db.closeConnection();
        if (this.sale == null) System.out.println("Sale not found in database");
    }
    
    /**
     * Get method for sale
     * @return sale corresponding to the sale return
     */
    public Transaction getSale() { return sale; }
    
    /**
     * Get method for reason
     * @return the reason these items were returned
     */
    public String getReason() { return reason; }
    
    /**
     * Get method for sale ID.
     * Sale ID number is just the date with all symbols removed.
     * @return the sale ID
     */
    public int getSaleID() { return saleID; } 
    
    /**
     * After the total is calculated by Transaction methods, negates the Money amount.
     * For returns, the total should be negative.
     * @return the new transaction total
     */
    public Money negateTotal() {
        return getTotal().multiply(new BigDecimal(-1));
    }
    
    /**
     * Overrides Transaction's becomeComplete method.
     * itemsReturned, counts the total number of items returned before completion.
     */
    @Override
    public void becomeComplete() {
        itemsReturned=lineItems.size();
        super.becomeComplete();     
    }
    
    /**
     *Add sale return to transactionhistory, don't update inventory
     */
    @Override
    public void updateInventory() {
        //use dbh, itemsReturned and lineItems to update inventory
        DBHandler db = DBHandler.getInstance();
        db.openConnection("sql595207", "nT1*rF4!");
        db.addTransaction("S", lineItems);
        db.updateInventory("saleproducts", lineItems, true);
        db.closeConnection();
    }
    
    /**
     * Accept method for sale return.
     * Will be accepting a reimbursement instead of a payment.
     * @param r reimbursement for the customer
     * @return whether or not the reimbursement went through
     */
    @Override
    public boolean accept(Reimbursement r) {
        return r.verify(this);
    }
}
