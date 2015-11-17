package thescrumbags.Classes;

import java.util.*;

/**
 *
 * @author The Scrumbags
 */
public class RentalReceipt extends Receipt {
    public RentalReceipt() {}
    
    @Override
    public void makeReceiptBody(Transaction t) throws ClassCastException { 
        int month=date.get(GregorianCalendar.MONTH);
        int day=date.get(GregorianCalendar.DAY_OF_MONTH);
        int year=date.get(GregorianCalendar.YEAR);
        if(t instanceof Rental) {
            Rental r=(Rental)t;
            receiptBody+="SCRUMBAGS POS RECEIPT\t"+month+"/"+day+"/"+year+"\n\n";
            for (int i=0; i < r.getLineItemsLength(); i++) {
                receiptBody+=r.getLineItem(i).toString()+"\n";
                receiptBody+="Return date:\t\t" + r.getReturnDate().toString()+"\n\n";
            }           
        } else {
            throw new ClassCastException();
        }
    } 
}
