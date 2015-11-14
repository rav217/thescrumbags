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
    public SaleReceipt() {}
    
    @Override
    public void makeReceipt(Transaction t) throws ClassCastException {
        if(t instanceof Sale) { 
            Sale s=(Sale)t;
            
        } else {
            throw new ClassCastException();
        }
    }
    
    @Override
    public void printReceipt() {
        super.printReceipt();
    }
}
