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
    
    public Reimbursement() {
        this.amount=amount.multiply(new BigDecimal(-1));
    }
    public Money getAmount() { return amount; } 
    
}
