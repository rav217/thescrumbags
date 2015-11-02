package thescrumbags.Classes;

/**
 * Scrumbags POS CSE 216
 *
 * @author Ben Candell 
 * Money class
 */
import java.math.BigDecimal;
import java.util.*;

public class Money implements Comparable {

    private BigDecimal amount;

    public Money() {
        BigDecimal zero = new BigDecimal(0);
        this.amount = zero;
    }
    public Money(BigDecimal amount) {
        this.amount = amount; 
    }

    public BigDecimal getAmount() { return this.amount; } 
    
    public Money add(Money m) {
        amount=amount.add(m.getAmount());
        return this;
    }

    public Money add(BigDecimal bd) {
        amount=amount.add(bd);
        return this;
    }

    public Money subtract(Money m) {
        amount=amount.subtract(m.getAmount());
        return this;
    }

    public Money subtract(BigDecimal bd) {
        amount=amount.subtract(bd);
        return this;
    }

    public Money multiply(BigDecimal bd) {
        amount=amount.multiply(bd);
        return this;

    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Money) {
            Money money = (Money) o;
            return amount.equals(money.getAmount());
        }
        return true;
    }

    @Override
    public int compareTo(Object o) throws ClassCastException  {
        if (o instanceof Money) {
            Money money = (Money) o;
            return amount.compareTo(money.getAmount());
        }
        else throw new ClassCastException();
    }
}
