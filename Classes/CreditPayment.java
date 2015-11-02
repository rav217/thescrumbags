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
    
    //use Transaction with credit authorization service
    @Override
    public boolean verify(Transaction t) {
        if(cardNum.length()==16) return true;
        return false;
    }
    
}
