package thescrumbags.Classes;

import java.util.*;

/**
 * A class to represent a receipt for a rental.
 * Extends Receipt
 * @author The Scrumbags
 */
public class RentalReceipt extends Receipt {
    public RentalReceipt() { super(); }
    
     /**
     * Writes the actual receipt body.
     * @param t
     * @throws ClassCastException 
     */
    @Override
    public void makeReceiptBody(Transaction t) throws ClassCastException { 
        int month=date.get(GregorianCalendar.MONTH);
        int day=date.get(GregorianCalendar.DAY_OF_MONTH);
        int year=date.get(GregorianCalendar.YEAR);
        int hour=date.get(GregorianCalendar.HOUR);
        int minute=date.get(GregorianCalendar.MINUTE);
        int second=date.get(GregorianCalendar.SECOND);
        if(t instanceof Rental) {
            Rental r=(Rental)t;
            receiptBody+="SCRUMBAGS POS RECEIPT\n"+month+"/"+day+"/"+year+" "+ hour+":"+minute+":"+second+"\n\n";
            for (int i=0; i < r.getLineItemsLength(); i++) {
                receiptBody+=r.getLineItem(i).toString()+"\n";
                receiptBody+="Return date:\t\t" + r.getReturnDate().toString()+"\n\n";
            }           
        } else {
            throw new ClassCastException();
        }
    } 
}
