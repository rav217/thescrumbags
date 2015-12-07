package thescrumbags.Classes;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.math.BigDecimal;

/**
 * The Transaction class stores a list of items being sold or rented.
 * Stores the current date, the subtotal, and customer payment/reimbursement
 Adds new line items to the transaction, calculates subtotal, and processes payments
 * @author The Scrumbags
 */
public abstract class Transaction {

    protected ArrayList<LineItem> lineItems = new ArrayList<>();
    //date also functions as transaction ID
    protected GregorianCalendar date;
    protected boolean isComplete;
    protected Money subtotal;
    protected Money tax;
    protected Money total;
    
    // this value can be changed to reflect tax values
    protected static double tax_rate = 0.05;
    
    protected Receipt receipt;
    protected boolean isCredit;
    protected String ccNum;
    /**
     * Default constructor.
     * Creates new Calendar for the current date, sets subtotal to 0
     */
    public Transaction() {
        this.date = new GregorianCalendar();
        this.isComplete = false;
        this.subtotal = new Money(new BigDecimal(0));
        this.tax = new Money(new BigDecimal(0));
        this.total = new Money(new BigDecimal(0));
        this.isCredit=false;
        this.ccNum=null;
    }
    

    public abstract Receipt makeNewReceipt();
    
    /**
     * 2-arg constructor for Transaction
 Sets lineItems to li, subtotal to subtotal
     * @param li new line items
     * @param total new subtotal
     */
    public Transaction(ArrayList<LineItem> li, Money total){
        this.lineItems = li;
        this.total = total;
        this.tax = total.multiply(new BigDecimal(tax_rate));
        this.subtotal = total.subtract(tax);
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
    //adds new LineItem to the lineItems ArrayList, subtotal updated with new subtotal
    public LineItem makeLineItem(ProductDescription desc, int qty) {
        LineItem lineItem = new LineItem(desc, qty);
        lineItem = this.makeLineItem(lineItem);
        return lineItem;
    }
    
    public LineItem makeLineItem(LineItem li) {
        LineItem lineItem = new LineItem(li);
        lineItems.add(lineItem);
        
        // add item subtotal to subtotal
        Money tempSub = lineItem.getSubtotal();
        this.subtotal = this.subtotal.add(tempSub);
        
        // add the subtotal * tax_rate to the tax
        Money tempTax = tempSub.multiply(new BigDecimal(tax_rate));
        this.tax = this.tax.add(tempTax);
        
        // add the subtotal and tax to the total
        Money tempTot = tempSub.add(tempTax);
        this.total = this.total.add(tempTot);
        
        return lineItem;
    }

    public void removeLineItem(int index) {
        LineItem lineItem = lineItems.get(index);
        
        // get lineItem subtotal and subtract from transaction subtotal
        Money tempSub = lineItem.getSubtotal();
        this.subtotal = this.subtotal.subtract(tempSub);
        
        // get lineItem tax and subtract from transactions tax total
        Money tempTax = lineItem.getSubtotal().multiply(new BigDecimal(tax_rate));
        this.tax = this.tax.subtract(tempTax);
        
        // get the sum of tempSub and tempTax and subtract from transaction total
        Money tempTot = tempSub.add(tempTax);
        this.total = this.total.subtract(tempTot);
        
        // remove the line item from the lineItems ArrayList
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
    
    public Receipt getReceipt() { return receipt;}
    
    //returns the current subtotal for the Sale object
    public Money getSubtotal() {
        return subtotal;
    }
    
    public void setSubtotal(Money m) { 
        this.subtotal=m;
    }
    
    public Money getTotal() {
        return total;
    }
    
    public void setTotal(Money m) {
        this.total = m;
    }
    
    public Money getTax() {
        return this.tax;
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
