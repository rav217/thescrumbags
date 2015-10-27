/**
 * Scrumbags POS
 * CSE 216
 * @author Ben Candell
 * Money class
 */

package thescrumbags.Classes;

import java.util.*;

public class Money {
    private double amount;
    
    public Money() {}
    public Money(Money m) {
        copy(m); 
    }
    public Money(double amount) { this.amount=amount; }
    
    public void copy(Money m) {
        this.amount=m.getAmount();
    }
    
    public double getAmount() { return amount; } 
    
    public Money add(Money m) {
        this.amount+=m.getAmount();
        return this;
    }
    
    public Money add(double n)
    {
      this.amount += n;
      return this;
    }
    
    public Money subtract(Money m) {
        this.amount-=m.getAmount();
        return this;
    }
    
    public Money subtract(double n)
    {
      this.amount -= n;
      return this;
    }
    
    public Money times(Money m)
    {
      this.amount *= m.getAmount();
      return this;
    }
    
    public Money times(double n) {
        this.amount*=n;
        return this;
    }
    
    public boolean equals(Money m) {
        if (this.amount==m.getAmount()) return true;
        return false;
    }
}
