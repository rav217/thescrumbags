package thescrumbags.Classes;

/**
 * Scrumbags POS
 * CSE 216
 * @author Ben Candell
 * Money class
 */

import java.math.*;

public class Money {
    private BigDecimal amount;
    
    public Money() {}
    public Money(Money m) {
        copy(m); 
    }
    public Money(BigDecimal amount) { this.amount=amount; }
    
    public void copy(Money m) {
        this.amount=m.getAmount();
    }
    
    public BigDecimal getAmount() { return amount; } 
    
    public Money add(Money m) {
        this.amount.add(m.getAmount());
        return this;
    }
    
    public Money add(BigDecimal n)
    {
      this.amount.add(n);
      return this;
    }
    
    public Money subtract(Money m) {
        this.amount.subtract(m.getAmount());
        return this;
    }
    
    public Money subtract(BigDecimal n)
    {
      this.amount.subtract(n);
      return this;
    }
    
    public Money times(Money m)
    {
      this.amount.multiply(m.getAmount());
      return this;
    }
    
    public Money times(BigDecimal n) {
        this.amount.multiply(n);
        return this;
    }
    
    public boolean equals(Money m) {
        if (this.amount==m.getAmount()) return true;
        return false;
    }
}
