package thescrumbags.Classes;

import java.util.*;

/**
 *
 * @author The Scrumbags
 */
public class SaleReceipt extends Receipt {
    public SaleReceipt() { super(); }
    
    /**
     * Writes the actual receipt body.
     * @param t 
     */
    @Override
    public void makeReceiptBody(Transaction t) {
        int month=date.get(GregorianCalendar.MONTH);
        int day=date.get(GregorianCalendar.DAY_OF_MONTH);
        int year=date.get(GregorianCalendar.YEAR);
        int hour=date.get(GregorianCalendar.HOUR);
        int minute=date.get(GregorianCalendar.MINUTE);
        int second=date.get(GregorianCalendar.SECOND);
        receiptBody+="SCRUMBAGS POS RECEIPT\n"+month+"/"+day+"/"+year+" "+ hour+":"+minute+":"+second+"\n\n";
        for(LineItem l: t.getLineItems()) {
            receiptBody+=l.toString()+"\n";
        }
    }
}
