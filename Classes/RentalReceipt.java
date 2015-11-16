/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thescrumbags.Classes;

/**
 *
 * @author The Scrumbags
 */
public class RentalReceipt extends Receipt {
    public RentalReceipt(String[] recipients, String emailSubject, String body) {
        super(recipients, emailSubject, body);
    }
    
    @Override
    public void makeReceipt(Transaction t) {
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
