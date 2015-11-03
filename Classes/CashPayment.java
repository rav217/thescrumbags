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
public class CashPayment implements Payment {

    private Money cashGiven;
    private Money change;
    
    public CashPayment() {}
    
    public CashPayment(Money cashGiven) {
        
        this.cashGiven=cashGiven;
    }
    
    public CashPayment(double cashGiven) {
        this.cashGiven = new Money(cashGiven);
    }
    
    @Override
    public boolean verify(Transaction t) {
        if(cashGiven.compareTo(t.getTotal()) < 0 ) 
            return false;
        else{
            calculateChange(t.getTotal());
            return true;
        }
    }
    
    public void calculateChange(Money total) { 
        change = cashGiven.subtract(total);
    }
    
    public Money getChange() { return change; }
    
    public Money getCashGiven() { return cashGiven;}
}
