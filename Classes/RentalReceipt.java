package thescrumbags.Classes;

import java.util.GregorianCalendar;

/**
 *
 * @author The Scrumbags
 */
public class RentalReceipt extends Receipt {
    public RentalReceipt() {}
    
    @Override
    public void makeReceiptBody(Transaction t) {        
        if(t instanceof Rental) {
            Rental r=(Rental)t;
            receiptBody+="SCRUMBAGS POS\t"+date.toString()+"\n\n";
            for (int i=0; i < r.getLineItemsLength(); i++) {
                receiptBody+=r.getLineItem(i).toString()+"\n";
                receiptBody+="Return date:\t\t" + r.getReturnDate().toString()+"\n\n";
            }           
        } else {
            throw new ClassCastException();
        }
    } 
}
