/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thescrumbags.Classes;

import java.math.BigDecimal;

/**
 * To be used for all cash reimbursements
 * CreditReiumbursement will extend Reimbursement
 * @author benscandell
 */
public class Reimbursement {
    
    private Money amount;
    
    public Reimbursement() {}
    
    public Money getAmount() { return amount; } 
    
    public boolean verify(SaleReturn s) {
        amount=s.negateTotal();
        System.out.println("Give the customer $"+amount);
        return true; //TODO
    }
}
