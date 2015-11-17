package thescrumbags.Classes;

import java.util.*;

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
    public void makeReceiptBody(Transaction t) {
        int month=date.get(GregorianCalendar.MONTH);
        int day=date.get(GregorianCalendar.DAY_OF_MONTH);
        int year=date.get(GregorianCalendar.YEAR);
        receiptBody+="SCRUMBAGS POS RECEIPT\t"+month+"/"+day+"/"+year+"\n\n";
        for(LineItem l: t.getLineItems()) {
            receiptBody+=l.toString()+"\n";
        }
    }
}
