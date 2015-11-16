/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thescrumbags.Classes;

/**
 *
 * @author benscandell
 */
public class SaleReceipt extends Receipt {
    public SaleReceipt(String[] recipients, String emailSubject, String body) {
        super(recipients, emailSubject, body);
    }
    
    /**
     * Writes the actual receipt body.
     * @param t
     * @throws ClassCastException 
     */
    @Override
    public void makeReceipt(Transaction t) throws ClassCastException {
        if(t instanceof Sale) { 
            Sale s=(Sale)t;
            receiptBody+="SCRUMBAGS POS\t"+date.toString()+"\n\n";
            for (int i=0; i < s.getLineItemsLength(); i++) {
                receiptBody+=s.getLineItem(i).toString()+"\n";
            }
        } else {
            throw new ClassCastException();
        }
    }
}
