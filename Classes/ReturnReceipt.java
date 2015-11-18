package thescrumbags.Classes;

import java.util.GregorianCalendar;

/**
 * A class to represent a receipt for a sale return.
 * Extends Receipt.
 * @author The Scrumbags
 */
public class ReturnReceipt extends Receipt {

    public ReturnReceipt() { super(); }
    
    /**
     * Writes the actual receipt body.
     * @param t
     */
    @Override
    public void makeReceiptBody(Transaction t) {
        super.makeReceiptBody(t);
        /*
        for(LineItem l: t.getLineItems()) {
            receiptBody+=l.toString()+"\n";
        }
                */
    }
    
}
