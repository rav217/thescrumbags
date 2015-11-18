package thescrumbags.Classes;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.math.BigDecimal;

/**
 * The Transaction class stores a list of items being sold or rented.
 * Stores the current date, the total, and customer payment/reimbursement
 * Adds new line items to the transaction, calculates total, and processes payments
 * @author The Scrumbags
 */
public abstract class Transaction {

    protected ArrayList<LineItem> lineItems = new ArrayList<>();
    //date also functions as transaction ID
    protected GregorianCalendar date;
    protected boolean isComplete;
    protected Money total;
    protected Receipt receipt;
    protected boolean isCredit;
    protected String ccNum;

    /**
     * Default constructor.
     * Creates new Calendar for the current date, sets total to 0
     */
    public Transaction() {
        this.date = new GregorianCalendar();
        this.isComplete = false;
        this.total = new Money(new BigDecimal(0));
        this.isCredit=false;
        this.ccNum=null;
    }
    

    public abstract Receipt makeNewReceipt();
    
    /**
     * 2-arg constructor for Transaction
     * Sets lineItems to li, total to total
     * @param li new line items
     * @param total new total
     */
    public Transaction(ArrayList<LineItem> li, Money total){
        this.lineItems = li;
        this.total = total;
    }

    /**
     * Sets isComplete to true, called at time of sale completion
     */
    public void becomeComplete() {
        this.isComplete = true;
    }
    
    /**
     * Returns whether Transaction is complete
     * @return if complete
     */
    public boolean isComplete() {
        return this.isComplete;
    }

  //creates a new LineItem for the sale given a description and quantity,
    //adds new LineItem to the lineItems ArrayList, total updated with new subtotal
    public LineItem makeLineItem(ProductDescription desc, int qty) {
        LineItem lineItem = new LineItem(desc, qty);
        lineItems.add(lineItem);
        total = total.add(lineItem.getSubtotal());
        return lineItem;
    }
    
    public LineItem makeLineItem(LineItem li) {
        LineItem lineItem = new LineItem(li);
        lineItems.add(lineItem);
        total = total.add(lineItem.getSubtotal());
        return lineItem;
    }

    public void removeLineItem(int index) {
        LineItem lineItem = lineItems.get(index);
        total = total.subtract(lineItem.getSubtotal());
        this.lineItems.remove(index);
    }
    
    public LineItem getLineItem(int index) {
        return this.lineItems.get(index);
    }

    public int getLineItemsLength() {
        return this.lineItems.size();
    }
    
    public ArrayList<LineItem> getLineItems() { return lineItems; }
    
    public LineItem getLastLineItem() {
        return this.lineItems.get(this.lineItems.size() - 1);
    }
    
    public Receipt getReceipt() { return receipt; }

    //returns the current total for the Sale object
    public Money getTotal() {
        return total;
    }
    
    public void setTotal(Money m) { 
        this.total=m;
    }
    
    public boolean isCredit() { return isCredit; }
    
    public String getCCNum() { return ccNum; }
    
    /**
     * Accepts a given Payment
     * Passes itself to Payment's verify()
     * @param p given payment
     * @return whether or not payment went through
     */
    public boolean accept(Payment p) { 
        if(p instanceof CreditPayment) {
            CreditPayment c=(CreditPayment)p;
            ccNum= c.getCardNum();
            isCredit=true;
        }
        return p.verify(this);
    }
    
    /**
     * Accepts a given Reimbursement
     * Passes itself to Reimbursement's verify()
     * @param r given reimbursement
     * @return whether or not reimbursement went through
     */
    public boolean accept(Reimbursement r) { return false; }
    
    public void updateInventory() {}
    
    public GregorianCalendar getReturnDate() { return new GregorianCalendar(); }
}
