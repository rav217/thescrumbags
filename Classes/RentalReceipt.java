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
public class RentalReceipt extends Receipt {
    public RentalReceipt() {}
    
    @Override
    public void makeReceipt(Transaction t) {
        if(t instanceof Rental) {
            Rental r=(Rental)t;
            System.out.println("SCRUMBAGS POS -- SAMPLE");
            
        } else {
            throw new ClassCastException();
        }
        
    }
}
