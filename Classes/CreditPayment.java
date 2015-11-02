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
public class CreditPayment implements Payment {

    private String cardNum;
    
    public CreditPayment(String cardNum) {
        this.cardNum = cardNum;
    }
    
    @Override
    public boolean verify(Transaction t) {
        if(cardNum.length()==15) return true;
        return false;
    }
    
}
