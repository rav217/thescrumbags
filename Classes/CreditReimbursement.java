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
public class CreditReimbursement extends Reimbursement {
    
    private String cardNum;
    
    public CreditReimbursement(String cardNum) { 
        super();      
        this.cardNum=cardNum;
    }
    
    //use SaleReturn with credit authorization service
    @Override
    public boolean verify(Transaction t) {
        if(cardNum.length()==16) return true;
        return false;
    }
}
