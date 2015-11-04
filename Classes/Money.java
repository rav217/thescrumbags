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

    public Money (double amount) {
        this.amount = new BigDecimal(amount);
    }
    
    public java.math.BigDecimal getAmount() {
        return amount;
    }

    public Money add(Money m) {
        BigDecimal temp = amount;
        temp = temp.add(m.getAmount());
        return new Money(temp);
    }

    public Money add(BigDecimal bd) {
        amount = amount.add(bd);
        return this;
    }

    public Money subtract(Money m) {
        // this is much safer
        BigDecimal temp = amount;
        temp = temp.subtract(m.getAmount());
        return new Money(temp);
    }

    public Money subtract(BigDecimal bd) {
        amount = amount.subtract(bd);
        return this;
    }

    public Money multiply(BigDecimal bd) {
        amount = amount.multiply(bd);
        return this;

    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Money) {
            Money money = (Money) o;
            return amount.equals(money.getAmount());
        }
        else{
            return false;
        }
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.amount);
        return hash;
    }

    @Override
    public int compareTo(Object o) throws ClassCastException  {
        if (o instanceof Money) {
            Money money = (Money) o;
            if (this.amount.doubleValue() < money.getAmount().doubleValue()) {
                return -1;
            }
            else if (this.amount.doubleValue() == money.getAmount().doubleValue()) {
                return 0;
            }
            else {
                return 1;
            }
        }
        else throw new ClassCastException();
    }
}
