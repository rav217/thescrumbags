/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thescrumbags.Classes;

import java.math.BigInteger;

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
        int cardNumInt;
        try
        {
            if (this.cardNum.length() > 0){
                cardNumInt = Integer.parseInt(this.cardNum.substring(0,1));
            }
            else {
                return false;
            }
        }
        catch(NumberFormatException ex)
        {
            return false;
        }
        return cardNum.length() == 16 && cardNumInt >= 0;
    }
    
}
