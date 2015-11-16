package thescrumbags.Classes;

import java.util.GregorianCalendar;

/**
 *
 * @author The Scrumbags
 */
public class SaleReceipt extends Receipt {
    public SaleReceipt() {}
    
    /**
     * Writes the actual receipt body.
     * @param t
     * @throws ClassCastException 
     */
    @Override
    public void makeReceiptBody(Transaction t) throws ClassCastException {
        int date=GregorianCalendar.DATE;
        receiptBody+="SCRUMBAGS POS\t"+date+"\n\n";
        for(LineItem l: t.getLineItems()) {
            receiptBody+=l.toString()+"\n";
        }
    }
}
